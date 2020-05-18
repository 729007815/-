package com.sort;

import java.util.Arrays;

public class BubbleSort {

	public static void main(String[] args) {
		int a1[] = new int[80000];
		for(int i=0;i<80000;i++) {
			a1[i] = (int)(Math.random()*8000000);
			
		}
		int a[] = {9,8,2,0,-1,-2};
		System.out.println(System.currentTimeMillis()/1000);
		bubbleSort(a);

		System.out.println(System.currentTimeMillis()/1000);

	}
	public static void bubbleSort(int[] a ) {
		int b = 0;
		boolean c = false;
		for(int i=0;i<a.length-1;i++) {
			for(int j=0;j<a.length-i-1;j++) {
				if(a[j]>a[j+1]) {
					c = true;
					b = a[j];
					a[j] = a[j+1];
					a[j+1] = b;
				}
			}
			System.out.println("第"+ (i+1) +"次");
			System.out.println(Arrays.toString(a));
			if(!c) {
				break;
			}else {
				c = false;
			}
		}

	}
}
