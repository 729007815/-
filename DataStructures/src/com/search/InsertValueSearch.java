package com.search;

import java.util.ArrayList;

public class InsertValueSearch {
	public static void main(String[] args) {
		int[] a = new int[100];
		for(int i = 0; i<a.length;i++) {
			a[i] = i;
		}
		int[] a1 = {-1,0,3,3,6,9,80,80,99};
		System.out.println(insertValueSearch(a1,0,a1.length-1,3));
	}
	public static ArrayList<Integer> insertValueSearch(int[] arr,int left,int right,int finalVal) {
		System.out.println("1");
		if(left>right || finalVal<arr[left] || finalVal>arr[right]) {
			return new ArrayList<Integer>();
		}
		int mid = left + (right - left)*(finalVal - arr[left]) / (arr[right] - arr[left]);
		if(arr[mid]<finalVal) {
			return insertValueSearch(arr,mid+1,right,finalVal);
		}else if(arr[mid]>finalVal) {
			return insertValueSearch(arr,left,mid-1,finalVal);
		}else {
			ArrayList<Integer> resIndexList = new ArrayList<Integer>();
			resIndexList.add(mid);
			int temp = mid - 1;
			while(true) {
				if(temp < 0 || arr[temp] != finalVal) {
					//向左扫描
					break;
				}
				resIndexList.add(temp);
				temp--;
			}
			temp = mid + 1;
			while(true) {
				if(temp > arr.length-1 || arr[temp] != finalVal) {
					//向右扫描
					break;
				}
				resIndexList.add(temp);
				temp++;
			}
			return resIndexList;
		}
		
	}
}


