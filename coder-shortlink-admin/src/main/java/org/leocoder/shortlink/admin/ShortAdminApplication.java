package org.leocoder.shortlink.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author : 程序员Leo
 * @version 1.0
 * @date 2025-06-05 21:35
 * @description :
 */
@SpringBootApplication
@MapperScan("org.leocoder.shortlink.admin.dao.mapper")
public class ShortAdminApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ShortAdminApplication.class, args);
        Environment environment = context.getBean(Environment.class);

        System.out.println("访问链接：http://localhost:" + environment.getProperty("server.port"));
        System.out.println("(♥◠‿◠)ﾉﾞ  项目启动成功 ლ(´ڡ`ლ)ﾞ \n");
    }
}
