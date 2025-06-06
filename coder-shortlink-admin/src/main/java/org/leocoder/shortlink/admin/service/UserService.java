package org.leocoder.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.leocoder.shortlink.admin.dao.domain.UserDO;
import org.leocoder.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.leocoder.shortlink.admin.dto.resp.UserRespDTO;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-06-05 21:38
 * @description :
 */
public interface UserService extends IService<UserDO> {


    /**
     * 根据用户名获取用户信息
     */
    UserRespDTO getUserByUsername(String username);


    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return true：存在；false：不存在
     */
    Boolean hasUsername(String username);


    /**
     * ·注册用户
     *
     * @param requestParam 注册请求参数
     */
    void register(UserRegisterReqDTO requestParam);
}
