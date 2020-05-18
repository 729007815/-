package com.queue;

import java.util.Scanner;

public class ArrayQueue {
	public static void main(String[] args) {
	 //环形队列
		Array array = new Array(3);//队列有效数组仅有两个 一个作为空间约定
		char key = ' ';// 接受用户输入
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		while(loop) {
			System.out.println("s显示队列");
			System.out.println("e退出队列");
			System.out.println("a添加数据到队列");
			System.out.println("g取出队列数据");
			System.out.println("h队列头部数据");
			key = scanner.next().charAt(0);
			switch(key) {
			case 's':
				array.showQueue();
				break;
			case 'a':
				System.out.println("输出一个数");
				int value = scanner.nextInt();
				array.addQueue(value);
				break;
			case 'g':
				try {
					int res = array.getQueue();
					System.out.printf("取出的数据是%d\n",res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try {
					int res = array.headQueue();
					System.out.println("队列头的数据为"+res);
	
				} catch (Exception e) {
					System.out.println(e.getMessage());
}
				break;
			case 'e':
				scanner.close();
				loop = false;
				break;
				
			}
		}
		System.out.println("程序退出");
 }
}
class Array{
	private int maxSizes;//数组的最大容量
	private int front;//队列头 指向队列第一个元素初始值为0
	private int rear;//队列尾 指向队列最后一个元素的后一个元素 （要空出一个元素位置）初始值为0
	private int[] arr;//改数据用于存放数据 模拟队列
	//创建队列的构造器
	public Array(int arrayMaxSize) {
		maxSizes = arrayMaxSize;
		arr = new int[maxSizes];
		front =0;//指向队列头部的前一个位置
		rear = 0;//指向队列尾部的数据（队列最后一个数据）
	}
	//判断队列是否满
	public boolean ifFull() {
		return (rear +1)%maxSizes == front ;
	}
	//判断队列是否为空
	public boolean ifEmpty() {
		return rear == front;
	}
	//添加数据到队列
	public void addQueue(int n) {
		if(ifFull()) {
			System.out.println("队列满");
			return;
		}
		arr[rear] = n;
		rear = ( rear + 1 ) % maxSizes;
	}
	//获取队列的数据 出队列
	public int getQueue(){
		if(ifEmpty()){
			//通过抛出异常
			throw new RuntimeException("队列空不能取值");		
		}
		//这里需要分析出front就是指向队列的第一个元素
		//1.先把front对应的值保留到一个临时变量
		//2.将front后移 考虑取模
		//3.将临时保存的值返回
		int value = arr[front];
		front = (front + 1) % maxSizes;
		return value;
	}
	//显示队列所有数据
	public void showQueue() {
		if(ifEmpty()) {
			System.out.println("空队列无数组");
			return;			
		}
		for(int i = front;i< front + (rear + maxSizes - front)% maxSizes;i++) {
			System.out.printf("arr[%d]=%d\n",i%maxSizes,arr[i%maxSizes]);
			
		}
	}
	//显示队列的头数据 注意不是取出数据
	public int headQueue() {
		if(ifEmpty()){
			//通过抛出异常
			throw new RuntimeException("队列空 无数据");		
		}
		return arr[front];
	}
	
}

