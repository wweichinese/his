package com.example.his.service.interfaces;

import com.example.his.entity.User;

/**
 * @author 王伟
 * @date 2024/7/23 12:09
 * @desc
 */
public interface UserService {

    User getByUsername(String username);
}
