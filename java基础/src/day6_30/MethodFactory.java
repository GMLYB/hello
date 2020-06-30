package day6_30;

/*
    工厂方法模式
 */

interface Factory{
    Car getCar();
}

class AudiFactory implements Factory{

    @Override
    public Audi getCar() {
        return new Audi();
    }
}

class BydFactory implements Factory{

    @Override
    public BYD getCar() {
        return new BYD();
    }
}

public class MethodFactory {
    public static void main(String[] args) {
        Car a = new AudiFactory().getCar();
        Car b = new BydFactory().getCar();
        a.run();
        b.run();
    }
}
