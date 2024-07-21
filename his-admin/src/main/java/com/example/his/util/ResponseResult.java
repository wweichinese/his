package com.example.his.util;

import lombok.Data;

/**
 * @author 王伟
 * @date 2024/7/20 15:52
 * @desc
 */
@Data
public class ResponseResult<T> {

    private int code;

    private String message;

    private T data;

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
