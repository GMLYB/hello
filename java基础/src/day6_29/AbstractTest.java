package day6_29;

/*
    abstract关键字的使用
    1.abstract：抽象的
    2.abstract可以用来修饰的结构：类、方法

    3.abstract修饰类：抽象类
        > 此类不能实例化
        > 抽象类中一定有构造器，便于子类实例化的时候调用（涉及：子类对象实例化的全过程）
        > 开发中。都会提供抽象类的子类，让子类对象实例化，完成相关的操作

    4.abstract修饰方法，抽象方法
        > 抽象方法只有方法声明，没有方法实体
        > 包含抽象方法的类，一定是抽象类。反之，抽象类中可以没有抽象方法
        > 若子类重写了父类的所有的抽象方法后，此子类可以实例化；
          若子类没有全部重写父类的抽象方法，子类也是一个抽象类，需要使用abstract修饰

    abstract使用上的注意点：
    1.abstract不能用来修饰：属性、构造器等结构
    2.abstract不能用来修饰私有方法、静态方法、final的方法


 */

public class AbstractTest {
    public static void main(String[] args) {

        //一旦Person类抽象了，就不可实例化
//        Person p1 = new Person();
//        p1.eat();

        Person p1 = new Student("na",12);
        p1.eat();


    }

}

abstract class Person{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    //抽象方法
    public abstract void sleep();

    public void eat(){
        System.out.println("我在吃饭");
    }

    public void walk(){
        System.out.println("我在走");
    }
}

class Student extends Person{

    public Student(String name, int age) {
        super(name, age);
    }

    public Student() {
    }

    @Override
    public void sleep() {
        System.out.println("我好困-。-");
    }
}






