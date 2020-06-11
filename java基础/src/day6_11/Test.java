package day6_11;

public class Test {
    public static void main(String args[]){
        Singleton_1 s1 = Singleton_1.getInstance();
        Singleton_1 s2 = Singleton_1.getInstance();

        if(s1 == s2){
            System.out.println("s1 == s2");
        }else {
            System.out.println("s1 != s2");
        }

        Singleton_2 s3 = Singleton_2.getInstance();
        Singleton_2 s4 = Singleton_2.getInstance();
        if(s3 == s4){
            System.out.println("s3 == s4");
        }else {
            System.out.println("s3 != s4");
        }


        TestTmp t = new TestTmp();
        t.getTime();




    }


}
