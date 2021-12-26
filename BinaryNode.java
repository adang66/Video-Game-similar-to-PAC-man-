//Arpit Dang
//250960263
//Assignment 4 CS2210





public class BinaryNode {
	
	//Sets private parent, left, right and its value. 
	
	private BinaryNode parent;
	private BinaryNode left;
	private BinaryNode right;
	private Pixel value;
	
	public BinaryNode (Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) { //Constructor method if values are given
		this.value = value; 
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	public BinaryNode () { //Constructor method if values are not given, as they are set to null
		this.value = null;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	
	public BinaryNode getParent() { //Gets the parent
		return parent;
	}
	
	public void setParent (BinaryNode parent) { //Sets the parent
		this.parent = parent;
	}
	
	public void setLeft (BinaryNode p) { //Sets the left
		left = p;
	}
	
	public void setRight (BinaryNode p) { //Sets the right
		right = p;
	}
	
	public void setData (Pixel value) { //Sets the data
		this.value = value;
	}
	
	public boolean isLeaf() { //Checks if it is a leaf (returns true if it is)
		if (left == null && right == null & value == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Pixel getData() { //Gets the data
		return value;
	}
	
	public BinaryNode getLeft() { //Gets the left
		return left;
	}
	
	public BinaryNode getRight() { //Gets the right
		return right;
	}
	
	

}
