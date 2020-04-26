package org.jpcl.resview.view.resolver;

import org.springframework.web.servlet.view.AbstractUrlBasedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Administrator
 */
public abstract class JcViewResolver extends AbstractUrlBasedView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        makeResponse(response);
    }

    /**
     * 做成response回复的内容
     * @param response
     * @throws Exception
     */
    protected abstract void makeResponse(HttpServletResponse response) throws Exception;
}
