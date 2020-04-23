package org.jpcl.resview.view.model;

/**
 * @author Administrator
 */
public class ResObject {
    private int status;
    private String data;
    private String msg;

    {
        status = Status.R200.getCode();
        data = "";
        msg = "";
    }

    public ResObject() {
        this.status = Status.R200.getCode();
    }

    public ResObject(Status status) {
        this.status = status.getCode();
    }

    public int getStatus() {
        return status;
    }

    public ResObject setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getData() {
        return data;
    }

    public ResObject setData(String data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResObject setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "{status:" + status + ", data:\"" + data + "\", msg:\"" + msg + "\"}";
    }
}
