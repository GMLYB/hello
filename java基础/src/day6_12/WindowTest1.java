package day6_12;

/**
 *
 * 举例：创建三个窗口卖票，总票数为100张，用继承Thread的方式实现
 *
 * 存在线程安全问题,待解决
 *
 */

class Window1 extends Thread{

    private static int ticket = 100;

    @Override
    public void run() {
        while (true){
            if(ticket>0){
                System.out.println(getName()+":卖票，票号为:"+ticket);
                ticket--;
            }else {
                break;
            }

        }
    }
}

public class WindowTest1 {
    public static void main(String[] args) {
        Window1 w1 = new Window1();
        Window1 w2 = new Window1();
        Window1 w3 = new Window1();
        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();


    }
}
