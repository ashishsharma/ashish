package main.java.oops;

// Interface definition begins with a keyword interface
// Cannot be instantiated.
// Interface can inherit more than one interfaces. But interface "extends" other interfaces (not "implements") - In such case, a class implementing SimpleInterface, should also implement Runnable

public interface SimpleInterface extends Runnable {
	
	// allowed modifiers for fields are public, static, final
	// Internally, fields in a Interface are always as "public static final"
	// int myField OR public int myField OR public static int myField OR public static final int myField -- is always internally stored as public static final int myField
	// Since these are final, they cannot be changed in implementing class
	// and note, they cannot be private for sure
	int myField = 10;
	
	// similarly, public & abstract are only allowed modifiers for methods
	// Since it is only declaration - it does not make sense to make it static as the implementation is going to be somewhere else in implementing class
	// void doJob(..) OR public void doJob(..) OR public abstract void doJob(..) are internally stored as public abstract void doJob(..)
	// Note - it is only a method declaration (no implementation), without method body {}
	public abstract void doJob(String args);
	
	//public abstract void doLoging(String message);
}

// Note - if class is not implementing the abstract methods from implementing inteface - then the class should be of type "abstract"
// example, if TestInterface was NOT to provide an implementation of doJob(...) - it should have been declared: abstract class TestInterface implements SimpleInterface
// and some other class extending TestInterface - should provide doJob(..)'s implementation
class TestInterface implements SimpleInterface {
	
	public static void main(String[] args) {
		TestInterface ti = new TestInterface();
		ti.doJob("Ashish");
	}

	// implementing the method from SimpleInterface
	@Override
	public void doJob(String args) {
		// this is mistake ->
		//myField = 20;
		// fields from interface are final, they cannot be changed in implementing class
		
		System.out.println("Hello " + args + " " + myField);
	}
	
	// implementing the method from Runnable - SimpleInterface was extending Runnable
	public void run() {
		System.out.println("Implementing the method from interface - implemented by the interface - that this class is implementing");
	}
	
}