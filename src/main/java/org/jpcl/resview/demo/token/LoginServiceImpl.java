package org.jpcl.resview.demo.token;

import org.jpcl.resview.access.LoginService;
import org.jpcl.resview.access.security.JcUserDetails;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class LoginServiceImpl implements LoginService {
    @Override
    public JcUserDetails getUser(String userName) {
        JcUserDetails user = new JcUserDetails();
        user.setUsername("dw_chenglei");
        return user;
    }
}
