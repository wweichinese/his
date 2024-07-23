package com.example.his.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王伟
 * @date 2024/7/23 9:20
 * @desc
 */
@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /*@Autowired
    JwtUtil jwtUtil;*/

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Map<String, String> map = new HashMap<>();
        map.put("username", userDetails.getUsername());
        //jwt生成token
        /*String token = jwtUtil.getToken(map);
        RedisUser redisUser = RedisUser.builder().username(userDetails.getUsername())
                .password(userDetails.getPassword())
                .authorities(userDetails.getAuthorities().stream().map(i -> i.getAuthority()).collect(Collectors.toList())).build();
        //将用户信息保存到redis缓存中
        redisTemplate.opsForValue().set(userDetails.getUsername(), redisUser, 12, TimeUnit.HOURS);
        ResponseUtils.result(httpServletResponse, new ResultMsg(200, "登录成功！", token));*/
    }
}
