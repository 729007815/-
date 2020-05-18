package com.sort;

import java.util.Arrays;

public class RadixSort {
	//800W1s内
	public static void main (String[] args) {
		int a[] = {97,-80,-20,0,13,2};
		radixSort(a);
//		int a1[] = new int[8000000];
//		for(int i=0;i<8000000;i++) {
//			a1[i] = (int)(Math.random()*8000000);
//			
//		}
//		
//		System.out.println(System.currentTimeMillis()/1000);
//		radixSort(a1);
//
//		System.out.println(System.currentTimeMillis()/1000);

	

	}
	public static void radixSort(int[] arr) {
		int max = arr[0];//假定最大数为第一个
		int min = 0;
		for(int m = 1;m<arr.length;m++) {
			if(arr[m]>max) {
				max = arr[m];
			}
			if(arr[m]<min) {	
				min = arr[m];//找到负数最小值			
			}
		}
		if(min<0) {
			for(int mi = 0;mi<arr.length;mi++) {
				arr[mi] -= min;
				max -= min;
			}
		}
		System.out.println(Arrays.toString(arr)+"==");
		int maxLength = (max+"").length();//获取是几位数
		
		//定义一个二维数组，表示10个桶，每个桶就是一 个一维数组
		//说明
		//1.二维数组包含10个一维数组
		//2.为了防止在放入数的时候，数据溢出，则每个一维数组(桶),大小定为arr . length
		//3.名明确，基数排序是使用空间换时间的经典算法
		int[][] bucket = new int[10][arr.length];//以防所有数据个位数都是同一个数
		//为了记录每个桶中,实际存放了多少个数据,我们定义一一个-维数组来记录各个桶的每次放入的数据个数
		//可以这里理解
		//比如: bucketElementCounts[0] ，记录的就是bucket[0] 桶的放入数据个数
		int[] bucketElementCounts = new int[10];
		for (int n = 0, g=1; n < maxLength; n++,g*=10) {
			for (int i = 0; i < arr.length; i++) {
				int digitOfElement = arr[i]/g % 10;// 求各位数的数值
				bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];// [个位数值][该数存放的第几个]
				bucketElementCounts[digitOfElement]++;
			}
			// 存放完毕后再取回原数组
			int index = 0;
			for (int j = 0; j < bucketElementCounts.length; j++) {
				if (bucketElementCounts[j] != 0) {
					// 表示存储的有数据
					for (int k = 0; k < bucketElementCounts[j]; k++) {
						arr[index] = bucket[j][k];
						index++;
					}
				}
				bucketElementCounts[j] = 0;
			}
			System.out.println(Arrays.toString(arr));

		}
		if(min<0) {
			for(int mi = 0;mi<arr.length;mi++) {
				arr[mi] += min;
			}
		}
		System.out.println(Arrays.toString(arr));

	}
}
