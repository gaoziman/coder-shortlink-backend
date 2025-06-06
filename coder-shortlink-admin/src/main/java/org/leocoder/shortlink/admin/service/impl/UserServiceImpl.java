package org.leocoder.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.leocoder.shortlink.admin.common.convention.exception.ServiceException;
import org.leocoder.shortlink.admin.common.enums.UserErrorCodeEnum;
import org.leocoder.shortlink.admin.dao.domain.UserDO;
import org.leocoder.shortlink.admin.dao.mapper.UserMapper;
import org.leocoder.shortlink.admin.dto.resp.UserRespDTO;
import org.leocoder.shortlink.admin.service.UserService;
import org.leocoder.shortlink.admin.util.BeanUtil;
import org.springframework.stereotype.Service;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-06-05 21:38
 * @description :
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {


    /**
     * 根据用户名获取用户信息
     */
    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> wrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        // 查询用户信息
        UserDO userDO = baseMapper.selectOne(wrapper);

        if (userDO == null) {
            throw new ServiceException(UserErrorCodeEnum.USER_NOT_EXIST);
        }
        // 转换信息
        return BeanUtil.convert(userDO, UserRespDTO.class);
    }
}
