package com.kang.algorithms.a_sort.complex;

import java.util.Arrays;

import com.kang.algorithms.a_sort.BasicSort;
import com.kang.algorithms.a_sort.SortTestHelper;

/**
 * @author momo
 * @time 2017年8月24日上午11:13:40
 * 
 * 自底向上的使用归并排序
 * 1.使用迭代的方式来进行
 * 过程：对 8 7 6 5 4 3 2 1 进行排序
 * 还是使用一个归并的过程：既将两部分有序的数组进行归并，归并为一个有序的数组
 * 对元素组进行划分，划分的大小
 * size = 1 时 ：  8 | 7 | 6 | 5 | 4 | 3 | 2 | 1 
 * 然后分别对相邻的两个部分进行归并： 7 8 5 6 3 4 1 2 继续划分，经过上一次的划分，每相邻的两个数组已经是有序的，继续上面一个过程
 * size = 2 时： 7 8 | 5 6 | 3 4 | 1 2  重复上边的那个过程最终完成排序
 * 
 * 注意：刚开始的时候可以先不用管边界条件，但是我们对哪部分处理自己要记录清楚，最后再统一对边界进行处理
 */
public class MergeSortBU {
	
	//排序对[lo ... mid] 和 [mid + 1 ... hi]进行归并排序 
	public static void sort(int[] arr, int lo, int mid, int hi) {
		// 对归并排序的第二次优化，当需要排序的个数小于某个值的时候，选择插入排序
		if(hi - lo <= 15) {
			BasicSort.insertionSort(arr, lo, hi);
			return;
		}
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
	
	public static void mergeSort(int[] arr) {
		int n = arr.length - 1;
		
		for(int size = 1; size <= n; size += size ) {
			//eg : 对[i..i + size - 1] 和  [i + size,i + size + size -1]进行归并
			for(int i = 0; i + size <= n; i += size + size) {
				sort(arr, i,i + size -1, Math.min(i + size + size -1, n));
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		int[] arr = SortTestHelper.generateRandomArray(1000000, 0, 1000000);
		int[] arr1 = SortTestHelper.cloneArr(arr);
		SortTestHelper.testSort(MergeSortBU.class, "mergeSort", arr);
		SortTestHelper.testSort(MergeSort.class, "mergeSort", arr1);
	}
	
	
	
}
