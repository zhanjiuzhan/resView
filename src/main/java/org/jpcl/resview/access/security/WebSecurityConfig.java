package org.jpcl.resview.access.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用来配置security的权限认证
 * @author chenglei
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Resource
    private UserDetailsService myUserDetailsManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 进行权限配置
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/app/**").permitAll();

        // 开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面
        http.formLogin().usernameParameter("userName").passwordParameter("password")
                .loginProcessingUrl("/login")
                .successHandler(getSuccessHandler()).failureHandler(getFailureHandler());

        // 关闭CSRF跨域
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
       return NoOpPasswordEncoder.getInstance();
   }

    private AuthenticationSuccessHandler getSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            /**
             * 登录成功的处理
             * @param request
             * @param response
             * @param authentication 认证后用户的信息
             * @throws IOException
             * @throws ServletException
             */
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                 HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                logger.info("登陆成功了");
            }
        };
    }

    private AuthenticationFailureHandler getFailureHandler() {
        return new AuthenticationFailureHandler(){
            /**
             * 登录失败的处理
              * @param request
             * @param response
             * @param e
             * @throws IOException
             * @throws ServletException
             */
            @Override
             public void onAuthenticationFailure(HttpServletRequest request,
                  HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                logger.info("登陆失败了");
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(e.getMessage());
                e.printStackTrace();
             }
        };
    }
}
