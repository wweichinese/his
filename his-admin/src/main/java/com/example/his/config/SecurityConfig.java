package com.example.his.config;

import com.example.his.filter.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 王伟
 * @date 2024/7/16 3:33
 * @desc 上述仅仅配置了登录过滤器，还需要在全局配置类做一些配置，如下：
 * 应用登录过滤器的配置
 * 将登录接口、令牌刷新接口放行，不需要拦截
 * 配置AuthenticationEntryPoint、AccessDeniedHandler
 * 禁用session，前后端分离+JWT方式不需要session
 * 将token校验过滤器TokenAuthenticationFilter添加到过滤器链中，放在UsernamePasswordAuthenticationFilter之前。
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;

    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    @Autowired
    private RequestAccessDeniedHandler requestAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //禁用表单登录，前后端分离用不上
                .disable()
                //应用登录过滤器的配置，配置分离
                .apply(jwtAuthenticationSecurityConfig)
                .and()
                // 设置URL的授权
                .authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                // anyRequest() 所有请求   authenticated() 必须被认证
                .anyRequest()
                .authenticated()
                //处理异常情况：认证失败和权限不足
                .and()
                .exceptionHandling()
                //认证未通过，不允许访问异常处理器
                .authenticationEntryPoint(entryPointUnauthorizedHandler)
                //认证通过，但是没权限处理器
                .accessDeniedHandler(requestAccessDeniedHandler)
                .and()
                //禁用session，JWT校验不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //将TOKEN校验过滤器配置到过滤器链中，否则不生效，放到UsernamePasswordAuthenticationFilter之前
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
                // 关闭csrf
                .csrf().disable();
    }
    // 自定义的Jwt Token校验过滤器
    @Bean
    public TokenAuthenticationFilter authenticationTokenFilterBean() {
        return new TokenAuthenticationFilter();
    }

    //加密算法
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}



