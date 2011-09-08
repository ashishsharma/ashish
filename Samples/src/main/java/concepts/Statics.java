package main.java.concepts;

public class Statics {

	public static int a = 10;

	public static void dojob() {
		System.out.println("Hello!");
	}
}

class StaticTest extends Statics {


	public static void dojob() {
		a = 20;

		
		System.out.println("Hello!");
	}
}

class PersonShallowCopy implements Cloneable {

	public String name = null;
	public int id = 0;
	public SalaryShallowCopy salary = null;
	
	public PersonShallowCopy(String name, int id, int basicSal, int bonusSal) {
		this.name = name;
		this.id = id;
		SalaryShallowCopy sal = new SalaryShallowCopy();
		sal.basic = basicSal;
		sal.bonus = bonusSal;
		this.salary = sal;
	}
	
	public Object clone() throws CloneNotSupportedException {
		PersonShallowCopy p = (PersonShallowCopy) super.clone();
//		p.salary = (Salary) p.salary.clone();
		return p;
	}

}

class SalaryShallowCopy  /*implements Cloneable*/ {

	public int basic = 0;
	public int bonus = 0;

	public int getSalary() {
		return basic + bonus;
	}
	
//	public Object clone() throws CloneNotSupportedException {
//		Salary s = (Salary) super.clone();
//		return s;
//	}
}