package org.jpcl.resview.exception.exception;

import org.jpcl.resview.view.JcJsonView;
import org.jpcl.resview.view.model.JsonRes;

/**
 * 自定义异常的基类
 * @author Administrator
 */
public class MySelfException extends RuntimeException {

    private JsonRes jsonRes;

    public MySelfException(MyExResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum.getMessage());
        jsonRes = new JsonRes();
        jsonRes.setStatus(responseEnum.getCode());
        jsonRes.setMsg(responseEnum.getMessage());
    }

    public MySelfException(MyExResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
        this(responseEnum, args, message);
    }

    public JcJsonView getExMessage() {
        return new JcJsonView(this.jsonRes);
    }


}
