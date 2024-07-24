package com.example.his.config;

import com.example.his.filter.JwtAuthenticationLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 王伟
 * @date 2024/7/24 6:20
 * @desc 上述定义了一个认证过滤器JwtAuthenticationLoginFilter，这个是用来登录的过滤器，但是并没有注入加入Spring Security的过滤器链中，需要定义配置
 */
@Configuration
public class JwtAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    /**
     * userDetailService
     */
    @Qualifier("jwtTokenUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;
    /**
     * 登录成功处理器
     */
    @Autowired
    private LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;
    /**
     * 登录失败处理器
     */
    @Autowired
    private LoginAuthenticationFailureHandler loginAuthenticationFailureHandler;

    /**
     * 将登录接口的过滤器配置到过滤器链中
     * 1. 配置登录成功、失败处理器
     * 2. 配置自定义的userDetailService（从数据库中获取用户数据）
     * 3. 将自定义的过滤器配置到spring security的过滤器链中，配置在UsernamePasswordAuthenticationFilter之前
     */
    @Override
    public void configure(HttpSecurity http) {
        // 获取 AuthenticationManager
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        // 创建 JwtAuthenticationLoginFilter 实例并注入 AuthenticationManager
        JwtAuthenticationLoginFilter filter = new JwtAuthenticationLoginFilter(authenticationManager);

        //认证成功处理器
        filter.setAuthenticationSuccessHandler(loginAuthenticationSuccessHandler);
        //认证失败处理器
        filter.setAuthenticationFailureHandler(loginAuthenticationFailureHandler);
        //直接使用DaoAuthenticationProvider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //设置userDetailService
        provider.setUserDetailsService(userDetailsService);
        //设置加密算法
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        http.authenticationProvider(provider);
        //将这个过滤器添加到UsernamePasswordAuthenticationFilter之前执行
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
