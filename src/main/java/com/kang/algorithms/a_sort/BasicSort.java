package com.kang.algorithms.a_sort;
/**
 * 功能：基本排序的实现
 * 1.选择排序的思想：
 * 	对于一个n个元素的数组，通过n趟遍历，在每一趟的遍历中找到最小元素，放到当前最前位置 eg：
 *  在第一次遍历中，初始第一个元素最小，然后进行遍历，找到比第一个元素小的元素的话，交换位置，一趟下来，第一个元素是当前
 *  数组中的最小元素。以此类推
 *  
 *  
 * 2.插入排序的思想：
 *  在前n个元素已经排好序的前提下，拿第n+1个元素与前一个元素对比，如果小于它则交换，然后继续与前边元素做对比，直到大于它为止。
 *  插入排序下边有个优化前的与优化后的，效率相差非常大是因为前一个交换的次数太多，举个栗子：
 *  对 6 , 7 , 8 , 2 排序
 *  当遍历到最后一趟时：我们先用2和8交换顺序，再用2和7交换，又和6交换，而每次交换顺序中，不光要抓取数组的索引，而且还有三步操作，这个过程是非常耗时间的。
 *  优化后：我们将而拿出来赋值到一个临时变量temp上，
 *  用temp和8比较，不行，将8赋值到原来2的位置，在和7,6对比，7,6同时也向后移，到最后，把2放到最前边，这时就没有这个交换这步，效率也就提升了。
 *  
 *  其实仔细想想：不优化前的插入排序，其实就是一个变种的冒泡排序，不过是把当前位置的最小元素浮在最上边
 *  
 * 效率：O(N^2)级别
 * 
 * @user momo
 * 2017年8月20日
 */
public class BasicSort {
	
	//冒泡排序
	public static void bubbleSort(int[] arr) {
		int length = arr.length;
		for(int i = 0; i < length - 1 ; i ++ ) {
			for(int j = 0; j < length - i - 1 ; j ++ ) {
				if(arr[j] > arr[j+1])
					SortTestHelper.swap(arr, j, j + 1);
			}
		}
	}
	
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
	
	//插入排序，对数组中某一部分进行插入排序前闭后闭
	public static void insertionSort(int[] arr, int lo, int hi) {
		for(int i = lo + 1; i <= hi; i ++ ) {
			int temp = arr[lo] ;
			int j;
			for(j = i; j > lo && temp < arr[j - 1] ; j-- ) {
				arr[j] = arr[j - 1];
			}
			arr[j] = temp;
		}
	}
	
	public static void main(String[] args) throws Exception {
		int[] arr1 = SortTestHelper.generateRandomArray(100000, 1, 1000);
		int[] arr2 = SortTestHelper.cloneArr(arr1);
		SortTestHelper.testSort(BasicSort.class, "insertionSort2", arr2);
		
	}
	
	
}

