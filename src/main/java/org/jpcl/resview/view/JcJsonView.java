package org.jpcl.resview.view;

import org.jpcl.resview.view.model.JsonRes;
import org.jpcl.resview.view.resolver.impl.JcJsonViewResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Administrator
 */
public class JcJsonView extends ModelAndView {

    private JsonRes jsonRes;

    public JcJsonView() {
        jsonRes = new JsonRes();
        super.setView(new JcJsonViewResolver(jsonRes));
    }

    public JcJsonView(String data) {
        this();
        this.setData(data);
    }

    public JcJsonView(Status status) {
        this();
        this.setJsonRes(status);
    }

    public JsonRes setStatus(int code) {
        return this.jsonRes.setStatus(code);
    }

    public JsonRes setData(String data) {
        return this.jsonRes.setData(data);
    }

    public JsonRes setMsg(String msg) {
        return this.jsonRes.setMsg(msg);
    }

    public void setJsonRes(Status status) {
        this.jsonRes.setStatus(status.getCode());
        this.jsonRes.setMsg(status.getMsg());
    }
}
