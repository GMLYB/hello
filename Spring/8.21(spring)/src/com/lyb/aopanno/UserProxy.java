package com.lyb.aopanno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect//生成代理对象
public class UserProxy {

    @Pointcut(value = "execution(* com.lyb.aopanno.User.add(..))")
    public void pointdemo(){
    }

    // 前置通知
    // @Before 注解表示作为前置通知
//    @Before(value = "execution(* com.lyb.aopanno.User.add(..))")
    @Before(value = "pointdemo()")
    public void addBefore(){
        System.out.println("add() before.....");
    }

    //最终通知
    @After(value = "execution(* com.lyb.aopanno.User.add(..))")
    public void addAfter(){
        System.out.println("add() after.....");
    }
    //后置通知(返回通知)
    @AfterReturning(value = "execution(* com.lyb.aopanno.User.add(..))")
    public void addAfterReturning(){
        System.out.println("add() afterReturning.....");
    }
    //异常通知
    @AfterThrowing(value = "execution(* com.lyb.aopanno.User.add(..))")
    public void addAfterThrowing(){
        System.out.println("add() AfterThrowing.....");
    }

    //环绕通知
    @Around(value = "execution(* com.lyb.aopanno.User.add(..))")
    public void addAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("add() 环绕之前.....");
        // 被增强的方法执行
        proceedingJoinPoint.proceed();
        System.out.println("add() 环绕之后.....");
    }
}
