package com.example.his.service.interfaces;

import java.util.List;

/**
 * @author 王伟
 * @date 2024/7/23 12:09
 * @desc
 */
public interface RoleService {

    List<String> selectAllByUsername(String username);
}
