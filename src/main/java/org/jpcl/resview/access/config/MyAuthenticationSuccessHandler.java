package org.jpcl.resview.access.config;

import com.alibaba.fastjson.JSON;
import org.jcl.life.result.RetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private Logger logger =
            LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    /**
     * 认证成功后的处理
     *
     * @param request
     * @param request
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("认证成功, {}", authentication);
        // 反馈登录成功的信息
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RetResult ret = new RetResult<String, String>();
        ret.setData("登录成功");
        ret.setMsg("");
        out.write(JSON.toJSONString(ret));
    }
}
