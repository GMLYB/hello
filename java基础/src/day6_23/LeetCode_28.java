package day6_23;

/*
实现 strStr() 函数。
给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

示例 1:
输入: haystack = "hello", needle = "ll"
输出: 2

示例 2:
输入: haystack = "aaaaa", needle = "bba"
输出: -1

说明:
当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。

 */

public class LeetCode_28 {

    public int strStr(String haystack, String needle) {

        if(needle.length() == 0){
            return 0;
        }
        if (haystack.length() == 0 || haystack.length() < needle.length()){
            return -1;
        }

        if(needle.length() == 1){
            if (haystack.charAt(0) == needle.charAt(0)){
                return 0;
            }
        }
        int index = -1;
        int j = 1;

        for (int i = 0; i < haystack.length(); i++) {
//            System.out.println("外部的haystack:"+haystack.charAt(i));
            if(index == -1){
                if(haystack.charAt(i) == needle.charAt(0)){
                    index = i;
                }
            }else {
                System.out.println("index = "+index);
                System.out.println("haystack = "+haystack.charAt(i)+" ; needle = "+needle.charAt(j));
                if (haystack.charAt(i) != needle.charAt(j++)){
                    i = index;
                    index = -1;
                    j = 1;
                }
                if(j == needle.length()){
                    return index;
                }
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        String haystack = "mississippi";
        String needle = "issipi";
        LeetCode_28 code_28 = new LeetCode_28();
        int i = code_28.strStr(haystack, needle);
        System.out.println(i);
    }

}
