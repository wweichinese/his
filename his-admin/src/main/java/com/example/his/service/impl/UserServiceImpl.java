package com.example.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.his.entity.User;
import com.example.his.mapper.UserMapper;
import com.example.his.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王伟
 * @date 2024/7/23 12:13
 * @desc
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        return userMapper.selectOne(queryWrapper);
    }
}
