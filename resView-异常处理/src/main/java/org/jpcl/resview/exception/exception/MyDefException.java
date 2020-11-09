package org.jpcl.resview.exception.exception;


import org.jpcl.resview.exception.ExceptionEnum;

/**
 * @author Administrator
 */
public class MyDefException extends RuntimeException implements ExResponse {

    private int code;
    private String message;
    private ExceptionEnum ex;

    public MyDefException(final ExceptionEnum ex, final String message) {
        super(message);
        init(ex, message);
    }

    public MyDefException(final ExceptionEnum ex, final String message, final Throwable cause) {
        super(message, cause);
        init(ex, message);
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ExceptionEnum getExceptionEnum() {
        return this.ex;
    }

    private void init(ExceptionEnum ex, String message) {
        this.code = ex.getCode();
        this.message = message;
        this.ex = ex;
    }
}
