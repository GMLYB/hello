package day6_13;

/**
 *
 * 举例：创建三个窗口卖票，总票数为100张，用继承Thread的方式实现
 *
 * 使用同步方法处理几次Thread类的方式中线程安全问题
 *
 */

class Window5 extends Thread{

    private static int ticket = 100;

    @Override
    public void run() {
        while (true){
            show();
        }
    }

    private static synchronized void show(){//同步监视器：当前的类
//    private synchronized void show(){同步监视器：t1,t2,t3 此种解决方法是错误的
        if(ticket>0){
            System.out.println(Thread.currentThread().getName()+":卖票，票号为:"+ticket);
            ticket--;
        }
    }
}

public class WindowTest5 {
    public static void main(String[] args) {
        Window5 w1 = new Window5();
        Window5 w2 = new Window5();
        Window5 w3 = new Window5();
        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();


    }
}
