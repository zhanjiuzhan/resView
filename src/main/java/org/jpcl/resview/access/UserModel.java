package org.jpcl.resview.access;

/**
 * 用户信息的规范
 * @author Administrator
 */
public abstract class UserModel {

    /**
     * 用户的唯一标志 用户名
     */
    private String username;

    /**
     * 用户的鉴别 密码
     */
    private String password;

    /**
     * 用户的创建日期
     */
    private String createDate;

    /**
     * 用户的修改日期
     */
    private String updateDate;

    /**
     * 用户是否被禁用
     */
    private boolean enabled = true;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
