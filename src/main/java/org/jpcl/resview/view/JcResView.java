package org.jpcl.resview.view;

import org.jpcl.resview.view.model.ResObject;
import org.jpcl.resview.view.model.Status;
import org.jpcl.resview.view.resolver.JcViewResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Administrator
 */
public class JcResView extends ModelAndView {

    private ResObject resObject;

    public JcResView() {
        resObject = new ResObject();
        super.setView(new JcViewResolver(resObject));
    }

    public JcResView(String data) {
        this();
        this.setData(data);
    }

    public JcResView(Status status) {
        this();
        this.setResObject(status);
    }

    public ResObject setStatus(int code) {
        return this.resObject.setStatus(code);
    }

    public ResObject setData(String data) {
        return this.resObject.setData(data);
    }

    public ResObject setMsg(String msg) {
        return this.resObject.setMsg(msg);
    }

    public void setResObject(Status status) {
        this.resObject.setStatus(status.getCode());
        this.resObject.setMsg(status.getMsg());
    }
}
