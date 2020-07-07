package com.redis.dataStructure.ch10;

/**
 * @description: 二叉树
 * @author: helisen
 * @create: 2020-07-07 22:36
 **/
public class Tree {
	/**
	 * 根节点
	 */
	public Node root;

	/**
	 * 插入节点
	 * @param value
	 */
	public void insert(long value, String sData) {
		//封装节点
		Node newNode = new Node(value, sData);
		//引用当前节点
		Node current = root;
		//引用父节点
		Node parent;
		//如果root为null，也就是第一次插入的时候
		if(root == null) {
			root = newNode;
			return;
		}else {
			while(true) {
				//父节点指向当前节点
				parent = current;
				//如果当前指向的节点比插入的要大，则往左走
				if(current.data > value) {
					current = current.leftChild;
					if(current == null) {
						parent.leftChild = newNode;
						return;
					}
				}else {
					current = current.rightChild;
					if(current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	/**
	 * 查找节点
	 * @param value
	 */
	public Node find(long value) {
		//引用当前节点，从跟节点开始
		Node current = root;
		//循环，只要查找的节点不等于当前节点的数据项
		while(current.data != value) {
			if(current.data > value) {
				current = current.leftChild;
			}else {
				current = current.rightChild;
			}
			if(current == null) {
				System.out.println("没有该节点");
				return null;
			}
		}
		return current;
	}

	/**
	 * 删除节点
	 * @param value
	 */
	public void delete(long value) {

	}

	/**
	 * 前序遍历
	 * @param localNode
	 */
	public void frontOrder(Node localNode) {
		if(localNode != null) {
			//访问根节点
			System.out.println(localNode.data + ":" + localNode.sData);
			//前序遍历左子树
			frontOrder(localNode.leftChild);
			//前序遍历右子树
			frontOrder(localNode.rightChild);
		}
	}

	/**
	 * 中序遍历
	 * @param localNode
	 */
	public void middleOrder(Node localNode) {
		if(localNode != null) {
			//中序遍历左子树
			middleOrder(localNode.leftChild);
			//访问根节点
			System.out.println(localNode.data + ":" + localNode.sData);
			//中序遍历右子树
			middleOrder(localNode.rightChild);
		}
	}

	/**
	 * 后序遍历
	 * @param localNode
	 */
	public void afterOrder(Node localNode) {
		if(localNode != null) {
			//后序遍历左子树
			afterOrder(localNode.leftChild);
			//后序遍历右子树
			afterOrder(localNode.rightChild);
			//访问根节点
			System.out.println(localNode.data + ":" + localNode.sData);
		}
	}
}
