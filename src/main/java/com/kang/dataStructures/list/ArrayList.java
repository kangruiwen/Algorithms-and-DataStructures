package com.kang.dataStructures.list;

import java.util.Arrays;

/**
 * @author momo
 * @time 2017年8月22日下午4:08:00
 * 
 * 顺序表的实现
 * 思路：
 * 1.基本储存单元  使用数组
 *   构造函数中 ArrayList(length):表示list中可以保存多少个元素
 * 2.CRUD的实现 ,并且为了简单起见
 */
public class ArrayList {
	
	private static final int ARRAYSIZE = 32;
	
	private Object[] data;//用来保存数据
	
	private int size;
	
	public ArrayList() {
		this(ARRAYSIZE);
	}
	
	public ArrayList(int length) {
		data = new Object[length];
	}
	
	//新增元素
	public void add(Object obj) {
		boolean b = checkCapacity();
		if(!b) {
			data = Arrays.copyOf(data, data.length * 2);
		}
		data[size ++ ] = obj;
	}
	
	//在指定位置处增加元素
	public void add(int index, Object obj) {
		boolean b = checkCapacity();
		if(!b) {
			data = Arrays.copyOf(data, data.length * 2);
		}
		int tempCount = index;
		int length = data.length;
		Object[] newData = new Object[length+1];
		
		for(int i = 0 ; i < index ; i ++ ) {
			newData[i] = data[i];
		}
		newData[index] = obj;
		tempCount++;
		for(int j = index; j < length; j ++ ) {
			newData[tempCount++] = data[j];
		}
	}
	
	//查找指定位置处的元素，index是下标
	public Object get(int index) {
		if(index >= data.length) {
			return null;
		}
		return data[index];
	}
	
	// 修改指定位置处的元素
	public Object set(int index,Object obj) {
		if(index >= data.length) {
			Object[] newDate = Arrays.copyOf(data, index*2 -1);
			newDate[index] = obj;
			data = newDate;
			return null;
		}
		Object old = data[index];
		data[index] = obj;
		return old;
	}
	
	//删除指定位置处的元素
	public Object remove(int index) {
		Object returnData = data[index];
		int length = size();
		int copySize = 0;
		Object[] copyData = new Object[data.length];
		for(int i = 0; i < index; i ++ ) {
			copyData[i] = data[i];
			copySize ++ ;
		}
		for(int j = index + 1; j < length; j ++ ) {
			copyData[copySize -1] = data[j];
			copySize ++;
		}
		this.size = copySize;
		this.data = copyData;
		return returnData;	
	}
	
	//toString 方法重写
	public String toString() {
		String resultStr = "list = [";
		for(int i = 0 ; i < size - 1 ; i ++ ) {
			resultStr += "[ " + data[i].toString() + " ]" + ","; 
		}
		resultStr += "[ " + data[size-1].toString() + " ]]"; 
		return resultStr;
	}
	
	/**
	 * 计算顺序表中有多少个元素
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 检查是否超容
	 * @return
	 */
	private boolean checkCapacity() {
		if(size >= data.length ) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		ArrayList arr = new ArrayList(2);
		arr.add(1);
		arr.add(2);
		arr.add(3);
		System.out.println(arr.toString());
	}
}
