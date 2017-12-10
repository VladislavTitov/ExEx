package ru.kpfu.itis.exceptions.shared;

public class NoSuchIdException extends RuntimeException{
    public NoSuchIdException() {
        super();
    }

    public NoSuchIdException(String message) {
        super(message);
    }

    public NoSuchIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchIdException(Throwable cause) {
        super(cause);
    }

    protected NoSuchIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
