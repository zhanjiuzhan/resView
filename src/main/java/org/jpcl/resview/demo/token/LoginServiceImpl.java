package org.jpcl.resview.demo.token;

import org.jpcl.resview.access.LoginService;
import org.jpcl.resview.access.UserModel;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class LoginServiceImpl implements LoginService {
    @Override
    public UserModel getUser(String userName) {
        UserModel user = new UserModel() {};
        user.setUsername("dw_chenglei");
        return user;
    }
}
