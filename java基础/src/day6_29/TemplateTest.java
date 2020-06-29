package day6_29;

/**
 * 抽象类的应用：模本方法的设计模式 示例1
 */

public class TemplateTest {
    public static void main(String[] args) {

        Template t = new SubTemplate();
        t.spenfTime();
    }

}


abstract class Template{


    //计算某段代执行需要花费的时间
    public void spenfTime(){
        long start = System.currentTimeMillis();

        code();//不确定部分，易变部分

        long end = System.currentTimeMillis();

        System.out.println("花费的时间为："+(end - start));

    }

    public abstract void code();
}



class SubTemplate extends Template{

    //例如：计算1000以内的质数
    @Override
    public void code() {

        for (int i = 2; i <= 1000; i++) {
            boolean isFlag = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if(i % j ==0){
                    isFlag = false;
                    break;
                }
            }
            if (isFlag){
                System.out.println(i);
            }
        }
    }
}