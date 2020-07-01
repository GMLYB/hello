package day6_30;


/**
 * 接口的使用：静态代理模式
 * 特点：代理类和被代理类在编译期间就确定下来了
 */
public class staticProxyTest {
    public static void main(String[] args) {

        NikeClothFactory nike = new NikeClothFactory();
        ProxyClothFactory proxy = new ProxyClothFactory(nike);
        proxy.produceCloth();
    }
}

interface ClothFactory{

    void produceCloth();

}

//代理类
class ProxyClothFactory implements ClothFactory{

    private ClothFactory clothFactory;

    public ProxyClothFactory(ClothFactory clothFactory) {
        this.clothFactory = clothFactory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂做一些准备工作");

        clothFactory.produceCloth();

        System.out.println("代理工厂做一些收尾工作");
    }
}


//被代理类
class NikeClothFactory implements ClothFactory{

    @Override
    public void produceCloth() {
        System.out.println("耐克工厂生产一批运动服!");
    }
}