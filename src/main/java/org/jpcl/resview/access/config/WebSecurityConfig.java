package org.jpcl.resview.access.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * 用来配置security的权限认证
 * @author chenglei
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PermissionConfig permissionConfig;

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    @Resource
    private UserDetailsService myUserDetailsManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 进行权限配置
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/app/**").permitAll();



        //http.authorizeRequests().anyRequest().hasRole("USER")
                //.and().sessionManagement().invalidSessionUrl("/login.html");

        // 开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面
        http.formLogin().usernameParameter(permissionConfig.loginUserName).passwordParameter(permissionConfig.loginPassword)
                .loginPage(permissionConfig.loginPage).permitAll()
                .loginProcessingUrl(permissionConfig.loginPageUrl)
                .successHandler(successHandler).failureHandler(failureHandler);

        // 开启自动配置的注销功能
        //http.logout().logoutSuccessUrl("/login.html");

        // 开启记住我功能
        //http.rememberMe().rememberMeParameter("remeber");

        // 禁用缓存
        //http.headers().cacheControl();

        // 关闭CSRF跨域
        http.csrf().disable();
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
}
