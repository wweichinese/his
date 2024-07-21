package com.example.his.controller;

import com.example.his.entity.User;
import com.example.his.service.interfaces.LoginService;
import com.example.his.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 王伟
 * @date 2024/7/17 4:53
 * @desc
 */
@RequestMapping("/api/v1/")
@RestController
public class LoginController {

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public LoginService loginService;

    @PostMapping("/login")
    public ResponseResult<Map<String, String>> login(@RequestBody User user) {
        return loginService.login(user);
    }

}
