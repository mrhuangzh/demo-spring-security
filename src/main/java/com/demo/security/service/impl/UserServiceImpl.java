package com.demo.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.security.config.DBUserDetailsManager;
import com.demo.security.entity.po.User;
import com.demo.security.mapper.UserMapper;
import com.demo.security.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @Author: huangzh
 * @Date: 2024/5/23 14:31
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private DBUserDetailsManager dbUserDetailsManager;

    @Override
    public void saveUserDetails(User user) {

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()
                .username(user.getUsername()) //自定义用户名
                .password(user.getPassword()) //自定义密码
                .build();
        dbUserDetailsManager.createUser(userDetails);

    }
}
