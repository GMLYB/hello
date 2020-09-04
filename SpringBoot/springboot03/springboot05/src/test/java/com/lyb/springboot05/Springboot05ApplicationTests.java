package com.lyb.springboot05;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class Springboot05ApplicationTests {

    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());


    @Test
    void contextLoads() {

//        日志级别，由低到高 trace < debug < info < warn < error
        logger.trace("这是trace()日志");
        logger.debug("这是debug()日志");
        //默认是info级别
        logger.info("这是info()日志");
        logger.warn("这是warn()日志");
        logger.error("这是error()日志");
    }

}
