package ru.kpfu.itis.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.exceptions.shared.TokenCorruptedException;
import ru.kpfu.itis.exceptions.shared.TokenExpiredException;

import java.util.Date;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    public static final String SIGN_KEY = "ejgb:erne1321-1rint";

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication auth = (TokenAuthentication) authentication;
        String token = auth.getToken();
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(SIGN_KEY).parse(token).getBody();
        } catch (Exception ex) {
            throw new TokenCorruptedException("Token is invalid or corrupted!", ex);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.get("username", String.class));

        Date exp = claims.get("expiration", Date.class);

        if (exp == null) {
            throw new TokenCorruptedException("Token is invalid or corrupted!");
        }

        if (!exp.after(new Date())) {
            throw new TokenExpiredException("Token is expired!");
        }

        TokenAuthentication fullAuth = new TokenAuthentication(auth.getToken(), userDetails.getAuthorities(), true, userDetails);
        SecurityContextHolder.getContext().setAuthentication(fullAuth);
        return fullAuth;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(TokenAuthentication.class);
    }
}
