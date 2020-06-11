package day6_11;

/**
 *
 * 内部类实例，解决java不能多重继承的问题，实现类的多重继承
 *
 */

public class InnerClass {
    public static void main(String args[]){
        A a = new A();
        a.testB();
        a.testC();
    }
}


class A{
    public void testB(){
        new InnerB().testB();
    }
    public void testC(){
        new InnerC().testC();
    }

    private class InnerB extends B{
        @Override
        public void testB() {
            System.out.println("这是重写之后的testB方法");
        }
    }

    private class InnerC extends C{
        @Override
        public void testC() {
            System.out.println("这是重写之后的testC方法");
        }
    }
}

class B{
    public void testB(){

    }
}

class C{
    public void testC(){

    }
}