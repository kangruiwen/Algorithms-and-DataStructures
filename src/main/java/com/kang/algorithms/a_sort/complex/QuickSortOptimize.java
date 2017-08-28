package com.kang.algorithms.a_sort.complex;

import java.util.Date;
import java.util.Random;

import com.kang.algorithms.a_sort.BasicSort;
import com.kang.algorithms.a_sort.SortTestHelper;

/**
 * @author momo
 * @time 2017年8月24日下午2:04:11
 * 
 * 对快速排序的优化：
 * 
 * 1.由于当数组近乎有序的情况下，每次我们取基准的时候，可能某一方面（大于基准或小于基准）往往会占据绝大多数的元素，最坏的情况，递归树会退化为一个链表，从而整个方法的时间复杂度退化为O(N^2)级别
 * 所以我们优化为随机的在数组中选择基准，这样从统计意义上讲，时间复杂度的期望为O(NlogN)级别
 * 
 * 2.复杂的算法在最后几步优化为插入排序
 * 
 * 3.这是第一步优化，但是这个算法仍会在某种情况下退化，如果在数组中有大量的重复元素，例如：100W个元素，元素范围在0到10之间，这时算法复杂度一样会出现退化，退化的原因与上一节相同，同样是因为每次
 * 的分配都严重不均衡，从而导致理想中的平衡二茶树退化为链表，对这样的问题，我们使用双路快速排序法进行解决可看：QuickSort2Way
 * 
 */
public class QuickSortOptimize {
	
	private static Random rand = new Random(new Date().getTime());
	
	public static int partition(int[] arr,int lo, int hi) {
		
		int tempIndex = rand.nextInt(hi - lo) + lo + 1;
		
		//1.第一个优化，随机取一个基准出来
		SortTestHelper.swap(arr, lo, tempIndex);
		int temp = arr[lo];
		int sh = lo;
		
		for(int j = lo + 1; j <= hi; j ++ ) {
			if(arr[j] < temp) {
				sh++;
				SortTestHelper.swap(arr, j , sh);
			}
		}
		SortTestHelper.swap(arr, sh , lo);
		
		return sh;
	}
	
	private static void sort(int[] arr,int lo, int hi) {
		//2.第二个优化
		if(hi - lo <= 15) {
			BasicSort.insertionSort(arr, lo, hi);
			return;
		}
			
		
		int mid = partition(arr,lo,hi);
		sort(arr, lo, mid - 1);
		sort(arr, mid + 1, hi);
	}
	
	public static void quickSort(int[] arr) {
		sort(arr,0,arr.length - 1);
	}
	
	public static void main(String[] args) throws Exception {
		
		int[] arr = SortTestHelper.generateRandomArray(1000000, 0, 1000);
		
		SortTestHelper.testSort(QuickSortOptimize.class, "quickSort", arr);
		
	}
	
}
