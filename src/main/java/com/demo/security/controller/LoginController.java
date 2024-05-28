package com.demo.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: huangzh
 * @Date: 2024/5/23 17:43
 **/
@Tag(name = "login")
@Controller
public class LoginController {

    @Operation(summary = "loginPage")
    @GetMapping("/loginPage")
    public String loginPage() {
        return "myLogin";// 跳转到自定义界面
    }
}
