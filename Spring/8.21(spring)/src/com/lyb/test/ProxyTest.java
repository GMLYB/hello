package com.lyb.test;

import com.lyb.aopanno.User;
import com.lyb.dao.StudentDao;
import com.lyb.dao.StudentDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyTest {
    public static void main(String[] args) {

        Class[] interfaces = {StudentDao.class};
        StudentDao studentDao = new StudentDaoImpl();
//        StudentDao proxy = (StudentDao) Proxy.newProxyInstance(StudentDaoImpl.class.getClassLoader(), interfaces, new MyinvocationHander(studentDao));
        StudentDao proxy = (StudentDao) Proxy.newProxyInstance(StudentDaoImpl.class.getClassLoader(), StudentDaoImpl.class.getInterfaces(), new MyinvocationHander(studentDao));
        Integer add = proxy.add(1, 2);
        System.out.println(add);


    }

}

class MyinvocationHander implements InvocationHandler{

    private Object object;

    public MyinvocationHander(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("代理前执行的内容！ 方法为=" + method.getName() +  ",参数为=" + Arrays.toString(args));

        Object res = method.invoke(object, args);

        System.out.println("代理后执行的内容！object = " + object);

        return res;
    }
}