package com.demo.security.handler;

import com.alibaba.fastjson2.JSON;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: huangzh
 * @Date: 2024/5/23 18:11
 **/
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取用户身份信息
        Object principal = authentication.getPrincipal();

        // 创建结果对象
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "登录成功");
        result.put("data", principal);

        // 转换成json字符串
//        String json = new Gson().toJson(result);
        String json = JSON.toJSONString(result);

        // 返回响应
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().println(json);
    }
}
