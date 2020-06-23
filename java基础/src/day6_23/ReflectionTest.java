package day6_23;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 问题1：通过直接new的方式或反射的方式都可以调用公共的结构，开发中到底用哪个？
 *      建议：直接new的方式
 * 什么时候使用：反射的方式？
 *      反射的特征：动态性。不确定创建什么对象的时候，可以考虑使用反射
 *
 * 问题2：反射机制与面向对象中的封装性是不是矛盾的？如何看待两个技术？
 *      不矛盾。
 *
 *
 */

public class ReflectionTest {


    //反射之前，对Person类的操作
    @Test
    public void test1(){

        //1.创建Person类的对象
        Person tom = new Person("Tom", 23);
        tom.age = 10;
        tom.show();
        System.out.println(tom.toString());
        //在Person类外部，不可以通过Person类的对象调用其内部私有结构
        //比如：name、showNation()以及私有的构造器
    }
    //反射之后，对Person类的操作
    @Test
    public void test2() throws Exception {
        Class cl = Person.class;
        //1.通过反射，创建Person类的对象
        Constructor cons = cl.getConstructor(String.class, int.class);
        Object obj = cons.newInstance("Tom", 13);
        Person p1 = (Person) obj;
        System.out.println(obj.toString());

        //2.通过反射调用，调用对象指定的属性，方法
        //调用属性
        Field age = cl.getDeclaredField("age");
        age.set(p1,10);
        System.out.println(p1.toString());

        //调用方法
        Method show = cl.getDeclaredMethod("show");
        show.invoke(p1);

        System.out.println("****************************");

        //通过反射，可以调用Person类的私有结构的，比如：私有的构造器、方法、属性
        //调用私有的构造器
        Constructor cons1 = cl.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p2 = (Person) cons1.newInstance("Bob");
        System.out.println(p2);

        //调用私有的属性
        Field name = cl.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p2,"halaso");
        System.out.println(p2);

        //调用私有的方法
        Method showNation = cl.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String)showNation.invoke(p2,"中国China");//相当于p2.showNation("中国China")
        System.out.println(nation);

    }

    /*
        关于java.lang.Class类的理解
        1.类的加载过程：
            程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)，接着我们使用java.exe命令对某个字节码文件进行解释运行。
            相当于将某个字节码文件加载到内存中，次过程就称为类的加载。加载到内存中的类，就称为运行时类，此运行时类，就作为Class的一个实例。

        2.换句话说：Class的实例就对应这一个运行时类

        3.加载到内存中的运行时类，会缓存一定的时间，在此时间之内，我们可以通过不同的方式来获取此运行时类

     */


    //获取Class的实例的方式（前三种方式需要掌握）
    @Test
    public void test3() throws ClassNotFoundException {
        //方式一:调用运行时类的属性：.class
        Class class1 = Person.class;
        System.out.println(class1);
        //方式二：通过运行时类的对象
        Person p1 = new Person();
        Class class2 = p1.getClass();
        System.out.println(class2);

        //方式三：调用Class的静态方法：forName(String classPath)-->频率比较高
        Class class3 = Class.forName("day6_23.Person");
        System.out.println(class3);

        System.out.println(class1 == class2);//true
        System.out.println(class1 == class3);//true

    }

    @Test
    public void test4(){
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();

        //只要元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);

    }

}
