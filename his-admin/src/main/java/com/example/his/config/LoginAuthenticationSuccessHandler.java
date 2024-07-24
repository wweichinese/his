package com.example.his.config;

import com.example.his.util.JwtUtil;
import com.example.his.util.ResponseUtil;
import com.example.his.util.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 王伟
 * @date 2024/7/23 9:20
 * @desc 认证成功处理器AuthenticationSuccessHandler
 * 上述的过滤器接口一旦认证成功，则会调用AuthenticationSuccessHandler进行处理，因此我们可以自定义一个认证成功处理器进行自己的业务处理
 */
@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Map<String, String> map = new HashMap<>();
        map.put("username", userDetails.getUsername());
        //jwt生成token
        String token = jwtUtil.createJwt(map.toString());
        //todo
        /*RedisUser redisUser = RedisUser.builder().username(userDetails.getUsername())
                .password(userDetails.getPassword())
                .authorities(userDetails.getAuthorities().stream().map(i -> i.getAuthority()).collect(Collectors.toList())).build();
        //将用户信息保存到redis缓存中
        redisTemplate.opsForValue().set(userDetails.getUsername(), redisUser, 12, TimeUnit.HOURS);*/
        ResponseUtil.responseResult(httpServletResponse, new ResultMsg(200, "登录成功", token));
    }
}
