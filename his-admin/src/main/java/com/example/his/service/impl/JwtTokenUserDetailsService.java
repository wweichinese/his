package com.example.his.service.impl;

import com.example.his.entity.SecurityUser;
import com.example.his.service.interfaces.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 王伟
 * @date 2024/7/21 20:16
 * @desc
 */
@Service
public class JwtTokenUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库中查询
        SecurityUser securityUser = loginService.loadByUsername(username);
        System.out.println(securityUser);
        //用户不存在直接抛出UsernameNotFoundException，security会捕获抛出BadCredentialsException
        if (Objects.isNull(securityUser))
            throw new UsernameNotFoundException("用户不存在！");
        return securityUser;
    }
}
