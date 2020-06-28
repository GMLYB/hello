package day6_28;

public class LeetCode_66 {
    public int[] plusOne(int[] digits) {
        //先加一
        digits[digits.length-1]++;
        //最后一位表示10就返回
        if(digits[digits.length-1] != 10){
            return digits;
        }

        for (int i = digits.length-1; i > 0; i--) {
            //为 10 就 上一位 +1 ，同时变成0
            if (digits[i] > 9){
                digits[i-1]++;
                digits[i] = 0;
            }else {
                //没有 为10 就直接返回
                return digits;
            }
        }
        //第一位不为10直接返回，为10就新建一个数组，长度+1，第一个赋值为1即可
        if (digits[0] != 10){
            return digits;
        }
        int[] ints = new int[digits.length+1];
        ints[0] = 1;
        return ints;
    }

    public static void main(String[] args) {
        LeetCode_66 code_66 = new LeetCode_66();
        int[] digits = {1,8,9};
        int[] ints = code_66.plusOne(digits);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]+" ");
        }

    }
}
