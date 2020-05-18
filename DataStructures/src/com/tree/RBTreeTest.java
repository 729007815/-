package com.tree;

import java.util.Scanner;

public class RBTreeTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		RedBlackTree<String,Object> rbt = new RedBlackTree();
		while(true) {
			System.out.println("请输入k");
			String key = scanner.next();
			System.out.println();
			rbt.insert(key,null);
			rbt.inOrderPrint(rbt.getRoot());
		}

	}

}
