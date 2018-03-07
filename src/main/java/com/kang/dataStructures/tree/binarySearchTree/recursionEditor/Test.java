package com.kang.dataStructures.tree.binarySearchTree.recursionEditor;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @company 浙江鸿程计算机系统有限公司
 * @author kangrw
 * @time 2017年11月24日上午9:26:12
 * 
 * 二叉搜索树练习
 * 
 * 
 */
public class Test {
	
	private int count;
	private Node root;
	
	public Test() {
		count = 0;
		root = null;
	}
	
	public int size() {
		return count;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public boolean contains(int key) {
		return contains(root,key);
	}
	
	public String get(int key) {
		return get(root,key);
	}
	
	public void insert(int key,String value) {
		insert(root,new Node(key,value));
	}
	
	public void preOrder() {
		preOrder(root);
	}
	
	public void levelOrder() {
		Queue<Node> queue = new LinkedList<Node>();
		levelOrder(root,queue);
	}
	
	private void levelOrder(Node root,Queue<Node> queue) {
		if(root == null) return;
		System.out.println(root.value );
		queue.offer(root.left);
		queue.offer(root.right);
		
	}
	
	public void preOrder(Node node) {
		if(node == null) return;
		System.out.println(node.value);
		preOrder(node.left);
		preOrder(node.right);
	}
	
	public void insert(Node root,Node node) {
		if(root == null) root = node;
		if(node.key < root.key) {
			insert(root.left,node);
		}
		if(node.key < root.key) {
			insert(root.right,node);
		}
		if(node.key == root.key) {
			root.value = node.value;
		}
	}
	
	private String get(Node node , int key) {
		if(node == null) return null;
		if(key < node.key) 
			get(node.left,key);
		if ( key > node.key) 
			get(node.right,key);
		return node.value;
			
	}
	
	private boolean contains(Node node,int key) {
		if(node == null) return false;
		if(key < node.key) 
			contains(node.left,key);
		if ( key > node.key) 
			contains(node.right,key);
		return true;
	}
	
	private class Node{
		public int key;
		public String value;
		public Node left;
		public Node right;
		
		public Node(int key,String value) {
			this.key = key;
			this.value = value;
			left = right = null;
		}
	}
	
}
