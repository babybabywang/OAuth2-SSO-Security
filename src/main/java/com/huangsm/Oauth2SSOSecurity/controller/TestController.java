package com.huangsm.Oauth2SSOSecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 * @author huang
 * @PACKAGE_NAME com.huangsm.Oauth2SSOSecurity.controller
 * @PROJECT_NAME Oauth2-SSO-Security
 * @date 2019/1/3
 */
@RestController
public class TestController {
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "user id"+id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id"+id;
    }

}
