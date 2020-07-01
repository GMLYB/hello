package day7_01;

/*
    动态代理：手机加工厂：1.小米手机加工厂 2.一加手机加工厂

 */


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface PhoneFactory{
    void produce();
}

//被代理类
class XiaoMiPhoneFactory implements PhoneFactory{

    @Override
    public void produce() {
        System.out.println("我们是小米手机加工厂，生产小米手机！");
    }
}

class OnePlusPhoneFactory implements PhoneFactory{

    @Override
    public void produce() {
        System.out.println("我们是一加手机加工厂，生产一加手机！");
    }
}

class MyInvocationHandle implements InvocationHandler{

    //被代理类的对象
    private Object obj;

    public MyInvocationHandle(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前期准备工作");

        Object o = method.invoke(obj, args);

        System.out.println("后期准备工作");

        return o;
    }
}

class ProxyFactory{

    public static Object getInstance(Object obj){
        MyInvocationHandle handle = new MyInvocationHandle(obj);

        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handle);

    }
}

public class ProxyExercise {
    public static void main(String[] args) {
        XiaoMiPhoneFactory xiaomi = new XiaoMiPhoneFactory();
        OnePlusPhoneFactory oneplus = new OnePlusPhoneFactory();
        PhoneFactory xm = (PhoneFactory)ProxyFactory.getInstance(xiaomi);
        xm.produce();
        PhoneFactory op = (PhoneFactory)ProxyFactory.getInstance(oneplus);
        op.produce();

    }
}
