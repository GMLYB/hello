package day6_15;

import java.util.Random;

/**
 *1.有一个抽奖池,该抽奖池中存放了奖励的金额,该抽奖池用一个数组int[] arr = {10,5,20,50,100,200,500,800,2,80,300};
 * 创建两个抽奖箱(线程)设置线程名称分别为“抽奖箱1”，“抽奖箱2”，随机从arr数组中获取奖项元素并打印在控制台上,格式如下:
 * 抽奖箱1 又产生了一个 10 元大奖
 * 抽奖箱2 又产生了一个 100 元大奖
 * //.....
 *
 */

class Prize {
    private int [] arr;

    public Prize(int[] arr) {
        this.arr = arr;
    }

    public int[] getArr() {
        return arr;
    }
}

class DrawBox implements Runnable{

    private Prize prize;

    public DrawBox(Prize prize) {
        this.prize = prize;
    }
    private int[] arr;


//    private int[] arr = {10,5,20,50,100,200,500,800,2,80,300};

    @Override
    public void run() {
        if(prize.getArr().length!=0){
            arr = prize.getArr();
        }else {
            arr = new int[]{};
        }

        while (true){

            synchronized (this) {
                notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int position = new Random().nextInt(arr.length);
                System.out.println(Thread.currentThread().getName()+":"+arr[position]);

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class MultiThreadTest1 {
    public static void main(String[] args) {
        int[] arr = {10,5,20,50,100,200,500,800,2,80,300};
        Prize prize = new Prize(arr);

        DrawBox drawBox = new DrawBox(prize);

        Thread t1 = new Thread(drawBox);
        Thread t2 = new Thread(drawBox);

        t1.setName("抽奖箱一");
        t2.setName("抽奖箱二");

        t1.start();
        t2.start();

    }
}
