package com.kang.dataStructures.tree.binarySearchTree.recursionEditor;
/**
 * @author momo
 * @time 2017年9月4日下午5:08:46
 * 
 * 二分查找法(递归版本)：
 * 限制：只能对于有序的数列使用二分查找法
 * 目的：查找指定元素下标位置，没有则返回-1
 * eg：查找target = 8
 * 1 2 3 4 5 6 7 8 9 
 * 思路：
 * 1. 第一次取出中间元素5，与target进行对比
 * 2. 5 < 8 取数组右边部分，重复1。如果找到元素=target则返回，最后找不到返回-1
 * 
 * 
 * 对floor与ceil方法得说明
 * 如果存在一个数组
 *  .....  < V ..... | ... == V .... | .... > V ....
 * 查询在target = V 的元素下标，由于V有重复元素，这样如果使用binarySearch方法的话是能返回一个 =V 元素的正确下标，但是并不确定是哪一个
 * floor方法指的是找到 = V 的最小下标，ceil方法指的是找到 = V 的最大下标，如果没有=V的，floor返回不大于V元素的的最大下标，ceil返回大于V元素的最小下标
 * 这一部分待完成
 */
public class BinarySearch {
	
	public static int binarySearch(int[] arr, int target) {
		//[l..r]中查找target
		return binarySearch(arr ,0 ,arr.length - 1 ,target);
	}
	
	
	public static int floor(int[] arr,int target) {
		return 0;
	}

	public static int ceil(int[] arr, int target) {
		return 0;
	}
	
	private static int binarySearch(int[] arr, int l , int r , int target) {
		
		// int mid = (l + r) / 2   这种除法是会出错的，因为l + r 可能会造成int的越界，因为 int的范围是 -2^32 ~ 2^32 - 1这样的一个范围
		int mid = l + (r - l) / 2;
		//递归出口
		if(mid < 0 || mid >= arr.length)
			return -1;
		if(arr[mid] == target) {
			return mid;
		} else if (arr[mid] > target) {
			return binarySearch(arr,l,mid - 1,target);
		} else { // arr[mid] < target
			return binarySearch(arr,mid + 1,r,target);
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9};
		System.out.println(binarySearch(arr,8));
	}
	
}
