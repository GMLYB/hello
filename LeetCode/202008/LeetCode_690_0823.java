package August;


import java.util.ArrayList;
import java.util.List;

/*
给定一个保存员工信息的数据结构，它包含了员工唯一的id，重要度 和 直系下属的id。
比如，员工1是员工2的领导，员工2是员工3的领导。他们相应的重要度为15, 10, 5。
那么员工1的数据结构是[1, 15, [2]]，员工2的数据结构是[2, 10, [3]]，员工3的数据结构是[3, 5, []]。
注意虽然员工3也是员工1的一个下属，但是由于并不是直系下属，因此没有体现在员工1的数据结构中。
现在输入一个公司的所有员工信息，以及单个员工id，返回这个员工和他所有下属的重要度之和。

示例 1:
输入: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
输出: 11
解释:
员工1自身的重要度是5，他有两个直系下属2和3，而且2和3的重要度均为3。因此员工1的总重要度是 5 + 3 + 3 = 11。

注意:
一个员工最多有一个直系领导，但是可以有多个直系下属
员工数量不超过2000。

 */
public class LeetCode_690 {


    public int getImportance(List<Employee> employees, int id) {


        //统计 importtance 总和
        int sum = 0;

        for (Employee employee : employees) {
            if (employee.id == id) {
                // 没有下属 返回自身的 importance
                if (employee.subordinates.size() == 0) {
                    return employee.importance;
                } else {
                    //有下属情况
                    for (Integer subordinate : employee.subordinates) {
                        //统计下属的 importance
                        sum += getImportance(employees, subordinate);
                    }
                    //返回自身 + 下属的 importance
                    return sum + employee.importance;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {

        LeetCode_690 code = new LeetCode_690();
        List<Employee> list = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(2);
        l1.add(3);
        Employee employee1 = new Employee(1, 5, l1);
        Employee employee2 = new Employee(2, 3, new ArrayList<Integer>());
        Employee employee3 = new Employee(3, 3, new ArrayList<Integer>());

        list.add(employee1);
        list.add(employee2);
        list.add(employee3);
        System.out.println(code.getImportance(list, 1));
    }


}


class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;

    public Employee(int id, int importance, List<Integer> subordinates) {
        this.id = id;
        this.importance = importance;
        this.subordinates = subordinates;
    }
}
