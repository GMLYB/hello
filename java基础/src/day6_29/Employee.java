package day6_29;

/*
    编写工资系统，实现不同类型员工（多态）的按月发放工资。如果当月出现某个Employee对象的生日，则将雇员的工资增加100元
    实验说明：
    1.定义一个Employee类，该类包含：
    private成员变量name，number，birthday，其中birthday为MyDate类的对象；abstract方法earnings()；
    toString()方法输出对象的name、number和birthday
    2.MyDate类包含：
    private成员变量year、month、day
    toDateString()方法返回日期对应的字符串：xxxx年xx月xx日
    3.定义SalariedEmployee类继承Employee类，实现按月计算工资的员工处理。该类包括：private成员变量monthlySalary；
    实现父类的抽象方法earnings()，该方法的返回monthlySalary值，toString()方法输出员工类型信息及员工的name，number，birthday
    4.参照SalariedEmployee类定义HourlyEmployee类，实现按小时计算工资的员工处理。该类包括：
        private成员变量wage和hour；
        实现父类的抽象方法earnings()，该方法返回wage和hour值
        toSting()方法输出员工类型信息及员工的name、number、birthday。
    5.定义PayrollSystem类，创建Employee变量数组并初始化，该数组存放各类型雇员对象的引用。利用循环结构遍历数组元素，输出各个对象的类型
    name、number、birthday，以及该对象生日。当键盘输入本月月份值时，如果本月是某个Employee对象的生日，还需要输出增加工资信息
    提示：
        定义People类型的数组People[] c1 = new People[10]
        数组元素赋值
        c1[0] = new People("John","0001",20);
        c1[1] = new People("Bob","0002",19);

       若People有两个子类Student和Officer，则数组元素赋值时，可以使父类类型的数组元素指向子类。
       c1[0] = new Student("John","0001",20,85.0);
       c1[1] = new Officer("Bob","0002",19,90.5);

 */

/*
    1.定义一个Employee类，该类包含：
    private成员变量name，number，birthday，其中birthday为MyDate类的对象；abstract方法earnings()；
    toString()方法输出对象的name、number和birthday
 */
public abstract class Employee {
    private String name;
    private int number;
    private MyDate birthday;

    public abstract double earnings();

    public Employee(String name, int number, MyDate birthday) {
        this.name = name;
        this.number = number;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "[" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", birthday=" + birthday.toDateString() +
                ']';
    }


}
