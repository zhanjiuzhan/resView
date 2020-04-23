package org.jpcl.resview.access.config;

import org.jcl.life.string.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 权限 自己的配置信息
 */
@Component
public final class PermissionConfig implements InitializingBean {

    @Autowired
    private  Environment env;

    public String loginPageUrl;
    public String loginPage;
    public String loginUserName;
    public String loginPassword;

    /*
    @Value("${test.msg}")
    public String test;
    */

    /**
     * 对配置的信息进行初始化
     */
    private void init() {
        String tmp = env.getProperty("note.login.page.url");
        loginPageUrl = StringUtils.isEmpty(tmp) ? "/login" : tmp;
        tmp = env.getProperty("note.login.page");
        loginPage = StringUtils.isEmpty(tmp) ? "/login.html" : tmp;
        tmp = env.getProperty("note.login.user.name");
        loginUserName = StringUtils.isEmpty(tmp) ? "userName" : tmp;
        tmp = env.getProperty("note.login.user.password");
        loginPassword = StringUtils.isEmpty(tmp) ? "password" : tmp;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
