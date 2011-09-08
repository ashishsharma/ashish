package main.java.threads;

// Notes:
// Even if it is synchronized - it is mutable. Any 2 threads can change the state of the object.

// Refer to the notes at bottom of the class

public class Mutable_Synchronized_RGB {
	
	private int red;
	private int blue;
	private int green;
	private String colorName;
	
	public Mutable_Synchronized_RGB(int r, int b, int g, String name) {
		boolean valid = check(r,g,b);
		if(!valid)
			throw new IllegalArgumentException();
		this.red = r;
		this.blue = b;
		this.green = g;
		this.colorName = name;
	}
	
	private boolean check(int r, int b, int g) {
		if ( (r < 0 || r > 256) || (b < 0 || b > 256) || (g < 0 || g > 256))
			return false;
		return true;
	}
	
	public synchronized void set (int r, int b, int g, String name) {
		boolean valid = check(r,g,b);
		if(!valid)
			throw new IllegalArgumentException();
		this.red = r;
		this.blue = b;
		this.green = g;
		this.colorName = name;
	}
	
	public synchronized int getRGB() {
		return ((red << 16) | (green << 8) | blue);
	}
	
	public synchronized String getName() {
		return colorName;
	}

	public synchronized void invert() {
		red = 255 - red;
		blue = 255 - blue;
		green = 255 - green;
		colorName = "Inverted " + colorName;
	}
}

// NOTES

//SynchronizedRGB color = new SynchronizedRGB(0, 0, 0, "Pitch Black");
//...
//int myColorInt = color.getRGB();      //Statement 1
//String myColorName = color.getName(); //Statement 2
//If another thread invokes color.set after Statement 1 but before Statement 2, the value of myColorInt won't match the value of myColorName. To avoid this outcome, the two statements must be bound together:
//synchronized (color) {
//  int myColorInt = color.getRGB();
//  String myColorName = color.getName();
//} 
//This kind of inconsistency is only possible for mutable objects Ñ it will not be an issue for the immutable version of SynchronizedRGB.
