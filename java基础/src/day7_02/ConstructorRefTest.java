package day7_02;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、构造器引用
 *      和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致。
 *      抽象方法的返回值即为构造器所属的类的类型
 *
 * 二、数组引用
 *      可以把数组看作是一个特殊的类，则写法与构造器引用一致.
 *
 */

public class ConstructorRefTest {

    //构造器引用
    //Supplier 中的 T get()
    @Test
    public void test1() {

        Supplier<Employee> sup1 = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        System.out.println(sup1.get());
        System.out.println("************************");

        Supplier<Employee> sup2 = () -> new Employee();
        System.out.println(sup2.get());

        System.out.println("************************");

        Supplier<Employee> sup3 = Employee :: new;
        System.out.println(sup3.get());

    }

    //Function 中的 R apply(T t)
    @Test
    public void test2() {

        Function<Integer,Employee> func1 = id -> new Employee(id);
        Employee employee = func1.apply(10001);
        System.out.println(employee);

        System.out.println("======================");

        Function<Integer,Employee> func2 = Employee :: new;
        System.out.println(func2.apply(10002));
    }


    //BiFunction 中的 R apply(T t,U u)
    @Test
    public void test3() {
        BiFunction<Integer,String,Employee> func1 = (id,name) -> new Employee(id,name);
        Employee emp1 = func1.apply(5465, "雄安新区");
        System.out.println(emp1);
        System.out.println("------------------------");
        BiFunction<Integer,String,Employee> func2 = Employee::new;
        Employee emp2 = func1.apply(4564, "雄安4新区");
        System.out.println(emp2);

    }

    //数组引用
    //Function 中的 R apply(T t)
    @Test
    public void test4() {
        Function<Integer,String[]> func1 = lengh -> new String[lengh];
        String[] arr1 = func1.apply(5);
        System.out.println(arr1);
        System.out.println("-------------------------");
        Function<Integer,String[]> func2 = String[]::new;
        String[] arr2 = func1.apply(6);
        System.out.println(arr2);
    }

}
