package day6_14;

/**
 * 例子：银行有一个账户。
 * 有两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额
 *
 *   分析：
 * 1.是否是多线程问题？是，两个储户线程
 * 2.是否有共享数据？有，账户（账户余额）
 * 3.是否有线程安全问题？有
 * 4.需要考虑如何解决线程安全问题？同步机制：三种方式
 *
 */


//class Account implements Runnable{
//
//    //余额
//    private int count = 0;
//    //存钱数
//    private int num = 1000;
//
//    @Override
//    public void run() {
//
//        while (true){
//            if (num<=3000){
//                count += 1000;
//                System.out.println(Thread.currentThread().getName()+"存钱1000，账户余额为:" + count);
//                num += 1000;
//            }else {
//                break;
//            }
//
//        }
//    }
//}

//
//class Account extends Thread{
//
//    //余额
//    private static int count = 0;
//    //存钱数
//    private int num = 1000;
//
//    @Override
//    public void run() {
//        while (num<=3000){
//
//            synchronized (Account.class){
//
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                count += 1000;
//                System.out.println(getName()+"存钱1000，账户余额为:" + count);
//                num += 1000;
//            }
//        }
//    }
//}
class Account{
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double amt){
        if(amt>0){
            balance += amt;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":存钱成功，余额为："+balance);
        }
    }
}

class Customer extends Thread{

    private Account account;

    public Customer(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            account.deposit(1000);
        }
    }
}


public class BankTest2 {
    public static void main(String[] args) {
//        Account account = new Account();
//        Thread t1 = new Thread(account);
//        Thread t2 = new Thread(account);
//
//        Account t1 = new Account();
//        Account t2 = new Account();
//
//
//        t1.setName("用户一");
//        t2.setName("用户二");
//
//        t1.start();
//        t2.start();

        Account account = new Account(0);
        Customer c1 = new Customer(account);
        Customer c2 = new Customer(account);

        c1.setName("用户一");
        c2.setName("用户二");

        c1.start();
        c2.start();
    }
}
