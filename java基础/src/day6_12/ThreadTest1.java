package day6_12;

/**
 * 多线程的创建，方式一：继承Thread类
 * 1.创建一个继承Thread类的子类
 * 2.重写Thread类的run（）方法-->将此线程执行的操作声明在run（）中
 * 3.创建Thread类的子类对象
 * 4.通过此对象调用start（）方法
 *
 * 例子：遍历100之内的全部偶数
 */

class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0;i < 100;i++){
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

public class ThreadTest1 {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();

        for (int i = 0;i < 100;i++){
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i+"****main******");
            }
        }
    }
}
