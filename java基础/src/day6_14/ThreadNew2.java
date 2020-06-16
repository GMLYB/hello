package day6_14;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * 创建线程的方式四：使用线程池
 * 好处：
 *       提高响应速度（减少了创建线程的时间）
 *       降低资源消耗（重复利用线程池中的线程，不需要每次都创建）
 *       便于线程管理
 *          corePoolSize：核心池的大小
 *          maxmumPoolSize：最大线程数
 *          keepAliveTime：线程没有任务时最多保持多长时间后会终止
 *          ...........
 *
 */

class NumberThread1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

class NumberThread2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}


public class ThreadNew2 {
    public static void main(String[] args) {
        //1.提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
//        ThreadPoolExecutor service1 = (ThreadPoolExecutor)service;
        //设置线程池的一些属性
//        System.out.println(service.getClass());
//        service1.setCorePoolSize(15);
//        service1.setKeepAliveTime();

        //2.执行指定的线程操作。需要提供实现Runnable接口或Callable接口实现类的对象
        service.execute(new NumberThread1());//适用于Runnable
        service.execute(new NumberThread2());//适用于Runnable
//        service.submit();//适用于Callable
        //3.关闭连接池
        service.shutdown();
    }
}
