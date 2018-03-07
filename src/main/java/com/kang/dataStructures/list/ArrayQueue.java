package com.kang.dataStructures.list;
/**
 * @author momo
 * @time 2017年8月22日下午4:12:45
 * 
 * 队的数组实现
 */
public class ArrayQueue {
	
	 private int[] mArray;
	 private int mCount;

	 public ArrayQueue(int sz) {
		 mArray = new int[sz];
		 mCount = 0;
	 }

	 // 将val添加到队列的末尾
	 public void add(int val) {
		 mArray[mCount++] = val;
	 }

	 // 返回“队列开头元素”
	 public int front() {
		 return mArray[0];
	 }
	 
	// 返回“栈顶元素值”，并删除“栈顶元素”
	 public int pop() {
		 int ret = mArray[0];
		 mCount--;
		 for (int i=1; i<=mCount; i++)
			 mArray[i-1] = mArray[i];
		 return ret;
	 }

	 // 返回“栈”的大小
	 public int size() {
		 return mCount;
	 }

	 // 返回“栈”是否为空
	 public boolean isEmpty() {
		 return size()==0;
	 }
	 
}
