package day6_12;

/**
 * 测试Thread中的常用方法
 * 1.start（）：启动当前线程，调用当前线程run（）方法
 * 2.run（）：通常需要重写Thread类中的此方法，将创建的线程需要执行的操作声明在此方法中
 * 3.currentThread（）：静态方法，返回执行当前代码的线程
 * 4.getName（）：获取当前线程的名字
 * 5.setName（）：设置当前线程的名字   或者通过构造方法进行起名字
 * 6.yield():释放当前CPU的执行权
 * 7.join(): 在线程A中调用线程B的join（）方法，此时线程A进入阻塞状态，直到线程B完全执行完以后，线程A才结束阻塞状态
 * 8.stop（）：已过时。当执行此方法时，强制结束当前线程
 * 9.sleep（long millitime）：让当前线程“睡眠”指定的millitime毫秒，在指定的millitime毫秒时间内，当前线程是阻塞状态
 * 10.isAlive（）：判断当前线程是否还存活
 *
 * 线程的优先级：
 *     MAX_PRIORITY：10
 *     MIN_PRIORITY：1
 *     NORM_PRIORITY：5 -->默认
 *     涉及方法：
 *     getPriority（）：返回线程优先级
 *     setPriority（int newPriority）：改变线程的优先级
 *
 */

class MyThread3 extends Thread{
    @Override
    public void run() {
        for(int i = 0;i<100;i++){
            if(i % 2 == 1){
                System.out.println(Thread.currentThread().getName()+":"+i+":"+getPriority());

            }
//            if(i % 20 == 0){
//                yield();
//            }
        }
    }

    public MyThread3(String name){
        super(name);
    }
}

public class ThreadMethodTest {
    public static void main(String[] args) {
        MyThread3 thread3 = new MyThread3("我是线程一");
        //起名
//        thread3.setName("计算奇数线程");
//        thread3.setPriority(Thread.MAX_PRIORITY);
        thread3.setPriority(8);
        thread3.start();

        //给主线程起名字
        Thread.currentThread().setName("我是主线程");
        Thread.currentThread().setPriority(1);
        for (int i = 0;i < 100;i++){
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i+"****main******"+":"+Thread.currentThread().getPriority());
            }
            if(i == 20){
                try {
                    thread3.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(thread3.isAlive());
    }
}
