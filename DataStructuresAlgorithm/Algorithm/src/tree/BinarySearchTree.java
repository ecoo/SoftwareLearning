package tree;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	private static class BinaryNode<AnyType>{
		BinaryNode(AnyType theElement){
			this(theElement,null,null);
		}
		BinaryNode(AnyType theElement,BinaryNode<AnyType> lt,BinaryNode<AnyType> rt){
			element = theElement;
			left = lt;
			right = rt;
		}
		AnyType element;//The data in the node
		BinaryNode<AnyType> left;//Left child
		BinaryNode<AnyType> right;//Right child
	}
	
	private BinaryNode<AnyType> root;
	
	public BinarySearchTree(){
		root = null;
	}
	public void makeEmpty(){
		root = null;
	}
	public boolean isEmpty(){
		return root == null;
	}
	public boolean contains(AnyType x){
		return contains(x,root);
	}
	public AnyType finMin(){
		if(isEmpty())
			throw new RuntimeException();
		return finMin(root).element;
	}
	public AnyType findMax(){
		if(isEmpty())
			throw new RuntimeException();
		return finMax(root).element;
	}
	public void insert(AnyType x){
		root = insert(x,root);
	}
	public void remove(AnyType x){
		root = remove(x,root);
	}
	/*
	 * Print the tree contents in sorted order.
	 */
	public void printTree(){
		if(isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}
	/*
	 * Internal method to print a subtree in sorted order.
	 * @param t the node that roots the subtree.
	 */
	private void printTree(BinaryNode<AnyType> t){
		if(t!=null){
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}
	/* 
	 * Internal method to find an item in a subtree.
	 * @param x is item to search for.
	 * @param t the node that roots the subtree.
	 * @return node containing the matched item.
	 */
	private boolean contains(AnyType x,BinaryNode<AnyType> t){
		if(t == null)
			return false;
		int compareResult = x.compareTo(t.element);
		if(compareResult<0)
			return contains(x,t.left);
		else if(compareResult>0)
			return contains(x,t.right);
		else
			return true;//Match
	}
	/*
	 * Internal method to find the smallest item in a subtree.
	 * @param t the node that roots the subtree
	 * @return node containing the smallest.
	 */
	private BinaryNode<AnyType> finMin(BinaryNode<AnyType> t){
		if(t == null)
			return null;
		else if(t.left==null)
			return t;
		return finMin(t.left);
	}
	/*
	 * Internal method to find the largest item in a subtree.
	 * @param t the node that roots the subtree.
	 * @return node containing the largest item.
	 */
	private BinaryNode<AnyType> finMax(BinaryNode<AnyType> t){
		//β�ݹ�
		/*if(t==null)
			return null;
		else if(t.right==null)
			return t;
		return finMax(t.right);*/
		//����β�ݹ�
		if(t!=null)
			while(t.right!=null)
				t = t.right;
		
		return t;
	}
	/*
	 * Internal method to insert into a subtree.
	 * @param x the item to insert.
	 * @param t the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> insert(AnyType x,BinaryNode<AnyType> t){
		if(t==null)
			return new BinaryNode<AnyType>(x,null,null);
		int compareResult = x.compareTo(t.element);
		if(compareResult<0)
			t.left = insert(x,t.left);
		else if(compareResult>0)
			t.right = insert(x,t.right);
		else
			;//Duplicate;do nothing
		return t;
	}
	/*
	 * Internal method to remove from a subtree.
	 * @param x the item to remove.
	 * @param t the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> remove(AnyType x,BinaryNode<AnyType> t){
		if(t==null)
			return t;//Item not found ; do nothing
		int compareResult = x.compareTo(t.element);
		if(compareResult<0)
			t.left = remove(x,t.left);
		else if(compareResult>0)
			t.right = remove(x,t.right);
		else if(t.left!=null&&t.right!=null){
			//Two children
			t.element =  finMin(t.right).element;
			t.right = remove(t.element,t.right);
		}else
			t = (t.left!=null)?t.left:t.right;
		return t;
		
	}
}
