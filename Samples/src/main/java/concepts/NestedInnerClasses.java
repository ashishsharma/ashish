package main.java.concepts;


// wrong - >
// import main.java.algo.faaltoo;
// imports are checked and validated at compile time.

	//	Why Use Nested Classes?
	//
	//		It is a way of logically grouping classes that are only used in one place.
	//		It increases encapsulation. Example: kind of entry class in hashmap.
	//		Nested classes can lead to more readable and maintainable code.


// fyi - top level class can be only public OR default
// and name of top level class is the name of .java file
public class NestedInnerClasses {
	
	public static void main(String[] args) {
		OuterClass outerObj = new OuterClass();
		
		// A nested class is a member of its enclosing class.
		
		// instantiate static nested
		OuterClass.StaticNestedClass statNestClass = new OuterClass.StaticNestedClass();
		
		//instantiate non-static inner
		OuterClass.InnerClass innerClass = outerObj.new InnerClass();
	}
	
	
	// anonymous local classes
	public ABC getAnon() {
		
		return new ABC() {
			public int getVal() {
				return 0;
			}
		}; // semi-colon required
		
	}
	
}

interface ABC {
	public int getVal();
}


class OuterClass {
	
	private int outerPrivVal = 10;
	public int outerPubVal = 20;
	
	private void outerPricFxn() {
		System.out.println("In Outer Private Function");
	}
	
	public void outerPubFxn() {
		System.out.println("In Outer Public Function");
	}
	
	public void doJob() {
		OuterClass.StaticNestedClass statNestClass = new StaticNestedClass();
	}
	
	// static nested classes are just like any top level class
	// Static nested classes do not have access to other members of the enclosing class.
	// Only way to access it is via object of Outer, just like any other top level class.
	// via object, it can access even private members. This is not possible via any top level class. Can be only done in nested static class.
	static class StaticNestedClass {
		
		public void staticNestedClassFxn() {
			OuterClass outerObj = new OuterClass();
			outerObj.outerPrivVal = 13;
			outerObj.outerPubVal = 23;
			outerObj.outerPricFxn();
			outerObj.outerPubFxn();
		}
		
	}
	
	// inner class can access all members of outer class
	// this class can be public/private/protected/dafault - but if private - cannot be accessed outside outerclass. - if protected, cannot be accessed outside this pkg and any classes extending outer.
	class InnerClass {
		
		public void innerClassFxn() {
			outerPrivVal = 15;
			outerPubVal = 25;
			outerPricFxn();
			outerPubFxn();
		}
		
	}
	
}
