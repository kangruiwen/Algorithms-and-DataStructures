package com.kang.dataStructures.heap;
/**
 * 功能：最大堆的实现,为了简便使用int型
 * @user momo
 * 2017年8月27日
 * 
 * 
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
	
	public void intsert(int item) {
		if (count == capacity) {
			System.out.println("堆已经满");
			return;
		}
		data[count + 1] = item;
		count ++ ;
		shiftUp(count);
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
	
}










