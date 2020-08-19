package pojo;

public class Order {
    private String oname;

    public Order() {
        System.out.println("bean生命周期第 一 步：无参构造方法");
    }

    public void setOname(String oname) {
        System.out.println("bean生命周期第 二 步：调用set方法设置属性值");
        this.oname = oname;
    }

    public void initMethod(){
        System.out.println("bean生命周期第 三 步：执行初始化方法");
    }

    public void destroyMethod(){
        System.out.println("bean生命周期第 五 步：bean销毁");
    }

}
