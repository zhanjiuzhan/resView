package org.jpcl.resview.access;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 登陆的痕迹信息记载
 * @author Administrator
 */
public interface LoginTrace {

    /**
     * 用户登陆的信息
     * @param username
     * @param msg
     */
    void loginTrace(String username, String msg);

    /**
     * 用户登出的信息
     * @param username
     */
    void logoutTrace(String username);

    /**
     * 取得当前的时间
     * @return
     */
    default String getNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
