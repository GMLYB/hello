package day7_02;

/*
    方法引用的使用

    1.使用情境：当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！

    2.方法引用，本质上就是Lambda表达式，而Lambda表达式作为函数式接口的实例，所以方法引用，也是函数式接口的实例

    3.使用格式：  类(对象) :: 方法名

    4.具体分成如下的三种情况：
        对象 :: 非静态方法
        类 :: 静态方法
        类 :: 非静态方法

     5.方法引用使用的要求：接口中的抽象方法它的形参列表和返回值与方法引用的形参列表和返回值类型相同！(针对前两种)


 */

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodRefTest {


    //情况一：对象::实例方法
    //Consumer中的void accept(T t)
    //PrintStream中的void println(T t)
    @Test
    public void test1() {
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("北京");

        System.out.println("----------------------------");

        PrintStream ps = System.out;

        Consumer<String> con2 = ps::println;
        con2.accept("beijing");

    }

    //Supplier  中的 T get()
    //Employee 中的String getName()
    @Test
    public void test2() {
        Employee emp = new Employee(1001, "Tom", 23, 5620.4);
        Supplier<String> sup1 = () -> emp.getName();
        System.out.println(sup1.get());
        System.out.println("----------------------------");
        Supplier<String> sup2 = emp::getName;
        System.out.println(sup2.get());

    }


    //情况二：类 :: 静态方法
    //Comparator 中的int compare(T t1,T t2)
    //Integer 中的int compare(T t1,T t2)
    @Test
    public void test3() {

        Comparator<Integer> com1 = (t1, t2) -> Integer.compare(t1, t2);
        int compare1 = com1.compare(135, 21);
        System.out.println(compare1);
        System.out.println("-------------------------------");
        Comparator<Integer> com2 = Integer::compare;
        int compare2 = com2.compare(5, 5);
        System.out.println(compare2);

    }

    //Function 中的 apply(T t)
    //Math 中的 Long round(Double d)
    @Test
    public void test4() {

        Function<Double, Long> fun1 = d -> Math.round(d);
        Long lg1 = fun1.apply(5.6);
        System.out.println(lg1);

        System.out.println("====================");

        Function<Double, Long> fun2 = Math::round;
        Long lg2 = fun2.apply(7.8);
        System.out.println(lg2);


    }

    //情况三：类 :: 实例方法（有难度）
    //Comparator 中的 int Compare(T t1,T t2)
    //String 中的 int t1.compareTo(t2)

    @Test
    public void test5() {
        Comparator<String> com1 = (t1, t2) -> t1.compareTo(t2);
        System.out.println(com1.compare("asda", "dfigj"));
        System.out.println("-------------------------");
        Comparator<String> com2 = String::compareTo;
        System.out.println(com2.compare("asda", "dfigj"));


    }

    //BiPredicate 中的 boolean test(T t1, T t2);
    //String 中的 boolean t1.equals(t2)
    @Test
    public void test6() {

        BiPredicate<String, String> pre1 = (s1, s2) -> s1.equals(s2);
        System.out.println(pre1.test("ajsd", "asdj"));
        System.out.println("-------------------");
        BiPredicate<String, String> pre2 = String::equals;
        System.out.println(pre2.test("hsdf", "hsdf"));
    }

    //Function  中的 R apply(T t)
    //Employee 中的String getName()
    @Test
    public void test7() {
        Function<Employee,String> fun1 = e -> e.getName();
        System.out.println(fun1.apply(new Employee(1002,"Bob",65,56432)));
        System.out.println("--------------------------");
        Function<Employee,String> fun2 = Employee::getName;
        System.out.println(fun2.apply(new Employee(1002,"Bob",65,56432)));
    }
}
