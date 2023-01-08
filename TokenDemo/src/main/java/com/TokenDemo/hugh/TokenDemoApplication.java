package com.TokenDemo.hugh;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hughs
 */
@SpringBootApplication
@MapperScan("com.TokenDemo.hugh.mapper")
@Slf4j
public class TokenDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TokenDemoApplication.class, args);
        log.info("启动成功");
    }

}
