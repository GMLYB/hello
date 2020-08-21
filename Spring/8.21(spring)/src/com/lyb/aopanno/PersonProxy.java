package com.lyb.aopanno;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PersonProxy {
    @Before(value = "execution(* com.lyb.aopanno.User.add(..))")
    public void addBefore(){
        System.out.println("Perosn add() before...");
    }
}
