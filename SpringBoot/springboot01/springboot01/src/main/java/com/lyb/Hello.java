package com.lyb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication 标注主程序，说明是一个spring boot应用
 */
@SpringBootApplication
public class Hello {

    public static void main(String[] args) {

        //启动spring应用
        SpringApplication.run(Hello.class,args);
    }
}
