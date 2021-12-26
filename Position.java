//Arpit Dang
//250960263
//Assignment 4 CS2210



public class Position {

	//Indicates the private integer variables
	
	private int x;
	private int y;
	
	
	public Position (int xtemp, int ytemp) { //Constructor method
		x = xtemp;
		y = ytemp;	
	}
	
	public int xCoord() { //Returns the xCoordinate
		return x;
	}
	
	public int yCoord() { //Returns the yCoordinate
		return y;
	}
	
	public int compareTo (Position p) { //Compares 2 coordinates. 
		
		if (y > p.yCoord()) { //Checks y coordinate first, and the original is bigger, returns 1
			return 1;
		}
		
		else if (y < p.yCoord()) { //if y is smaller, returns -1
			return -1;
		}
		
		else if (y == p.yCoord() && x > p.xCoord()) { //If y is the same, checks x and returns 1 and -1 based on which one is bigger
			return 1;
		}
		
		else if (y == p.yCoord() && x < p.xCoord()) {
			return -1;
		}
		
		else  { //If both, x and y are the same, returns 0 (only option left, so used else)
			return 0;
		}
		
		
	}
}
