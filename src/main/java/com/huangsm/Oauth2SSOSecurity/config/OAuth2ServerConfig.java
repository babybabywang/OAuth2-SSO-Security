package com.huangsm.Oauth2SSOSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

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

    /**
     * token存储库
     * @return
     */
    @Bean("tokenStore1")
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }


    /**
     * AccessToken转换器-定义token的生成方式，这里使用JWT生成token，对称加密只需要加入key等其他信息（自定义）。
     * @return
     */
    @Bean("converter")
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter=new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    /**
     *创建AuthorizationServerTokenServices 接口的实现类时，
     * 你可以考虑使用DefaultTokenServices 类，它使用随机值创建令牌，
     * 并处理除永久令牌以外的所有令牌，对于永久令牌，它委托TokenStore类进行处理。
     * @return
     */
    @Bean
    @Primary
    public DefaultTokenServices tokenServices(){
        DefaultTokenServices defaultTokenServices=new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
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
