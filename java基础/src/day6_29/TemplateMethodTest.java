package day6_29;

/**
 * 抽象类的应用：模板方法的设计模式 示例2
 */

public class TemplateMethodTest {
    public static void main(String[] args) {
        BankTemplateMethod btm1 = new DrawMoney();
        btm1.process();
        BankTemplateMethod btm2 = new ManageMoney();
        btm2.process();
    }
}

abstract class BankTemplateMethod {
    //具体方法
    public void takeNumber() {
        System.out.println("取号排队");
    }
    public abstract void transact();
    public void evaluate() {
        System.out.println("评分反馈");
    }
    //模板方法，把基本操作组合到一起，子类一般不能重写
    public final void process() {
        this.takeNumber();
        this.transact();//像钩子一样。具体执行时，挂在哪个子类，就执行哪个子类的实现代码
        this.evaluate();
    }
}

class DrawMoney extends BankTemplateMethod {
    @Override
    public void transact() {
        System.out.println("我要取款！");
    }
}

class ManageMoney extends BankTemplateMethod {
    @Override
    public void transact() {
        System.out.println("我要理财!");
    }
}