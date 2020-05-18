package com.sparsearray;

public class SparseArray {
	public static void main(String[] args) {
		//创建一个二维数组11*11
		//0无棋子 1白棋 2黑棋
		int chessArr1[][] = new int[11][11];
		chessArr1[1][2] = 1;
		chessArr1[2][4] = 2;
		for(int[] row:chessArr1) {
			for(int data: row) {
				System.out.printf("%d\t",data);
			}
			System.out.println("");
		}
		//二维数组转化为稀疏数组
		//1遍历二维数组 得到非零数据的个数
		int sum = 0;
		for(int i = 0;i<11;i++) {
			for(int j = 0;j<11;j++) {
				if(chessArr1[i][j] != 0) {
					sum++;					
				}
			}			
		}
		System.out.println("sum="+sum);
		//2 创建对应的稀疏数组
		int sparseArr[][] = new int[sum+1][3];
		//给稀疏数组赋值
		sparseArr[0][0] = 11;//行
		sparseArr[0][1] = 11;//列
		sparseArr[0][2] = sum;//数据个数
		int count = 0;//记录是第几个非零数据
		for(int i = 0;i<11;i++) {
			for(int j = 0;j<11;j++) {
				if(chessArr1[i][j] != 0) {
					count++;
					sparseArr[count][0] = i;
					sparseArr[count][1] = j;
					sparseArr[count][2] = chessArr1[i][j];	
				}
			}			
		}
		System.out.println();
		System.out.println("稀疏数组为");
		for(int i = 0;i<sparseArr.length;i++) {
			System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
			
		}
		System.out.println("");
		//将稀疏数组恢复成二维数组
		//1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的维数组
		//2.在读取稀疏数组后几行的数据,并赋给原始的二维数组即可.
		int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
		for(int i =1;i<sparseArr.length;i++) {
			chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		for(int[] row:chessArr2) {
			for(int data: row) {
				System.out.printf("%d\t",data);
			}
			System.out.println("");
		}	
	}
}
