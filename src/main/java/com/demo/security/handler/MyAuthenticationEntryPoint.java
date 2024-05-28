package com.demo.security.handler;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: huangzh
 * @Date: 2024/5/24 11:25
 **/
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 获取错误信息
        String localizedMessage = authException.getLocalizedMessage();

        // 创建结果对象
        Map<String, Object> result = new HashMap<>();
        result.put("code", -1);
        result.put("message", "需要登录");

        // 转换成json字符串
        String json = new Gson().toJson(result);

        // 返回响应
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().println(json);
    }
}
