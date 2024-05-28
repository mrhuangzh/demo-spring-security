package com.demo.security.config;

import com.demo.security.handler.*;
import com.demo.security.strategy.MySessionInformationExpiredStrategy;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: huangzh
 * @Date: 2024/5/23 16:19
 **/
@Configuration
@EnableMethodSecurity// 使得@PreAuthorize生效
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
//                .csrf(Customizer.withDefaults())// 默认开启csrf
                .csrf(AbstractHttpConfigurer::disable)// 关闭csrf
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/user/list").hasAuthority("USER_LIST")// 具有USER_LIST权限的用户可以访问/user/list
//                        .requestMatchers("/user/add").hasAuthority("USER_ADD")// 具有USER_ADD权限的用户可以访问/user/add
//                        .requestMatchers("/user/list").hasAnyRole("ADMIN")// 具有管理员角色的用户可以访问/user/**
                                .anyRequest()// 对所有请求开启授权保护
                                .authenticated()// 已认证的请求会被自动授权
                )
//                .formLogin(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/loginPage")// 指定自定义登录页面
                        .permitAll()// 无需授权即可访问
                        .loginProcessingUrl("/login")// form表单提交的路径，默认与loginPage设置的值相同
                        .usernameParameter("username")// 自定义表单用户名参数，默认是username
                        .passwordParameter("password")// 自定义表单密码参数，默认是password
                        .failureUrl("/loginPage?error")// 登录失败的返回地址
                        .successHandler(new MyAuthenticationSuccessHandler())
                        .failureHandler(new MyAuthenticationFailureHandler())
                )
//                .logout(logout -> logout
//                        .logoutSuccessHandler(new MyLogoutSuccessHandler()))
                .sessionManagement(session -> session
                        .maximumSessions(2)// 允许统一账户最大同时登录数量
                        .expiredSessionStrategy(new MySessionInformationExpiredStrategy())
                )
                .exceptionHandling(exception -> {
                    exception
//                            .authenticationEntryPoint(new MyAuthenticationEntryPoint())// 请求未认证的接口
                            .accessDeniedHandler(new MyAccessDeniedHandler());// 请求未授权的接口
                })
//                .authorizeRequests(authorize->authorize
//                        .requestMatchers("/user/**").hasRole("ADMIN")// 具有管理员角色的用户可以访问/user/**
//                        .anyRequest()// 对所有请求开启授权保护
//                        .authenticated()// 已认证的请求会被自动授权
//                )
                .httpBasic(Customizer.withDefaults())
        ;
        return httpSecurity.build();
    }
}
