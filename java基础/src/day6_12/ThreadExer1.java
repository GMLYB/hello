package day6_12;

/**
 *  线程练习：创建两个线程，其中一个线程遍历100之内的偶数，另外一个遍历100之内的奇数
 *
 */


class MyThread1 extends Thread{
    @Override
    public void run() {
        for(int i = 0;i<100;i++){
            if(i % 2 == 1){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        for(int i = 0;i<100;i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+"："+i);
            }
        }
    }
}

public class ThreadExer1 {
    public static void main(String[] args) {
        //方式一：创建两个新的线程
//        MyThread1 t1 = new MyThread1();
//        MyThread2 t2 = new MyThread2();
//        t1.start();
//        t2.start();

        //方式二：创建Thread的匿名子类的方式
        new Thread( ){
            @Override
            public void run() {
                for(int i = 0;i<100;i++){
                    if(i % 2 == 1){
                        System.out.println(Thread.currentThread().getName()+"：奇数:"+i);
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for(int i = 0;i<100;i++){
                    if(i % 2 == 0){
                        System.out.println(Thread.currentThread().getName()+"：偶数:"+i);
                    }
                }
            }
        }.start();
    }
}
