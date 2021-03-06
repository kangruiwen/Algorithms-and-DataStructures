package com.kang.algorithms.a_sort;

import java.lang.reflect.Method;
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
			if(i != 0 && i % 500 ==0) {
				System.out.println();
			}
		}
		System.out.println();
	}
	
	/**
	 * 对排序方法的性能测试
	 * @param clazz
	 * @param methodName
	 * @param arr
	 * @throws Exception
	 */
	public static void testSort(Class<?> clazz ,String methodName , int[] arr) throws Exception {
		Object obj = clazz.newInstance();
		Method method = clazz.getMethod(methodName, int[].class);
		double startTime = new Date().getTime();
		method.invoke(obj, arr);
		double endTime = new Date().getTime();
		System.out.println("是否排序成功：" + (isSorted(arr) ? "是" : "否"));
		System.out.println("执行时间为：" + (endTime - startTime)/1000d + "s");
	}

	/**
	 * 数组是否排序成功
	 * @param arr
	 */
	public static boolean isSorted(int[] arr) {
		for(int i = 0 ; i < arr.length - 1 ; i ++ ) {
			if(arr[i] > arr[i+1])
				return false;
		}
		return true;
	}
	
	/**
	 * 数组的复制
	 * @param data
	 * @return
	 */
	public static int[] cloneArr(int[] data) {
		int[] resultArr = new int[data.length];
		for(int i = 0; i < data.length; i++ ) {
			resultArr[i] = data[i];
		}
		return resultArr;
	}
	
	/**
	 * 生成近乎有序的数组
	 * @param n 数组的长度
	 * @param swapTimes 打乱的次数
	 * @return
	 */
	public static int[] generateNearlyOrderedArray(int n, int swapTimes) {
		int[] result = new int[n];
		for(int i = 0; i < n; i ++ ) {
			result[i] = i;
		}
		
		Random rand = new Random(new Date().getTime());
		for(int j = 0; j < swapTimes; j ++ ) {
			int posx = rand.nextInt(n);
			int posy = rand.nextInt(n);
			swap(result,posx,posy);
		}
		return result;
	}
	
	/**
	 * 数组元素的交换
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static void swap(int[] arr,int i,int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main(String[] args) {
		System.out.println(3/2);
	}
	
}
