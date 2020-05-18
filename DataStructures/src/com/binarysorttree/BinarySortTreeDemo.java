package com.binarysorttree;

public class BinarySortTreeDemo {

	public static void main(String[] args) {
		int[] arr = {7,3,10,12,5,1,9,2};
		BinarySortTree binarySortTree = new BinarySortTree();
		//添加到二叉排序树中
		for(int i = 0;i<arr.length;i++) {
			binarySortTree.add(new Node(arr[i]));
		}
		//中序遍历
		System.out.println("中序遍历");
		binarySortTree.infixOrder();
		binarySortTree.delNode(2);
		binarySortTree.delNode(3);
		binarySortTree.delNode(7);
		binarySortTree.delNode(5);
		binarySortTree.delNode(9);
		binarySortTree.delNode(12);
		binarySortTree.delNode(10);
		binarySortTree.delNode(1);
		
		System.out.println("==");
		binarySortTree.infixOrder();
	
	}

	
}
//创建二叉排序树
class BinarySortTree{
	private Node root;
	//添加节点
	public void add(Node node) {
		if(root == null) {
			root = node;
		}else {
			root.add(node);
		}
	}
	//中序遍历
	public void infixOrder() {
		if(root != null) {
			root.infixOrder();
			
		}else {
			System.out.println("树为空 无法遍历");
		}
	}
	//查找要删除的节点
	public Node search(int value) {
		if(root == null) {
			return null;
		}else {
			return root.search(value);
		}
	}
	//查找要删除节点的父节点
	public Node searchParent(int value) {
		if(root == null) {
			return null;
		}else {
			return root.searchParent(value);
		}
	}
	
	//node 传入的节点（当做二叉排序树的根节点）
	//返回该二叉排序树的最小节点的值 并删除该值
	public int delRightTree(Node node) {
		Node target = node;
		//循环查找右子树的左节点 找到最小值
		while(target.left != null) {
			target = target.left;
		}
		//这是target指向最小节点 删除
		delNode(target.value);
		return target.value;
		
	}
	
	//删除节点
	public void delNode(int value) {
		if(root == null) {
			return;
		}
		//	要删除的节点
		Node targetNode = search(value);
		if(targetNode == null) {
			return ;
		}
		if(root.left == null &&root.right == null) {
			//只有一个节点	
			root = null;
			return;
		}
		Node parent = searchParent(value);
		if(targetNode.left == null && targetNode.right == null ) {
			//节点为叶子结点
			//判断targetNode是其父节点的左子节点还是右子节点
			if(parent.left != null && parent.left.value == value) {
				parent.left = null;
			} else if(parent.right != null && parent.right.value == value) {
				parent.right = null;
			}
		}else if(targetNode.left != null && targetNode.right != null) {
			//有两个子树节点的节点
			int minVal = delRightTree(targetNode.right);
			targetNode.value = minVal;
		} else {
			// 删除只有一颗子树的节点
			if (targetNode.left != null) {
				if (parent != null) {// 非根节点
					// 有左子节点
					if (parent.left.value == value) {
						// 是左子节点
						parent.left = targetNode.left;
					} else {
						// 是右子节点
						parent.right = targetNode.left;
					}
				} else {
					root = targetNode.left;
				}
			} // 有右子节点
			else {
				if (targetNode.right != null) {
					if (parent.left.value == value) {
						// 是左子节点
						parent.left = targetNode.right;
					} else {
						// 是右子节点
						parent.right = targetNode.right;
					}
				} else {
					root = targetNode.right;
				}
			}
		}
	}
}
//节点
class Node{
	int value;
	Node left;
	Node right;
	public Node(int value) {
		this.value = value;
	}
	
	public String toString() {
		return "Node [value=" + value + "]";
	}

	//添加节点
	//递归 需要满足二叉排序树
	public void add(Node node) {
		if(node == null) {
			return;
		}else {
		//判断传入的节点的值 和当前子树的根节点的关系
			if(node.value < this.value) {
				if(this.left == null) {
					this.left = node;
				}else {
					this.left.add(node);//递归找到位置
				}
			}else {
					if(this.right == null) {
						this.right = node;
					}else {
						this.right.add(node);//递归找到位置
					}
				}
			}
		}
	//中序遍历
	public void infixOrder() {
		if(this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if(this.right != null) {
			this.right.infixOrder();
		}
	}
	//查找要删除的节点
	public Node search(int value) {
		if(value == this.value) {
			//找到就是该节点
			return this;
		}else if(value < this.value){
			//如果查找到的值小于当前节点的值 则向左子树找
			//若左子节点为空则不能继续查找
			if(this.left == null) {
				return null;
			}
			return this.left.search(value);
		}else {
			//如果查找到的值大于当前节点的值 则向右子树找
			if(this.right == null) {
				return null;
			}
			return this.right.search(value);
		}
	}
	//查找要删除节点的父节点
	public Node searchParent(int value) {
		if((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
			return this;//当前节点为要删除节点的父节点
		}else {
			if(value < this.value && this.left != null){
				//如果查找到的值小于当前节点的值 则向左子树找
				//若左子节点为空则不能继续查找
				return this.left.searchParent(value);
			}
			else if(value >= this.value && this.right != null){
				//如果查找到的值大于当前节点的值 则向右子树找
				//若右子节点为空则不能继续查找
				return this.right.searchParent(value);
			}else {
				//无父节点 
				return null;
			}
		}
		
	}
}
	

