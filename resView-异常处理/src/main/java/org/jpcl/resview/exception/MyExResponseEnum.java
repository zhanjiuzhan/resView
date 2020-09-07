package org.jpcl.resview.exception;

import org.jpcl.resview.exception.exception.MySelfExceptionAssert;

/**
 * @author Administrator
 */
public enum MyExResponseEnum implements MySelfExceptionAssert {

    BAD_LICENCE_TYPE(7001, "Bad licence type."),

    LICENCE_NOT_FOUND(7002, "Licence not found.")
    ;

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;

    MyExResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
