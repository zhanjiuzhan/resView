package org.jpcl.resview.view.resolver.impl;

import com.alibaba.fastjson.JSON;
import org.jpcl.resview.view.model.JsonRes;
import org.jpcl.resview.view.resolver.JcAbstractViewResolver;

import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

/**
 * @author Administrator
 */
public class JcJsonViewResolver extends JcAbstractViewResolver {

    private JsonRes jsonObject;

    public JcJsonViewResolver(JsonRes obj) {
        this.jsonObject = obj;
    }

    @Override
    protected void makeResponse(HttpServletResponse response) throws Exception {
        Object dataObj = this.jsonObject.getData();
        String res = "{status:" + this.jsonObject.getStatus() + ", data:" + JSON.toJSONString(dataObj) + ", msg:\"" + this.jsonObject.getMsg() + "\"}";
        response.setContentType("text/html; charset=utf-8");
        response.setContentLength(res.getBytes("UTF-8").length);
        // TODO xss攻击过滤
        Writer out = response.getWriter();
        out.write(res);
        out.flush();
    }
}
