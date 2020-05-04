package org.jpcl.resview.access.impl;

import org.jpcl.resview.access.LoginTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 */
public abstract class AbstractLoginTrace implements LoginTrace {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String LOGIN_SUCCESS = "登陆成功";
    public static final String LOGIN_FAIL = "登陆失败";

    @Override
    public void loginTrace(String username, String msg) {
        logger.info("用户 [{}] 进行登陆, {}", username, msg);
        StringBuffer sbf = new StringBuffer();
        sbf.append("用户: " + username);
        sbf.append(", 时间: " + getNowDate());
        sbf.append(", 信息: " + msg);
        login(sbf.toString());
    }

    @Override
    public void logoutTrace(String username) {
        logger.info("用户注销");
        StringBuffer sbf = new StringBuffer();
        sbf.append("用户: " + username);
        sbf.append(", 于: " + getNowDate());
        sbf.append("退出系统");
        logout(sbf.toString());
    }

    /**
     * 登陆消息
     * @param msg
     */
    public abstract void login(String msg);

    /**
     * 登出消息
     * @param msg
     */
    public abstract void logout(String msg);


}
