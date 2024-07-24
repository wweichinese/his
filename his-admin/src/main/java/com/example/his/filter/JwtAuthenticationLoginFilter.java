package com.example.his.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author 王伟
 * @date 2024/7/23 9:13
 * @desc 登录认证过滤器
 * Spring Security默认的表单登录认证的过滤器是UsernamePasswordAuthenticationFilter，
 * 这个过滤器并不适用于前后端分离的架构，因此我们需要自定义一个过滤器。
 * 参照UsernamePasswordAuthenticationFilter这个过滤器改造一下。
 */
public class JwtAuthenticationLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationLoginFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/api/v1/login", "POST"));
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取表单提交数据
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> credentials = objectMapper.readValue(request.getInputStream(), Map.class);
        //获取用户名和密码
        //取出前端传递的用户名，密码，封装到 UsernamePasswordAuthenticationToken，数据库存储的用户名，密码会封装到 SecurityUser
        String userName = credentials.get("username");
        String password = credentials.get("password");
        //封装到token中提交
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, password);
        return authenticationManager.authenticate(authRequest);
    }
}
