package day6_17;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class StringTest2 {

    String str = new String("good");
    char[] ch = {'t','e','s','t'};

    public void Change(String str,char ch[]){
        str = "test ok";
        ch[0] = 'b';
    }

    public static void main(String[] args) {
        StringTest2 ex = new StringTest2();
        ex.Change(ex.str,ex.ch);
        System.out.println(ex.str);//good
        System.out.println(ex.ch);//best
    }

    @Test
    public void test(){
        String s1 = "javaEEhadoop";
        String s2 = "javaEE";
        String s3 = s2 + "hadoop";

        System.out.println(s1 == s3);//false

        final String s4 = "javaEE";//final-->变成常量
        String s5 = s4 + "hadoop";
        System.out.println(s1 == s5);//true

    }

    /**
     * 1.模拟一个trim方法，去除字符串两端的空格
     */
    @Test
    public void test2(){
        String s1 = "  lkdjflskfjl kj sdkfjl  ";
        int start = 0;
        int end = 0;
        char[] arr = s1.toCharArray();
        for (int i = 0; i < s1.length(); i++) {
            if(arr[i] != ' '){
                start = i;
                break;
            }
        }
        for (int i = s1.length()-1; i >=0; i--) {
            if(arr[i] != ' '){
                end = i;
                break;
            }
        }
        String laststr = "";
        for (int i = start; i <= end; i++) {
            laststr += arr[i];
        }
        System.out.println("notr:"+laststr);
        System.out.println("trim:"+s1.trim());
    }

    /**
     * 2.将一个字符串进行反转。将字符串中指定部分进行反转。比如"abcdefg"反转为"abfedcg"
     */
    public String reverse(String str,int startIndex,int endIndex){

        char[] arr = str.toCharArray();
        for(int x = startIndex,y = endIndex;x < y;x++,y++){
            char temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
        }
        return new String(arr);
    }

    @Test
    public void test3(){
        String s = "abcdefg";//0-6
        int start = 2;
        int end  = 5;
        StringBuilder laststr = new StringBuilder(s.length());
        laststr.append(s.substring(0,start));
        for (int i = end; i >= start; i--) {
            laststr.append(s.charAt(i));
        }
        laststr.append(s.substring(end+1));
        System.out.println("last:"+laststr.toString());
    }

    /**
     * 3.获取一个字符串在另外一个字符串出现的次数
     * 比如获取:"ab"在"abkkcadkabkebfkabkskab"中出现场的次数
     */
    public int getCount(String mainStr,String subStr){
        int count =0;
        int index = 0;
        if(mainStr.length() >= subStr.length()){
            //方式一
//            while ((index = mainStr.indexOf(subStr)) != -1){
//                count++;
//                mainStr = mainStr.substring(index+subStr.length())
//            }

            while ((index = mainStr.indexOf(subStr,index)) != -1){
                count++;
                index += subStr.length();
            }
        }
        return count;
    }


    @Test
    public void test4(){
        String mob = "abkkcadkababakeabbfkabkskab";
        String ti = "ab";
        int ci = 0;
        boolean pd = true;
        for (int i = 0; i < mob.length(); i++) {
            if(ti.charAt(0) == mob.charAt(i)){
                for (int j = 0; j < ti.length(); j++) {
                    if(ti.charAt(j) != mob.charAt(i+j)){
                        pd = false;
                    }
                }
                if(pd){
                    ci++;
                }else {
                    pd = true;
                }
            }
        }
        System.out.println(ci);
    }

    /**
     * 4.获取两个字符串中最大相同的子串。比如：
     * str1 = "abcwerthelloyuiodef";str2 = "cvhellobnm"
     * 提示：将短的那个字符串进行长度依次递减的子串与较长的串比较
     */
    public String getMaxSameString(String str1,String str2){
        String maxStr = (str1.length() >= str2.length())? str1 : str2;
        String minStr = (str1.length() < str2.length())? str1 : str2;
        int length = minStr.length();
        for (int i = 0; i < length; i++) {

            for (int x = 0,y = length - i; y <= length ; x++,y++) {
                String subStr = minStr.substring(x, y);
                if(maxStr.contains(subStr)){
                    return subStr;
                }
            }
        }
        return null;
    }

    @Test
    public void test5(){
        String str1 = "abcwerthelloyuiodef";
        String str2 = "cvhellobnm";
        String maxSameString = getMaxSameString(str1, str2);
        System.out.println(maxSameString);

    }

    /**
     * 5.对字符串中字符进行自然顺序排序
     * 提示：
     * 1)字符串变成字符数组
     * 2)对数组排序，选择，冒泡，Arrays.sort()
     * 3)将排序后的数组变成字符串
     */
    @Test
    public void test6(){
        String str = "dasfskdljasdjkf";

        char[] arr = str.toCharArray();

        Arrays.sort(arr);

        String lstr = "";
        for (char c:arr) {
            lstr += c;
        }
        System.out.println(lstr);
    }

}
