package com.sort;

import java.util.Arrays;

public class InsertSort {
	//8万个数据2s
	public static void main(String[] args) {
//		int a1[] = new int[80000];
//		for(int i=0;i<80000;i++) {
//			a1[i] = (int)(Math.random()*8000000);
//			
//		}
//		System.out.println(System.currentTimeMillis()/1000);
//		insertSort(a1);
//
//		System.out.println(System.currentTimeMillis()/1000);
		int a[] = {9,8,2,0,-1,-2};
		insertSort(a);
		
	}
	public static void insertSort(int[] arr) {
	
//		1. Index >= 0保证在给Val找插入位置，不越界
//		2. Val < arr[ Index] 待插入的数，还没有找到插入位置
//		3.就需要将arr[Index] 后移

		for(int i=1; i<arr.length;i++) {
			int value = arr[i];
			int index = i-1;//index前有序 后面是无序
			while(index >=0 && value<arr[index]) {
				arr[index+1] = arr[index];//左端序列往后挪
				index--;
			}
			//优化 判断是否需要赋值
			if(index+1 != i) {//最后一位
			arr[index+1] = value;
			System.out.println(Arrays.toString(arr));
			}
			}
	}
}
