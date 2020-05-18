package com.linkedlist;

import java.util.Stack;

//演示栈stack的基本使用 先进后出
public class TestStack {
	public static void main(String[] args) {
		Stack<String> stack = new Stack();
		//入栈
		stack.add("剑姬");
		stack.add("鱼人");
		stack.add("癞蛤蟆");
		stack.add("给爷爬");
		//出栈
		while(stack.size() > 0) {
			System.out.println(stack.pop());//将栈中所有数据取出
		}
	}
}
