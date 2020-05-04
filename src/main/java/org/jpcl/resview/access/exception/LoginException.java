package org.jpcl.resview.access.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 */
public class LoginException extends RuntimeException {

    private Logger logger = LoggerFactory.getLogger(LoginException.class);

    private String msg;
    private String username;

    public LoginException(String username, String msg) {
        super(msg);
        this.msg = msg;
        this.username = username;
    }

    @Override
    public String toString() {
        logger.error(super.toString());
        return this.msg;
    }

    public String getUsername() {
        return username;
    }
}
