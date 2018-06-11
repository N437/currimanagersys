package com.winter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.winter.mapper")
@ServletComponentScan("com.winter.controller")

public class Springboot2MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(Springboot2MybatisApplication.class, args);
    }
}
