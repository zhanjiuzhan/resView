package org.jpcl.resview.access.token;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.jpcl.resview.view.model.JsonRes;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.UUID;

/**
 * 关于token的一些操作
 * @author Administrator
 */
public abstract class AbstractTokenService {

    /**
     * 取得一个UUID当作 加密的key 没用
     * @return
     */
    private String generateTokenKey() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    /**
     * 生成一个token
     * @param username
     * @return
     */
    public String generateToken(String username) {
        String token = JwtToken.generateToken(username);
        return token;
    }

    /**
     * 根据header的信息取得token 再取得用户名 token失效返回null
     * @param authHeader
     * @return
     * @throws AuthenticationException
     */
    private String getUsernameByHeader(String authHeader) {
        if (authHeader == null || authHeader.trim().length() == 0) {
            return null;
        }

        // 这个token是否正确 并且是否过期
        Claims claims = JwtToken.getClaimByToken(authHeader);
        if (claims == null || JwtToken.isTokenExpired(claims.getExpiration())) {
            return null;
        }
        return claims.getSubject();
    }

    /**
     * 是否权限校验能够通过
     * @param authHeader
     * @return
     */
    public boolean isAuthentication(String url, String authHeader) {
        boolean isAuthentication = false;
        String username = getUsernameByHeader(authHeader);
        if (username != null) {
            // 用户信息或者角色信息是否能匹配上
            if (isAuthenticationUrl(url, username)) {
                isAuthentication = true;
            }
        }
        return isAuthentication;
    }

    /**
     * 主要是角色 鉴权
     * @param url
     * @param username
     * @return
     */
    protected abstract boolean isAuthenticationUrl(String url, String username);


    /**
     * 认证失败了
     * @param response
     */
    public void accessFailue(HttpServletResponse response) throws IOException {
        JsonRes jsonRes = new JsonRes();
        jsonRes.setStatus(401);
        jsonRes.setMsg("没有权限");
        String res = JSON.toJSONString(jsonRes);
        response.setContentType("application/json; charset=utf-8");
        response.setContentLength(res.getBytes("UTF-8").length);
        Writer out = response.getWriter();
        out.write(res);
        out.flush();
    }
}
