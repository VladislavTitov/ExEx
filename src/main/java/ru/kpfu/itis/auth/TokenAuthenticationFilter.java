package ru.kpfu.itis.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.kpfu.itis.exceptions.shared.TokenNotFoundException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends GenericFilterBean {

    private static final String AUTH_HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private RestAuthEntryPoint authEntryPoint;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader(AUTH_HEADER);
        if (isNotRequiringProtection(request)) {
            filterChain.doFilter(request, servletResponse);
        } else if (!isTokenValid(token)) {
            authEntryPoint.commence(request, (HttpServletResponse) servletResponse, new TokenNotFoundException("Token must be specified in this request!"));
        } else {
            try {
                token = token.replace(PREFIX, "");
                authManager.authenticate(new TokenAuthentication(token));
                filterChain.doFilter(request, servletResponse);
            } catch (AuthenticationException ex) {
                authEntryPoint.commence(request, (HttpServletResponse) servletResponse, ex);
            }
        }
    }

    private boolean isNotRequiringProtection(HttpServletRequest request) {
        return request.getRequestURI().equals("/exex/api/v1/users") && request.getMethod().equals("POST")
                || request.getRequestURI().endsWith("favicon.ico")
                || request.getRequestURI().startsWith("/exex/api/v1/login") && request.getMethod().equals("POST");
    }

    private boolean isTokenValid(String token) {
        return token != null && !token.equals("");
    }
}
