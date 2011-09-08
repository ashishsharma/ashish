package main.java.threads;

// Making the class immutable without Synchronized


// Strategy for Defining Immutable Objects: 

// 1) Don't provide "setter" methods Ñ methods that modify fields or objects referred to by fields.
// 2) Make all fields final and private.
// 3) Don't allow subclasses to override methods. The simplest way to do this is to declare the class as final. A more sophisticated approach is to make the constructor private and construct instances in factory methods.
// 4) If the instance fields include references to mutable objects, don't allow those objects to be changed:
// 		- Don't provide methods that modify the mutable objects.
// 		- Don't share references to the mutable objects.


// class is final
final public class Immutable_RGB {

// Applying this strategy to Mutable_Synchronized_RGB.java results in the following steps:
//
//		1) There are two setter methods in this class. The first one, set, arbitrarily transforms the object, and has no place in an immutable version of the class. The second one, invert, can be adapted by having it create a new object instead of modifying the existing one.
//		2) All fields are already private; they are further qualified as final.
//		3) The class itself is declared final.
//		4) Only one field refers to an object, and that object is itself immutable. Therefore, no safeguards against changing the state of "contained" mutable objects are necessary.

	// fields are final
    final private int red;
    final private int green;
    final private int blue;
    final private String name;

    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255
                || green < 0 || green > 255
                || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public Immutable_RGB(int red, int green, int blue, String name) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    // there is no setters.
    // whatever is set, it is set in the constructor
    // If any thread has accessed constructor - every thread would have got its own copy of object
    

    public int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public String getName() {
        return name;
    }

    // anything that changes object, should return a new object
    public Immutable_RGB invert() {
        return new Immutable_RGB(255 - red, 255 - green, 255 - blue, 
                "Inverse of " + name);
    }
}