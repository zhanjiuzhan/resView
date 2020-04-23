package org.jpcl.resview.view.resolver;

import org.jpcl.resview.view.model.ResObject;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.Map;

/**
 * @author Administrator
 */
public class JcViewResolver extends AbstractUrlBasedView {
    private ResObject body;
    public JcViewResolver(ResObject content) {
        this.body = content;
    }
    @Override
    protected void renderMergedOutputModel(Map<String, Object> map,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO xss攻击过滤
        response.setContentType("text/html; charset=utf-8");
        response.setContentLength(body.toString().getBytes("UTF-8").length);
        Writer out = response.getWriter();
        out.write(body.toString());
        out.flush();
    }
}
