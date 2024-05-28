package com.demo.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: huangzh
 * @Date: 2024/5/23 10:18
 **/
@Tag(name = "index")
@Controller
public class IndexController {

    @Operation(summary = "index")
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @Operation(summary = "currentUser")
    @GetMapping("/currentUser")
    @ResponseBody
    public Map<String, Object> currentUser() {

        System.out.println("index controller");

        SecurityContext context = SecurityContextHolder.getContext();// 存储认证对象的上下文
        Authentication authentication = context.getAuthentication();// 认证对象
        String username = authentication.getName();// 用户名
        Object principal = authentication.getPrincipal();// 身份
        Object credentials = authentication.getCredentials();// 凭证(脱敏)
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();// 权限

        System.out.println(username);
        System.out.println(principal);
        System.out.println(credentials);
        System.out.println(authorities);

        // 创建结果对象
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("data", username);

        return result;
    }
}