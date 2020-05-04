package org.jpcl.resview.demo.token;

import org.jpcl.resview.access.token.AbstractTokenService;
import org.springframework.stereotype.Component;

/**
 * 没有权限校验
 * @author Administrator
 */
@Component
public class TokenServiceImpl extends AbstractTokenService {
    @Override
    protected boolean isAuthenticationUrl(String url, String username) {
        return true;
    }
}
