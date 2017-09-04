package com.kang.dataStructures.tree.binarySearchTree.recursionEditor;
/**
 * @author momo
 * @time 2017年9月4日下午6:32:24
 * 
 * 二分搜索树
 * 优势：高效，不仅可以查找数据，还可以高效的插入删除数据，动态维护数据，可以很方便的回答很多数据之间的关系问题
 * 
 * 定义：
 * 	1.二叉树
 * 	2.每个节点的健值大于左孩子节点
 * 	3.每个节点的健值小于右孩子节点
 * 	4.以左右孩子节点为根的树仍为二叉查找树
 * 	5.不一定为一颗完全二叉树，顾这种结构使用数据保存并不合适，也不方便
 * 
 * 思路：
 * 	1.节点的构造
 * 
 */
public class BinarySearchTree {
	
	private class Node {
		
		public String key;
		public int value;
		public Node left;
		public Node rigth;
		
		// 树中的节点为私有的类, 外界不需要了解二分搜索树节点的具体实现
		public Node(String key, int value) {
			this.key = key;
			this.value = value;
			this.left = this.rigth = null;
		}
	}
	
	
}
