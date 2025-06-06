package org.leocoder.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-06-06 18:55
 * @description : 用户注册请求DTO
 */
@Data
public class UserRegisterReqDTO {
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
}
