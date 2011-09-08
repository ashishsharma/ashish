package main.java.concepts;


// Objects in Java are referred using reference types, and there is no direct way to copy the contents of an object into a new object.
// The assignment of one reference to another merely creates another reference to the same object.

// Use -
// When you don't want to modify the method caller's (or original) object.
// Different analysis on same set of data in an Object. Rather than fetching the data again and again, populate object once and clone it for a new analysis.
// Games - walls, doors, cars graphics - clone the object, change the params.

// For any class to able to be cloned .clone() - it MUST implement java.lang.Cloneable interface. Otherwise is .clone is used, it throws java.lang.CloneNotSupportedException at runtime.
// It is a marker interface - which simply tells JVM that the Object.clone() method can be called on the objects of this or sub classes.

// Shallow Copy - see clone() method
// This is a result of the default cloning functionality provided by the Object.clone() 
// When this is used, it copies the primitives and the references. Only references are copied to new cloned Object. Actual referenced object remains as it. That means, the member references in original and cloned object - point to same object.

// Deep Copy - see deepClone() method
// Copy the actual member objects along with primitives and member references.
// We need to override the clone() method in order to achieve it. But need to make sure that member Objects that we are trying to copy, should also implement Cloneable, only then then can be cloned.

// After cloning, origObj != clonedObj // true, origObj.getClass() == clonedObj.getClass() // true, origObj.equals(clonedObj) // by default false, but if you have overridden equals - then depends.

// java's Object's default cloning is protected Object clone() {...}, so in any class super.clone works and you can typecast. But you can make clone function public too. Then it becomes your responsibility to make sure when you call clone of Clonable class from any class, you know how to handle typecast.

public class Clones {
	
	
	public static void main(String[] args) throws CloneNotSupportedException {

		Person p1 = new Person("AAA", 123, 100, 200);
		System.out.println("Original");
		System.out.println(p1.salary.basic);
		Person p2 = (Person) p1.deepClone();
		//p2.name = "BBB";
		//p2.salary.basic = 2222;
		System.out.println(p1.equals(p2));
		System.out.println("Clone");
		System.out.println(p2.salary.basic);
		System.out.println("Original");
		System.out.println(p1.salary.basic);
	}

}


// cloneable class implements Cloneable
class Person implements Cloneable {

	public String name = null;
	public int id = 0;
	public Salary salary = null;
	
	public Person(String name, int id, int basicSal, int bonusSal) {
		this.name = name;
		this.id = id;
		// using Object type member to test for cloning
		Salary sal = new Salary();
		sal.basic = basicSal;
		sal.bonus = bonusSal;
		this.salary = sal;
	}
	
	// shallow copying
	protected Object clone() throws CloneNotSupportedException {
		// This is default cloning provided by the Object.clone() 
		return super.clone();
	}
	
	// deep copying
	protected Object deepClone() throws CloneNotSupportedException {
		// The clone() method produces an Object, which must be recast to the proper type
		Person p = (Person) super.clone();
		// In deep cloning, we also clone the member objects, but for this to work - member objects should be Cloneable
		p.salary = (Salary) p.salary.clone();
		return p;
	}

}

// member object of Person class as Cloneable. If it was not, then deep cloning would not have been possible
class Salary  implements Cloneable {

	public int basic = 0;
	public int bonus = 0;

	public int getSalary() {
		return basic + bonus;
	}
	
	// implementing clone()
	protected Salary clone() throws CloneNotSupportedException {
		Salary s = (Salary) super.clone();
		return s;
	}
}

// Cloneables with final fields - not meant go together. No error, but when try to change, it cribs.
// The only solution is to remove the final modifier from the field, giving up the benefits the modifier conferred.