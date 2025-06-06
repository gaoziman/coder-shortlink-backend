package org.leocoder.shortlink.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.leocoder.shortlink.admin.dao.domain.UserDO;

import java.util.List;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-06-05 21:37
 * @description : 用户Mapper
 */
public interface UserMapper extends BaseMapper<UserDO> {

    /**
     * 获取所有用户名称列表
     *
     * @return 用户名称列表
     */
    List<String> getAllUsernames();

}
