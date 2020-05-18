package com.tree.thread;
public class ThreadedBinaryTreeDemo {

	public static void main(String[] args) {
	//测试中序线索二叉树功能
		ThreadedBinaryTree binaryTree = new ThreadedBinaryTree();
		HeroNode root = new HeroNode(1,"菲兹");
		HeroNode n2 = new HeroNode(3,"维恩");
		HeroNode n3 = new HeroNode(6,"奎因");
		HeroNode n4 = new HeroNode(8,"潘森");
		HeroNode n5 = new HeroNode(10,"维克多");
		HeroNode n6 = new HeroNode(14,"菲奥娜");
		//简单处理手动创建
		root.setLeft(n2);
		root.setRight(n3);
		n2.setLeft(n4);
		n2.setRight(n5);
		n3.setLeft(n6);
		binaryTree.setRoot(root);
		binaryTree.threadedNodes();
		HeroNode le = n5.getLeft();
		HeroNode ri = n5.getRight();
		System.out.println(le);
		System.out.println(ri);
		
		System.out.println("线索化遍历");
		binaryTree.threadedList();

	}

}

//定义ThreadedBinaryTree二叉树
class ThreadedBinaryTree{
	private HeroNode root;
	//为实现线索化 需要创建给指向当前节点的前驱节点的指针
	//在递归线索化时 pre总是保留前一个节点
	private HeroNode pre = null;
	public HeroNode getRoot() {
		return root;
	}
	public void setRoot(HeroNode root) {
		this.root = root;
	}
	//重载方法
	public void threadedNodes() {
		this.threadedNodes(root);
	}
	//编写对二叉树进行中序线索化的方法
	//node就是当前需要线索化的节点
	public void threadedNodes(HeroNode node) {
		if(node == null) {
			return;
		}
		//1.先线索化左子树
		threadedNodes(node.getLeft());
		//2.线索化当前节点
		//处理当前节点的前驱节点
		if(node.getLeft() == null) {
			//让当前节点的左指针 指向前驱节点
			node.setLeft(pre);
			//修改当前节点的左指针类型 指向前驱节点
			node.setLeftType(1);
		}
		//处理后继节点
		if(pre!=null && pre.getRight() == null) {
			//让前驱节点的右指针指向当前节点
			pre.setRight(node);
			pre.setRightType(1);
		}
		//每处理一个节点后让当前节点是下一个节点的前驱节点
		pre = node;
		
		//3.再线索化右子树
		threadedNodes(node.getRight());
		
	}
	//中序遍历线索化二叉树
	public void threadedList() {
		//定义一个变量 存储当前遍历的节点 从root开始
		HeroNode node = root;
		while(node != null) {
			//先向左遍历
			while(node.getLeftType() == 0) {//表示不是线索化处理后的
				node = node.getLeft();
				
			}
			System.out.println(node);
			//后继节点则一直输出
			while(node.getRightType() == 1) {
				node = node.getRight();
				System.out.println(node);
			}
			node = node.getRight();
		}
	}
	
	
}

//创建HeroNode节点
class HeroNode{
	private int no;
	private String name;
	private HeroNode left;
	private HeroNode right;
	//如果 leftType == 0 表示指向左子树 1表示指向前驱节点
	//如果 rightType == 0 表示指向左子树 1表示指向后继节点
	private int leftType;
	private int rightType;
	
	/**
	 * @return the leftType
	 */
	public int getLeftType() {
		return leftType;
	}
	/**
	 * @param leftType the leftType to set
	 */
	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}
	/**
	 * @return the rightType
	 */
	public int getRightType() {
		return rightType;
	}
	/**
	 * @param rightType the rightType to set
	 */
	public void setRightType(int rightType) {
		this.rightType = rightType;
	}
	public int getNo() {
		return no;
	}
	public HeroNode(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(int no) {
		this.no = no;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the left
	 */
	public HeroNode getLeft() {
		return left;
	}
	/**
	 * @param left the left to set
	 */
	public void setLeft(HeroNode left) {
		this.left = left;
	}
	/**
	 * @return the right
	 */
	public HeroNode getRight() {
		return right;
	}
	/**
	 * @param right the right to set
	 */
	public void setRight(HeroNode right) {
		this.right = right;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + "]";
	}
	
	
	
}