package day6_29;


/**
 * 接口的使用：静态代理模式 示例2
 */
public class staticProxyTest {
    public static void main(String[] args) {
        RealStar realStar = new RealStar();
        Proxy proxy = new Proxy(realStar);
        proxy.confer();
        proxy.signContract();
        proxy.bookTicket();
        proxy.sing();
        proxy.collectMoney();
    }
}

interface Star{
    void confer();//面谈

    void signContract();//签合同

    void bookTicket();//订票

    void sing();//唱歌

    void collectMoney();//收钱
}

class RealStar implements Star{

    @Override
    public void confer() {

    }

    @Override
    public void signContract() {

    }

    @Override
    public void bookTicket() {

    }

    @Override
    public void sing() {
        System.out.println("明星自己唱歌！");
    }

    @Override
    public void collectMoney() {

    }
}

//代理类
class Proxy implements Star{

    private Star real;

    public Proxy(Star real) {
        this.real = real;
    }

    @Override
    public void confer() {
        System.out.println("经纪人面谈");
    }

    @Override
    public void signContract() {
        System.out.println("经纪人签合同");
    }

    @Override
    public void bookTicket() {
        System.out.println("经纪人订票");
    }

    @Override
    public void sing() {
        real.sing();
    }

    @Override
    public void collectMoney() {
        System.out.println("经纪人收钱");
    }
}