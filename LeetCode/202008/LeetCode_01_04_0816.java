package August;

/*
    给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
回文串不一定是字典当中的单词。

示例1：
输入："tactcoa"
输出：true（排列有"tacocat"、"atcocta"，等等）

 */
public class LeetCode_01_04 {
    public boolean canPermutePalindrome(String s) {

        //统计 1 的个数
        int count = 0;
        char[] cs = s.toCharArray();
        //128 对应 ascill表的128位
        int[] zm = new int[128];
        for (int i = 0; i < cs.length; i++) {
            //没有这个字母就加 统计 1 的个数也加 1
            if (zm[cs[i]] == 0){
                zm[cs[i]]++;
                count++;
                //有这个字母就减 统计 1 的个数也减一
            }else {
                zm[cs[i]]--;
                count--;
            }
        }
        //偶数的话，要求全部是0 所以统计 1 的个数要为0
        if (cs.length % 2 == 0){
            if (count == 0){
                return true;
            }else {
                return false;
            }
        }
        //奇数的话，要求只有一个 1 ，其他都是0
        if (cs.length % 2 != 0){
            if (count == 1){
                return true;
            }else {
                return false;
            }
        }
        return false;

    }


    public static void main(String[] args) {
        String s = "tomato";
//        char[] cs = s.toCharArray();
        for (Character c : s.toCharArray()){
            System.out.println(c);
        }
        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.charAt(i));
        }
    }
}
