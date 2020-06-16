package day6_14;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 解决线程安全问题的方式三：Lock锁 -- JDK5.0新增
 *
 * 面试题：synchronized与lock的异同？
 * 相同点：二者都可以解决线程安全问题
 * 不同点：synchronized机制在执行完相应的同步代码以后，自动释放同步监视器--->目前来说用的多
 *         lock需要手动的启动同步（lock（）），同时结束同步也需要手动实现（unlock（））---->建议使用
 * 优先使用顺序：Lock->同步代码块（已经进入了方法体，分配了相应资源）->同步方法（在方法体之外）
 *
 * 面试题：如何解决线程安全问题？有几种方式？
 * 两种/三种，同步代码块、同步方法；lock锁
 *
 */

class Window implements Runnable{
    private int ticket = 100;
    /**
     * ReentrantLock(); true 公平锁 false 非公平锁 默认采用非公平锁
     * 公平锁：操作会排一个队按顺序执行，来保证执行顺序。（会消耗更多的时间来排队）
     * 非公平锁：是无序状态允许插队，jvm会自动计算如何处理更快速来调度插队。（如果不关心顺序，这个速度会更快）
     */
    //第一步 ：实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true){

            try {
//              2.调用锁定方法lock（）
                lock.lock();

                if(ticket > 0){

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"售票号为："+ticket);
                    ticket--;
                }else {
                    break;
                }
            }finally {
                //3.调用解锁方法
                lock.unlock();
            }

        }
    }
}

public class LockTest {

    public static void main(String[] args) {
        Window w = new Window();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();


    }
}
