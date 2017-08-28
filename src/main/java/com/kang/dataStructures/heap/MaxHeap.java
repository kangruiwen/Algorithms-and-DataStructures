package com.kang.dataStructures.heap;

import java.util.Date;
import java.util.Random;

/**
 * 功能：最大堆的实现,为了简便使用int型
 * @user momo
 * 2017年8月27日
 * 
 * 最大堆的定义：
 * 1.任意一个节点都不大于他的父亲节点
 * 2.堆总是一颗完全二叉树
 * 所谓的完全二叉树：是指这个二叉树除了最后一层以外，所有层的节点数必须是最大值，既若数深度n从1开始，除最后一层的所有节点数必须为   2^n
 * 如果计算机是向下取整的情况下，完全二叉树的一些性质：
 * parent(i) = i / 2;
 * left child (i) = 2 * i;
 * right child (i) = 2 * i + 1;
 * 
 * 最大堆的实现步骤：
 * 1.定义数组data
 * 2.定义响应构造函数，函数中给出堆的最大值，简便期间不扩展堆
 * 3.定义堆中现有元素大小，既size()函数，堆是否为空
 * 4.向堆中插入元素
 * 5.推出堆中最大元素
 * 6.结束
 * 
 * 另外注意：为使堆中下标与现有元素大小相同，数组中第一个元素不存值  eg：
 * data = [- 62 41 30 28 16 22 13 19 17 15]
 * 对应
 * 				   62
 * 			/             \
 * 		   41             30
 *     /       \      /        \
 *    28       16    22        13 
 *   /  \     / 
 * 19    17  15
 * 
 * 最核心的两个步骤是：shiftUp,shiftDown,既通常说的上浮与下沉
 * 上浮操作：当向堆中新插入一个元素的时候由于破坏 
 *
 */
public class MaxHeap {
	
	private int[] data;
	private int count;
	private int capacity;
	
	public MaxHeap(int catacity) {
		data = new int[catacity + 1];
		count = 0;
		this.capacity = catacity;
	}
	
	public int size() {
		return count;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public void insert(int item) {
		if (count == capacity) {
			System.out.println("堆已经满");
			return;
		}
		data[count + 1] = item;
		count ++ ;
		shiftUp(count);
	}
	
	 // 从最大堆中取出堆顶元素, 即堆中所存储的最大数据
    public int extractMax(){
        int ret = data[1];
        swap( 1 , count );
        count --;
        shiftDown(1);
        return ret;
    }
	
	public int getMax() {
		return data[1];
	}
	
	private void shiftDown(int k) {
		while( 2*k <= count ){
			int j = 2*k; // 在此轮循环中,data[k]和data[j]交换位置
            if( j+1 <= count && data[j+1] > data[j] )
                j ++;
            // data[j] 是 data[2*k]和data[2*k+1]中的最大值
            if( data[k] > data[j] ) break;
            swap(k, j);
            k = j;
        }
	}
	
	private void shiftUp(int k) {
		while(k > 1 && data[k] > data[k/2]) {
			swap(k,k/2);
			k /= 2;
		}
	}
	
	private void swap(int i , int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	 // 测试 MaxHeap
    public static void main(String[] args) {
    	Random rand = new Random(new Date().getTime());

        MaxHeap maxHeap = new MaxHeap(100);
        int N = 100; // 堆中元素个数
        int M = 100; // 堆中元素取值范围[0, M)
        for( int i = 0 ; i < N ; i ++ )
            maxHeap.insert(rand.nextInt(M));

        Integer[] arr = new Integer[N];
        // 将maxheap中的数据逐渐使用extractMax取出来
        // 取出来的顺序应该是按照从大到小的顺序取出来的
        for( int i = 0 ; i < N ; i ++ ){
            arr[i] = maxHeap.extractMax();
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 确保arr数组是从大到小排列的
        for( int i = 1 ; i < N ; i ++ )
            assert arr[i-1] >= arr[i];
    }
	
}

