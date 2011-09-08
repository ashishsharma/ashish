package main.java.concepts;

import java.util.ArrayList;
import java.util.List;

// Generics in Java allow "a type or method to operate on objects of various types while providing compile-time type validity

public class Generics {
	
	public static void main(String[] args) throws Exception {
		Generics g = new Generics();
		
		System.out.println("Test without Generic");
		g.withOutGeneric();
		
		System.out.println("Generic Type Erasure");
		g.genericTypeErasure();
		
		System.out.println("Generic demo");
		g.doit();
	}
	
	//****************************************************

	
	// code compiles without error, it throws a runtime exception (java.lang.ClassCastException) when executing the third line of code
	public void withOutGeneric() {
		try {
			List v = new ArrayList();
			v.add("test");
			Integer i = (Integer) v.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	//****************************************************

	
	public void doit() throws Exception {
		
		// MapKeyVal - used for Integer, String
		MapKeyVal<Integer, String> intStrOne = new MapKeyVal<Integer, String>(1, "one");
		MapKeyVal<Integer, String> intStrTwo = new MapKeyVal<Integer, String>(2, "two");
		MapKeyVal<Integer, String> intStrThree = new MapKeyVal<Integer, String>(3, "three");

		// MyList - used for MapKeyVal with Integer, String
		MyList<MapKeyVal<Integer, String>> listIntStr = new MyList<MapKeyVal<Integer,String>>(5);
		listIntStr.addElem(intStrOne);
		listIntStr.addElem(intStrTwo);
		listIntStr.addElem(intStrThree);
		listIntStr.print();
		
		// MapKeyVal - used for String, String
		MapKeyVal<String, String> strStrOne = new MapKeyVal<String, String>("ek", "one");
		MapKeyVal<String, String> strStrTwo = new MapKeyVal<String, String>("do", "one");
		MapKeyVal<String, String> strStrThree = new MapKeyVal<String, String>("teen", "one");

		// MyList - used for MapKeyVal with String, String
		MyList<MapKeyVal<String, String>> listStrStr = new MyList<MapKeyVal<String,String>>(5);
		listStrStr.addElem(strStrOne);
		listStrStr.addElem(strStrTwo);
		listStrStr.addElem(strStrThree);
		listStrStr.print();
		
		// with generic, return type matches
		MapKeyVal<String, String> s = listStrStr.getElem(0);
		String sVal = s.getValue();
		System.out.println(s.getClass().getName());
		System.out.println(sVal.getClass().getName());
		
		// testing generics with method and constructor
		TestChild child = new TestChild("ashish");
		Util u = new Util(child);
		
		
		// 
		ArrayList<TestChild> childList = new ArrayList<TestChild>();
		childList.add(new TestChild("c-aaa"));
		childList.add(new TestChild("c-bbb"));


		ArrayList<TestParent> parentList = new ArrayList<TestParent>();
		parentList.add(new TestParent("pp-aaa"));
		parentList.add(new TestParent("pp-bbb"));
		
		System.out.println("? extends : "+u.inspectExtendBounds(childList).getClass().getName());
		
		System.out.println("? super : "+u.inspectSuperBounds(parentList).getClass().getName());
		
	}
	
	//****************************************************

	// Type Erasure

	// Generics are checked at compile-time for type-correctness. 
	// At run time, generic type information is then removed and they will be just like of type Object
	// ArrayList<Integer> and ArrayList<Float> will be converted to the non-generic type ArrayList, which can contain arbitrary objects. 
	// The compile-time check guarantees that the resulting code is type-correct.
	// One version of the class or function is com- piled, works for all type parameters
	public void genericTypeErasure() {
		ArrayList<Integer> li = new ArrayList<Integer>();
		ArrayList<Float> lf = new ArrayList<Float>();
		if (li.getClass() == lf.getClass()) // evaluates to true
			System.out.println("Equal");
	}

}

//****************************************************


class Util {
	
	// constructors can declare Type variables as classes do.
	// <Item> here is the type declaration with the constructor
	// But anything declared with the constructor is has a local scope within that method
	// Item will not be available outside this method.
	public <Item> Util(Item I) {
		System.out.println("In Generic Constructor");
		inspect(I);
	}
	
	// 
	
	// methods can declare Type variables as classes do.
	// <U> is the type declaration with the method
	// But anything declared with the method is has a local scope within that method
	// U will not be available outside this method.
	public <U> void inspect(U u) {
		System.out.println(u.getClass().getName());
	}
	
	// wildcard "? extends T"- to specify anything, but with this bound condition - all which extend T
	
	public <T> T inspectExtendBounds(ArrayList<? extends T> l) {
		return l.get(0);
	}

	// wildcard "? super T"- to specify anything, but with this bound condition - all which are super classes of T

	public <T> T inspectSuperBounds(ArrayList<? super T> l) {
		return (T) l.get(0); // downcasting super to child
	}
	
}

//****************************************************


// A class is generic if it declares one or more type variables.
// These type variables are known as the type parameters of the class.
// Here T is type variable which is accessible in whole class
class MyList<T> {

	// in java, array of generic like T[] is not allowed. we cannot have T[] t = new T[];
	// because we cannot instantiate T... as T t = new T()
	// so we use an Object to hold all the data and while returning we typecast it to T
	Object[] list;
	int index = 0;

	public MyList(int size) {
		list = new Object[size];
	}

	public void addElem(T elem) {
		list[index] = elem;
		index++;
	}

	public T getElem(int i) throws Exception {
		if (i > index)
			throw new Exception("IndexOutOfBound");
		return (T) list[i];
	}

	public void print() {
		for (Object o : list) {
			if(o != null)
				System.out.println(o.toString());
		}
	}
}

//****************************************************

// This class declares more than one Type variables
class MapKeyVal<K, V> {

	// type variables
	// type variables are used just like any other variables in a class
	K key;
	V val;

	// passing variables of Type declared by class
	public MapKeyVal(K k, V v) {
		key = k;
		val = v;
	}

	// returning variables of Type declared by class
	public K getKey() {
		return key;
	}

	public V getValue() {
		return val;
	}

	// using variables of Type in a logic
	public String toString() {
		return "(" + key + "," + val + ")";
	}
}

//****************************************************

class TestParent {
	String name = "";

	public TestParent(String n) {
		name = n;
	}
	
	public String toString() {
		return name;
	}
}

//****************************************************

class TestChild extends TestParent {

	public TestChild(String n) {
		super(n);
	}

	public void print() {
		System.out.println(name);
	}
}

// *************************************
// NOTES
// *************************************

// static with Generics

// Because there is only one copy per generic class at runtime, static variables are shared among all the instances of the class, 
// regardless of their type parameter. As a result, the type parameter cannot be used in the declaration of static variables 
// or in static methods. Static variables and static methods are "outside" of the scope of the class's parameterized types.
// Type parameter of templated class cannot be used for static methods and variables.
// OTHER Static variables are shared between instances of a classes of different type parameters

// wrong - > public <T> static void myMthod() {...}
// wrong - > static T t;

// *************************************

// Instantiation of a variable of generic Type.. new T()

// instantiating a Java class of a parameterized type is impossible because instantiation requires a call to a constructor,
// which is unavailable if the type is unknown.
//	 
// following code will not compile:
//
//	 T instantiateElementType(List<T> arg)
//	 {
//	   return new T(); //causes a compile error
//	 }

// *************************************

/*
 * More WildCard Examples - 
 * ***********************************

 *  Illegal call.
 * 
  	public static <T> T writeAll(Collection<T> coll, Sink<T> snk) {
	    T last;
	    return last;
	}
	...
	Sink<Object> s;
	Collection<String> cs;
	String str = writeAll(cs, s); // Illegal call. cs and s should be of same tyoe

*************************************
*
* Call is OK, but wrong return type.

  	public static <T> T writeAll(Collection<? extends T>, Sink<T>) {...}
	...
	String str = writeAll(cs, s);  
	
	// Here t is passed as Object and str is accepting String
	
*************************************
*
* Works.

	public static <T> T writeAll(Collection<T> coll, Sink<? super T> snk) {
    ...
	}
	String str = writeAll(cs, s); // Yes! 
	
	// T passed as String, returned as String

*************************************
*
* Unknown types can be passed as <?>

*/
