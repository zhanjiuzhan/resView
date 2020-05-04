package org.jpcl.resview.access;

/**
 * @author Administrator
 */
public interface LoginService {

    /**
     * 返回对应用户名的用户信息
     * @param userName
     * @return
     */
    UserModel getUser(String userName);
}
