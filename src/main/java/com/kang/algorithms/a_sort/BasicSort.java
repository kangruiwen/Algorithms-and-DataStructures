package com.kang.algorithms.a_sort;
/**
 * 功能：基本排序的实现
 * 1.选择排序的思想：
 * 	对于一个n个元素的数组，通过n趟遍历，在每一趟的遍历中找到最小元素，放到当前最前位置 eg：
 *  在第一次遍历中，初始第一个元素最小，然后进行遍历，找到比第一个元素小的元素的话，交换位置，一趟下来，第一个元素是当前
 *  数组中的最小元素。以此类推
 * 2.插入排序的思想：
 *  在前n个元素已经排好序的前提下，拿第n+1个元素与前一个元素对比，如果小于它则交换，然后继续与前边元素做对比，直到大于它为止。
 * 
 * @user momo
 * 2017年8月20日
 */
public class BasicSort {
	
	//选择排序
	public static void selectSort(int[] arr) {
		int length = arr.length;
		for(int i = 0; i < length - 1 ; i ++ ) {
			int min = arr[i];
			for(int j = i + 1; j < length ; j ++ ) {
				if(arr[j] < min) {
					min = arr[j];
					SortTestHelper.swap(arr,i,j);
				}
			}
		}
	}
	
	
	//插入排序 -- 初级版本，效率偏低
	public static void insertionSort1(int[] arr) {
		int length = arr.length;
		for(int i = 1; i < length; i ++ ) {
			for( int j = i; j > 0 && arr[j] < arr[j - 1] ; j-- ) 
				SortTestHelper.swap(arr,j,j - 1);
		}
	}
	
	//插入排序 -- 升级版本，效率提高
	public static void insertionSort2(int[] arr) {
		int length = arr.length;
		for(int i = 1; i < length; i ++ ) {
			int elem = arr[i];
			int j;
			for( j = i; j > 0 && elem < arr[j - 1] ; j-- ) {
				arr[j] = arr[j-1];
			}
			arr[j] = elem;
		}
	}
	
	
	
	public static void main(String[] args) throws Exception {
		int[] arr1 = SortTestHelper.generateRandomArray(50000, 1, 1000);
		int[] arr2 = SortTestHelper.cloneArr(arr1);
		SortTestHelper.testSort(BasicSort.class, "selectSort", arr1);
		SortTestHelper.testSort(BasicSort.class, "insertionSort2", arr2);
	}
	
	
}

