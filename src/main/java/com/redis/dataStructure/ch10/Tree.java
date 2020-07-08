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
	 *  删除节点
	 * 删除节点存在 3 种情况，这 3 种情况分别如下：
	 * 1) 该节点是叶子节点，也就是说没有左右子节点，可以直接删除
	 * 2) 该节点存在左节点或者右节点，删除后需要对子节点移动
	 * 3) 该节点同时存在左右子节点，不能简单的删除，但是可以通过和后继节点交换后转换为前两种情况
	 * @param value
	 */
	public void delete(long value) {
		Node current = root;
		if(current.data == value) {
			root = null;
			return;
		}

		Node parent = current;
		while(current.data != value) {
			parent = current;
			if(current.data > value) {
				current = current.leftChild;
			}else if(current.data < value){
				current = current.rightChild;
			}
			if(current == null) {
				System.out.println("该节点不存在");
				return;
			}
		}
		//到了这里，current就是该节点
		//1）如果是叶子节点
		if(current.leftChild == null && current.rightChild == null) {
			//左子节点
			if(parent.leftChild == current) {
				parent.leftChild = null;
			}else {
				//右子节点
				parent.rightChild = null;
			}
		}else if(current.leftChild != null && current.rightChild == null) {
			//2)如果是左子节点不能为空，右子节点为空
			//左子节点
			if(parent.leftChild == current) {
				parent.leftChild = current.leftChild;
			}else {
				//右子节点
				parent.rightChild = current.leftChild;
			}
		}else if(current.leftChild == null && current.rightChild != null) {
			//3)如果是左子节点为空，右子节点不为空
			if(parent.leftChild == current) {
				parent.leftChild = current.rightChild;
			}else {
				parent.rightChild = current.rightChild;
			}
		}else {
			//4)如果左右子节点都不为空，这里就分用左子树中最大的那个节点替换被删除的节点，或用右子树中最小的那个节点替换被删除的节点
			//这里采用寻找右子树中最小的那个节点去替换被删除节点的位置
			Node c;
			if(parent.leftChild == current) {
				c = findRightMinNode(current);
				parent.leftChild = c;
			}else {
				c = findRightMinNode(current);
				parent.rightChild = c;
			}
			c.leftChild = current.leftChild;
			c.rightChild = current.rightChild;
		}
	}

	/**
	 * 查找右子树中最小节点并返回
	 * @param current
	 * @return
	 */
	private Node findRightMinNode(Node current) {
		Node c = current.rightChild;
		Node p = c;
		while (c.leftChild != null) {
			p = c;
			c = c.leftChild;
		}
		//更新右子树中最小节点的父节点的左节点
		if (c.rightChild != null) {
			p.leftChild = c.rightChild;
		} else {
			p.leftChild = null;
		}
		return c;
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
