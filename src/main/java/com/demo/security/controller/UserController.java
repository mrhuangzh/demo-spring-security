package com.demo.security.controller;

import com.demo.security.entity.po.User;
import com.demo.security.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: huangzh
 * @Date: 2024/5/23 14:32
 **/
@Tag(name = "user")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    public UserService userService;

    //    @PreAuthorize("hasRole('ADMIN') and authentication.name == 'admin'")// 用户必须有 ADMIN 角色 并且 用户名是 admin 才能访问此方法
    @PreAuthorize("hasAuthority('USER_LIST')")// 用户必须有 USER_ADD 权限 才能访问此方法
    @GetMapping("/list")
    public List<User> getList() {
        return userService.list();
    }

    @Operation(summary = "add")
    @PostMapping("/add")
    public void add(@RequestBody User user) {
        userService.saveUserDetails(user);
    }
}