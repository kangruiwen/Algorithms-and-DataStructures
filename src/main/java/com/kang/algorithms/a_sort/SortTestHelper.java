package com.kang.algorithms.a_sort;

import java.util.Date;
import java.util.Random;

/**
 * 功能：排序的测试用例生成函数
 * @user momo
 * 2017年8月20日
 */
public class SortTestHelper {
	
	/**
	 * @param n 返回的数组大小
	 * @param rangL 随机数的左区间（包含）
	 * @param rangR 随机数的右区间（包含）
	 * @return 返回的数据数组
	 * @throws Exception 
	 */
	public static int[] generateRandomArray(int n , int rangL, int rangR) throws Exception {
		
		if(rangL > rangR){
			throw new Exception();
		}
		
		int[] arr = new int[n];
		Random rand = new Random(new Date().getTime());
		for(int i = 0 ; i < n ; i ++ ) {
			arr[i] = rand.nextInt(rangR - rangL + 1) + rangL ;
		}
		return arr;
	}
	
	/**
	 * 打印数组
	 * @param a
	 */
	public static void printArray(int[] a) {
		for(int i = 0 ; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
	
}
