//Arpit Dang
//250960263
//Assignment 4 CS2210



public class Icon implements IconADT {
	
	//Private variables
	
	private BinarySearchTree tree;
	private int id;
	private int width;
	private int height;
	private String type;
	private Position pos;
	
	public Icon (int id, int width, int height, String type, Position pos) { //Constructor method, and sets all the variables with the given data
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		this.tree = new BinarySearchTree();
	}
	
	public void setType (String type) { //Sets the type
		this.type = type;
	}
	
	public int getWidth() { //Gets the width
		return width;
	}
	
	public int getHeight() { //Gets the heigth
		return height;
	}
	
	public String getType () { //Gets the type
		return type;
	}
	
	public int getId() { //Gets the ID
		return id;
	}
	
	public Position getOffset () { //Gets the position
		return pos;
	}
	
	public void setOffset (Position value) { //Sets the position
		this.pos = value;
	}
	
	public void addPixel (Pixel pix) throws DuplicatedKeyException { //Adds the pixel into the tree
		tree.put(tree.getRoot(), pix);
	}
	
	public boolean intersects (Icon otherIcon) {	//Checks if the 2 icons intersect
		
			
		try { //Have the try and catch method because the tree might be empty
		
		
			int xOriginal = this.getOffset().xCoord(); //Gets the x and y coordinates of the original icon, of their position
			int yOriginal = this.getOffset().yCoord();
			int xOther = otherIcon.getOffset().xCoord();//Gets the x and y coordinates of the other icon that it will compare it to, of their position
			int yOther = otherIcon.getOffset().yCoord();
			
			Pixel smallestP = otherIcon.tree.smallest(tree.getRoot()); //Gets the smallest pixel
		
			while (tree.successor(tree.getRoot(), smallestP.getPosition()) != null) { //Keeps going if is NOT null, until it goes through all of the pixels
				
				int x;
				int y;
				x = smallestP.getPosition().xCoord() + xOriginal - xOther; //Finds the specifc position according to the rules given the assignment instructions
				y = smallestP.getPosition().yCoord() + yOriginal - yOther;
				
				boolean test; //Makes a boolean test
				Pixel px = otherIcon.tree.get(otherIcon.tree.getRoot(), new Position(x,y)); //Tries to find the pixel in the OtherIcon tree
				
				if (px == null) { //If not there, test failed
					test = false;
				}
				
				else { //Else, it is true
					test = true;
				}
				
				if (test) { //If it matches, it means that it overlaps and returns TRUE, and then the method ends. Uses a private method to find the pixel.
					return true;
				}
				
				smallestP = tree.successor(tree.getRoot(), smallestP.getPosition()); //If not, then moves onto the next pixel in order
			
			}
			
			} catch (EmptyTreeException e) { //If catches an empty tree, gives an error
			
			}
		
			return false; //If it went through all the pixels, and has not met, it returns false
		
	}
	


}
