package com.kang.dataStructures.tree.binarySearchTree.recursionEditor;

import java.util.LinkedList;

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
 * 说明：
 * 	1.下边很多方法都是一个共有的，然后调用一个私有的重构方法，这样做的目的是对用户完成封装，用户并不需要了解BinarySearchTree里边还有一个Node的结构，
 * 	既BinarySearchTree的内部实现用户不需要了解
 * 	2.深度优先遍历：对于前中后序遍历来说，都是对某一个分支知道找到树根为止，再返回递归的，这样的遍历就叫做深度优先遍历
 * 	3.广度优先遍历：既层序遍历，既对一层一层的进行一个遍历，下边对以某个节点为根的二分搜索树的层序遍历的基本思想做一个说明（依赖于一个队列）：
 * 		3.1 将节点node1插入队列中
 * 		3.2 将节点node1出队并进行相应操作，这里由于是遍历，就只对它进行一个输出操作
 * 		3.3 将节点node1的孩子，分别先左后右的方式进行入队操作
 * 		3.4 出队重复3.2，3.3,3.4步骤，直到最后一层，这样子就完成了层序遍历
 * 		说明：这个思路主要依托于队列的先进先出的数据结构
 *	4.删除的实现（应该是这个部分最难的地方）：
 *		4.1 删除最大、最小值（以最大值为例进行说明）
 *			根据二叉搜索树的性质，一个节点的的值大于其左孩子，小于其右孩子，那么最大值一定是没有右孩子的节点,下边是其伪码
 *				delete(node)  
 *				if(node.right == null){
 *					Node lnode = node.left;
 *					count --;
 *					node.left = null;
 *					return lnode;
 *				}
 *				node.right = delete(node.right);
 *				return node.right;
 * 		4.2 删除任意元素，可看下边的6
 * 
 * 实现步骤：
 * 	1.节点的构造
 * 	2.基础部分的搭建：属性：根节点、计数器；方法：构造方法、返回元素个数方法
 * 	3.insert 的实现，利用二叉树的性质2和3递归实现。同理实现search、contain
 *  4.前中后遍历的实现
 *  5.层序遍历的实现
 *  6.节点的删除
 *  	6.1 节点最大值，最小值的查询
 *  	6.2 最大节点的删除，最小节点的删除 
 *  	6.3 删除左右都有孩子的节点
 *  	这个算法有Hubbard提出，所以也称为Hubbard Deletion
 *  	以删除一个一个key为58的节点为例
 *  	
 *            41
 *           /  \
 *         ...   58
 *             /    \
 *           50      60
 *          /  \    /  \ 
 *         42  53  59  63
 *		我们要删除58，那么就要有一个元素来代替58，这个元素要满足，大于左子数小于右字数，那么右字数中的元素都大于左子树，所以右字数中的最小元素就是这个值
 *		上边这个过程可以总结为，拿掉右子树中最小值，并把这个值赋值给58的那个元素。     
 *
 */
public class BinarySearchTree <Key extends Comparable<Key>, Value> {
	
	private int count;
	private Node root; // 表示当前树的根节点
	
	
	public BinarySearchTree() {
		root = null;
		count = 0;
	}
	
	public int size() {
		return count;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	// 查看二分搜索树中是否存在键key
    public boolean contain(Key key){
        return contain(root, key);
    }

    // 在二分搜索树中搜索键key所对应的值。如果这个值不存在, 则返回null
    public Value search(Key key){
        return search( root , key );
    }
    
	public void insert(Key key,Value value) {
		// 对root节点为根的树添加健值为key-value的节点
		insert(root,key,value);
		count++;
	}
	
	//前序遍历
	public void preOrder() {
		preOrder(root);
	}
	
	//中序
	public void inOrder() {
		inOrder(root);
	}
	
	//后序
	public void postOrder() {
		postOrder(root);
	}
	
	//层序遍历的实现
	public void leaveOrder() {
		// 我们使用LinkedList来作为我们的队列
        LinkedList<Node> q = new LinkedList<Node>();
        q.add(root);
        while( !q.isEmpty() ){
            Node node = q.remove();
            System.out.println(node.key);
            if( node.left != null )
                q.add( node.left );
            if( node.rigth != null )
                q.add( node.rigth );
        }
	}
	
	//最小值的查询，返回key,如果树为空则返回null
	public Key minnum () {
		if(root == null)
			return null;
		return minnum(root).key;
	}
	
	//最大值的查询，返回key，如果树为空则返回null
	public Key maxnum() {
		if(root == null)
			return null;
		return maxnum(root).key;
	}
	
	//删除最小值
	public void deleteMin() {
		if(root == null)
			return;
		root = deleteMin(root);
	}
	
	//删除最大值，并返回最大值的Value
	public void deleteMax(){
		if(root == null)
			return;
		root = deleteMax(root);
	}
	
	//删除任意节点
	public void remove(Key key) {
		if(root == null)
			return;
		root = remove(root,key);
	}
	
	//前序
	private void preOrder(Node node) {
		if(node == null) 
			return;
		//这个算是前序的处理，这里为了简单起见就仅仅进行了输出
		System.out.println(node.value);
		preOrder(node.left);
		preOrder(node.rigth);
	}
	
	//中序
	private void inOrder(Node node) {
		if(node == null) 
			return;
		preOrder(node.left);
		System.out.println(node.value);
		preOrder(node.rigth);
		
	}
	
	//后序
	private void postOrder(Node node) {
		if(node == null) 
			return;
		preOrder(node.left);
		preOrder(node.rigth);
		System.out.println(node.value);
	}
	
	
	//查询最小值
	private Node minnum(Node node) {
		if(node.left == null) 
			return node;
		return minnum(node.left);
	}
	
	//查询最大值
	private Node maxnum(Node node) {
		if(node.rigth == null) 
			return node;
		return maxnum(node.rigth);
	}
	
	// 删除最小值
	// 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
	private Node deleteMin(Node node) {
		
		if(node.left == null ) {
			Node nodeRight = node.rigth;
			node.rigth = null;
			count -- ;
			return nodeRight;
		}
		
		node.left = deleteMin(node.left);
		return node;
		
	}
	
	//删除最大值
	private Node deleteMax(Node node) {

		if(node.rigth == null ) {
			Node nodeLeft = node.left;
			node.left = null;
			count -- ;
			return nodeLeft;
		}
		
		node.rigth = deleteMax(node.rigth);
		return node;
	}
	
	//删除任意元素
	private Node remove(Node node, Key key) {
		
		if(node == null) 
			return null;
		
		if (node.key.compareTo(key) < 0) {
			node.rigth = remove(node.rigth, key);
			return node;
		} else if (node.key.compareTo(key) > 0) {
			node.left = remove(node.left, key);
			return node;
		} else {
			// 待删除节点左子树为空的情况
            if( node.left == null ){
                Node rightNode = node.rigth;
                node.rigth = null;
                count --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if( node.rigth == null ){
                Node leftNode = node.left;
                node.left = null;
                count--;
                return leftNode;
            }
            
            // 待删除节点左右子树均不为空的情况
            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = new Node(minnum(node.rigth));
            count ++;

            successor.rigth = deleteMin(node.rigth);
            successor.left = node.left;

            node.left = node.rigth = null;
            count --;

            return successor;
		}
		
	}
	
	
	private void insert(Node node, Key key, Value value) {
		 
		if(node == null) 
			node = new Node(key,value);
		 
		if(node.key == key) {
			node.value = value;
		}else if(node.key.compareTo(key) < 0) {
			insert(node.rigth,key,value);
		}else{
			insert(node.left,key,value);
		}
	}
	 
	private Value search(Node node, Key key) {
		 
		if(node == null) 
			return null;
		 
		if(node.key.compareTo(key) == 0) {
			return node.value;
		}else if(node.key.compareTo(key) < 0) {
			return search(node.rigth,key);
		}else{
			 return search(node.left,key);
		}
	}
	 
	private boolean contain(Node node, Key key) {
		if(node == null) 
			return false;
		 
		if(node.key.compareTo(key) == 0) {
			return true;
		}else if(node.key.compareTo(key) < 0) {
			return contain(node.rigth,key);
		}else{
			return contain(node.left,key);
		}
	}
	 
	private class Node {
		
		private Key key;
		private Value value;
		private Node left;
		private Node rigth;
		
		// 树中的节点为私有的类, 外界不需要了解二分搜索树节点的具体实现
		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
			this.left = this.rigth = null;
		}
		
		 public Node(Node node){
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.rigth = node.rigth;
        }
	}
}
