package org.jpcl.resview.view.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public enum Status {
    R200(200, Msg.MSG_SUCCESS),
    R500(500, Msg.MSG_INTERNAL_ERROR),
    ;

    private int code;
    private String msg;
    private static Map<Integer, String> map;

    Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    static {
        map = new HashMap<>(Status.values().length);
        for (Status status : Status.values()) {
            map.put(status.getCode(), status.getMsg());
        }
    }

    public String getMsg(int code) {
        return map.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
