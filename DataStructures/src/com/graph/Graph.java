package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
	private ArrayList<String> vertexList;//存储定点集合
	private int[][] edges;//存储图对应的领结矩阵
	private int numOfEdges;//表示边的数目
	private boolean[] isVisited;//记录某个节点是否被访问 
	public static void main(String[] args) {
		int n = 9;//顶点数
		//String Vertex[] = {"A","B","C","D","E"};
		String Vertex[] = {"1","2","3","4","5","6","7","8","9"};
		//创建图对象
		Graph graph = new Graph(n);
		//循环添加顶点
		for(String vertex: Vertex) {
			graph.insertVertex(vertex);
		}
		//添加边
		//A-B A-C B-C B-D B-E
//		graph.insertEdge(0, 1, 1);
//		graph.insertEdge(0, 2, 1);
//		graph.insertEdge(1, 2, 1);
//		graph.insertEdge(1, 3, 1);
//		graph.insertEdge(1, 4, 1);
		
		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);
		graph.insertEdge(3, 7, 1);
		graph.insertEdge(4, 7, 1);
		graph.insertEdge(2, 5, 1);
		graph.insertEdge(2, 6, 1);
		graph.insertEdge(5, 6, 1);
	
		
		//显示领结矩阵
		graph.showGraph();
		//深度遍历
		System.out.println("深度遍历");
		graph.dfs();
		System.out.println("广度优先搜索");
		graph.bfs();
	}
	//构造器
	public Graph(int n) {
		//n顶点
		//初始化矩阵和vertexList
		edges = new int [n][n];
		vertexList = new ArrayList<String>(n);
		numOfEdges = 0;//默认为零
		 isVisited = new boolean[n];
		
	}
	//插入节点
	public void insertVertex(String vertex) {
		vertexList.add(vertex);
	}
	//添加边
	//v1：点的下标 及第几个顶点
	//v2: 第二个顶点对应的下标
	//weight 关联关系 如相通为1 不通为0
	public void insertEdge(int v1,int v2,int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdges++;
	}
	//返回节点的个数
	public int getNumOfVertex() {
		return vertexList.size();
	}
	//返回边的数目
	public int getNumOfEdges() {
		return numOfEdges;
	}
	//返回顶点i(下标)对应的数据 0=》A 1=>b 2=>c
	public String getValueByIndex(int i) {
		return vertexList.get(i);
	}
	//返回v1 v2的权值 （关系）
	public int getWeight(int v1,int v2) {
		return edges[v1][v2];
	}
	//显示图对应的矩阵 （遍历）
	public void showGraph() {
		for(int[] link : edges) {
			System.out.println(Arrays.toString(link));
		}
	}
	//dfs 深度优先搜索
	//得到第一个领结节点的下标 存在则返回对应的下标
	public int getFirstNeighbor(int index) {
		for(int j = 0; j<vertexList.size();j++) {
			if(edges[index][j] == 1) {
				return j;
			}
		}
		return -1;
	}
		//根据前一个领结节点的下标 来获取下一个领结节点
		//v2是v1的领结节点 找到v1的其他领结节点
	public int getNextNeighbor(int v1, int v2) {
		for (int j = v2 + 1; j < vertexList.size(); j++) {
			if (edges[v1][j] == 1) {
				return j;
			}
		}
		return -1;
	}
	//对dfs进行重载 遍历所有顶点  考虑不连通图a-b-c连通 d-e连通但是整体不连通
	public void dfs() {
		
		for(int i =0;i<getNumOfVertex();i++) {
			if(!isVisited[i]) {
				dfs(isVisited,i);
			}
		}
	}
	//深度优先遍历算法
	//i 第一次就是0（数组首尾）
	private void dfs(boolean[] isVisited,int i) {
		System.out.print(getValueByIndex(i) + "->");
		//标记为已读
		isVisited[i] = true;
		//查找顶点i的第一个领结节点w
		int w = getFirstNeighbor(i);
		while(w != -1) {//说明有
			if(!isVisited[w]) {//w未被访问
				dfs(isVisited,w);
			}else {
				w = getNextNeighbor(i,w);
			}
		}
	}
	
	//对一个顶点进行广度优先遍历的方法
	//重载 遍历所有的顶点 都进行广度优先搜索
	public void bfs() {
		for(int i = 0;i<getNumOfVertex();i++) {
			if(!isVisited[i]) {
				bfs(isVisited,i);
			}
		}
	}
	private void bfs(boolean[] isVisited,int i) {
		int u;//表示队列的头顶点对应下标
		int w;//u的第一个领结节点
		LinkedList queue = new LinkedList();//队列 顶点访问顺序
		//输出顶点信息
		System.out.print(getValueByIndex(i)+"=>");
		//标记为已访问
		isVisited[i] = true;
		//顶点加入节点
		queue.addLast(i);
		while(!queue.isEmpty()) {
			//取出队列头节点下标
			u = (Integer)queue.removeFirst();
			w = getFirstNeighbor(u);
			while(w != -1) {
				if(!isVisited[w]) {
					//未访问过
					System.out.print(getValueByIndex(w)+"=>");
					//输出后标记已访问
					isVisited[w] = true;
					//入队
					queue.addLast(w);
				}
				//找u的其他领结节点
				w = getNextNeighbor(u,w);//体现广度优先
			}
		}
	}

}
