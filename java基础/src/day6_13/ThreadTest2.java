package day6_13;

/**
 * 多线程的创建，方式二：实现Runnable的run()接口
 * 1.创建一个实现了Runnable接口的类
 * 2.实现类去实现Runnable中的抽象方法：run（）
 * 3.创建实现类的对象
 * 4.将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
 * 5.通过Thread类的对象调用start()
 *
 * 例子：遍历100之内的全部偶数
 */

//1.创建一个实现了Runnable接口的类
class MyThread implements Runnable{
    @Override
    //2.实现类去实现Runnable中的抽象方法：run（）
    public void run() {
        for (int i = 0;i < 100;i++){
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

public class ThreadTest2 {
    public static void main(String[] args) {
        //3.创建实现类的对象
        MyThread m1 = new MyThread();
        //4.将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
        Thread t1 = new Thread(m1);
        //5.通过Thread类的对象调用start()
        t1.start();
        for (int i = 0;i < 100;i++){
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i+"****main******");
            }
        }
    }
}
