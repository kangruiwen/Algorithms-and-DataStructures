package com.kang.algorithms.a_sort;
/**
 * 功能：选择排序的实现
 * 选择排序的思想：
 * 	对于一个n个元素的数组，通过n趟遍历，在每一趟的遍历中找到最小元素，放到当前最前位置 eg：
 *  在第一次遍历中，初始第一个元素最小，然后进行遍历，找到比第一个元素小的元素的话，交换位置，一趟下来，第一个元素是当前
 *  数组中的最小元素。以此类推
 * @user momo
 * 2017年8月20日
 */
public class SelectionSort {
	
	public static void sort(int[] arr) {
		int length = arr.length;
		for(int i = 0; i < length - 1 ; i ++ ) {
			int min = arr[i];
			for(int j = i + 1; j < length ; j ++ ) {
				if(arr[j] < min) {
					min = arr[j];
					swap(arr,i,j);
				}
			}
		}
	}
	
	private static void swap(int[] arr,int i,int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main(String[] args) throws Exception {
		int[] a = SortTestHelper.generateRandomArray(100, 0, 50);
		sort(a);
		SortTestHelper.printArray(a);
		System.out.println();
	}
	
	
}





