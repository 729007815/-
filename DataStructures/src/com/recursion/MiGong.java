package com.recursion;

public class MiGong {

	public static void main(String[] args) {
		//地图
		int[][] map = new int[8][7];
		for(int i = 0 ;i <7;i++) {
			map[0][i] = 1;
			map[7][i] = 1;
		}
		for(int i = 0 ;i <7;i++) {
			map[i][0] = 1;
			map[i][6] = 1;
			
		}
		//设置挡板
		map[3][1] = 1;
		map[3][2] = 1;
		//显示地图
		for(int i=0;i<8;i++) {
			for(int j=0;j<7;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("走过后");
		//使用递归给小球找路
		setWay(map,1,1);
		for(int i=0;i<8;i++) {
			for(int j=0;j<7;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
//递归回溯给小球找路
	//map地图  i，j起始位置
	//走到map[6][5]表示到终点
	//当map[i][j] 为0表示没有走的路  1为墙 2表示可以走的路 3表示走过的路 但是走不通
	//定义策略 先下再右再上再左 走不通则回溯
	public static boolean setWay(int[][] map,int i,int j) {
		if(map[6][5] == 2) {//到终点
			return true;
		}else {
			if(map[i][j] == 0) {//未走过
				map[i][j] = 2;//假定该店可走通
				if(setWay(map,i-1,j)) {//向下走
					return true;
					
				}else if(setWay(map,i,j-1)) {//向右走
					return true;
				}else if(setWay(map,i+1,j)) {//向右走
					return true;
				}else if(setWay(map,i,j+1)) {//向右走
					return true;
				}else {
					//该起始点走不通
					map[i][j] = 3;
					return false;
				}
			}else {
				//起始点为1 2 3
				return false;
				
			}
		}
	}
	
}
