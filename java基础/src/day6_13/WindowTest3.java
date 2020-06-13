package day6_13;

/**
 *
 * 举例：创建三个窗口卖票，总票数为100张，用继承Thread的方式实现
 *
 * 使用同步代码块解决继承Thread类的方式的线程安全问题
 *
 *说明：在继承Thread类创建爱你多线程的方式中，慎用this充当同步监视器，考虑使用当前类充当同步监视器
 *
 */

class Suo{

}

class Window2 extends Thread{

    private static int ticket = 100;
    //记得static，必须公用一把锁
    static Suo suo = new Suo();

    @Override
    public void run() {
        while (true){

          //  synchronized (this){     不能用this，这里不唯一
          //  synchronized (Window2.class){  正确，类也是对象,也是唯一的  Class cla = Window2.class; Window2.class 只会加载一次
            synchronized (suo){
                if(ticket>0){
                    System.out.println(getName()+":卖票，票号为:"+ticket);
                    ticket--;
                }else {
                    break;
                }
            }
        }
    }
}

public class WindowTest3 {
    public static void main(String[] args) {
        Window2 w1 = new Window2();
        Window2 w2 = new Window2();
        Window2 w3 = new Window2();
        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();


    }
}
