package com.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

	public static void main(String[] args) {
		int[] a = {1,2,3,4,5,5,9,60};
		System.out.println(binarySearch(a,0,a.length-1,5));

	}
	public static ArrayList<Integer> binarySearch(int[] arr,int left,int right,int finalVal) {
		if(left>right) {
			return new ArrayList<Integer>();
		}
		int mid = (left + right) / 2;
		if(arr[mid]<finalVal) {
			return binarySearch(arr,mid+1,right,finalVal);
		}else if(arr[mid]>finalVal) {
			return binarySearch(arr,left,mid-1,finalVal);
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
