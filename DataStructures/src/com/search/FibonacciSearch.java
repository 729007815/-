package com.search;

import java.util.Arrays;

public class FibonacciSearch {
	public static int maxSize = 20;
	public static void main(String[] args) {
		int arr[] = {1,3,8,9,11,30};
		System.out.println(fibSearch(arr,11));
	}
	public static int[] fib() {	
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for(int i=2;i<maxSize;i++) {
			f[i] = f[i-1] + f[i-2];
		}
		return f;
	}
	public static int fibSearch(int[] a,int key) {
		int low = 0;
		int high = a.length-1;
		int k = 0;//表示斐波那契分割数值的下标
		int mid = 0;
		int f[] = fib();//得到斐波那契数列
		//获取k 下标
		while(high > f[k] - 1) {
			k++;
		}
		//因为f[k]值可能大于a的长度，因此我们需要使用Arrays类，构造一个 新的数组，并指向a[ ]
		//不足的部分会使用0填充
		int[] temp = Arrays.copyOf(a, f[k]);
		//改为用a数组最后的数据填充
		for(int i = high+1;i<temp.length;i++) {
			temp[i] = a[high];
		}
		//获取k
		while(low<=high) {
			mid = low + f[k-1] - 1;
			if(key<temp[mid]) {
				//应继续往左找
				k--;
			}else if(key>temp[mid]) {
				//向右找
				low = mid + 1;
				k-=2;
				//f[k] = f[k-1] + f[k-2]
				//即下次循环mid = low + f[k-1-2] - 1;
			}else {
				if(mid<=high) {
					return mid;
				}else {
					return high;
				}
			}
		}
		return -1;
		
	}
}
