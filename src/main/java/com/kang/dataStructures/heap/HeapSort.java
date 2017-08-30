package com.kang.dataStructures.heap;

import java.util.Date;
import java.util.Random;

import com.kang.algorithms.a_sort.SortTestHelper;

/**
 * @author momo
 * @time 2017年8月30日上午10:07:23
 * 不使用额外的空间，对数据进行原地的堆排序
 * 
 * 这样的话就对堆中数组有以下两个要求
 * 1.堆中数组下标从0开始
 * 2.不适用额外的数组空间
 */
public class HeapSort {
	
	public static void heapSort(int[] arr) {
		int count = arr.length - 1; // count 为元素个数 - 1 ，其实表示的为最后元素下标
		// 如果堆中下标从0开始，可以总结一下规律
		// father(i) = (i-1)/2
		// left child (i) = i * 2 + 1
		// right child (i) = i * 2 + 1 + 1
		
		//1. 构建最大堆
		for(int i = (count - 1) / 2; i >= 0 ; i -- ) {
			shiftDown(arr,i,count);
		}
		
		//2. 取出第一个大元素，然后继续heapify
		for( int i = count; i > 0 ; i-- ){
            SortTestHelper.swap( arr, 0, i);
            shiftDown(arr, 0, i - 1);
        }
	}
	
	// 下标为0时，调整后的shiftDown
	private static void shiftDown(int[] arr , int i, int count) {
		while((2 * i + 1) <= count ) {
			int j = 2 * i + 1; //j 代表我们要交换的元素
			if(j + 1 <= count && arr[j+1] > arr[j]) {
				j ++;
			}
			if(arr[i] >= arr[j]) 
				break;
			SortTestHelper.swap(arr, i, j);
			i = j;
		}
	}
	
	public static void main(String[] args) {
		
		Random rand = new Random(new Date().getTime());
		int[] att = new int[100];
		
		for(int i = 0; i < 100; i ++ ) {
			att[i] = rand.nextInt(100);
		}
		
		HeapSort.heapSort(att);
		
		for(int i = 0 ; i < 100; i ++ ) {
			System.out.print(att[i] + " ");
		}
	}
	
}
