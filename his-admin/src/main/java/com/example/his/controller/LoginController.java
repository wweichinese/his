package com.example.his.controller;

import com.example.his.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王伟
 * @date 2024/7/17 4:53
 * @desc
 */
@RequestMapping("/api/v1/")
@RestController
public class LoginController {

    @PostMapping("/login")
    //取出前端post提交的用户名，密码，并进行SpringSecurity 的登录验证
    public String login(@RequestBody User user) {
        return "success";
    }

}
