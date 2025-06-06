package org.leocoder.shortlink.admin.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.leocoder.shortlink.admin.dao.mapper.UserMapper;
import org.redisson.api.RBloomFilter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-06-06 18:51
 * @description : 初始化布隆过滤器
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BloomFilterInitializer implements ApplicationRunner {

    private final UserMapper userMapper;

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("开始加载数据库中的用户名到布隆过滤器...");
        List<String> allUsernames = userMapper.getAllUsernames();
        allUsernames.forEach(userRegisterCachePenetrationBloomFilter::add);
        log.info("加载完成，共计{}个用户名", allUsernames.size());
    }
}
