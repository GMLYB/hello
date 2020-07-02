package day7_02;

/*

1.Stream关注的是对数据的运算，与CPU打交道
       集合关注的是数据的存储，与内存打交道

    2.
    ①Stream 自己不会存储元素。
    ②Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
    ③Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行


    3.Stream执行流程
        1.Stream的实例化
        2.一系列中间操作（过滤，映射...）
        3.终止操作

    4.说明：
        1.一个中间操作链，对数据源的数据进行处理
        2.一旦执行终止操作，就执行中间操作链，并产生结果，之后，不会再被使用


 */

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPITest {

    //创建Stream 方式一：通过集合
    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();
        //default Stream<E> stream(): 返回一个顺序流
        Stream<Employee> stream = employees.stream();
        //default Stream<E> parallelStream() : 返回一个并行流
        Stream<Employee> parallelStream = employees.parallelStream();
    }

    //创建Stream 方式二：通过数组
    @Test
    public void test2(){
        //调用Arrays类中的static <T> Stream <T> stream(T[] array)：返回一个流
        int[] arr = new int[]{1,2,3,4,5,6};
        IntStream stream = Arrays.stream(arr);

        Employee e1 = new Employee(10001,"Tom");
        Employee e2 = new Employee(10002,"Joln");
        Employee[] arr1 = new Employee[]{e1,e2};
        Stream<Employee> stream1 = Arrays.stream(arr1);
    }

    //创建Stream 方式三：通过Stream的of()
    @Test
    public void test3(){
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
    }
}
