package com.hashtable;

import java.util.Scanner;

public class HashTableDemo {
	public static void mian(String[] args) {
		//创建哈希表
		HashTab hashTab = new HashTab(7);
		String key = "";
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("输入add 添加");
			System.out.println("输入list 遍历");
			System.out.println("输入find 查找");
			System.out.println("输入exit 退出");
			key = scanner.next();
			switch(key){
			case "add":
				System.out.println("输入id");
				int id = scanner.nextInt();
				System.out.println("输入名字");
				String name = scanner.next();
				Emp emp = new Emp(id,name);
				hashTab.add(emp);
				break;
			case "list":
				hashTab.list();
				break;
			case "find":
				System.out.println("输入要查找的id");
				int no = scanner.nextInt();
				hashTab.findEmp(no);
				break;
				
			case "exit":
				scanner.close();
				break;
				
			}
			break;
		}
	}
	
}
class HashTab{
	private EmpLinkedList[] empLinkedListArray;
	private int size;//标识有多少条链表
	//构造器
	public HashTab(int size) {
		this.size = size;
		//初始化empLinkedListArray
		empLinkedListArray = new EmpLinkedList[size];
		//分别初始化每一条链表
		for(int i = 0;i<size;i++) {
			empLinkedListArray[i] = new EmpLinkedList();
		}
		
	}
	//添加
	public void add(Emp emp) {
		int empNo = hashFun(emp.id);//应添加到哪个链表
		empLinkedListArray[empNo].add(emp);
		
	}
	//遍历
	public void list() {
		for(int i=0;i<size;i++) {
			empLinkedListArray[i].list(i);
		}
	}
	//编写散列函数 使用一个简单取模法
	public int hashFun(int id) {
		return id%size;
	}
	public void findEmp(int id) {
		//用散列函数确定到哪条链表查找
		int empNo = hashFun(id);
		Emp emp = empLinkedListArray[empNo].findE(id);
		if(emp != null) {
			System.out.println("第"+(empNo+1)+"条链表找到"+id);
		}else {
			System.out.println("未找到");
		}
	}
}
class Emp{
	public int id;
	public String name;
	public Emp next;
	public Emp(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
}
class EmpLinkedList{
	//创建链表
	//头指针 执行第一个emp （有效 指向第一个emp）
	private Emp head;//默认为null
	//添加雇员到链表
	//直接加在最后 id自增
	public void add (Emp emp) {
		if(head == null) {
			//添加第一个
			head = emp;
			return;
		}else {
			Emp curEmp = head;
			while(true) {
				if(curEmp.next == null) {
				break;//到最后
				}
				curEmp = curEmp.next;
			}
			curEmp.next = emp;
		
		}
	}
	public void list(int no) {
		if(head == null) {
			System.out.println((no+1) + "链表为空");
			return;
		}
		System.out.println((no+1) + "当前链表信息为");
		Emp curEmp = head;//辅助指针
		while(true) {
			System.out.println(curEmp.id + "名字 "+curEmp.name);
			if(curEmp.next == null) {
				break;
			}
			curEmp = curEmp.next;//后移
		}
	}
	public Emp findE(int id) {
		if(head == null) {
			System.out.println("链表为空");
			return null;
		}
		Emp curEmp = head;
		while(true) {
			if(curEmp.id == id) {
				break;//找到退出
			}
			if(curEmp.next == null) {//未找到
				curEmp = null;
				break;
			}
			curEmp = curEmp.next;
		}
		return curEmp; 
	}
}
