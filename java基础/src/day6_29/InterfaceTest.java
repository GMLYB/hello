package day6_29;

/**
 * 接口的使用
 * 1.接口使用interface来定义
 * 2.java中，接口和类是两个并列的结构
 * 3.如何定义接口：定义接口中的成员
 *
 *   3.1 JDK7及其以前：
 *      只能定义全局常量和抽象方法
 *          > 全局常量：public static final的(书写的时候可以不写，默认存在)
 *          > 抽象方法：public abstract 的
 *
 *   3.2 JDK8：除了定义全局常量和抽象方法之外，还可以定义静态方法、默认方法（略）
 *
 *
 *
 * 4.接口中不能定义构造器！意味着接口不可以实例化
 *
 * 5.java开发中，接口都听过用类去实现(implements)的方式去使用
 *   如果实现类覆盖了接口中的所有抽象方法，则此实现类就可以实例化
 *   如果实现类没有覆盖接口中的所有抽象方法，则此实现类仍为一个抽象类
 *
 * 6. java类可以实现多个接口 --> 弥补了Java单继承性的局限性
 *   格式： class AA extends BB implements CC,DD{}
 *
 * 7.接口和接口之间可以继承，且可以多继承
 *
 * ****************************************
 * 8.接口具体的使用，体现多态性
 * 9.接口，实际上可以看成是一种规范
 *
 * 面试题：抽象类和接口有什么异同？
 *
 *
 */

public class InterfaceTest {
    public static void main(String[] args) {
        System.out.println(Flyable.MAX_SPEED);
        System.out.println(Flyable.MIN_SPEED);
//        Flyable.MIN_SPEED = 2;

        Plane plane = new Plane();
        plane.fly();
    }
}

interface Attackable{

    void attack();
}

interface Flyable{

    //全局常量
    public static final int MAX_SPEED = 7900;//第一宇宙速度
    int MIN_SPEED = 1;

    public abstract void fly();

    //省略了public abstract
    void stop();
}

class Plane implements Flyable{

    @Override
    public void fly() {
        System.out.println("通过飞机引擎起飞");
    }

    @Override
    public void stop() {
        System.out.println("驾驶员减速停止");
    }
}

abstract class Kite implements Flyable{

    @Override
    public void fly() {
        System.out.println("怎么大风越狠，我心越荡");
    }

}

class Bullet extends Object implements Flyable,Attackable{

    @Override
    public void attack() {

    }

    @Override
    public void fly() {

    }

    @Override
    public void stop() {

    }
}
//***********************
interface AA{
    void method1();

}

interface BB{
    void method2();
}

interface CC extends AA,BB{

}
