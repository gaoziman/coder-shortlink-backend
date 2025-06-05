package org.leocoder.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.leocoder.shortlink.admin.dao.domain.UserDO;
import org.leocoder.shortlink.admin.dto.UserRespDTO;

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
}
