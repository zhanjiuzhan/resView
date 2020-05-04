package org.jpcl.resview.access;

/**
 * @author Administrator
 */
public interface LoginService<T extends UserModel> {

    /**
     * 返回对应用户名的用户信息
     * @param userName
     * @return
     */
    T getUser(String userName);
}
