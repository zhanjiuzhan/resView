package org.jpcl.resview.access.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


/**
 * @author chenglei
 */
public class JcUserDetails implements UserDetails {

    private static final long serialVersionUID = 7448215284148355835L;

    private final Logger logger = LoggerFactory.getLogger(JcUserDetails.class);

    private String userName;
    private String password;

    private String roles;
    private boolean enable = true;

    private List<GrantedAuthority> grantedAuthoritys;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthoritys;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * 当前账号是否已经过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 当前账号是否被锁
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 当前账号证书（密码）是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 当前账号是否被禁用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.enable;
    }


    public JcUserDetails setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public JcUserDetails setPassword(String password) {
        this.password = password;
        return this;
    }

    public JcUserDetails setGrantedAuthoritys(List<GrantedAuthority> grantedAuthoritys) {
        this.grantedAuthoritys = grantedAuthoritys;
        return this;
    }
}