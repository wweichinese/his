package com.example.his.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author 王伟
 * @date 2024/7/14 22:05
 */
@RequestMapping("/api/v1/")
@RestController
public class BasicController {

    // http://127.0.0.1:8080/hello?name=lisi
    @GetMapping("hello")
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello 王伟";
    }

    // http://127.0.0.1:8080/user
    @RequestMapping("/user")
    public User user() {
        User user = new User();
        user.setName("theonefx");
        user.setAge(666);
        return user;
    }

    // http://127.0.0.1:8080/save_user?name=newName&age=11
    @RequestMapping("/save_user")

    public String saveUser(User u) {
        return "user will save: name=" + u.getName() + ", age=" + u.getAge();
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html() {
        return "index.html";
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
        user.setName("zhangsan");
        user.setAge(18);
    }
}
