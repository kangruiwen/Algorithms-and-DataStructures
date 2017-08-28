package com.kang.algorithms.a_sort.complex;

import com.kang.algorithms.a_sort.SortTestHelper;

/**
 * 功能：三路快速排序法
 * @user krw
 * 2017年8月27日
 * 
 * 总结一下：边界条件难处理
 */
public class QuickSort3Way {
	
	public static void sort(int[] arr, int lo , int hi) {
		//1. 初始化情况
		//[lo+1 ... eq) < v ;  [eq ... i) == v ; (j ... hi] > v; [i ... j]为现在正在遍历的
		int eq = lo + 1;
		int i = lo + 1;
		int j = hi;
		int temp = arr[lo];
		while(i <= j) {
			if(arr[i] < temp) {
				SortTestHelper.swap(arr, i, eq);
				eq ++ ;i ++ ;
			}else if (arr[i] > temp) {
				SortTestHelper.swap(arr, i, j);
				j--;
			}else{
				i++;
			}
		}
		SortTestHelper.swap(arr, lo, eq - 1);
		eq--;
		sort(arr, lo, eq - 1);
		sort(arr,i,hi);
	}
	
	public static void quickSort(int[] arr) {
		int length = arr.length;
		sort(arr,0,length - 1);
	}
	
	public static void main(String[] args) throws Exception {
		
		int[] arr = SortTestHelper.generateRandomArray(1000, 0, 10);
		
		SortTestHelper.testSort(QuickSort3Way.class, "quickSort", arr);
		
		SortTestHelper.printArray(arr);
		
	}	
	
	
}
