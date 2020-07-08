package com.redis.dataStructure.ch10;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-07 22:52
 **/
public class TestTree {
	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.insert(100, "zhangsan");
		tree.insert(50, "lisi");
		tree.insert(40, "wangwu");
		tree.insert(20, "zhaoliu");
		tree.insert(45, "james");
		tree.insert(80, "tom");
		tree.insert(60, "tom1");
		tree.insert(90, "tom2");
		tree.insert(120, "tom3");
		tree.insert(110, "tom4");
		tree.insert(105, "tom5");
		tree.insert(115, "tom6");
		tree.insert(150, "tom7");
		tree.insert(130, "tom8");
		tree.insert(200, "tom9");
		tree.insert(42, "tom10");
		tree.insert(48, "tom11");

//		System.out.println(tree.root.data);
//		System.out.println(tree.root.rightChild.data);
//		System.out.println(tree.root.rightChild.leftChild.data);
//		System.out.println(tree.root.leftChild.data);
//
//		Node node = tree.find(20);
//		System.out.println(node.data + ":" + node.sData);

//		tree.frontOrder(tree.root);
//		tree.middleOrder(tree.root);
//		tree.afterOrder(tree.root);

		tree.delete(40);
//		tree.frontOrder(tree.root);
		tree.middleOrder(tree.root);
	}
}
