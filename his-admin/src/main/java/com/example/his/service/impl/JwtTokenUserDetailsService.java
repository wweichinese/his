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
 * @desc UserDetailsService这个类是用来加载用户信息，包括用户名、密码、权限、角色集合，我们需要实现这个接口，从数据库加载用户信息
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
        if (Objects.isNull(securityUser)) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        return securityUser;
    }
}
