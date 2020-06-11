package day6_11;

/**
 *
 * 模块设计模式
 *
 */

public abstract class Template {

    public final void getTime(){
        long start = System.currentTimeMillis();
        code();
        long end = System.currentTimeMillis();
        System.out.println("code方法执行的时间："+(end-start));
    }

    public abstract void code();

}

class TestTmp extends Template{

    @Override
    public void code() {
        int k = 0;
        for (int i = 0;i<5000;i++){
            k++;
        }
        System.out.println(k);
    }
}
