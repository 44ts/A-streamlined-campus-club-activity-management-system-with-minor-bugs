package com.campusactivity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.campusactivity.mapper")
public class CampusActivitySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusActivitySystemApplication.class, args);
    }

}