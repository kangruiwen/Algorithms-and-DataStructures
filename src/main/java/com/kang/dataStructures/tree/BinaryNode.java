package com.kang.dataStructures.tree;
/**
 * 功能：二叉树的节点，为了方便起见，元素使用int型
 * @user momo
 * 2017年8月29日
 */
public class BinaryNode {
	
	public int data;
	public BinaryNode lt;
	public BinaryNode rt;
	
	public BinaryNode(int data,BinaryNode lt,BinaryNode rt) {
		this.data = data;
		this.lt = lt;
		this.rt = rt;
	}
	
	public BinaryNode(int data) {
		this(data, null, null);
	}

}
