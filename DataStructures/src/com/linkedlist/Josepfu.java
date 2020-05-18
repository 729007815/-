package com.linkedlist;

public class Josepfu {
//丢手绢问题
	public static void main(String[] args) {
		CircleSingleLinkedList a = new CircleSingleLinkedList();
		a.addBoy(5);//加入五个小孩节点
		a.showBoy();
		a.countBoy(1, 2, 5);//第一个小孩开始 每次数两个 五个小孩玩
	}

}
//创建一个环形单向链表
class CircleSingleLinkedList{
	//创建一个first节点 当前无编号
	private Boy first = new Boy(-1);
	//添加小孩节点 构建环形链表
	public void addBoy(int nums) {
		//数据校验
		if(nums<=0) {
			System.out.println("nums值不正确");
			return;
		}
		Boy curBoy =null;//辅助指针帮助构建环形链表
		//for循环创建环形链表
		for(int i=1;i<=nums;i++) {
			//根据编号 创建小孩节点
			Boy boy = new Boy(i);
			//如果是第一个小孩
			if(i==1) {
				first = boy;
				first.setNext(first);//构成环
				curBoy =first;
			}else {
				curBoy.setNext(boy);
				boy.setNext(first);
				curBoy = boy;//指针后移
			}
		}
	}
	public void showBoy() {
		//判断是否为空
		if(first==null) {
			System.out.println("没有小孩");
			return;
		}
		//first不动 需要辅助指针
		Boy curBoy = first;
		while(true) {
			System.out.println("小孩编号" + curBoy.getNo());
			if(curBoy.getNext() == first) {
				break;
				
			}
			curBoy = curBoy.getNext();
		}
	}
	//根据用户的输入 计算出小孩出圈顺序
	//startNo 表示从第几个小孩开始数
	//countNum 数几次
	//nums 最初有多少个小孩在圈中
	public void countBoy(int startNo,int countNum,int nums) {
		if(first == null || startNo < 1 || startNo > nums) {
			System.out.println("参数错误");
			return;
		}
		//创建辅助指针 帮助小孩出圈
		Boy helper =first;
		while(true) {//应让其指向链表最后一个节点
			if(helper.getNext() == first) {
				break;
			}else {
				helper = helper.getNext();
			}
		}
		//小孩报数前 先让first与helper移动k-1次
		for(int j=0;j<startNo-1;j++) {
			//最开始的小孩开始报数 （报数为2只移动一次所以-1）
			first = first.getNext();
			helper = helper.getNext();		
		}
		//出圈 循环操作 直到圈中只有一个节点
		while(true) {
			if(helper == first) {
				//此时圈中只有一个节点
				break;
			}else {
				for(int j=0;j<countNum-1;j++) {
					//最开始的小孩开始报数 （报数为2只移动一次所以-1）
					first = first.getNext();
					helper = helper.getNext();		
				}
				//此时first为要出圈的小孩
				System.out.println("出圈小孩编号"+first.getNo());
				//将first节点出圈
				first = first.getNext();
				helper.setNext(first);
			}
				
		}
		System.out.println("最后仅有的小孩编号"+first.getNo());
		
		
	}
}
//先创建一个boy类 表示一个节点
class Boy{
	private int no;
	private Boy next;
	public Boy(int no) {
		this.no = no;
	}	
	public int getNo() {
		return no;
	}	
	
	public Boy getNext() {
		return next;
	}
	
	public void setNext(Boy next) {
		this.next = next;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
}