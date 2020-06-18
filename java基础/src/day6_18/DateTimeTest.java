package day6_18;

import org.junit.Test;

import java.util.Date;

public class DateTimeTest {

    @Test
    public void test1(){

        Date date1 = new Date();
        System.out.println(date1.toString());//Thu Jun 18 12:21:11 CST 2020

        System.out.println(date1.getTime());//1592454071040
        //构造器二：创建指定毫秒数的Date对象
        Date date2 = new Date(1592454071040L);
        System.out.println(date2.toString());

        //创建java.sql.Date对象
        java.sql.Date date3 = new java.sql.Date(1592454071040L);
        System.out.println(date3);//2020-06-18

        //如何将java.util.Date对象转换为java.sql.Date对象
        //情况一
        Date date4 = new java.sql.Date(1592454071040L);
        java.sql.Date date5 = (java.sql.Date) date4;
        //情况二
        Date date6 = new Date();
        java.sql.Date date7 = new java.sql.Date(date6.getTime());



    }
}
