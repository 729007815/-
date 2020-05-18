package com.linkedlist;

import java.util.Stack;

public class SingleLinkedList {
	public static void main(String[] args) {
		//先创建节点
		HeroNode hero1 = new HeroNode(1,"菲兹","小鱼人");
		HeroNode hero2 = new HeroNode(2,"菲奥娜","剑姬");
		HeroNode hero3 = new HeroNode(3,"布里茨","机器人");
		HeroNode hero4 = new HeroNode(4,"迪迦","奥特曼");
		
		//创建链表
		SingleLinked singleLinked = new SingleLinked();
		//加入
//		singleLinked.add(hero1);
//		singleLinked.add(hero2);
//		singleLinked.add(hero3);
//		singleLinked.add(hero4);
//		singleLinked.list();
		//按编号加入
		singleLinked.addByOrder(hero3);
		singleLinked.addByOrder(hero4);
		singleLinked.addByOrder(hero2);
		singleLinked.addByOrder(hero1);
		singleLinked.list();
		//修改
//		HeroNode newhero4 = new HeroNode(4,"迪迦","丑13");
//		singleLinked.update(newhero4);
//		singleLinked.list();
		//删除
//		singleLinked.delet(4);
//		singleLinked.delet(3);
//		singleLinked.delet(2);
//		singleLinked.delet(1);
//		singleLinked.list();
		System.out.println(getlength(singleLinked.getHead()));
//		HeroNode res = findLastIndexNode(singleLinked.getHead(),2);
//		System.out.println("res=" + res);
	
		System.out.println("反转");
		reverseList(singleLinked.getHead());
		singleLinked.list();
		
		//反转链表 不改变链表结构
		System.out.println("栈逆序打印");
		reversePrint(singleLinked.getHead());
	
	}
	// 利用栈的先进后出 逆序打印
	//
	public static void reversePrint(HeroNode head) {
		if(head.next == null || head.next.next == null) {
			return ;
		}
		//创建一个栈 将各个节点压入栈
		Stack<HeroNode> stack = new Stack();
		HeroNode cur = head.next;
		while(cur != null) {
			stack.push(cur);
			cur = cur.next;
		}
		while(stack.size()>0) {
			System.out.println(stack.pop());
		}
	}
	//单链表 反转
	//
	public static void reverseList(HeroNode head) {
		if(head.next == null || head.next.next == null) {
			return ;
		}
		//定义一个辅助的指针（变量） 帮助遍历原来的链表
		HeroNode cur = head.next;
		HeroNode next = null; //当前节点cur的下一个节点
		HeroNode reverseHead = new HeroNode(0,"","");//反转后的头节点
		while(cur != null) {
			next = cur.next;//先保存当前节点的下个节点
			cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
			reverseHead.next = cur;//将反转表头的下一节点与cur相连
			cur = next;//cur后移
		}
		//将head.next指向reverseHead.next 实现单链表反转
		head.next = reverseHead.next;
		
	}
// 方法 查找单链表中的倒数第K个节点
//1. 接受head节点同时接受一个index
//2. index 表示倒数第index个节点
//3.先得到总长度（getlength） 遍历length-index次
	public static HeroNode findLastIndexNode(HeroNode head,int index) {
		if(head.next == null) {
			return null;
		}
		//得到长度
		int size = getlength(head);
		if(index <= 0 && index>size) {
			return null;
		}
		//size-index位置
		HeroNode cur = head.next;
		for(int i=0;i<size-index;i++) {
			cur = cur.next;
		}
		return cur;
	}
	

//方法： 获取到单链表的节点个数（带头节点的链表 不统计头节点）
public static int getlength(HeroNode head) {
	if(head.next == null) {
		return 0;
	}
	int length = 0;
	//定义一个辅助变量
	HeroNode cur = head.next;
	while(cur != null) {
		length++;
		cur = cur.next;
	}
	return length;
}
}
//定义 SingleLinkedList 管理英雄
class SingleLinked{
	
	//初始化头节点 不变
	private HeroNode  head = new HeroNode(0,"","");
	public HeroNode getHead() {
		return head;
	}
	
	public void setHead(HeroNode head) {
		this.head = head;
	}
	//添加节点到单链表中
	//当不考虑编号顺序时 1.找到当前链表的最后节点 2.将最后节点的next指向新的节点
	public void add(HeroNode heroNode) {
		//因为head节点不动 需要一个辅助遍历temp
		HeroNode temp = head;
		//遍历链表，找到最后
		while(true) {
			//找到链表的最后
			if(temp.next == null) {
				break;//找到最后一个
			}
			temp = temp.next;//每次temp被赋值为temp.next后 temp.next 相当于之前的temp.next.next
}
		temp.next = heroNode;
	}
	//显示链表（遍历）
	public void list() {
		//判断链表是否为空
		if(head.next == null) {
			System.out.println("空链表");
			return;
		}
		//头节点不动 定义一个辅助变量来遍历
		HeroNode temp = head.next;
		while(true) {
			//判断链表是否到最后
			if(temp == null) {
				break;//找到最后一个
			}
			//输出节点信息
			System.out.println(temp.toString());
			//将temp后移
			temp = temp.next;
		}
	}
	//修改节点的信息 根据no编号 编号不变
	public void update(HeroNode newHeroNode) {
		if(head.next == null) {
			System.out.println("链表为空 ");
			return;
		}
		//找到要修改的节点 定义一个辅助变量
		HeroNode temp = head.next;
		boolean flag = false;
		while(true) {
			if(temp == null) {
				//到链表的最后
				return;
			}
			if(temp.no == newHeroNode.no) {
				//找到位置
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if(flag) {
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
			
		}else {
			System.out.println("未找到该编号节点 无法修改"+newHeroNode.no);
		}
	}
	//第二种方式在添加数据时 根据排名将数据插入指定位置
	public void addByOrder(HeroNode heroNode) {
		//头节点不动 通过一个辅助变量（指针） 帮助找到指定位置
		//因为单链表 所以找到的temp是位于添加位置的前一个节点
		HeroNode temp = head;
		boolean flag = false;//标志添加的标号是否存在
		while(true) {
			if(temp.next == null) {
				break;//已经在链表最后
			}
			if(temp.next.no>heroNode.no) {//位置找到 就在temp的后面插入
				break;
			}
			else if(temp.next.no == heroNode.no) {
				//要添加的标号已经存在
				flag = true;
				break;
			}
			temp = temp.next;//后移
		}
		if(flag) {
			//true时不能添加
			System.out.println("添加的英雄编号 " + heroNode.no + "不能添加");
		}else {
			//可加入
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
	}
	//删除节点
	//先找到需要删除的这个节点的前一个节点temp
	//temp.next = temp.next.next;
	//被删除的节点 将不会有其他引用指向 会被垃圾回收机制回收
	public void delet(int no) {
		//head不能动 需要一个temp辅助节点 找到待删除节点的前一个节点
		//在比较时 是temp.next.no和需要删除的节点的no比较
		HeroNode temp = head;
		boolean flag = false;//是否找到节点
		while(true) {
			if(temp.next==null) {
				//找到最后一位了
				break;	
			}
			if(temp.next.no == no) {
				flag = true;
				break;
			}
			temp = temp.next;//后移遍历
		}
		if(flag) {
			temp.next = temp.next.next;
		}else {
			System.out.println("找不到要删除的节点" + no);
		}
	}
	
	
}
	//每个heroNode对象都是一个节点
class HeroNode{
	public int no;
	public String name;
	public String nickname;
	public HeroNode next; //指向下一个节点
	//构造器
	public HeroNode(int hNo,String hName, String hNickname) {
		this.no = hNo;
		this.name = hName;
		this.nickname = hNickname;
	}
	//为了显示方便 重写toString方法
	
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
}

