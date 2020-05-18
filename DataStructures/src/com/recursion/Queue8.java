package com.recursion;

public class Queue8 {
	//定义一个max值表示有多少个皇后
	int max = 8;
	//定义数组保存皇后存放位置结果
	int[] array = new int[max];
	static int count = 0;//统计多少次
	public static void main(String[] args) {
		Queue8 queue8 = new Queue8();
		queue8.check(0);
		System.out.println("共有"+count+"种解法");
	
	}
	//放置第n个皇后
	private void check(int n) {
		if(n == max) {//n=8 八个皇后已经放好
			print();
			return;
		}
		//依次放入 并判断是否冲突
		for(int i=0;i<max;i++) {
			array[n] = i;
			if(judge(n)) {
				//不冲突继续放下一个 递归
				check(n+1);
			}
			//冲突则循环
		}
	}
	//查看当放置第n个皇后时 检测是否和前面放置的皇后同行同列
	private boolean judge(int n) {
		for(int i=0;i<n;i++) {
			if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])) {
				//是否同列 || 斜率是否为1
				return false;
				
			}
		}
		return true;
		
	}
	//将皇后摆放位置输出
	private void print() {
		count++;
		for(int i=0;i<array.length;i++) {
			System.out.print(array[i]+" ");
		}
		System.out.println(" ");
	}
}

