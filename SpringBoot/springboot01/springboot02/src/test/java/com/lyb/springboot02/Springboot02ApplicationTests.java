package com.lyb.springboot02;

import com.lyb.springboot02.bean.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot单元测试
 *
 * 可以在测试期间很方便的类似编码一样进行自动注入等容器的功能
 */

@RunWith(SpringRunner.class)
@SpringBootTest
class Springboot02ApplicationTests {

    @Autowired
    private Person person;

    @Test
    void contextLoads() {
        System.out.println(person);
    }

}
