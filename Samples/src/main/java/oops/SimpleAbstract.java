package main.java.oops;

// Abstract classes declared with the abstract keyword
// Cannot be instantiated. It can only be used as a super-class for other classes

// abstract class's inheritance is just like any normal class - can implement more than one interfaces, but can inherit only one class

public abstract class SimpleAbstract {
	
	// The public constructor has no use when the class is of type abstract. The abstract classes are 
	// not allowed to instantiate the class. So I went for the protected constructor.
	protected SimpleAbstract() {
		System.out.println("Constructor");
	}
	
	// allowed modifiers - public, protected, private, static, final, transient & volatile -- OR default
	// Note - if field is private, it can be used inside the abstract class only
	int myField = 10;
	
	int otherField;
	
	// methods in abstract class CAN have implementations
	public void implementedMethod() {
		System.out.println("This is implemented inside ABSTRACT class");
	}

	// ABSTRACT method - must be implemented by extending class
	// methods can’t have implementation only when declared abstract, otherwise they can
	// allowed modifier - public or protected OR default
	// note - if it is protected - it is visible only in class that extends its abstract class. Then any class then uses an object of extending class - will not be able to see that.
	public abstract void doJob();

}

// A class can inherit only from ONE abstract class (but a class may implement many interfaces) 
// and MUST override all its abstract methods
class TestAbstract extends SimpleAbstract {
	
	public static void main(String[] args) {
		TestAbstract ti = new TestAbstract();
		ti.doJob();
	}

	// implementing abstract method
	@Override
	public void doJob() {
		// fields can be reassigned vales in abstract class - as they are not final, as in Interface
		otherField = 30;
		
		// running non-abstract method from abstract class
		implementedMethod();
		
		System.out.println(myField + " and " + otherField + " are 2 fields.");
		
	}
}