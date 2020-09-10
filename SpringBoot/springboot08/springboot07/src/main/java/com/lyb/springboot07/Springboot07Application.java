package com.lyb.springboot07;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.lyb.springboot07.mapper")
@SpringBootApplication
public class Springboot07Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot07Application.class, args);
    }

}
