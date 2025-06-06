package org.leocoder.shortlink.admin.dao.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-06-05 21:37
 * @description : 用户实体类
 */
@Data
@TableName(value = "t_user")
public class UserDO {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 注销时间戳
     */
    private Long deletion;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标识 0: 未删除 1: 已删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;



}
