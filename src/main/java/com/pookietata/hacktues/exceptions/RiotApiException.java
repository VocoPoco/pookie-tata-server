package com.pookietata.hacktues.exceptions;

public class RiotApiException extends RuntimeException {
    public RiotApiException(String message) {
        super(message);
    }

    public RiotApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
