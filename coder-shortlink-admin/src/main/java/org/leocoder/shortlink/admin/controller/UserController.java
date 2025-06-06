package org.leocoder.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.leocoder.shortlink.admin.common.convention.result.Result;
import org.leocoder.shortlink.admin.common.convention.result.Results;
import org.leocoder.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.leocoder.shortlink.admin.dto.resp.ActualUserRespDTO;
import org.leocoder.shortlink.admin.dto.resp.UserRespDTO;
import org.leocoder.shortlink.admin.service.UserService;
import org.leocoder.shortlink.admin.util.BeanUtil;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return true:存在 false:不存在
     */
    @GetMapping("/api/shortlink/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }


    /**
     * 用户注册
     * @param requestParam 用户注册参数
     * @return 成功返回空，失败返回错误信息
     */
    @PostMapping("/api/shortlink/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }
}