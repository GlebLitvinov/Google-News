package com.home.textutils.utils;

public class TextUtilsException extends Exception {
    public TextUtilsException() {
    }

    public TextUtilsException(String message) {
        super(message);
    }

    public TextUtilsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextUtilsException(Throwable cause) {
        super(cause);
    }

    public TextUtilsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
