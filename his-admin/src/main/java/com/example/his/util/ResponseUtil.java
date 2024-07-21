package com.example.his.util;

/**
 * @author 王伟
 * @date 2024/7/20 15:54
 * @desc
 */
public class ResponseUtil {

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(200, "Success", data);
    }

    public static <T> ResponseResult<T> success(String message, T data) {
        return new ResponseResult<>(200, message, data);
    }

    public static <T> ResponseResult<T> error(int code, String message) {
        return new ResponseResult<>(code, message, null);
    }

    public static <T> ResponseResult<T> error(int code, String message, T data) {
        return new ResponseResult<>(code, message, data);
    }
}
