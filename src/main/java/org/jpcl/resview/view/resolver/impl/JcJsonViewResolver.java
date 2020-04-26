package org.jpcl.resview.view.resolver.impl;

import com.alibaba.fastjson.JSON;
import org.jpcl.resview.view.model.JsonRes;
import org.jpcl.resview.view.resolver.JcViewResolver;

import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

/**
 * @author Administrator
 */
public class JcJsonViewResolver extends JcViewResolver {

    private JsonRes jsonObject;

    public JcJsonViewResolver(JsonRes obj) {
        this.jsonObject = obj;
    }

    @Override
    protected void makeResponse(HttpServletResponse response) throws Exception {
        String res = JSON.toJSONString(this.jsonObject);
        response.setContentType("text/html; charset=utf-8");
        response.setContentLength(res.getBytes("UTF-8").length);
        // TODO xss攻击过滤
        Writer out = response.getWriter();
        out.write(res);
        out.flush();
    }

    public JsonRes getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonRes jsonObject) {
        this.jsonObject = jsonObject;
    }
}