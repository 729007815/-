package com.tree;

public class BinaryTreeDemo {
	public static void main(String[] args) {
		
		//创建二叉树
		BinaryTree binaryTree = new BinaryTree();
		HeroNode root = new HeroNode(1,"菲兹");
		HeroNode n2 = new HeroNode(2,"维恩");
		HeroNode n3 = new HeroNode(3,"奎因");
		HeroNode n4 = new HeroNode(4,"潘森");
		
		root.setLeft(n2);
		root.setRight(n3);
		n3.setRight(n4);
		binaryTree.setRoot(root);
//		System.out.println("前序遍历");//1234
//		binaryTree.preOrder();
//		System.out.println("中序遍历");//2134
//		binaryTree.infixOrder();
//		System.out.println("后序遍历");//2341
//		binaryTree.postOrder();
		
		//前序遍历
//		System.out.println("前序遍历");
//		HeroNode resNode = binaryTree.preOrderSearch(4);
//		if(resNode != null) {
//			System.out.println(resNode.getName()+" "+resNode.getNo());
//			
//		}else {
//			System.out.println("未找到");
//		}
		
		//测试删除
		System.out.println("删除前 前序遍历");
		binaryTree.preOrder();
		binaryTree.delNode(3);
		System.out.println("删除后 前序遍历");
		binaryTree.postOrder();
	}
}
//定义BinaryTreeDemo二叉树
class BinaryTree{
	private HeroNode root;
	public HeroNode getRoot() {
		return root;
	}
	public void setRoot(HeroNode root) {
		this.root = root;
	}
	//前序遍历
	public void preOrder() {
		if(this.root != null) {
			this.root.preOrder();
		}else {
			System.out.println("二叉树为空 无法遍历");
		}
	}
	//中序遍历
	public void infixOrder() {
		if(this.root != null) {
			this.root.infixOrder();
		}else {
			System.out.println("二叉树为空 无法遍历");
		}
	}
	//后序遍历
	public void postOrder() {
		if(this.root != null) {
			this.root.postOrder();
		}else {
			System.out.println("二叉树为空 无法遍历");
		}
	}
	public HeroNode preOrderSearch(int no) {
		if(this.root != null) {
			return this.root.preOrderSearch(no);
		}else {
			System.out.println("二叉树为空 无法遍历");
			return null;
		}
		
	}
	//中序遍历
	public HeroNode infixOrderSearch(int no) {
		if(this.root != null) {
			return this.root.infixOrderSearch(no);
		}else {
			System.out.println("二叉树为空 无法遍历");
			return null;
		}
	}
	//后序遍历
	public HeroNode postOrderSearch(int no) {
		if(this.root != null) {
			return this.root.postOrderSearch(no);
		}else {
			System.out.println("二叉树为空 无法遍历");
			return null;
		}
	}
	//删除节点
	public void delNode(int no) {
		if(root != null) {
			//如果只有一个root节点 立即判断是否为要删除节点
			if(root.getNo() == no) {
				root = null;
			}else {
				root.delNode(no);
			}
		}
	}
	
}
//创建HeroNode节点
class HeroNode{
	private int no;
	private String name;
	private HeroNode left;
	private HeroNode right;
	/**
	 * @return the no
	 */
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
	//前序遍历
	//1先输出当前节点(初始的时候是root节点)
	//2如果左子节点不为空，则递归继续前序遍历
	//2如果右子节点不为空，则递归继续前序遍历

	public void preOrder() {
		System.out.println(this);//先输出父节点
		//向左递归遍历
		if(this.left != null) {
			this.left.preOrder();
		}
		if(this.right != null) {
			this.right.preOrder();
		}
	}
	//中序遍历
	//1如果当前节点的左子节点不为空，则递归中序遍历，
	//2输出当前节点 如果当前节点的右子节点不为空，则递归中序遍历

	public void infixOrder() {
		//向左递归遍历
		if(this.left != null) {
			this.left.preOrder();
		}
		//输出父节点
		System.out.println(this);
		if(this.right != null) {
			this.right.preOrder();
		}
	}
	//后序遍历
	//1如果当前节点的左子节点不为空，则递归后序遍历，
	//2如果当前节点的右子节点不为空，则递归后序遍历
	//3输出当前节点
	

	public void postOrder() {
		//向左递归遍历
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
		// 输出父节点
		System.out.println(this);
	}
	//前序查询
	public HeroNode preOrderSearch(int no) {
		System.out.println("进入前序遍历 比较次数");
		if(this.no == no) {
			return this;
		}else {
			HeroNode resNode = null;
			if(this.left != null) {
				resNode = this.left.preOrderSearch(no);
			}
			if(resNode != null) {
				return resNode;
			}
			//左边没找到则找右边
			if(this.right != null) {
				resNode = this.right.preOrderSearch(no);
			}
			return resNode;
			
		}
		
	}
	//中序遍历
	public HeroNode infixOrderSearch(int no) {
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.infixOrderSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		// 左边没找到则找中间
		if(this.no == no) {
			return this;
		}
		if (this.right != null) {
			resNode = this.right.infixOrderSearch(no);
		}
		return resNode;

	}
	//后序遍历查找
	public HeroNode postOrderSearch(int no) {
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.postOrderSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		// 左边没找到则找右
		if (this.right != null) {
			resNode = this.right.postOrderSearch(no);
		}
		//右没找到则找根节点
		if(this.no == no) {
			return this;
		}
		return resNode;

	}
	public void delNode(int no) {
		
		//递归删除结点
		//1.如果删除的节点是叶子节点，则删除该节点
		//2.如果删除的节点是非叶子节点，则删除该子树
//		1.因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点。
//		2.如果当前结点的左子结点不为空，并且左子结点就是要删除结点，就将this.left = null;并且就返回(结束递归删除)
//		3.如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right= null ;并 且就返回(结束递归删除)
//		4.如果第2和第3步没有删除结点,那么我们就需要向左子树进行递归删除
//		5.如果第4步也没有删除结点，则应当向右子树进行递归删除。

		 //2.如果当前结点的左子结点不为空,并且左子结点就是要删除结点，就将this.left = null;并且就返回(结束递归删除)
		if(this.left != null && this.left.no == no) {
			this.left = null;
			return;
		}
		if(this.right != null && this.right.no == no) {
			this.right = null;
			return;
		}
		if(this.left != null) {
			this.left.delNode(no);
			
		}
		if(this.right != null) {
			this.right.delNode(no);
			
		}
		 
	}
	
}