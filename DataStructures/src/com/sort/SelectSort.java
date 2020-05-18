package com.sort;

import java.util.Arrays;

public class SelectSort {
	//8万个2秒
	public static void main(String[] args) {
//		int a1[] = new int[80000];
//		for(int i=0;i<80000;i++) {
//			a1[i] = (int)(Math.random()*8000000);
//			
//		}
//		System.out.println(System.currentTimeMillis()/1000);
//		selectSort(a1);
//
//		System.out.println(System.currentTimeMillis()/1000);

		int[] a = {9,800,45,321,25,990,-10,-6};
		selectSort(a);
		
	}
	public static void selectSort(int[] arr) {
		for(int i=0;i<arr.length-1;i++) {
			int index = i;
			int min = arr[i];
			for(int j=i+1;j<arr.length;j++) {
				if(min>arr[j]) {
					index = j;
					min = arr[j];
				}
			}
			if(index != i) {
			arr[index] = arr[i];
			arr[i] = min;
			}
			System.out.println(Arrays.toString(arr));
		}
	}
}
