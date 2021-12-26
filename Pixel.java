//Arpit Dang
//250960263
//Assignment 4 CS2210





public class Pixel {
	
	//The private variables, the int is the color, Position is p.
	private int color;
	private Position p;
	
	public Pixel (Position p, int color) { //Constructor method
		
		this.p = p;
		this.color = color;
		
	}
	
	public Position getPosition() { //Returns the Position
		return p;
	}
	
	public int getColor() { //Returns the color
		return color;
	}

}
