package com.example.his.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.his.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 王伟
 * @Date 2024/7/21 19:42
 * @Desc
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userid);
}
