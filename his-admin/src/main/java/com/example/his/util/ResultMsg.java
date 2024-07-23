package com.example.his.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 王伟
 * @date 2024/7/23 11:37
 * @desc
 */
@Data
@AllArgsConstructor
public class ResultMsg {

    private int code;

    private String msg;

    private String token;
}
