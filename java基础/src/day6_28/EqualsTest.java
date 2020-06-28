package day6_28;

import java.util.Date;

/**
 * 面试题： == 和 equals() 区别
 *
 * 一、== 使用：
 * ==：运算符
 * 1.可以使用在基本数据类型变量和引用数据类型变量中
 * 2.如果比较的是基本数据类型变量：比较两个变量保存的数据是否相等
 *   如果比较的是引用数据类型变量：比较两个对象的地址值是否相等。即两个引用是否指向同一个对象实体
 * 注意：== 符号使用时，必须保证符号左右两边的变量类型一致
 *
 * 二、equals()使用：
 * 1.是一个方法，不是运算符
 * 2.只能适用于引用数据类型
 * 3.Object类中equals()的定义：
 *      public boolean equals(Object obj){
 *          return (this == obj);
 *      }
 *      说明：Object类中定义的equals()和==的作用是相同的：比较两个对象的地址值是否相同，即两个引用是否指向同一个对象实体
 *
 * 4.像String、Date、File、包装类等都重写了Object类中的equals()方法。重写以后，比较的不是两个引用地址是否相同，而是比较两个对象的“实体内容”是否相同
 *
 *
 * 5.通常情况下，我们自定义的类如果使用equals()的话，也通常是比较两个对象的“实体内容”是否相同，那么，我们需要对Object类中的equals()进行重写
 *
 */

public class EqualsTest {
    public static void main(String[] args) {

        //基本数据类型
        int i = 10;
        int j = 10;
        double d = 10.0;
        System.out.println(i == j);//true
        System.out.println(i == d);//true

        boolean b = true;
//        System.out.println(i == b);//除了Boolean类型的，其他七个基础数据类型都可以进行比较

        char c = 10;
        System.out.println(i == c);//true

        char c1 = 'A';
        char c2 = 65;
        System.out.println(c1 == c2);//true

        //引用类型：
        String str1 = new String("GoodMan");
        String str2 = new String("GoodMan");
        System.out.println(str1 == str2);//false

        Person p1 = new Person("Tom",21);
        Person p2 = new Person("Tom",21);
        System.out.println(p1 == p2);//false

        System.out.println("-------------------------");
        System.out.println(p1.equals(p2));//false  重写之后 变成true
        //String、Date等重写了equals()方法，所以比较的是值而不是地址
        System.out.println(str1.equals(str2));//true

        Date date1 = new Date(53153434535L);
        Date date2 = new Date(53153434535L);
        System.out.println(date1 == date2);//false
        System.out.println(date1.equals(date2));//true


    }
}
