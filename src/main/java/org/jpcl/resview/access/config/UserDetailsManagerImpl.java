package org.jpcl.resview.access.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author chenglei
 */
@Service
public class UserDetailsManagerImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserDetailsManagerImpl.class);

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        /*
        User user = userService.getUserByName(userName);
        if (null == user) {
            String log = "该 [" + userName + "] 用户不存在";
            logger.error(log);
            throw new UsernameNotFoundException(log);
        }
        List<Role> roles = roleService.getRoles(user.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : roles) {
            if (role != null && role.getName() != null) {
                GrantedAuthority grantedAuthority =
                        new SimpleGrantedAuthority(role.getName());

                // 此处将用户对应的角色信息添加到 GrantedAuthority
                // 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                grantedAuthorities.add(grantedAuthority);
            }
        }
        MyUserDetail userDetails = new MyUserDetail();
        userDetails.setPassword(user.getPassword())
                .setGrantedAuthoritys(grantedAuthorities)
                .setUserName(user.getUserName());
        return userDetails;

         */
        return null;
    }
}
