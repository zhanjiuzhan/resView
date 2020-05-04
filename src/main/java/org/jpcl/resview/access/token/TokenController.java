package org.jpcl.resview.access.token;

import org.jpcl.resview.access.LoginService;
import org.jpcl.resview.access.UserModel;
import org.jpcl.resview.access.exception.LoginException;
import org.jpcl.resview.access.impl.AbstractLoginTrace;
import org.jpcl.resview.view.JcJsonView;
import org.jpcl.resview.view.model.JsonRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 使用token进行登陆的接口
 * @author Administrator
 */
@Controller
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private AbstractTokenService tokenService;

    @Autowired(required = false)
    private AbstractLoginTrace loginTrace;

    @ExceptionHandler(value = {LoginException.class})
    public JcJsonView loginExceptionHandle(Model model, LoginException e) {
        if (loginTrace != null) {
            loginTrace.loginTrace(e.getUsername(), e.toString());
        }
        JsonRes jsonRes = new JsonRes();
        jsonRes.setStatus(400);
        jsonRes.setMsg(e.toString());
        return new JcJsonView(jsonRes);
    }

    /**
     * 登陆的url处理 一般是第一次登陆
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public JcJsonView login(String userName, String password) {
        validParameter(userName, password);
        // 从数据源或其它检索用户是否存在
        UserModel userModel = loginService.getUser(userName);
        if (userModel == null) {
            throw new LoginException(userName, "用户不存在。");
        }

        // 用户信息正确 生成一个token返回 每当用户登陆的时候都会生成一个token
        String token = tokenService.generateToken(userModel.getUsername());
        return new JcJsonView(token);
    }

    private void validParameter(String userName, String password) {
        if (userName == null || userName.trim().length() == 0) {
            throw new LoginException(userName, "用户名不合法。");
        }
        if (password == null || password.trim().length() == 0) {
            throw new LoginException(userName, "密码不合法。");
        }
    }
}
