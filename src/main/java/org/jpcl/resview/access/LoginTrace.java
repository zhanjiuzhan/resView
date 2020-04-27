package org.jpcl.resview.access;

/**
 * 登陆的痕迹信息记载
 * @author Administrator
 */
public interface LoginTrace {

    /**
     * 用户登陆的信息
     */
    void loginTrace();

    /**
     * 用户登出的信息
     */
    void logoutTrace();
}
