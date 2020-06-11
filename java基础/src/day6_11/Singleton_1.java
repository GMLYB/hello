package day6_11;

/**
 * 单例模式：饿汉式，在类加载之后，还没有人调用的时候，就先new好一个对象，以后无论谁调用方法，都直接返回new好的那个对象
 */
class Singleton_1 {
    //设置为私有属性，不允许外部创建该类的对象
    private Singleton_1(){
    }
    //先创建好对象
    private static Singleton_1 instance = new Singleton_1();
    //static，直接类名调用
    public static Singleton_1 getInstance(){
        return instance;
    }

}

