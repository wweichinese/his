package com.example.his.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 王伟
 * @date 2024/7/23 9:13
 * @desc 登录认证过滤器
 * Spring Security默认的表单登录认证的过滤器是UsernamePasswordAuthenticationFilter，
 * 这个过滤器并不适用于前后端分离的架构，因此我们需要自定义一个过滤器。
 * 参照UsernamePasswordAuthenticationFilter这个过滤器改造一下。
 */
public class JwtAuthenticationLoginFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * 构造方法，调用父类的，设置登录地址/login，请求方式POST
     */
    public JwtAuthenticationLoginFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        //获取表单提交数据
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        //封装到token中提交
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, password);
        return getAuthenticationManager().authenticate(authRequest);
    }
}
