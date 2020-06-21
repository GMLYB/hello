package day6_21;

import java.io.Serializable;

/**
 * Person需要满足如下要求，方可序列化
 * 1.需要实现接口：Serializable
 * 2.需要当前类提供一个全局常量：serialVersionUID
 *      小知识：
 *                                  对象的序列化
 *          凡是实现Serializable接口类的都有一个表示序列化版本标识符的静态常量：
 *          * private static final long serialVersionUID
 *          * serialVersionUID用来表名类的不同版本的兼容性，简言之，其目的是以序列化对象进行版本控制，有关各版本反序列化是否兼容。
 *          * 如果类没有显示定义这个静态常量，它的值是java运行时环境根据类的内部细节自动生成的。
 *              若类的实例变量做了修改，serialVersionUID可能会发生变化，故建议显示声明
 *          简单来说，java的序列化机制是通过运行时判断类的serialVersionUID来验证版本的一致性的，在进行反序列化时，JVM会把传来的字节流中的
 *          serialVersionUID与本地相应实体的serialVersionUID进行比较，如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常（InvalidClassException）
 *
 * 3.除了当前Person类需要实现Serializable接口之外，还必须保证其内部所有属性也必须是可序列化的（默认情况下，基本数据类型可序列化）
 *
 *
 * 补充：ObjectInputStream 和 ObjectOutputStream 不能序列化 static 和 transient 修饰的成员变量
 *
 */

public class Person implements Serializable {

    private static final long serialVersionUID = 3354231354L;

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
