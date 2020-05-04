package org.jpcl.resview.demo.token;

import org.jpcl.resview.access.impl.AbstractLoginTrace;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class LoginTraceImpl extends AbstractLoginTrace {
    @Override
    public void login(String msg) {
        System.out.println(msg);
    }

    @Override
    public void logout(String msg) {
        System.out.println(msg);
    }
}
