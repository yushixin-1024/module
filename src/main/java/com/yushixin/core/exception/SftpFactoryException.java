package com.yushixin.core.exception;

/**
 * Sftp异常
 */
public class SftpFactoryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SftpFactoryException() {
        super("Sftp异常");
    }

    public SftpFactoryException(String message) {
        super(message);
    }

    public SftpFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
