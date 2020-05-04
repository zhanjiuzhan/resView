package org.jpcl.resview.access.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenglei
 */
@Service
public class UserDetailsManagerImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserDetailsManagerImpl.class);

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");

        // 此处将用户对应的角色信息添加到 GrantedAuthority
        // 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
        grantedAuthorities.add(grantedAuthority);

        JcUserDetails userDetails = new JcUserDetails();
        userDetails.setPassword("123456");
        userDetails.setGrantedAuthorities(grantedAuthorities);
        userDetails.setUsername("user");;
        return userDetails;
    }
}
