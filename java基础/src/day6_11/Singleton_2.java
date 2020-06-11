package day6_11;

/**
 *单例模式：懒汉式，第一次有人调用方法时候，来创建一个new对象，之后再有人调用方法，就直接返回之前第一次new好的对象
 */
class Singleton_2 {

    //将构造器私有化，保证在此类的外部，不能调用的本类的构造器
    private Singleton_2(){
    }
    //先申明类的引用
    private static Singleton_2 instance = null;
    //设置公共方法来访问类的实例
    public static Singleton_2 getInstance(){
        //如果类的实例未创建，那就需要先创建，在返回给调用者，因此需要static修饰
        if(instance == null){
            instance = new Singleton_2();
        }
        return instance;
    }
}
