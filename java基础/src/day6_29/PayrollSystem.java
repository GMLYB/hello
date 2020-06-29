package day6_29;

import java.util.Calendar;

/*
    5.定义PayrollSystem类，创建Employee变量数组并初始化，该数组存放各类型雇员对象的引用。利用循环结构遍历数组元素，输出各个对象的类型
    name、number、birthday，以及该对象生日。当键盘输入本月月份值时，如果本月是某个Employee对象的生日，还需要输出增加工资信息
 */
public class PayrollSystem {
    public static void main(String[] args) {

        Employee[] emps = new Employee[2];
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);

        emps[0] = new SalariedEmployee("马尚",1002,new MyDate(1997,2,5),10000);


        emps[1] = new HourlyEmployee("李萨飞",2001,new MyDate(1893,6,12),50,240);

        for (int i = 0; i < emps.length; i++) {
            System.out.print(emps[i]+" ");
            double salary = emps[i].earnings();
            System.out.println("月工资为"+salary);
            if(emps[i].getBirthday().getMonth() == month+1){
                System.out.println("生日快乐！奖励100元钱!");
            }
        }
    }
}
