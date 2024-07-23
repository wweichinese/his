package com.example.his.util;

import java.util.UUID;

/**
 * @author 王伟
 * @date 2024/7/23 10:10
 * @desc
 */
public class UuidUtil {

    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
