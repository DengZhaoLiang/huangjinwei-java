package com.huangjinwei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@MapperScan(value = "com.huangjinwei.mapper")
@SpringBootApplication
public class HuangjinweiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuangjinweiApplication.class, args);
    }

}
