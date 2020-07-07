package com.redis.dataStructure.ch10;

/**
 * @description: 二叉树节点
 * @author: helisen
 * @create: 2020-07-07 22:33
 **/
public class Node {
	/**
	 * 数据项
	 */
	public  long data;
	/**
	 * 数据项
	 */
	public String sData;
	/**
	 * 左子节点
	 */
	public Node leftChild;
	/**
	 * 右子节点
	 */
	public Node rightChild;

	/**
	 * 构造函数
	 * @param data
	 */
	public Node(long data, String sData) {
		this.data = data;
		this.sData = sData;
	}
}
