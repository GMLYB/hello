package day6_30;

/*
    简单工厂模式
 */

class CarFactory{
    //方式一
    public static Car getCar(String type){
        if ("奥迪".equals(type)){
            return new Audi();
        }else if ("比亚迪".equals(type)){
            return new BYD();
        }else {
            return null;
        }
    }

    //方式二
    public static Car getAudi(){
        return new Audi();
    }

    public static Car getByd(){
        return new BYD();
    }

}

public class SimpleFactory {
    public static void main(String[] args) {
        Car a = CarFactory.getCar("奥迪");
        a.run();
        Car b = CarFactory.getByd();
        b.run();
    }
}

