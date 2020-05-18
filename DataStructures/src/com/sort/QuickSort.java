package com.sort;

import java.util.Arrays;

public class QuickSort {
	//不到一秒 800W1s
	public static void main(String[] args) {
//		int a1[] = new int[8000000];
//		for(int i=0;i<8000000;i++) {
//			a1[i] = (int)(Math.random()*800000000);
//			
//		}
//
//		System.out.println(System.currentTimeMillis()/1000);
//		quickSort(a1,0,a1.length-1);
//
//		System.out.println(System.currentTimeMillis()/1000);
		int[] a =  {-5, 0, 3, 8, -8, 10, 9};
		quickSort(a,0,6);
	}
	public static void quickSort(int[] arr,int L,int R) {
		if(L>=R) {
			return;
		}
		int left = L;
		int right = R;
		int pivot = arr[left];
		while(left<right) {
			while(left<right && arr[right]>=pivot) {
				right-- ;//从右往左开始判断找到小于pivot的退出 大于的不管（本身就在右边） 
			}
			//{-5, 0, 3, 8, -8, 10, 9};
			if(left<right) {//找到的情况
				arr[left] = arr[right];//将小于pivot的值放在左边
				System.out.println(Arrays.toString(arr));//[-8, 0, 3, 8, -8, 10, 9]
			}
			while(left<right && arr[left]<=pivot) {
				left++;//在小于right内 从左往右 找到左边大于pivot的值为止
			}
			if(left<right) {//预防右边有两个比pivot小的数值
				arr[right] = arr[left];//找到了大于pivot的值 则将该值放到上次找到小于pivot值的位置
				
				System.out.println(Arrays.toString(arr));//[-8, -5, 3, 8, 0, 10, 9]
			}
			if(left>=right) {//表示没有在左端找到大于pivot的值
				arr[left] = pivot;//将上一次放在最左端的值的下标对应的值改为pivot
				//完成一次互换位置
			}
			System.out.println(Arrays.toString(arr));
		}
		System.out.println(left+" "+ right);
		quickSort(arr,L,left-1);//处理pivot左端的值 left=right
		quickSort(arr,right+1,R);//处理pivot左端的值 left=right
		System.out.println(Arrays.toString(arr));
	}
}
