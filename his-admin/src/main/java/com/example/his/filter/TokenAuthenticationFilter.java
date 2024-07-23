package com.example.his.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.his.entity.User;
import com.example.his.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 王伟
 * @date 2024/7/24 5:36
 * @desc 客户端请求头携带了token，服务端肯定是需要针对每次请求解析、校验token，因此必须定义一个Token过滤器，这个过滤器的主要逻辑如下：
 * 从请求头中获取accessToken
 * 对accessToken解析、验签、校验过期时间
 * 校验成功，将authentication存入ThreadLocal中，这样方便后续直接获取用户详细信息。
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * UserDetailsService的实现类，从数据库中加载用户详细信息
     */
    @Qualifier("jwtTokenUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        /**
         * token存在则校验token
         * 1. token是否存在
         * 2. token存在：
         *  2.1 校验token中的用户名是否失效
         */
        if (!StrUtil.isEmpty(token)) {
            Claims claims = jwtUtil.parseJwt(token);
            String subject = claims.getSubject();
            JSONObject jsonObject = JSON.parseObject(subject);
            String username = jsonObject.getString("username");

            //todo 从redis缓存中获得对应用户数据
            //RedisUser redisUser = (RedisUser) redisTemplate.opsForValue().get(username);
            String[] a = {};
            //todo
            //List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(redisUser.getAuthorities().toArray(a));
            List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList("admin");
            //todo redisUser暂用User代替
            User user = new User();
            user.setUserName("admin");
            user.setPassword("admin");
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, authorityList);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //继续执行下一个过滤器
        filterChain.doFilter(request, response);
    }
}
