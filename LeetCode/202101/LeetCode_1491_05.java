package com.lyb;
/*
 * 1491. 去掉最低工资和最高工资后的工资平均值
	给你一个整数数组 salary ，数组里每个数都是 唯一 的，其中 salary[i] 是第 i 个员工的工资。
	请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。

	示例 1：
	输入：salary = [4000,3000,1000,2000]
	输出：2500.00000
	解释：最低工资和最高工资分别是 1000 和 4000 。
	去掉最低工资和最高工资以后的平均工资是 (2000+3000)/2= 2500
	示例 2：
	
	输入：salary = [1000,2000,3000]
	输出：2000.00000
	解释：最低工资和最高工资分别是 1000 和 3000 。
	去掉最低工资和最高工资以后的平均工资是 (2000)/1= 2000
	示例 3：
	
	输入：salary = [6000,5000,4000,3000,2000,1000]
	输出：3500.00000
	示例 4：
	
	输入：salary = [8000,9000,2000,3000,6000,1000]
	输出：4750.00000
	 
	
	提示：
	
	3 <= salary.length <= 100
	10^3 <= salary[i] <= 10^6
	salary[i] 是唯一的。
	与真实值误差在 10^-5 以内的结果都将视为正确答案。
 * 
 * 
 */
public class LeetCode_1491 {

	public static void main(String[] args) {
		LeetCode_1491 code = new LeetCode_1491();
		int[] salary = new int[]{48000,59000,99000,13000,78000,45000,
		                          31000,17000,39000,37000,93000,77000,
		                          33000,28000,4000,54000,67000,6000,1000,11000};
		double average = code.average(salary);
		System.out.println(average);
		
	}

	public double average(int[] salary) {

		int max = 0;
		int min = 999999;
		double sum = 0;
		for (int i = 0; i < salary.length; i++) {
			sum += salary[i];
			if (min > salary[i]) {
				min = salary[i];
			}
			if (max < salary[i]) {
				max = salary[i];
			}
		}
		sum = sum - max - min;
		return sum / (salary.length - 2);
	}
}
