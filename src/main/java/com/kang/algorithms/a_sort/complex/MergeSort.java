package com.kang.algorithms.a_sort.complex;

import java.util.Arrays;

import com.kang.algorithms.a_sort.SortTestHelper;

/**
 * @author momo
 * @time 2017年8月23日上午10:34:14
 * 
 * 功能：归并排序的实现 效率： O(N)logN 级别
 * 缺点：以空间换时间
 * 
 * 思路：举个例子吧：
 * 像数组   3 1 4 2 
 * 1.可以向将他们分为两个部分 3 1 | 4 2 
 * 2.然后继续分组为 3 | 1 和   4 | 2
 * 3.然后对 3 | 1 排序，从左边拿第一个元素和右边的第一个元素做对比,拿较小的元素填入原素组中，填完之后，因为3没有可对比的，将3直接填入原数组中
 * 4.在对 4 | 2 排序，这是，原数组左右两边均为已经排好序的元素，重复3...
 * 
 * 注意：我们在排序的时候采取了以空间换时间的策略，每次对一个部分排序的时候，都是先做一个这部分的拷贝，
 * 如   4 | 2
 * 会先拷贝一份 int[] arr = {4,2}
 * 然后使用这部分做对比后，然后填入原数组相应位置，注意，做对比的时候是拿这份拷贝做对比，赋值付给原数组
 */
public class MergeSort {
	
	//排序
	public static void sort(int[] arr, int lo, int mid, int hi) {
		
		int[] tempArr = Arrays.copyOfRange(arr,lo,hi + 1);// Arrays 函数中的参数为 左闭右开区间
		
		int leftIndex = lo, rightIndex = mid + 1; 
		
		for(int i = lo; i <= hi; i ++ ) {
			if ( leftIndex > mid ) {
				arr[i] = tempArr[rightIndex - lo]; rightIndex ++ ; 
			} else if ( rightIndex > hi) {
				arr[i] = tempArr[leftIndex - lo ]; leftIndex ++;
			} else if (tempArr[leftIndex - lo] < tempArr[rightIndex - lo]) {
				arr[i] = tempArr[leftIndex - lo ];  leftIndex ++;
			}else if(tempArr[rightIndex - lo ] <= tempArr[leftIndex - lo]) {
				arr[i] = tempArr[rightIndex -lo ]; rightIndex ++; 
			}
		}
	}
	
	//归并
	public static void merge(int[] arr, int lo , int hi) {
		if(lo >= hi) 
			return ;
		int mid = (lo + hi) / 2;
		merge(arr, lo, mid);
		merge(arr, mid + 1, hi);
		sort(arr, lo, mid, hi);
	}
	
	//综合--左右是闭区间
	public static int[] mergeSort(int[] arr) {
		int lo = 0;
		int hi = arr.length - 1;
		merge(arr, lo, hi);
		return arr;
	}
	
	public static void main(String[] args) throws Exception {
		int[] arr = SortTestHelper.generateRandomArray(100000, 0, 1000);
		SortTestHelper.testSort(MergeSort.class, "mergeSort", arr);
	}
	
}
