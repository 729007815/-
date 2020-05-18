package com.linkedlist;

public class Calculator {
	public static void main(String[] args) {
		String expression = "5-2*3+1";
		//定义两个栈 一个存放数 一个存放运算符
		ArrayStack2 numstack = new ArrayStack2(10);
		ArrayStack2 operstack = new ArrayStack2(10);
		//定义需要的相关变量
		int index = 0; //用于扫描
		int num1 = 0;
		int num2 = 0;
		int oper = 0;
		int res = 0;
		char ch = ' ';//将每次扫描到的char保存到ch
		String keepNum = "";//用于拼接多位数
		//开始while扫描expression
		while(true) {
			//依次得到expression的每一个字符
			//substring(开始取值的位置,取值终点位置) charAt 获取第几个数据（0为第一个）
			ch = expression.substring(index,index+1).charAt(0);
			//判断ch是运算符还是数字
			if(operstack.isOper(ch)) {
				//判断是否为空
				if(!operstack.isEmpty()) {
					//如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符,就需要从数栈中pop出两个数。
					//在从符号栈中pop出一个符号，进行运算,将得到结果入数栈,然后将当前的操作符入符号栈
					if(operstack.priority(ch) <= operstack.priority(operstack.peek())) {
						num1 = numstack.pop();
						num2 = numstack.pop();
						oper = operstack.pop();
						res = numstack.cal(num1, num2, oper);
						numstack.push(res);
						operstack.push(ch);	
					}else {
						operstack.push(ch);
					}
				}else {
					//为空直接入符号栈
					operstack.push(ch);
				}
				
			}else {
				//数字直接入栈
				//numstack.push(ch-48);//ch转换后为ascll码 此码比原数大48
				//当处理多位数时无法立刻入栈 会被拆开加入
				//需要在expression的表达式中index下一位还是数需要拼接在进入数栈
				//定义一个字符串变量进行拼接
				keepNum += ch;
				//如果ch是最后一位 直接入栈
				if(index == expression.length()-1) {
					
					numstack.push(Integer.parseInt(keepNum));
				}
				//判断下一个字符是不是数字
				else if(operstack.isOper(expression.substring(index+1,index+2).charAt(0))) {
					//直到下一位是运算符就入栈
					numstack.push(Integer.parseInt(keepNum));
					//同时需要把keepNum清空
					keepNum = "";
					
				}
			}
			//让index+1 判断是否到最后
			index++;
			if(index>=expression.length()) {
				break;
				}
			}
		//当扫描完毕 就顺序得从舒展和符号栈中pop出相应的数和符号 并运行
		while(true) {
			if(operstack.isEmpty()) {
				break;
				}
			num1 = numstack.pop();
			num2 = numstack.pop();
			oper = operstack.pop();
			res = numstack.cal(num1, num2, oper);
			numstack.push(res);//入栈
			}
		System.out.println("表达式"+expression+"="+numstack.pop());
		}

}
class ArrayStack2{
	private int maxSize;//栈的大小
	private int[] stack;//数组模拟栈
	private int top = -1;//top表示栈顶 初始化为-1
	//返回栈顶的值
	public int peek() {
		return stack[top];
	}
	public ArrayStack2(int maxSize) {
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
		}else {
		top++;
		stack[top] = value;
		}
	}
	//出栈
	public int pop() {
		if(isEmpty()) {
			//抛出异常
			throw new RuntimeException("栈空无数据");
		}else {
		int value = stack[top];
		top--;
		return value;
		}
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
	//返回运算符的优先级（程序员定） 使用数字表示 数值大优先级高
	public int priority(int oper) {
		if(oper == '*' || oper == '/') {
			return 1;
		}else if(oper == '+' || oper == '-') {
			return 0;
		}else {
			return -1;//目前表达式只有+-*/
		}
	}
	//判断是不是一个运算符
	public boolean isOper(char val) {
		return val == '*' || val == '/' || val == '+' || val == '-';
	}
	//计算方法
	public int cal(int num1,int num2,int oper) {
		int res = 0;//存放计算结果
		switch(oper) {
		case '+':
			res = num1+num2;
			break;
		case '-':
			res = num2-num1;
			break;
		case '*':
			res = num1*num2;
			break;
		case '/':
			res = num2/num1;
			break;
		}
		return res;
		
	}
}