package day6_13;

/**
 * 使用同步方法解决实现Runnable接口的线程安全问题
 *
 * 关于同步方法的总结：
 * 1.同步方法仍然设计到同步监视器，只是不需要我们显式的声明
 * 2.非静态的同步方法，同步监视器为：this
 *   静态的同步方法，同步监视器为：当前类本身
 *
 */



class Window4 implements Runnable{

    private int ticket = 100;

    @Override
    public void run() {
        while (show()){
            show();
        }
    }
    private synchronized boolean show(){//同步监视器：this
        if(ticket>0){
            System.out.println(Thread.currentThread().getName()+":卖票，票号为:"+ticket);
            ticket--;
            return true;
        }else {
            return false;
        }
    }

}
public class WindowTest4{
    public static void main(String[] args) {
        Window4 w1 = new Window4();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w1);
        Thread t3 = new Thread(w1);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
