package com.linkedlist;

public class DoubleLinkedList {
	//双向链表
	public static void main(String[] args) {
	System.out.println("双向链表测试");
	HeroNode2 hero1 = new HeroNode2(1,"菲兹","小鱼人");
	HeroNode2 hero2 = new HeroNode2(2,"菲奥娜","剑姬");
	HeroNode2 hero3 = new HeroNode2(3,"布里茨","机器人");
	HeroNode2 hero4 = new HeroNode2(4,"迪迦","奥特曼");
	DoubleLinked doublelink = new DoubleLinked();
//	doublelink.add(hero1);
//	doublelink.add(hero2);
//	doublelink.add(hero3);
//	doublelink.add(hero4);
	//按编号添加
	doublelink.addByOrder(hero4);
	doublelink.addByOrder(hero1);
	doublelink.addByOrder(hero3);
	doublelink.addByOrder(hero4);
	doublelink.list();
//	//修改
//	System.out.println("双向链表修改测试");
//	HeroNode2 newhero4 = new HeroNode2(4,"迪迦","丑13");
//	doublelink.update(newhero4);
//	doublelink.list();
//	//删除
//	System.out.println("双向链表删除测试");
//	doublelink.delet(4);
//	doublelink.list();
	}
}
//创建双向链表的类
class DoubleLinked{
	//初始化头节点 不变
	private HeroNode2  head = new HeroNode2(0,"","");
	public HeroNode2 getHead() {
		return head;
	}
	//遍历双向链表
	public void list() {
		//判断链表是否为空
		if(head.next == null) {
			System.out.println("空链表");
			return;
		}
		//头节点不动 定义一个辅助变量来遍历
		HeroNode2 temp = head.next;
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
	public void add(HeroNode2 heroNode) {
		//因为head节点不动 需要一个辅助遍历temp
		HeroNode2 temp = head;
		//遍历链表，找到最后
		while(true) {
			//找到链表的最后
			if(temp.next == null) {
				break;//找到最后一个
			}
			temp = temp.next;//每次temp被赋值为temp.next后 temp.next 相当于之前的temp.next.next
		}
		temp.next = heroNode;
		heroNode.pre = temp;//形成双向链表
	}
	//双向链表的修改与单向链表一样
	public void update(HeroNode2 newHeroNode) {
		if(head.next == null) {
			System.out.println("链表为空 ");
			return;
		}
		//找到要修改的节点 定义一个辅助变量
		HeroNode2 temp = head.next;
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
		//删除节点
		//对于双向链表 我们可以直接找到要删除的节点 不用找前一个节点
		//被删除的节点 将不会有其他引用指向 会被垃圾回收机制回收
		public void delet(int no) {
			//head不能动 需要一个temp辅助节点 找到待删除节点的前一个节点
			//在比较时 是temp.next.no和需要删除的节点的no比较
			if(head.next == null) {
				System.out.println("链表为空 ");
				return;
			}
			HeroNode2 temp = head.next;
			boolean flag = false;//是否找到节点
			while(true) {
				if(temp==null) {
					//找到最后一位了
					break;	
				}
				if(temp.no == no ) {
					flag = true;//找到删除位置
					break;
				}
				temp = temp.next;//后移遍历
			}
			if(flag) {
				temp.pre.next = temp.next;//有头节点 pre不会为空
				if(temp.next != null) {
				temp.next.pre = temp.pre;//这里有问题 加入删除的节点是最后一个 会报错
				}
			}else {
				System.out.println("找不到要删除的节点" + no);
			}
		}
		public void addByOrder(HeroNode2 heroNode) {
			//头节点不动 通过一个辅助变量（指针） 帮助找到指定位置
			//因为单链表 所以找到的temp是位于添加位置的前一个节点
			HeroNode2 temp = head;
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
				heroNode.pre = temp;
				heroNode.next = temp.next;
				temp.next = heroNode;
			}
		}
	
}
class HeroNode2{
		public int no;
		public String name;
		public String nickname;
		public HeroNode2 next; //指向下一个节点
		public HeroNode2 pre;//指向前一个节点
		//构造器
		public HeroNode2(int hNo,String hName, String hNickname) {
			this.no = hNo;
			this.name = hName;
			this.nickname = hNickname;
		}
		//为了显示方便 重写toString方法
		
		public String toString() {
			return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
		}
}
