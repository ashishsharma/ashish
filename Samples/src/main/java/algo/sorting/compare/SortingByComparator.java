package main.java.algo.sorting.compare;

import java.util.Arrays;
import java.util.Comparator;

/**** implements Comparator ****/

/*
 * Implementing the Comparable interface enables you to define ONE way to
 * compare instances of your class. This interface has one method, compareTo which decides the comparing criteria.
 * 
 * However, objects are sometimes comparable in many ways. 
 * For example, two Employee objects may need to be compared by age or by last/first name. 
 * In cases like this, create a Comparator that defines how to compare two objects. 
 * To make objects comparable in two ways, then you need two comparators.
 */

public class SortingByComparator  {

	public static void main(String[] args) {
		Employee[] p = new Employee[3];

		p[0] = new Employee();
		p[0].setFirstName("zz");
		p[0].setLastName("bb");
		p[0].setAge(17);

		p[1] = new Employee();
		p[1].setFirstName("cc");
		p[1].setLastName("dd");
		p[1].setAge(11);

		p[2] = new Employee();
		p[2].setFirstName("aa");
		p[2].setLastName("ff");
		p[2].setAge(12);

		// If comparator is implemented, it will do the sorting as per the logic passed comparator class
		Arrays.sort(p, new FirstNameComparator());

		// print sorted list
		for (Employee per : p) {
			System.out.println(per.getFirstName());
		}

	}

}


/*Comparator*/

//comparator is a separate class altogether that writes the logic for comparison.
//this class can then be passed as a comparator while sorting : Arrays.sort(employee, new FirstNameComparator());

class FirstNameComparator implements Comparator<Object> {
	
	//it MUST implement compare method
	//it has 2 input of the type Object (It is later casted to object of type we want)
	public int compare(Object o1, Object o2) {
		
		//Since it always takes type Object as param, it becomes necessary to check the types before comparing
		if(!(o1 instanceof Employee) || !(o2 instanceof Employee))
			throw new ClassCastException("Comparing objects not of type Employee");
		
		String firstNameO1 = ((Employee) o1).getFirstName();
		String firstNameO2 = ((Employee) o2).getFirstName();
		
		if(!firstNameO1.equals(firstNameO2)) {
			
			//this compareTo is the standard function of String class
			//do not confuse it with compareTo in Comparable
			return firstNameO1.compareTo(firstNameO2);
		}
		else {
			//good practice is to have a backup logic if the items compared are same
			String lastNameO1 = ((Employee) o1).getLastName();
			String lastNameO2 = ((Employee) o2).getLastName();
			return lastNameO1.compareTo(lastNameO2);
		}
	}
	
}

//in case of comparator, this class does not need to implement any comparing logic. unlike it used to do while using comparable.

class Employee {
	
	private String firstName = null;
	private String lastName = null;
	private int age = 0;	
	
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

/*
 * NOTES
 * 
 * 1) We can any number of comparator classes for Employee here - which do
 * sorting on basis of different criteria name, salaray, address, etc. We will
 * just need to change the Comparator class being passed as a param to .sort
 * method.
 * 
 * 2) In a class we can have both Comparable and Comparator being used. If
 * nothing is specified in .sort method - logic of comparable will be used. Or
 * else the logic of Comparator class being passed will be used. Also, then
 * .compareTo(...) used inside comparator will call the compareTo of Comparable.
 * 
 * 3) Comparator classes - we can either have standalone public/non-public(as in
 * my example) classes, inner classes, nested classes - the way we want.
 */
