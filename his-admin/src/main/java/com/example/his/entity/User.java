package com.example.his.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王伟
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value="sys_user")
public class User {

    @TableId
    private Long userId;

    private String userName;

    private String password;

}
