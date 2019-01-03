package com.huangsm.Oauth2SSOSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.Resources;

/**
 * 测试接口
 * @author huang
 * @PACKAGE_NAME com.huangsm.Oauth2SSOSecurity.controller
 * @PROJECT_NAME Oauth2-SSO-Security
 * @date 2019/1/3
 */
@RestController
public class TestController {
    @Resource(name = "stringRedisTemplate1")
    private RedisTemplate redisTemplate;
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "user id"+authentication.getPrincipal();
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object refresh_to_access = redisTemplate.opsForValue().get("refresh_to_access:4a3dee44-75e8-440e-82fb-04700f47cf07");
        System.out.println(refresh_to_access);
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getName());
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getDetails());
        return "order id"+authentication.getPrincipal();
    }

}
