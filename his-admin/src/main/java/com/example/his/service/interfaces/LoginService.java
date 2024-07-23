package com.example.his.service.interfaces;

import com.example.his.entity.SecurityUser;

/**
 * @author 王伟
 * @date 2024/7/21 15:15
 * @desc
 */
public interface LoginService {

    SecurityUser loadByUsername(String username);

}
