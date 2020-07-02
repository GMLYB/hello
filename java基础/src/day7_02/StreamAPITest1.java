package day7_02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * 测试Stream的中间操作
 *
 */

public class StreamAPITest1 {

    //1-筛选与切片
    @Test
    public void test1() {
        List<Employee> list = EmployeeData.getEmployees();
//        filter(Predicate p) -- 接收 Lambda ， 从流中排除某些元素。
        Stream<Employee> stream = list.stream();
        stream.filter(new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() > 7000;
            }
        }).forEach(new Consumer<Employee>() {
            @Override
            public void accept(Employee employee) {
                System.out.println(employee);
            }
        });
        System.out.println("*************************");

        list.stream().filter(employee -> employee.getSalary() > 7000).forEach(System.out::println);
        System.out.println("--------------------------------");
//        limit(long maxSize) -- 截断流，使其元素不超过给定数量。
        list.stream().limit(3).forEach(System.out::println);
//        skip(long n) -- 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素 不足 n 个，则返回一个空流。与 limit(n) 互补
        System.out.println("============================");
        list.stream().skip(3).forEach(System.out::println);
//        distinct() -- 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
        System.out.println("---------------------------------");
        list.add(new Employee(1010,"马化腾",50,6666));
        list.add(new Employee(1010,"马化腾",50,6666));
        list.add(new Employee(1010,"马化腾",50,6666));
        list.add(new Employee(1010,"马化腾",50,6666));
        list.add(new Employee(1010,"马化腾",50,6666));
        list.stream().distinct().forEach(System.out::println);
    }

    //2-映射
    @Test
    public void test2() {
        //map(Function f) -- 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List<String> list = Arrays.asList("aa", "Bb", "cc", "dd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
        System.out.println("----------------------------");

        //得到员工名字长度大于3的员工姓名
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<String> nameStream = employees.stream().map(Employee::getName);
        nameStream.filter(name -> name.length() > 3).forEach(System.out::println);
        System.out.println("============================");
        //flatMap(Function f) -- 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest1::FromStringToStream);
        characterStream.forEach(c -> System.out.print(c+" "));

    }

    public static Stream<Character> FromStringToStream(String str){
        ArrayList<Character> list = new ArrayList<>();
        for (Character c: str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }


    //3-排序
    @Test
    public void test3() {
        //sorted() -- 产生一个新流，其中按自然顺序排序  -->  自然排序
        List<Integer> list = Arrays.asList(12, 354, 32, 1, 351, 3, 12, 31, 32, 135, 534);
        Stream<Integer> stream = list.stream();
        stream.sorted().distinct().forEach(System.out::println);
        //sorted(Comparator comp) -- 产生一个新流，其中按比较器顺序排序 --> 定制排序
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted((e1,e2) ->{

            int ageValue = Integer.compare(e1.getAge(), e2.getAge());
            if(ageValue != 0){
                return ageValue;
            }else {
                return Double.compare(e1.getSalary(),e2.getSalary());
            }
        }).forEach(System.out::println);
    }


}
