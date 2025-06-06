package org.leocoder.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.leocoder.shortlink.admin.common.convention.result.Result;
import org.leocoder.shortlink.admin.common.convention.result.Results;
import org.leocoder.shortlink.admin.dto.resp.ActualUserRespDTO;
import org.leocoder.shortlink.admin.dto.resp.UserRespDTO;
import org.leocoder.shortlink.admin.service.UserService;
import org.leocoder.shortlink.admin.util.BeanUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-06-05 21:34
 * @description : 用户管理
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private  final UserService userService;



    /**
     * 根据用户名获取用户信息
     */
    @GetMapping("/api/shortlink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }


    /**
     * 根据用户名获取真实用户信息-未脱敏
     */
    @GetMapping("/api/shortlink/v1/user/actual/{username}")
    public Result<ActualUserRespDTO> getActualUserByUsername(@PathVariable("username") String username) {
        return Results.success(BeanUtil.convert(userService.getUserByUsername(username), ActualUserRespDTO.class));
    }
}