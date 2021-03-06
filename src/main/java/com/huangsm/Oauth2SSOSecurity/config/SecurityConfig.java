package com.huangsm.Oauth2SSOSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.function.Function;

/**
 * SpringSecurity配置类
 * @author huangsm
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyPasswordEncoder myPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(myPasswordEncoder)
                .withUser("user_1").password("123456").authorities("USER")
                .and()
                .passwordEncoder(myPasswordEncoder)
                .withUser("user_2").password("123456").authorities("USER");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/*").permitAll();
        // @formatter:on
    }

}
