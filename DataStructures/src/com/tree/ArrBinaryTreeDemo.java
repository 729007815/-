package com.tree;

public class ArrBinaryTreeDemo {

	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7};
		ArrBinaryTree a = new ArrBinaryTree(arr);
		a.preOrder();
		System.out.println();
		a.infixOrder();
		System.out.println();
		a.postOrder();
	}

}
class ArrBinaryTree{
	private int[] arr;

	public ArrBinaryTree(int[] arr) {
		this.arr = arr;
	}
	public void preOrder() {
		this.preOrder(0);
	}
	//编写一个方法 完成顺序存储二叉树的前序遍历
	public void preOrder(int index) {
		//index数组下标
		//如果数组为空 或者arr.length = 0
		if(arr == null && arr.length == 0) {
			System.out.println("数组为空 不能按照二叉树的前序遍历");
			return;
		}
		System.out.println(arr[index]);// 输出当前元素
		//向左递归遍历
		if((index*2+1) < arr.length) {
			preOrder(index*2+1);
		}if((index*2+2) < arr.length) {
			preOrder(index*2+2);
		}
			
	}
	
	public void infixOrder() {
		this.infixOrder(0);
	}
	//编写一个方法 完成顺序存储二叉树的前序遍历
	public void infixOrder(int index) {
		//index数组下标
		//如果数组为空 或者arr.length = 0
		if(arr == null && arr.length == 0) {
			System.out.println("数组为空 不能按照二叉树的前序遍历");
			return;
		}
		//向左递归遍历
		if((index*2+1) < arr.length) {
			preOrder(index*2+1);
		}
		System.out.println(arr[index]);// 输出当前元素
		if((index*2+2) < arr.length) {
			preOrder(index*2+2);
		}
			
	}
	
	public void postOrder() {
		this.postOrder(0);
	}
	//编写一个方法 完成顺序存储二叉树的前序遍历
	public void postOrder(int index) {
		//index数组下标
		//如果数组为空 或者arr.length = 0
		if(arr == null && arr.length == 0) {
			System.out.println("数组为空 不能按照二叉树的前序遍历");
			return;
		}
		//向左递归遍历
		if((index*2+1) < arr.length) {
			preOrder(index*2+1);
		}if((index*2+2) < arr.length) {
			preOrder(index*2+2);
		}
		System.out.println(arr[index]);// 输出当前元素
			
	}
}
