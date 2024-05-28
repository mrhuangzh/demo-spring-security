package com.demo.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.security.entity.po.User;

/**
 * @Author: huangzh
 * @Date: 2024/5/23 14:30
 **/
public interface UserService extends IService<User> {
    void saveUserDetails(User user);
}
