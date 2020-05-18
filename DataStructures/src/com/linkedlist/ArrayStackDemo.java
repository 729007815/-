package com.linkedlist;

import java.util.Scanner;

public class ArrayStackDemo {

	public static void main(String[] args) {
		ArrayStack a = new ArrayStack(50);
		String key = "";
		boolean loop = true;//控制是否退出菜单
		Scanner scanner = new Scanner(System.in);
		while(loop) {
			System.out.println("show: 显示栈");
			System.out.println("exit: 退出程序");
			System.out.println("push:添加数据到栈");
			System.out.println("pop: 取出数据");
			key = scanner.next();
			switch(key) {

			case "show":
				a.list();
				break;
			case "push":
				System.out.println("输入添加的值");
				int value = scanner.nextInt();
				a.push(value);
				break;
			case "pop":
				try {
					int res = a.pop();
					System.out.println("取出的数据值为"+res);
					
					
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
				break;
			case "exit":
				scanner.close();
				loop = false;
				System.out.println("程序退出");
				break;
			}
				
				
			}
		}
}


class ArrayStack{
	private int maxSize;//栈的大小
	private int[] stack;//数组模拟栈
	private int top = -1;//top表示栈顶 初始化为-1
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[maxSize];
	}
	//栈满
	public boolean isFull() {
		return top == maxSize-1;
	}
	public boolean isEmpty() {
		return top == -1;
	}
	//入栈 push
	public void push(int value) {
		if(isFull()) {
			System.out.println("栈满");
		}
		top++;
		stack[top] = value;
	}
	//出栈
	public int pop() {
		if(isEmpty()) {
			//抛出异常
			throw new RuntimeException("栈空无数据");
		}
		int value = stack[top];
		top--;
		return value;
	}
	//遍历栈 需要从栈顶开始到栈底
	public void list() {
		if(isEmpty()) {
			//抛出异常
			System.out.println("栈空无数据");
			return;
		}
		for(int i=top;i>=0; i--) {
			System.out.println("stack"+i+": "+stack[i]);
		}
	}
}