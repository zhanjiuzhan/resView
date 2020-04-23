package org.jpcl.resview.demo.controller;

import io.jsonwebtoken.Claims;
import org.jpcl.resview.access.token.JwtToken;
import org.jpcl.resview.view.JcResView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.AuthenticationException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/token")
public class TokenController {

    @RequestMapping("/login")
    public JcResView login() {
        String token = JwtToken.generateToken("dw_chenglei");
        return new JcResView(token);
    }

    @RequestMapping("/getUserInfo")
    public JcResView getUserInfo(@RequestHeader("Authorization") String authHeader) throws AuthenticationException {
        // 黑名单token
        List<String> blacklistToken = Arrays.asList("禁止访问的token");
        Claims claims = JwtToken.getClaimByToken(authHeader);
        if (claims == null || JwtToken.isTokenExpired(claims.getExpiration()) || blacklistToken.contains(authHeader)) {
            throw new AuthenticationException("token 不可用");
        }

        String userId = claims.getSubject();
        // 根据用户id获取接口数据返回接口
        return new JcResView(userId);
    }


}
