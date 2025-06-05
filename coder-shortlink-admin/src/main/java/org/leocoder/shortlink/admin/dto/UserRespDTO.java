package org.leocoder.shortlink.admin.dto;

import lombok.Data;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-06-05 21:38
 * @description : 用户信息返回DTO
 */
@Data
public class UserRespDTO {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;


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
}
