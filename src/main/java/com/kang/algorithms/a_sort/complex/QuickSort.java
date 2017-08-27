package com.kang.algorithms.a_sort.complex;

import com.kang.algorithms.a_sort.SortTestHelper;

/**
 * @author momo
 * @time 2017年8月24日下午2:04:11
 * 
 * 快速排序
 * partition1 的思路：
 * 
 * [A][....<A][.....>A][C......]
 *  |       |           |
 *  |		|		  C元素遍历到的元素 i
 *  |    这个是A现在
 *  |    应该在的位置lo
 *  |
 * A为基准元素
 * 
 * 如果C < A 则应该将C放在小于A的那个部分，则可以说是要是小于A的那个部分扩大一个单位，所以我们是lo++，然后在和C对应的位置交换元素，则等于说是小于A的部分增加一个单位，而
 * 大于A的部分向右平移一个单位，并且首尾交换了顺序，这时i++，遍历下一个元素，直到最后，并且最后由于，lo指向的是小于A的元素的最末尾下标，我们再和基准交换一下元素，这时基准就在其相应位置了
 * 
 */
public class QuickSort {
	
	
	// 对 arr中[lo...hi]部分的元素进行partition
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
