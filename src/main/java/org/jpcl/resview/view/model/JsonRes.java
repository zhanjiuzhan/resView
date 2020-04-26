package org.jpcl.resview.view.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class JsonRes {
    private int status;
    private Object data;
    private String msg;

    /**
     * 返回的code
     */
    private static Map<Integer, String> code = new HashMap() {
        {
            put(500, "服务器忙， 请稍后再试。");
        }
    };

    /**
     * 默认是200
     */
    public JsonRes() {
        this.status = 200;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
