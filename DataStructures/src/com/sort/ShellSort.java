package com.sort;

import java.util.Arrays;

public class ShellSort {
	//八百万 3s
	public static void main(String[] args) {
		int a1[] = new int[8000000];
		for(int i=0;i<8000000;i++) {
			a1[i] = (int)(Math.random()*800000000);
			
		}
		System.out.println(System.currentTimeMillis()/1000);
		shellSort2(a1);

		System.out.println(System.currentTimeMillis()/1000);

//		int[] a = {4,7,9,8,5};
//		//shellSort1(a);
//		shellSort2(a);
		
	}
	//交换法(优化的冒泡)
	//用7s
	public static void shellSort1(int[] arr) {
		int val = 0;
		for(int gap = arr.length/2;gap>0;gap/=2) {
			for(int i = gap;i<arr.length;i++) {
				//遍历各组所有元素 共有gap组 步长为gap
				for(int j = i-gap;j>=0;j-=gap) {
					if(arr[j] > arr[j+gap]) {
						val = arr[j];
						arr[j] = arr[j+gap];
						arr[j+gap] = val;
					}
				}
			}
			//System.out.println(Arrays.toString(arr));
		}
	}
	//移动法（优化的插入法）
	//1s内
	public static void shellSort2(int[] arr) {
		int val = 0;
		for(int gap = arr.length/2;gap>0;gap/=2) {
			for(int i=gap;i<arr.length;i++) {
			int j = i;
			val = arr[j];
			if(arr[j]<arr[j-gap]) {
				while(j-gap >= 0 && val<arr[j-gap]) {//注意必须是val 比较一次后若再下一个数比val大则该数后移 否则val值的位置不变
					arr[j] = arr[j-gap];
					j-=gap;
					
				}
				arr[j] = val;		
				//System.out.println(Arrays.toString(arr));			
			}
			
			}
		}
		}
		
}
