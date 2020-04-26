package org.jpcl.resview.access.config;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.io.PrintWriter;

/**
 * 用来配置security的权限认证
 * @author chenglei
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    private UserDetailsService myUserDetailsManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 进行权限配置
        /*http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/app/**").permitAll();*/
        http.authorizeRequests().anyRequest().permitAll();



        //http.authorizeRequests().anyRequest().hasRole("USER")
                //.and().sessionManagement().invalidSessionUrl("/login.html");

        // 开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面
        /*http.formLogin().usernameParameter("userName").passwordParameter("password")
                .loginPage("/login.html").permitAll()
                .loginProcessingUrl("/login")
                .successHandler(getSuccessHandler()).failureHandler(getFailureHandler());

        // 开启自动配置的注销功能
        //http.logout().logoutSuccessUrl("/login.html");

        // 开启记住我功能
        //http.rememberMe().rememberMeParameter("remeber");

        // 禁用缓存
        //http.headers().cacheControl();

        // 关闭CSRF跨域
        http.csrf().disable();*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }


   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsManager).passwordEncoder(new BCryptPasswordEncoder());;
    }*/

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
             * @param authentication
             * @throws IOException
             * @throws ServletException
             */
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request,
                 HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

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

             }
        };
    }
}
