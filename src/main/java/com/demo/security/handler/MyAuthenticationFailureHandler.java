package com.demo.security.handler;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: huangzh
 * @Date: 2024/5/23 18:36
 **/
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 获取错误信息
        String localizedMessage = exception.getLocalizedMessage();

        // 创建结果对象
        Map<String, Object> result = new HashMap<>();
        result.put("code", -1);
        result.put("message", localizedMessage);

        // 转换成json字符串
        String json = new Gson().toJson(result);

        // 返回响应
//        response.setContentType("application/json;charset=UTF-8");
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().println(json);
    }
}
