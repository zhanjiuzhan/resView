package org.jpcl.resview.access.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户拦截
 * @author Administrator
 */
@Controller
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private AbstractTokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        String authHeader = request.getHeader("Authorization");
        if (url.contains("/login")) {
            filterChain.doFilter(request, response);
        } else {
            if (tokenService.isAuthentication(url, authHeader)) {
                // 认证通过
                filterChain.doFilter(request, response);
            } else {
                // 认证不通过
                tokenService.accessFailue(response);
            }
        }
    }
}
