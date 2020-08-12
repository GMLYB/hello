package August;

/*
假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。

示例 1:
输入: flowerbed = [1,0,0,0,1], n = 1
输出: True

示例 2:
输入: flowerbed = [1,0,0,0,1], n = 2
输出: False

注意:
数组内已种好的花不会违反种植规则。
输入的数组长度范围为 [1, 20000]。
n 是非负整数，且不会超过输入数组的大小。

 */
public class LeetCode_605 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        // count 统计可以种植的花的数量
        int count = 0;
        // 统计两颗花之间的 0 的数量
        int tmp = 0;
        //用来记录第一朵花的坐标和最后一朵花的坐标
        int head = 0;
        int foot = flowerbed.length - 1;
        //找到第一朵花，看看第一朵花前面有多少个0， 可以种植的花的数量为 tmp / 2 朵花
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0){
                tmp++;
                head++;
            }else {

                break;
            }
        }
        count =  count + tmp / 2;
        tmp = 0;
        //和要求比，要是大于等于要求的，就返回true
        if (count >= n){
            return true;
        }
        //找到最后一朵花，看看最后一朵花后面有多少个0， 可以种植的花的数量为 tmp / 2 朵花
        for (int i = flowerbed.length - 1; i >= 0; i--) {
            if (flowerbed[i] == 0){
                tmp++;
                foot--;
            }else {
                break;
            }
        }
        //要是最后一朵花的位置还小于第一朵花的位置，证明给出的花园中并没有花，直接计算中间有多少个0，然后可以种植的花的数量为 (flowerbed.length+1) / 2 朵花
        if (foot < head){
            count = (flowerbed.length+1) / 2;
            if(count>=n){
                return true;
            }else{
                return false;
            }
        }
        // 最后一朵花的位置大于等于第一朵花的位置，计算最后一朵花距离最后的位置可以放的花的数量
        count =  count + tmp / 2;
        tmp = 0;
        if (count >= n){
            return true;
        }

        // 最后一朵花和第一朵花不是同一朵的情况下 计算中间可以种植的花的数量 两个1之间可以种植的花的数量为 (tmp - 1) / 2 朵
        if (head != foot){
            for (int i = head; i <= foot; i++) {
                if (flowerbed[i] == 0){
                    tmp++;
                }else {
                    count = count + (tmp - 1) / 2;
                    tmp = 0;
                    if (count >= n){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LeetCode_605 code = new LeetCode_605();
//        int[] flowerbed = new int[]{1,0,0,0,1};
        int[] flowerbed = new int[]{0,0};
        int n = 2;
        System.out.println(code.canPlaceFlowers(flowerbed, n));
    }
}
