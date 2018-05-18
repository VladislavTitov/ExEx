package ru.kpfu.itis.exceptions.shared;

import org.springframework.security.core.AuthenticationException;

public class TokenExpiredException extends AuthenticationException {

    public TokenExpiredException(String msg, Throwable t) {
        super(msg, t);
    }

    public TokenExpiredException(String msg) {
        super(msg);
    }
}
