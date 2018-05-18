package ru.kpfu.itis.exceptions.shared;

import org.springframework.security.core.AuthenticationException;

public class TokenCorruptedException extends AuthenticationException {

    public TokenCorruptedException(String msg, Throwable t) {
        super(msg, t);
    }

    public TokenCorruptedException(String msg) {
        super(msg);
    }
}
