package com.example.his.controller;

import cn.hutool.core.date.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王伟
 * @date 2024/7/16 3:08
 * @desc
 */
@RequestMapping("/api/open")
@RestController
public class ApiOpenController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "开放 api " + DateUtil.now();
    }
}
