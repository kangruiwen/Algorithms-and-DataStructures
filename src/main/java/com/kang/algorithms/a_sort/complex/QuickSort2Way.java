 package com.kang.algorithms.a_sort.complex;

import java.util.Date;
import java.util.Random;

import com.kang.algorithms.a_sort.BasicSort;
import com.kang.algorithms.a_sort.SortTestHelper;

/**
 * 功能：双路快速排序法
 * 
 * 排序思路：
 * 
 * -------------------------------------------------------
 * | V |  ...  < V  ...  |.|  ........ |.| ...  > V .... |
 * -------------------------------------------------------
 *   |     小于V的部分                      |             |      大于V的部分        |
 *   lo                   i             j                hi
 *   
 * 在我们的排序中，lo为我们选定的基准temp，其中[lo + 1,...,i)前闭后开是小于V的部分，(j,...hi] 前开后闭是大于V的部分
 * 开始遍历：
 * 1.当 arr[i] < temp 时   i++ , 直到 i >= temp 时停止,既i停止时，arr[i] >= temp;
 * 2.当 arr[j] > temp 时   j-- , 直到 j <= temp 时停止,既j停止时，arr[j] <= temp;
 * 3.然后交换i与j指向的元素再次 i++,j--;
 * 
 * 在这种遍历的时候尤其要注意边界条件，在这里有三个边界条件，既入口条件、出口条件和遍历是数组的边界
 * 
 * 入口条件：开始时i = lo + 1 , j = hi; 这样小于V的部分为[lo + 1,...,lo + 1 )为空，大于V的部分，(hi,hi]也为空，满足
 * 出口条件：前边我们分析过，最后的时候i与j分别指向的是大于temp与小于temp的元素，所以在循环结束的最后我们需要交换lo与j所代表的元素
 * 边界问题：在循环时i要小于等于hi，j要大于等于lo+1
 * 
 * 另外要注意的是：在解决这种问题的时候，可以先不用管具体的边界条件，先把大的排序框架整理清楚，最后处理边界问题。
 * 
 * 双路快速排序法是对快速排序的一个优化
 * 另外它还有一个优化，可看三路快速排序法：QuickSort3Way
 * 
 * @user momo
 * 2017年8月27日
 */
public class QuickSort2Way {
	
	private static Random rand = new Random(new Date().getTime());
	
	public static int partition(int[] arr, int lo, int hi) {
		
		int tempIndex = rand.nextInt(hi - lo + 1) + lo;
		
		SortTestHelper.swap(arr, lo, tempIndex);
		
		int temp = arr[lo];
		
		int i = lo + 1;
		int j = hi;
		while ( true ) {
			while( i <= hi && arr[i] < temp) {
				i++;
			}
			while( j >= lo + 1 && arr[j] > temp) {
				j--;
			}
			if( i > j ) 
				break;
			SortTestHelper.swap(arr,i,j);
			i ++;
			j --;
		}
		SortTestHelper.swap(arr,lo, j);
		return j;
	}
	
	/**
	 * 对[lo ... hi]进行排序，前闭后闭
	 */
	public static void sort(int[] arr,int lo, int hi) {
		if(hi - lo <= 15) {
			BasicSort.insertionSort(arr, lo, hi);
			return;
		}
		
		int sh = partition(arr,lo,hi);
		
		sort(arr,lo,sh - 1);
		sort(arr,sh + 1,hi);
	}
	
	public static void quickSort(int[] arr) {
		int length = arr.length;
		sort(arr,0,length - 1);
	}
	
	public static void main(String[] args) throws Exception {
		
		int[] arr = SortTestHelper.generateRandomArray(10000000, 0, 10);
		
		SortTestHelper.testSort(QuickSort2Way.class, "quickSort", arr);
		
		SortTestHelper.testSort(QuickSort.class, "quickSort", arr);//这个很有可能由于递归过深而导致内存栈长度不够
	}	
	
}
