package org.jpcl.resview.access.config;

import com.alibaba.fastjson.JSON;
import org.jcl.life.result.RetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author chenglei
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private Logger logger =
            LoggerFactory.getLogger(MyAuthenticationFailureHandler.class);

    /**
     * 认证失败的处理
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        logger.info("登录失败, {}");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RetResult ret = new RetResult<String, String>();
        ret.setData("登录失败");
        ret.setMsg(e.toString());
        out.write(JSON.toJSONString(ret));
    }
}
