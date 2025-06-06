package org.leocoder.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.leocoder.shortlink.admin.common.convention.exception.ClientException;
import org.leocoder.shortlink.admin.common.convention.exception.ServiceException;
import org.leocoder.shortlink.admin.common.enums.UserErrorCodeEnum;
import org.leocoder.shortlink.admin.dao.domain.UserDO;
import org.leocoder.shortlink.admin.dao.mapper.UserMapper;
import org.leocoder.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.leocoder.shortlink.admin.dto.resp.UserRespDTO;
import org.leocoder.shortlink.admin.service.UserService;
import org.leocoder.shortlink.admin.util.BeanUtil;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import static org.leocoder.shortlink.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-06-05 21:38
 * @description :
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;

    private final RedissonClient redissonClient;

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

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return true：存在；false：不存在
     */
    @Override
    public Boolean hasUsername(String username) {
        return userRegisterCachePenetrationBloomFilter.contains(username);
    }


    /**
     * 注册用户
     *
     * @param requestParam 用户注册请求参数
     */
    @Override
    public void register(UserRegisterReqDTO requestParam) {
        // 判断用户名是否存在
        if (hasUsername(requestParam.getUsername())) {
            throw new ClientException(UserErrorCodeEnum.USER_NAME__EXIST);
        }
        // 获取分布式锁
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());

        try {
            if (lock.tryLock()) {
                // 保存用户信息
                UserDO userDO = BeanUtil.convert(requestParam, UserDO.class);
                int insert = baseMapper.insert(userDO);
                if (insert != 1) {
                    throw new ClientException(UserErrorCodeEnum.USER_SAVE_ERROR);
                }
                // 加入布隆过滤器
                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
                return;
            }
            throw new ClientException(UserErrorCodeEnum.USER_NAME__EXIST);
        } finally {
            lock.unlock();
        }
    }
}
