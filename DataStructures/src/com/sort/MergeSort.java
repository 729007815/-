package com.sort;

import java.util.Arrays;

public class MergeSort {
	//800W 2s
	public static void main(String[] args) {
//		int[] a =  {-5, 0, 3, 8, -8, 10, 9};
//		mergeSort(a,0,a.length-1,temp);
		
		int a1[] = new int[8000000];
		int[] temp = new int[a1.length];
		for(int i=0;i<8000000;i++) {
			a1[i] = (int)(Math.random()*800000000);
			
		}

		System.out.println(System.currentTimeMillis()/1000);
		mergeSort(a1,0,a1.length-1,temp);

		System.out.println(System.currentTimeMillis()/1000);
		//System.out.println(Arrays.toString(a));
		
	}
	public static void mergeSort(int[] arr,int left,int right,int[] temp) {
		if(left<right) {
			int mid = (right+left)/2;
			//左递归分解
			mergeSort(arr,left,mid,temp);
			//向右递归分解
			mergeSort(arr,mid+1,right,temp);
			//合并
			merge(arr,left,right,mid,temp);
		}
		
	
}
	public static void merge(int[] arr,int left,int right,int mid,int[] temp) {
		//temp中转数组
		int l = left;//左端初始索引
		int r = mid + 1;//右端初始索引
		int t = 0;//temp初始索引
		//(一)
		//先把左右两边(有序)的数据按照规则填充到temp数组
		//直到左右两边的有序序列,有一边处理完毕为止
		while(l<=mid && r<=right) {
			//如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
			//即将左边的当前元素，拷贝到temp数组

			if(arr[l]<=arr[r]) {
				temp[t] = arr[l];
				t++;
				l++;
			}else{
				temp[t] = arr[r];
				t++;
				r++;
			}
			
		}
		//(二)
		//把有剩余数据的一边的数据依次全部填充到temp
		while(l<=mid) {
			temp[t] = arr[l];
			t++;
			l++;
		}
		while(r<=right) {
			temp[t] = arr[r];
			t++;
			r++;
		}
		//(三)
		//将temp数组的元素拷贝到arr
		t = 0;
		int tempLeft = left;
		while(tempLeft<=right) {
			arr[tempLeft] = temp[t];
			t++;
			tempLeft++;
		}
		
		
	}
}
