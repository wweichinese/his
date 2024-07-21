package com.example.his.service.interfaces;

import com.example.his.entity.User;
import com.example.his.util.ResponseResult;

import java.util.Map;

/**
 * @author 王伟
 * @date 2024/7/21 15:15
 * @desc
 */
public interface LoginService {

    ResponseResult<Map<String, String>> login(User user);

    ResponseResult logout();
}
