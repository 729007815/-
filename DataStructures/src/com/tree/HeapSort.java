package com.tree;

import java.util.Arrays;

public class HeapSort {
	//800W 2s
	public static void main(String[] args) {
		//int arr[] = {4,7,8,5,9,800,50,0,-50,-4};
		//heapSort(arr);
		int a1[] = new int[8000000];
		for(int i=0;i<8000000;i++) {
			a1[i] = (int)(Math.random()*8000000);
			
		}
		System.out.println(System.currentTimeMillis()/1000);
		heapSort(a1);

		System.out.println(System.currentTimeMillis()/1000);

	}
	public static void heapSort(int arr[]) {
		int temp = 0;
		//System.out.println("堆排序");
		//形成大顶堆
		for(int i = arr.length/2-1;i>=0;i--) {//总共多少个非叶子节点
			adjustHeap(arr,i,arr.length);
			
		}
		for(int j = arr.length-1;j>0;j--) {
			//将堆顶最大元素与末尾元素交换 将最大元素沉底
			//在形成大顶堆 再交换
			temp = arr[0];
			arr[0] = arr[j];
			arr[j] = temp;
			adjustHeap(arr,0,j);//因为只变动了首尾两个数 所以可以直接只调整root的局部树从上到下
		}
		//System.out.println(Arrays.toString(arr));
	}
	
	//将一个数组（二叉树） 调整成大顶堆（从下至上调整）
	
	//功能： 完成将以i对应的非叶子节点的树调整成大顶堆
	//i 表示非叶子节点在数组中的索引
	//length 表示多少个元素调整 （不断减少）
	public static void adjustHeap(int arr[],int i ,int length) {
		int temp = arr[i];//先取出当前元素的值 
		for(int k = i*2+1;k<length;k = k*2+1) {
			//k是i节点的左子节点
			if(k+1<length && arr[k]<arr[k+1]) {//左子节点小于右子节点
				k++;//指向右子节点 		
			}
			if(arr[k]>temp) {
				arr[i] = arr[k];
				arr[k] = temp;//该三角二叉树（3个数据）形成大顶堆
				i = k;//i指向k 继续循环比较
			}else {
				break;//从下至上调成 所以可以直接break;
			}
		}//for结束后已经将i为父节点的树的最大值放在了最顶端（局部）
	}
}
