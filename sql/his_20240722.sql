-- ----------------------------
-- 1、用户信息表
-- ----------------------------
drop table if exists sys_user;
CREATE TABLE `sys_user` (
    `user_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
    `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '用户昵称',
    `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '密码',
    `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '用户类型（0-系统用户）',
    `phone_number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '手机号',
    `sex` char(1) CHARACTER SET utf8mb4 DEFAULT '1' COLLATE utf8mb4_general_ci NULL COMMENT '用户性别（1-男，2-女，3-未知）',
    `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '头像地址',
    `status` char(1) CHARACTER SET utf8mb4 DEFAULT '1' COLLATE utf8mb4_general_ci NULL COMMENT '帐号状态（0-停用，1-正常）',
    `del_flag` char(1) CHARACTER SET utf8mb4 DEFAULT '1' COLLATE utf8mb4_general_ci NULL COMMENT '删除标志（0-已删除， 1-存在）',
    `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '最后登录IP',
    `latest_login_time` datetime NULL COMMENT '最后登录时间',
    `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '创建人',
    `create_time` datetime NULL COMMENT '创建时间',
    `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '更新人',
    `update_time` datetime NULL COMMENT '更新时间',
    `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='用户信息表'
AUTO_INCREMENT=100000
ROW_FORMAT=DYNAMIC
AVG_ROW_LENGTH=0;


-- ----------------------------
-- 2、角色信息表
-- ----------------------------
drop table if exists sys_role;
CREATE TABLE `sys_role` (
    `role_id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_key` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色英文key',
    `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
    `role_sort` int(4) NOT NULL COMMENT '显示顺序',
    `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    `menu_check_strictly` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '菜单树选择项是否关联显示（Y|N）',
    `province_check_strictly` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '省份树选择项是否关联显示（Y|N）',
    `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '角色状态（0-停用，1-正常）',
    `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '删除标志（0-已删除， 1-存在）',
    `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '创建人',
    `create_time` datetime NULL COMMENT '创建时间',
    `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '更新人',
    `update_time` datetime NULL COMMENT '更新时间',
    `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
    PRIMARY KEY (`role_id`)
) ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='角色信息表'
AUTO_INCREMENT=1
ROW_FORMAT=DYNAMIC
AVG_ROW_LENGTH=0;


-- ----------------------------
-- 3、菜单信息表
-- ----------------------------
drop table if exists sys_menu;
CREATE TABLE `sys_menu` (
    `menu_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
    `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父菜单ID',
    `order_num` int(4) NULL COMMENT '显示顺序',
    `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '路由地址',
    `component` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '组件路径',
    `query` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '路由参数',
    `route_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '路由名称',
    `is_frame` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'Y' COMMENT '是否为外链（Y|N）',
    `is_cache` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否缓存（Y|N）',
    `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '菜单显示（0-隐藏，1-显示）',
    `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '菜单状态（0-停用，1-正常）',
    `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '权限标识',
    `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
    `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '创建人',
    `create_time` datetime NULL COMMENT '创建时间',
    `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '更新人',
    `update_time` datetime NULL COMMENT '更新时间',
    `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
    PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='菜单信息表'
ROW_FORMAT=DYNAMIC
AVG_ROW_LENGTH=0;


-- ----------------------------
-- 4、用户角色关联表
-- ----------------------------
drop table if exists sys_user_role;
CREATE TABLE `sys_user_role` (
                                 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                                 `role_id` int(4) NOT NULL COMMENT '角色ID',
                                 PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
COMMENT='用户和角色关联表'
ROW_FORMAT=DYNAMIC
AVG_ROW_LENGTH=0;


-- ----------------------------
-- 5、角色菜单关联表
-- ----------------------------
drop table if exists sys_role_menu;
CREATE TABLE `sys_role_menu` (
                                 `role_id` int(4) NOT NULL COMMENT '角色ID',
                                 `menu_id` int(10) NOT NULL COMMENT '菜单ID',
                                 PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
COMMENT='角色和菜单关联表'
ROW_FORMAT=DYNAMIC
AVG_ROW_LENGTH=0;
