package August;

import java.util.Arrays;


/*
冬季已经来临。你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
现在，给出位于一条水平线上的房屋和供暖器的位置，找到可以覆盖所有房屋的最小加热半径。
所以，你的输入将会是房屋和供暖器的位置。你将输出供暖器的最小加热半径。

说明:
给出的房屋和供暖器的数目是非负数且不会超过 25000。
给出的房屋和供暖器的位置均是非负数且不会超过10^9。
只要房屋位于供暖器的半径内(包括在边缘上)，它就可以得到供暖。
所有供暖器都遵循你的半径标准，加热的半径也一样。

示例 1:
输入: [1,2,3],[2]
输出: 1
解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。

示例 2:
输入: [1,2,3,4],[1,4]
输出: 1
解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。


 */
public class LeetCode_475 {

    public int findRadius(int[] houses, int[] heaters) {

        Arrays.sort(heaters);
        Arrays.sort(houses);

        int i = 0;
        int radius = 0;

        for (int house : houses){
            //寻找右边的房子
            while (i < heaters.length && heaters[i] < house){
                i++;
            }
            //第一个供暖器在右边  就用第一个供暖器的位置减去 第一个房子的位置
            if (i == 0){
                radius = Math.max(radius,heaters[0] - house);
            }else if (i == heaters.length){
                //最后一个供暖器都不在房子的右边，才会出现 i == heaters.length 情况，直接用最右边的房子减去最后一个供暖器的距离
                return Math.max(radius,houses[houses.length - 1] - heaters[i-1]);
            }else {
                //左右房子两边最短距离 和 之前的最短距离比 取最大值
                radius = Math.max(radius,Math.min(heaters[i] - house,house - heaters[i-1]));
            }
        }
        return radius;
    }


    public static void main(String[] args) {

    }
}
