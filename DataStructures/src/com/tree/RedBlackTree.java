package com.tree;

public class RedBlackTree <K extends Comparable<K>,V>{
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private RBNode root;

	/**
	 * @return the root
	 */
	public RBNode getRoot() {
		return root;
	}

	
	//获取当前节点的父节点
	private RBNode parentof(RBNode node) {
		if(node != null) {
			return node.parent;
		}else {
			return null;
		}
	}
	
	// 节点是否为红色
	private boolean isRed(RBNode node) {
		if (node != null) {
			return node.color == RED;
		}
		return false;
	}

	// 节点是否为黑色
	private boolean isBlack(RBNode node) {
		if (node != null) {
			return node.color == BLACK;
		}
		return false;
	}

	// 设置节点为红色
	private void setRed(RBNode node) {
		if (node != null) {
			node.color = RED;
		} else {
			System.out.println("输入值为空无法设置");
		}
	}

	// 设置节点为黑色
	private void setBlack(RBNode node) {
		if (node != null) {
			node.color = BLACK;
		} else {
			System.out.println("输入值为空无法设置");
		}
	}
	
	//中需打印二叉树
	public void inOrderPrint() {
		inOrderPrint(this.root);
	}
	public void inOrderPrint(RBNode node) {
		if(node != null) {
			inOrderPrint(node.left);
			System.out.println("ket"+node.key+"value"+node.value);
			inOrderPrint(node.right);
		}
	}
	//左旋
//	1.将x的右子节点指向y的左子节点(ly)，将y的左子节点的父节点更新为x
//	2.当x的父节点(不为空时)，更新y的父节点为x的父节点，并将x的父节点指定子树(当前x的子树位置)指定为y
//	3.将x的父节点更新为y,将y的左子节点更新为x
	private void leftRotate(RBNode x) {
		RBNode y = x.right;
		x.right = y.left;
		if(y.left != null) {
			y.left.parent = x;
		}
		if(x.parent != null) {
			y.parent = x.parent;
			if(x == x.parent.left) {
				//如果x为左子节点
				x.parent.left = y;
			}else {//x为右子节点
				x.parent.right = y;
			}
		}else {
			//x为根节点
			this.root = y;
			this.root.parent = null;
		}
		x.parent = y;
		y.left = x;
	}
	//右旋
//	1.将y的左子节点指向x的右子节点，并且更新x的右子节点的父节点为y
//	2.当y的父节点不为空时， 更新x的父节点为y的父节点， 更新y的父节点的指定子节点(y当前的位置)为x
//	3.更新y的父节点为x，更新x的右子节点为y
	private void rightRotate(RBNode y) {
		RBNode x = y.left;
		y.left = x.right;
		if(x.right != null) {
			x.right.parent = y;
		}
		if(y.parent != null) {
			x.parent = y.parent;
			if(y == y.parent.left) {
				y.parent.left = x;
			}else {
				y.parent.right = x;
			}
		}else {
			this.root = x;
			this.root.parent = null;
		}
		y.parent = x;
		x.right = y;
	}
	//公开的插入方法
	public void insert(K key,V value) {
		RBNode node = new RBNode();
		node.setKey(key);
		node.setValue(value);
		//新节点一定是红色
		node.setColor(RED);
	}
	public void insert(RBNode node) {
		//1.查找当前node的父节点
		RBNode parent = null;
		RBNode x = this.root;
		while(x != null) {
			parent = x;
			//cmp>0说明node.key大于x.key需要到右子树查找
			//cmp == 0 说明需要进行替换操作
			int cmp = node.key.compareTo(x.key);
			if(cmp > 0) {
				x = x.right;
			}else if(cmp == 0) {
				x.setValue(node.getValue());
				return;
			}else {
				x = x.left;
			}
		}
		node.parent = parent;
		if(parent != null) {//是否为空树
			
			//判断node是在左子树还是右子树
			int cmp = node.key.compareTo(parent.getValue());
			if(cmp > 0) {
				parent.right = node;
			}else {
				parent.left = node;
			}
		}else {
			this.root = node;
		}
		insertFixUp(node);
	}
	
//	插入后修复红黑树平衡的方法
//	情景1:红黑树为空树，将跟节点染色为黑色
//	情景2:插入节点的key已经存在， 不需要处理
//	情景3:插入节点的父节点为黑色，因为你所插入的路径，黑色节点没有变化，所以红黑树依然平衡，所以不需要处理。
//	情景4需要咱们去处理
//	---情景4: 插入节点的父节点为红色
//	情景4.1: 叔叔节点存在，并且为红色(父-叔双红)，将爸爸和叔叔染色为黑色，将爷爷染色为红色，并且再以爷爷节点为当前节点进行下-轮处理
//	情景4.2:叔叔节点不存在，或者为黑色，父节点为爷爷节点的左子树
//	---情景4.2.1: 插入节点为其父节点的左子节点(LL情况)，将爸爸染色为黑色，将爷爷染色为红色，然后以爷爷节点右旋，就完成了
//	---情景4.2.2: 插入节点为其父节点的右子节点(LR情况)，以爸爸节点进行-次左旋， 得到LL双红的情景(4.2.1)，然后指定爸爸 节点为当前节点进行下一轮处理
//	---情景4.3: 叔叔节点不存在，或者为黑色，父节点为爷爷 节点的右子树
//	情景4.3.1:插入节点为其父节点的右子节点(RR情况)，将爸爸染色为黑色，将爷爷染色为红色，然后以爷爷节点左旋，就完成了
//	情景4.3.2:插入节点为其父节点的左子节点(RL情况)，以爸爸节点进行一 一次右旋， 得到RR双红的情景(4.3.1)，然后指定爸爸节点为当前节点进行下一 轮处理
	private void insertFixUp(RBNode node) {
		this.root.setColor(BLACK);//情景1
		RBNode parent = parentof(node);
		RBNode gradparent = parentof(parent);
		//情景4
		if(parent != null && isRed(parent)) {
			//父节点红色 那么一定存在爷爷节点（根节点一定为黑）
			RBNode uncle = null;
			if(parent == gradparent.left) {
				uncle = gradparent.right;
				//情景4.1叔叔节点存在并且为红色 父叔双红
				if(uncle != null && isRed(uncle)) {
					//将叔叔 父 染色为黑 爷爷染色为红 再以爷爷节点为当前节点 进行下一轮
					setBlack(parent);
					setBlack(uncle);
					setRed(gradparent);
					insertFixUp(gradparent);
					return;
				}
				//情景4.2叔叔节点不存在或者为黑色
				if(uncle == null || isBlack(uncle)) {
					//情景4.2.1: 插入节点为其父节点的左子节点(LL情况)，将爸爸染色为黑色，将爷爷染色为红色，然后以爷爷节点右旋
					if(node == parent.left) {
						setBlack(parent);
						setRed(gradparent);
						rightRotate(gradparent);
						return;
					}
					//情景 4.2.2插入节点为其父节点的右子节点(LR情况)，以爸爸节点进行-次左旋， 得到LL双红的情景(4.2.1)，
					//然后指定爸爸 节点为当前节点进行下一轮处理
					if(node == parent.right) {
						leftRotate(parent);
						insertFixUp(parent);
						return;
					}		
				}				
			}else {
				//父节点为爷爷节点的右子节点 
				//情景4.1叔叔节点存在并且为红色 父叔双红
				uncle = gradparent.left;
				if(uncle != null && isRed(uncle)) {
					//将叔叔 父 染色为黑 爷爷染色为红 再以爷爷节点为当前节点 进行下一轮
					setBlack(parent);
					setBlack(uncle);
					setRed(gradparent);
					insertFixUp(gradparent);
					return;
				}
				//情景4.3: 叔叔节点不存在，或者为黑色，父节点为爷爷 节点的右子树
				if(uncle == null && isBlack(uncle)) {
					//情景4.3.1:插入节点为其父节点的右子节点(RR情况)，将爸爸染色为黑色，将爷爷染色为红色
					//然后以爷爷节点左旋
					if(node == parent.right) {
						setBlack(parent);
						setRed(gradparent);
						leftRotate(gradparent);
						return;
					}
					//情景4.3.2:插入节点为其父节点的左子节点(RL情况)，以爸爸节点进行一 一次右旋
					// 得到RR双红的情景(4.3.1)，然后指定爸爸节点为当前节点进行下一 轮处理
					if (node == parent.left) {
						rightRotate(parent);
						insertFixUp(parent);
						return;
					}
				}
			}
		}
	}
	
	static class RBNode <K extends Comparable<K>,V>{
		private K key;
		private V value;
		private boolean color;//默认红色
		private RBNode left;
		private RBNode right;
		private RBNode parent;
		
		public RBNode() {
			
		}
		public RBNode(K key, V value, boolean color, RBNode left, RBNode right, RBNode parent) {
			super();
			this.key = key;
			this.value = value;
			this.color = color;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
		/**
		 * @return the key
		 */
		public K getKey() {
			return key;
		}
		/**
		 * @param key the key to set
		 */
		public void setKey(K key) {
			this.key = key;
		}
		/**
		 * @return the value
		 */
		public V getValue() {
			return value;
		}
		/**
		 * @param value the value to set
		 */
		public void setValue(V value) {
			this.value = value;
		}
		/**
		 * @return the color
		 */
		public boolean isColor() {
			return color;
		}
		/**
		 * @param color the color to set
		 */
		public void setColor(boolean color) {
			this.color = color;
		}
		/**
		 * @return the left
		 */
		public RBNode getLeft() {
			return left;
		}
		/**
		 * @param left the left to set
		 */
		public void setLeft(RBNode left) {
			this.left = left;
		}
		/**
		 * @return the right
		 */
		public RBNode getRight() {
			return right;
		}
		/**
		 * @param right the right to set
		 */
		public void setRight(RBNode right) {
			this.right = right;
		}
		/**
		 * @return the parent
		 */
		public RBNode getParent() {
			return parent;
		}
		/**
		 * @param parent the parent to set
		 */
		public void setParent(RBNode parent) {
			this.parent = parent;
		}
		
	}
	
}