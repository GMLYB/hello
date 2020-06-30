package day6_30;
/*
    无工厂模式
 */

public class NoFactory {
    public static void main(String[] args) {
        Car a = new Audi();
        Car b = new BYD();
        a.run();
        b.run();

    }
}

interface Car{
    void run();
}

class Audi implements Car{

    @Override
    public void run() {
        System.out.println("奥迪在跑");
    }
}

class BYD implements Car{
    @Override
    public void run() {
        System.out.println("比亚迪再跑");
    }
}