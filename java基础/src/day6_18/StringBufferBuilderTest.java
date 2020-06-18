package day6_18;

import org.junit.Test;

/**
 * 源码分析：
 * String str = new String();//char[] value = new char[0];
 * String str1 = new String("abc");//char[] value = new char[]{'a','b','c'};
 *
 * StringBuffer sb1 = new StringBuffer();//char[] value = new char[16];//底层创建了一共长度为16的数组
 * sb1.append('a');//value[0] = 'a';
 * sb1.append('b');//value[1] = 'b';
 * StringBuffer sb2 = new StringBuffer("abc");//char[] value = new char["abc".length()+16];
 * 问题1：System.out.println(sb2.length());//3
 * 问题2：扩容问题：如果要添加的数据底层数组盛不下了，那就需要扩容底层数组。
 *      默认情况下，扩容为原来容量的2倍+2，同时将原有数组中的元素复制到新的数组中
 *
 *      指导意义：开发中建议大家使用：StringBuffer(int capacity)或者StringBuilder(int capacity)
 *
 *      对比String、StringBuffer、StringBuilder三者的效率：
 *      从高到底：StringBuilder > StringBuffer > String
 *
 */


public class StringBufferBuilderTest {
    public static void main(String[] args) {
        //初始化设置
        long startTime = 0L;
        long endTime = 0L;
        String text = "";
        StringBuffer buffer = new StringBuffer("");
        StringBuilder builder = new StringBuilder("");
        //开始对比
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            buffer.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer的执行时间："+(endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            builder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder的执行时间："+(endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            text += i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("String的执行时间："+(endTime - startTime));

    }

    @Test
    public void testStringBuffer(){
        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);

        System.out.println(sb.length());//4

        System.out.println(sb);//"null"

        StringBuffer sb1 = new StringBuffer(str);//空指针异常

        System.out.println(sb1.length());//

    }
}
