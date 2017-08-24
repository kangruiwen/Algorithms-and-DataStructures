package com.kang.algorithms.a_sort.complex;

import com.kang.algorithms.a_sort.SortTestHelper;

/**
 * @author momo
 * @time 2017年8月24日下午2:04:11
 * 
 * 快速排序
 * 
 */
public class QuickSort {
	
	
	// 对 arr中[lo...hi]部分的元素进行partition，下边这段简洁的代码也是可以的，但是自己并不理解，为什么
	public static int partition1(int[] arr,int lo, int hi) {
		//1.先拿出来基准
		int temp = arr[lo];
		int sh = lo;
		
		// 目标 最终使 [lo ... sh - 1] < temp   &&   [sh + 1 .... j) > temp
		for(int j = lo + 1; j <= hi; j ++ ) {
			if(arr[j] < temp) {
				sh++;
				SortTestHelper.swap(arr, j , sh);
			}
		}
		SortTestHelper.swap(arr, sh , lo);
		
		return sh;
	}
	
	// 对 arr中[lo...hi]部分的元素进行partition,这个是在网上查的，但是明显，这个效率要慢一些
	public static int partition2(int[] arr,int lo, int hi) {
		//1.先拿出来基准
		int temp = arr[lo];
		
		while(lo < hi) {
			while(lo < hi && arr[hi] >= temp) {
				hi--;
			}
			if(lo < hi) 
				arr[lo] = arr[hi];

			while(lo < hi && arr[lo] < temp){
				lo++;
			}
			if(lo < hi) 
				arr[hi] = arr[lo];
		}
		
		arr[lo] = temp;
		return lo;
	}
			
	
	
	private static void sort(int[] arr,int lo, int hi) {
		if(lo >= hi) 
			return;
		// 对 arr中[lo...hi]部分的元素进行partition，并返回partition后的元素应在的位置 
		int mid = partition1(arr,lo,hi);
		sort(arr, lo, mid - 1);
		sort(arr, mid + 1, hi);
	}
	
	public static void quickSort(int[] arr) {
		sort(arr,0,arr.length - 1);
	}
	
	public static void main(String[] args) throws Exception {
		
		int[] arr = SortTestHelper.generateRandomArray(1000000, 0, 100000);
		
		SortTestHelper.testSort(QuickSort.class, "quickSort", arr);
		
	}
	
}
