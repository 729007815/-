package com.hashtable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {

	public static void main(String[] args) {
	String content = "i like like like java do you like a java";
	byte[] contentBytes = content.getBytes();
	System.out.println(contentBytes.length);//40
	List<Node> nodes = getNodes(contentBytes);
	System.out.println(nodes);
	System.out.println("赫夫曼树");
	Node huffmanTreeRoot = creatHuffmanTree(nodes);
	System.out.println("前序遍历");
	preOrder(huffmanTreeRoot);
	getCodes(huffmanTreeRoot);
	System.out.println("赫夫曼编码表"+huffmanCodes);
	}
	//byte 接受字节数组 
	//返回List形式
	private static List<Node> getNodes(byte[] bytes){
		ArrayList<Node> nodes = new ArrayList<Node>();
		//遍历bytes 统计每个Byte出现的次数 map【key,value】
		Map<Byte,Integer> counts = new HashMap<>();
		for(byte b :bytes) {
			Integer count = counts.get(b);
			if(count == null) {
				//map没有该字符数据 第一次
				counts.put(b,1);
			}else {
				counts.put(b,count+1);
			}					
		}
		//把每一个键值对转化成一个Node对象 加入到nodes集合
		//遍历map
		for(Map.Entry<Byte, Integer> entry:counts.entrySet()) {
			nodes.add(new Node(entry.getKey(),entry.getValue()));
		}
		return nodes;
	}
	//通过list创建对应的赫夫曼树
	private static Node creatHuffmanTree(List<Node> nodes) {
		while(nodes.size() > 1) {
			//从小到大
			Collections.sort(nodes);
			Node leftnode = nodes.get(0);
			Node rightnode = nodes.get(1);
			//创建新的二叉树 跟节点没有data只有权值
			Node parent = new Node(null,leftnode.weight+rightnode.weight);
			parent.left = leftnode;
			parent.right  = rightnode;
			nodes.remove(leftnode);
			nodes.remove(rightnode);
			nodes.add(parent);
		}
		//nodes最后的节点就是赫夫曼树的根节点
		return nodes.get(0);
		
	}
	private static void preOrder(Node root) {
		if(root != null) {
			root.preOrder();
		}else {
			System.out.println("赫夫曼树为空");
		}
	}
	//生成赫夫曼树对应的赫夫曼编码
	//思路1.将赫夫曼编码表放在map<byte,string>
	static Map<Byte,String> huffmanCodes = new HashMap<Byte,String>();
	//2.生成赫夫曼编码表 需要拼接路径定义一个StringBuilder存储某个叶子结点的路径
	static StringBuilder stringBuilder = new StringBuilder();
	//重载方法
	private static Map<Byte,String> getCodes(Node root){
		if(root == null) {
			return null;
		}
		//处理左右子树
		getCodes(root.left,"0",stringBuilder);
		getCodes(root.right,"1",stringBuilder);
		return huffmanCodes;
	}
	private static void getCodes(Node node,String code,StringBuilder stringBuilder) {
		//将传入的node节点的所有叶子结点的赫夫曼编码 并放入到huffmanCodes集合
		//node 传入节点
		//code 路径 左子节点为0 右子节点为1
		//StringBuilder 用于拼接路径
		StringBuilder stringBuilder2= new StringBuilder(stringBuilder);
		stringBuilder2.append(code);//将code加入stringBuilder2
		if(node != null) {
			//如果node == null 不处理
			//判断node是否为叶子节点
			if(node.data == null) {
				//非叶子结点
				//递归处理
				//向左
				getCodes(node.left,"0",stringBuilder2);
				//向右
				getCodes(node.right,"1",stringBuilder2);
			}else {
				//叶子节点 表示找到了最后
				huffmanCodes.put(node.data, stringBuilder2.toString());
			}
			
			
			
		}
	}
}	


//创建Node 数据和权值
class Node implements Comparable<Node>{
	Byte data;//存放数据（字符本身） 如'a'=>97 asc码
	int weight;//权值 表示字符出现的次数
	Node left;
	Node right;
	public Node(Byte data, int weight) {
		this.data = data;
		this.weight = weight;
	}

	public int compareTo(Node o) {
		// 从小到大排序
		return this.weight - o.weight;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [data=" + data + ", weight=" + weight + "]";
	}
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
	//通过list创建对应的赫夫曼树
	
}