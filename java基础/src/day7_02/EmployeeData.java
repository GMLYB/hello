package day7_02;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {

    public static List<Employee> getEmployees(){

        List<Employee> list = new ArrayList<>();

        list.add(new Employee(1001,"马化腾",34,6000.89));
        list.add(new Employee(1002,"马云",35,532.1));
        list.add(new Employee(1003,"刘德强",15,45613.8));
        list.add(new Employee(1004,"雷军",98,54.56));
        list.add(new Employee(1005,"李彦宏",1,49843));
        list.add(new Employee(1006,"比尔盖茨",56,823.55));
        list.add(new Employee(1007,"任正非",12,6684.59));
        list.add(new Employee(1008,"扎克伯格",81,8848.24));
        return list;
    }
}
