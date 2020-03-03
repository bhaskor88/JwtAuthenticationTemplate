package com.bohniman.cid.appapi.exception;

/**
 * CommonException
 */
public class CommonException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }
}