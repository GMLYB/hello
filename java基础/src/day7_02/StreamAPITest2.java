package day7_02;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试Stream的终止操作
 *
 */

public class StreamAPITest2 {

    //1-匹配与查找
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();

//        allMatch(Predicate p) 检查是否匹配所有元素
        //练习：是否所有的员工的年龄都大于18
        boolean allMatch = employees.stream().allMatch(employee -> employee.getAge() > 18);
        System.out.println(allMatch);
        System.out.println("======================");

//        anyMatch(Predicate p) 检查是否至少匹配一个元素
        //练习：是否存在员工的工资大于10000
        boolean anyMatch = employees.stream().anyMatch(employee -> employee.getSalary() > 10000);
        System.out.println(anyMatch);
        System.out.println("======================");

//        noneMatch(Predicate p) 检查是否  没有  匹配所有元素
        //练习：是否存在员工姓“雷”
        boolean noneMatch = employees.stream().noneMatch(employee -> employee.getName().startsWith("雷"));
        System.out.println(noneMatch);
        System.out.println("=====================");
//        findFirst() 返回第一个元素
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first);
//        findAny() 返回当前流中的任意元素
        Optional<Employee> anyemployee = employees.parallelStream().findAny();
        System.out.println(anyemployee);

    }

    @Test
    public void test2() {
        List<Employee> employees = EmployeeData.getEmployees();
//        count() 返回流中元素总数
        long count = employees.stream().filter(employee -> employee.getSalary() > 5000).count();
        System.out.println(count);
//        max(Comparator c) 返回流中最大值
        Stream<Double> salarystream = employees.stream().map(employee -> employee.getSalary());
        Optional<Double> max = salarystream.max(Double::compare);
        System.out.println(max);
//        min(Comparator c) 返回流中最小值
        Optional<Employee> minemployee = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(minemployee);

        System.out.println("========================");
//        forEach(Consumer c) 内部迭代(使用 Collection 接口需要用户去做迭代，称为外部迭代。相反，Stream API 使用内部 迭代——它帮你把迭代做了)
        employees.stream().forEach(System.out::println);

    }


    //3-归约
    @Test
    public void test3() {

        //reduce(T iden, BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。 返回 T
        //计算1-10的自然数的和
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        //reduce(BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。 返回 Optional<T>
        //计算工资总和
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Double> salarystream = employees.stream().map(Employee::getSalary);
        //Optional<Double> summoney = salarystream.reduce(Double::sum);
        Optional<Double> summoney = salarystream.reduce((d1, d2) -> d1 + d2);
        System.out.println(summoney);

    }

    //4-收集
    @Test
    public void test4() {

//        collect(Collector c) 将流转换为其他形式。接收一个 Collector接口的 实现，用于给Stream中元素做汇总的方法

        //查找工资大于6000的员工，返回一个list或者set
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> employeeList = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        employeeList.forEach(System.out::println);
        System.out.println("=====================");
        Set<Employee> employeeSet = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toSet());
        employeeSet.forEach(System.out::println);


    }

}
