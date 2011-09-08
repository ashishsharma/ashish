package main.java.algo.sorting.compare;

import java.util.Arrays;

/**** implements Comparable ****/

/*
 * Implementing the Comparable interface enables you to define ONE way to
 * compare instances of your class. This interface has one method, compareTo which decides the comparing criteria.
 * 
 * However, objects are sometimes comparable in many ways. 
 * For example, two Person objects may need to be compared by age or by last/first name. 
 * In cases like this, create a Comparator that defines how to compare two objects. 
 * To make objects comparable in two ways, then you need two comparators.
 */

public class SortingByComparable  {

	public static void main(String[] args) {
		Employee[] p = new Employee[3];

		p[0] = new Employee();
		p[0].setFirstName("aa");
		p[0].setLastName("bb");
		p[0].setAge(17);

		p[1] = new Employee();
		p[1].setFirstName("cc");
		p[1].setLastName("dd");
		p[1].setAge(11);

		p[2] = new Employee();
		p[2].setFirstName("ee");
		p[2].setLastName("ff");
		p[2].setAge(12);

		// If comparable is implemented, it will do the sorting as per the logic in compareTo
		Arrays.sort(p);

		// print sorted list
		for (Employee per : p) {
			System.out.println(per.getFirstName());
		}

	}

}

/*
 * To create a comparator, write a class that implements the java.util.Comparator interface and the compare method
 */

class Person implements Comparable<Object> {
	
	private String firstName = null;
	private String lastName = null;
	private int age = 0;
	
	
	
	// The compareTo method accepts Object, so you can pass it an instance of any type
	// it should always be public, as the Comparable interface needs to access it
	public int compareTo(Object anotherPerson) {

		// first always check if the things being compared are of same type
		if (!(anotherPerson instanceof Person))
			throw new ClassCastException("Comparing object not of type Person");

		int anotherAge = ((Person) anotherPerson).getAge();

		// it is always current object minus object being compared
		// The compareTo method returns zero if the object passed is equal to this instance.
		// It returns a positive integer or a negative integer if this object is greater or smaller than the passed object.
		return this.age - anotherAge;
	}

	
	
	/* Getters & Setters */

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}



