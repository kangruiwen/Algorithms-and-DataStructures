package com.kang.dataStructures.tree;

/**
 * 功能：二叉查找树
 * 描述：树的左子节点小于树的根节点，树的右节点大于树的根节点
 * @user momo
 * 2017年8月29日
 */
public class A_BinarySerachTree {
	
	public BinaryNode root;//二叉树查找树的根节点
	
	public A_BinarySerachTree() {
		this.root = null;
	}
	
	/**
	 * 插入元素，返回的插入后元素的根节点，这样可以复合一个链式编程
	 * @param data 所要插入的元素
	 * @param root 要插入的树的根节点
	 * @return
	 */
	public BinaryNode insert(int data,BinaryNode root){
		if(null == root) 
			return new BinaryNode(data);
		if(data < root.data)
			root.lt = insert(data,root.lt);
		if(data > root.data)
			root.rt = insert(data,root.rt);
		else 
			;
			return root;
	}
	
	/**
	 * 递归方式
	 * 找出树中的最大值 
	 * @param root 树的根节点
	 * @return 最大值
	 */
	public int findMax(BinaryNode root) throws Exception {
		if(null == root) 
			throw new Exception("根节点为空！");
		else if(null != root.rt)
			return findMax(root.rt);
		else
			return root.data;
	}
	/**
	 * 递归方式
	 * 找出树中的最小值 
	 * @param root 树的根节点
	 * @return 最小值，如果返回负一则代表树为空
	 */
	public int findMin(BinaryNode root) throws Exception {
		if(null == root) 
			throw new Exception("根节点为空！");
		else if(null != root.lt )
			return findMin(root.lt);
		else 
			return root.data;
	}
	
	public BinaryNode remove(int data,BinaryNode root) throws Exception{
		if(root == null) 
			return root;
		//前边两个if是为了找到与x值相等的那个节点
		if(data < root.data)
			root.lt = remove(data,root.lt);
		else if(data > root.data)
			root.rt = remove(data,root.rt);
		//找到对应节点后若有两个子节点
		else if(root.lt != null && root.rt != null) {
			root.data = findMin(root.rt);//使树上当前节点的值等于右子树最小节点的值，其余不变
			root.rt = remove(root.data,root.rt);//遍历的去掉右子树上找到的那个最小节点
		}else//只有一个节点的情况 
			root = (root.lt != null) ? root.lt : root.rt;
		return root;
	}
}
