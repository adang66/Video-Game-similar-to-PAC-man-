//Arpit Dang
//250960263
//Assignment 4 (CS2210)




public class BinarySearchTree implements BinarySearchTreeADT{
	
	private BinaryNode root; //Private variable
	
	public BinarySearchTree () { //Constructor method that enables the root into a BinaryNode
		root = new BinaryNode();
	}
	
	public Pixel get (BinaryNode r, Position key) { //Used to return the Pixel storing the given key if it is even stored in the tree to begin with. If not, returns null.
		return gettingNode (r, key).getData();	//"gettingNode" is a private class created by me written down below. 
		
	}
	
	public void put (BinaryNode r, Pixel data) throws DuplicatedKeyException { //Puts the given Pixel data in the tree if there is no item with the same key. If there is, then throws the DuplicatedKeyException
		BinaryNode nodeTemp = gettingNode (r, data.getPosition()); //Makes a temporary BinaryNode, with the given Pixel data.
		
		if (nodeTemp.isLeaf() == false ) { 
			//Checks if that temporary node is a leaf or not, if it is NOT a leaf, data cannot be added because it is an internal node.
			throw new DuplicatedKeyException("Already the key exists!");
		}
		
		else {
			nodeTemp.setData(data); //Enter the given Pixel data into the code
			BinaryNode leftSide = new BinaryNode(); //Make a new node for the left side
			BinaryNode rightSide = new BinaryNode(); //Make a new node for the right side
			nodeTemp.setLeft(leftSide); //Attach the new left node to the original temporary node
			nodeTemp.setRight(rightSide);//Attach the new right node to the original temporary node
			leftSide.setParent(nodeTemp); //Make the original temporary node parents for both, left and right nodes.
			rightSide.setParent(nodeTemp);
		}
		
	}
	
	public void remove (BinaryNode r, Position key) throws InexistentKeyException { //Code to remove a BinaryNode from the tree

		
		BinaryNode nodeTemp = gettingNode(r, key); //Uses the private method written below to find the code
		
		if (nodeTemp.isLeaf()) { //Checks if the temporary Node is a leaf, or not, and if it is a leaf, means that that the input BinaryNode DOES NOT exist.
			throw new InexistentKeyException("The entered pixel DOES NOT exist!"); //Throws the inexsistent key exception
			
		} 
		
		else {
			
			if (nodeTemp.getLeft().isLeaf() || nodeTemp.getRight().isLeaf()) { //Checks if the children of the given node are leaves, and even if one of them is, it follows through with this 'if' statement
				
				if (nodeTemp.getLeft().isLeaf()) { //If the left one is a leaf, it makes 2 new temporary nodes, the right and the parent node of the original node.
					BinaryNode rightNode = nodeTemp.getRight();
					BinaryNode parentNode = nodeTemp.getParent();
					
					
					if (parentNode == null) { //If the parentNode is null, indicating that the given node is the ROOT, it just makes the right node as the new Root.
						this.root = rightNode;
						rightNode.setParent(null);
						
					} 
					
					else { //If the entered node's parent is NOT the root, these steps follow.
						
						if (parentNode.getRight().equals(nodeTemp)) { //Checks to see if the right child of the parent node, is the same as the orignalNode that should be removed, if so, removes the orginalNode amd connects the parent to the child.
							parentNode.setRight(rightNode);
							rightNode.setParent(parentNode);
						} 
						
						else { //Does the same, except for the left side, if right side statement is NOT true
							parentNode.setLeft(rightNode);
							rightNode.setParent(parentNode);
							
						}
					}
				} 
				
				else { //Since at least one of the leafs is a child, and the left side WAS NOt the leaf, now this assumes that the RIGHT side is the leaf of the original Node. Follows the same steps as above, except for the left side now
					
					BinaryNode leftNode = nodeTemp.getLeft();
					BinaryNode parentNode = nodeTemp.getParent();
					
					if (parentNode == null) {
						
						this.root = leftNode;
						leftNode.setParent(null);
					} 
					
					else {
						
						if (parentNode.getRight().equals(nodeTemp)) {
							parentNode.setRight(leftNode);
							leftNode.setParent(parentNode);
							
						} 
						
						else {
							parentNode.setLeft(leftNode);
							leftNode.setParent(parentNode);
						
						}
					}
				}
			} 
			
			
			else { //This occurs if NONE of the children are leaf nodes, meaning that the node that wants to be removed is now an INTERNAL node
				
				BinaryNode nodeTemp1 = nodeTemp.getRight(); //Creates a temporary nodes that is the RIGHT child of it
				
				while (nodeTemp1.isLeaf() == false) { //Until it reaches the while loop, it keeps going to the left of the temporary node
					
					nodeTemp1 = nodeTemp1.getLeft();
				}
				
				nodeTemp1 = nodeTemp1.getParent(); //Once it has reached the leafNode, it takes the parent of that leaf
				nodeTemp.setData(nodeTemp1.getData()); //It replaces the data from the temporary node to the original node's data
				BinaryNode smallParent = nodeTemp1.getParent(); //Creates a new node which is the smallest PARENT. This is because since we went to the very left through the while loop. 
			
				if (smallParent.getLeft().equals(nodeTemp1)) { //This section sets the parent to the smallest node, this is from the example learned in the class lectures
					
					smallParent.setLeft(nodeTemp1.getRight());
				} 
				
				else {
					
					smallParent.setRight(nodeTemp1.getRight());
				}
				
				nodeTemp1.getRight().setParent(smallParent); //
			}
		}
	}
	
	public Pixel successor (BinaryNode r, Position key) { //Returns the next largest key in the BST
		
	
		if (r.isLeaf() == true) { //Checks if it the given node is a leaf, if so, return null
			return null;
		}
		
		else {
			
			BinaryNode tempNode = gettingNode (r, key); //Gets the node from the given data using the private method
			
			if ((tempNode.isLeaf() == false) && (tempNode.getRight().isLeaf() == false)) { // If the very right node, and the tempNode itself is NOT a leaf node, it goes to the very right node, and then, keeps going to the left using the while loop
				
				tempNode = tempNode.getRight(); 
				
				while (!tempNode.isLeaf()) {
					tempNode = tempNode.getLeft(); //After 1 right, keeps going to the very left, and that is the SUCCESSOR node
				}
				
				return tempNode.getParent().getData(); //Returns it
			}
			
			else { //If the right the original node OR the right child is a LEAF, then these steps occur
				
				BinaryNode parentNode = tempNode.getParent(); //Creates a temporary parent, which is the parent of the original node
				
				while (parentNode != null  && parentNode.getRight() == tempNode) { // It keeps going up until the original Node matches the right node of the temporary parent node. 
					tempNode = parentNode;
					parentNode = parentNode.getParent();
					
				}
				
				if (parentNode != null) { //If the parent node is NOT null from the while loop, it returns that data, and the internal node is removed
					return parentNode.getData();
				}
				
				else { //If it is null, then nothing is returned back
					return null;
				}
			}
		}
		
	}
		
		
		
	public Pixel predecessor (BinaryNode r, Position key) { //Returns the number before the given node in the order. // Similar steps to the Successor method, but the opposite is happening. E.g. instead of going right and then while loop left. This method goes left, and then while loop right. 
		
		//Similar concept to the Successor method, but the opposite is happening. Switch up the RIGHTs and LEFTs
		
		if (r.isLeaf()) {
			
			return null;
		}
		
		else {
			
			BinaryNode tempNode = gettingNode (r, key);
			
			if (tempNode.isLeaf() == false && tempNode.getLeft().isLeaf() == false) {
				
				tempNode = tempNode.getLeft();
				
				while (tempNode.isLeaf() == false) {
					tempNode = tempNode.getRight();
					
				}
				
				return tempNode.getParent().getData();
			}
			
			else {
				
				BinaryNode parentNode = tempNode.getParent();
				
				while (parentNode != null && parentNode.getLeft() == tempNode) {
					
					tempNode = parentNode;
					parentNode = parentNode.getParent();
				}
				
				if (parentNode == null) {
					return null;
				}
				
				else {
					return parentNode.getData();
				}
			}
		}
	}
	
	public Pixel smallest (BinaryNode r) throws EmptyTreeException { //Finds the SMALLEST node. Goes all the way to the left. 
		
		if (r.isLeaf() == true ) { //If empty, throws an exception
			throw new EmptyTreeException(null);
		}
		
		if (r.getLeft().isLeaf()) { //If the given node is a lead, just returns it.
			return r.getData();
		}
		
		else { //Uses the while loop to just keep going left
			BinaryNode tempNode = r;
			
			while (tempNode.getLeft().isLeaf() == false) {
				tempNode = tempNode.getLeft();
			}
			
			return tempNode.getData();
		}
	}
	
	public Pixel largest (BinaryNode r) throws EmptyTreeException {
		
		//Same concept as the SMallest method, except instead of left, this method goes RIGHT
		
		if (r.isLeaf() == true ) {
			throw new EmptyTreeException(null);
		}
		
		if (r.getLeft().isLeaf()) {
			return r.getData();
		}
		
		else {
			BinaryNode tempNode = r;
			
			while (tempNode.getRight().isLeaf() == false) {
				tempNode = tempNode.getRight();
			}
			
			return tempNode.getData();
		}
		
	}
	
	public BinaryNode getRoot() { //Returns the root
		return root;
	}
	
	private BinaryNode gettingNode (BinaryNode r, Position key) { //Private method that gets the code.
		if (r.isLeaf()) {
			return r;
		}
		else {
			if (key.compareTo(r.getData().getPosition()) == 0) { //If it is the same, it returns it
				return r;
			}
			
			else if (key.compareTo(r.getData().getPosition()) == 1) { //When comparison equals 1, using the rules from the assignment PDF, it uses recursive to do the same steps with the right Node
				return gettingNode (r.getRight(), key);
			}
			
			else  { //This indicates when it is compared to it, it is -1, and recursive method using the left node
				return gettingNode (r.getLeft(), key);
			}
		}
		
	}
	
}
