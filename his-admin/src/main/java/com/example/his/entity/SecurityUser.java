package com.example.his.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author 王伟
 * @date 2024/7/23 12:01
 * @desc UserDetails这个也是个接口，其中定义了几种方法，都是围绕着用户名、密码、权限+角色集合这三个属性，因此我们可以实现这个类拓展这些字段
 */
@Data
public class SecurityUser implements UserDetails {

    //用户名
    private String username;

    //密码
    private String password;

    //权限
    private Collection<? extends GrantedAuthority> authorities;

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public SecurityUser() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未被锁
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
