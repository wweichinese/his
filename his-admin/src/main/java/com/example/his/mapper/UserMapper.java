package com.example.his.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.his.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author 王伟
 * @date 2024/7/21 19:43
 * @desc
 */
 @Mapper
public interface UserMapper extends BaseMapper<User> {
}
