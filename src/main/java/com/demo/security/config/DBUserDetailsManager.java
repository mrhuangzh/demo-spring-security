package com.demo.security.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.security.entity.po.User;
import com.demo.security.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author: huangzh
 * @Date: 2024/5/23 16:15
 **/
@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    @Resource
    private UserMapper userMapper;


    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEnabled(true);
        userMapper.insert(user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(() -> "USER_LIST");
//            authorities.add(() -> "USER_ADD");
//            authorities.add(() -> "ROLE_ADMIN");

//            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority("USER_LIST"));
//            authorities.add(new SimpleGrantedAuthority("USER_ADD"));
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getEnabled(),
                    true,// 用户账号是否过期
                    true,// 用户凭证是否过期
                    true,// 用户是否未被锁定
                    authorities);// 权限列表

//            UserDetails userDetails = org.springframework.security.core.userdetails.User
//                    .withUsername(user.getUsername())
//                    .password(user.getPassword())
//                    .disabled(!user.getEnabled())
//                    .credentialsExpired(false)
//                    .accountLocked(false)
//                    .credentialsExpired(false)
//                    .roles("ADMIN")
////                    .authorities(authorities)// 务必注意，roles 中会调用 authorities 方法，两者之间会相互覆盖，若需要同时设置 USER_* 和 ROLE_* ，最好使用上面自主拼接的方式
//                    .build();
            return userDetails;
        }
    }
}
