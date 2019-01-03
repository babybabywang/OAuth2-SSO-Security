package com.huangsm.Oauth2SSOSecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 配置资源服务器
 * @author huang
 * @PACKAGE_NAME com.huangsm.Oauth2SSOSecurity.config
 * @PROJECT_NAME Oauth2-SSO-Security
 * @date 2019/1/3
 */
@Configuration
@EnableResourceServer
public class OAuth2ServerConfig extends ResourceServerConfigurerAdapter {
    private static final String DEMO_RESOURCE_ID="order";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .anonymous()
                .and()
                .authorizeRequests()
              //  .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
                //配置order访问控制，必须认证后才能访问
                .antMatchers("/order/**").authenticated();
    }
}
