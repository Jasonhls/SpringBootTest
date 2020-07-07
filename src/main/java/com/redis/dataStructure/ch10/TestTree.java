package com.redis.dataStructure.ch10;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-07 22:52
 **/
public class TestTree {
	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.insert(10, "zhangsan");
		tree.insert(20, "lisi");
		tree.insert(15, "wangwu");
		tree.insert(3, "zhaoliu");
		tree.insert(4, "james");
		tree.insert(90, "tom");

//		System.out.println(tree.root.data);
//		System.out.println(tree.root.rightChild.data);
//		System.out.println(tree.root.rightChild.leftChild.data);
//		System.out.println(tree.root.leftChild.data);
//
//		Node node = tree.find(20);
//		System.out.println(node.data + ":" + node.sData);

		tree.frontOrder(tree.root);
		tree.middleOrder(tree.root);
		tree.afterOrder(tree.root);
	}
}
