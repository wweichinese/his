package com.example.his.service.impl;

import com.example.his.entity.SecurityUser;
import com.example.his.entity.User;
import com.example.his.service.interfaces.LoginService;
import com.example.his.service.interfaces.RoleService;
import com.example.his.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 王伟
 * @date 2024/7/21 16:25
 * @desc
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Nullable
    @Override
    public SecurityUser loadByUsername(String username) {
        //获取用户信息
        User user = userService.getByUsername(username);
        if (Objects.nonNull(user)){
            SecurityUser securityUser = new SecurityUser();
            securityUser.setUsername(username);
            //todo 此处为了方便，直接在数据库存储的明文，实际生产中应该存储密文，则这里不用再次加密
            securityUser.setPassword(passwordEncoder.encode(user.getPassword()));
            //todo 查询该用户的角色
            //List<String> userRoles = roleService.selectAllByUsername(username);

            List<String> userRoles = new ArrayList<>();
            userRoles.add("admin");

            String[] a={};
            List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(userRoles.toArray(a));
            securityUser.setAuthorities(authorityList);
            return securityUser;
        }
        return null;
    }
}
