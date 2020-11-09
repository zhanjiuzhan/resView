package org.jpcl.resview.exception;



import org.jpcl.resview.exception.exception.ExAssert;
import org.jpcl.resview.exception.exception.ExResponse;
import org.jpcl.resview.exception.exception.MyDefException;

import java.text.MessageFormat;

/**
 * @author Administrator
 */
public enum ExceptionEnum implements ExResponse, ExAssert {
    /**
     * 用户身份认证失败异常
     */
    AUTHORIZATION_EXCEPTION(401, "用户[{0}]身份认证失败!"),

    /**
     * 内部方法调用参数不合法
     */
    INTERNAL_INVALID_PARAMETER_EXCEPTION(500, "内部方法参数传递错误.  类名.方法名: {0}, 参数名: {1}, 传递值: {2}"),

    /**
     * 主要针对于对外接口调用参数异常
     */
    INVALID_PARAMETER_EXCEPTION(400, "参数[{1}]信息错误: {0}, {1}: {2}"),

    /**
     * 操作 出错 可以向外示警
     */
    OPERATION_EXCEPTION(500, "操作出错: {0}")
    ;

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;

    ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public MyDefException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new MyDefException(this, msg);
    }

    @Override
    public MyDefException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new MyDefException(this, msg, t);
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
