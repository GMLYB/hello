package day6_13;

/**
 * 使用同步机制将单例模式中的懒汉式改写成线程安全的
 *
 */


public class BankTest {


}

class Bank{

    private Bank(){

    }
    private static Bank instance = null;

//    private static synchornized Bank Getinstance(){//线程同步方法
    private static Bank Getinstance(){

//        //方式一：效率稍差
//        synchronized (Bank.class){
//            if(instance == null){
//                instance = new Bank();
//            }
//            return instance;
//        }

//       if(instance == null){
//            instance = new Bank();
//        }
        //方式二：效率较高
        if(instance == null){
            synchronized (Bank.class){
                if(instance == null){
                    instance = new Bank();
                }
            }
        }
        return instance;
    }

}
