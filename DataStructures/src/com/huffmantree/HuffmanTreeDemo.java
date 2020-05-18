package com.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTreeDemo {
	public static void main(String[] args) {
		int arr[] = {13,7,8,3,29,6,1};
		Node node = createHuffmanTree(arr);
		//测试
		preOrder(node);
		
	}
	public static void preOrder(Node root) {
		if(root != null) {
			root.preOrder();
		}else {
			System.out.println("空树无法遍历");
		}
	}
	//创建赫夫曼树的方法
	public static Node createHuffmanTree(int[] arr) {
		//1.为了操作方便 遍历arr 2.将每个元素构建成一个node 3.将node放在ArrayList中
		List<Node> nodes = new ArrayList<Node>();
		for(int value:arr) {
			nodes.add(new Node(value));
		}
		//循环处理
		while(nodes.size() > 1) {
			//排序 从小到大
			Collections.sort(nodes);
			System.out.println("nodes="+nodes);
			//1.取出跟节点权值最小的两颗二叉树节点
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			//2.构建一颗新的二叉树
			Node parent = new Node(leftNode.value + rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;
			//3.删除处理过的二叉树
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			//4.将parent加入到nodes
			nodes.add(parent);
		
			System.out.println("第一次处理后"+nodes);
		}
		//返回赫夫曼树的root节点
		return nodes.get(0);
	}
}

//创建节点类
//为了让Node对象持续排序Collections集合排序
//让Node实现Comparable接口
class Node implements Comparable<Node>{
	int value;//节点值
	char c; //字符
	Node left;//左子节点
	Node right;//右子节点
	//前序遍历
	public void preOrder() {
		System.out.println(this);
		if(this.left != null) {
			this.left.preOrder();
		}
		if(this.right != null) {
			this.right.preOrder();
		}
	}
	public Node(int value) {
		this.value = value;
	}
	
	public String toString() {
		return "Node [value=" + value + "]";
	}

	@Override
	public int compareTo(Node o) {
		//表示从小到大排序
		return this.value - o.value;
	}
	
}
