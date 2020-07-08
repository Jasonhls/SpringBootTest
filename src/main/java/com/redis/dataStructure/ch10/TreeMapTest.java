package com.redis.dataStructure.ch10;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-08 14:49
 **/
public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap<Integer, Object> tree = new TreeMap<>();
        tree.put(100, "zhangsan");
        tree.put(50, "lisi");
        tree.put(40, "wangwu");
        tree.put(20, "zhaoliu");
        tree.put(45, "james");
        tree.put(80, "tom");
        tree.put(60, "tom1");
        tree.put(90, "tom2");
        tree.put(120, "tom3");
        tree.put(110, "tom4");
        tree.put(105, "tom5");
        tree.put(115, "tom6");
        tree.put(150, "tom7");
        tree.put(130, "tom8");
        tree.put(200, "tom9");
        tree.put(42, "tom10");
        tree.put(48, "tom11");


        //treeMap默认采用了二叉树的中序遍历
//        printTreeMap(tree);

        tree.remove(50);
        printTreeMap(tree);

    }

    /**
     * treeMap默认采用了二叉树的中序遍历
     */
    private static void printTreeMap(TreeMap<Integer, Object> tree) {
        Iterator<Map.Entry<Integer, Object>> iterator = tree.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Object> next = iterator.next();
            System.out.print(next.getKey() + ":");
            System.out.println(next.getValue());
        }
    }
}
