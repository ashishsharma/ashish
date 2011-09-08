package main.java.concepts;


// http://blog.sanaulla.info/2009/12/01/working-with-java-enumerated-types-enums/

public class Enums {
	
	//to access enums in a class
	public Day myDayEnumObj;
	public Enums(Day d) {
		myDayEnumObj = d;
	}

	
	// Note: All enums implicitly extend java.lang.Enum. 
	// Since Java does not support multiple inheritance, an enum cannot extend anything else.
	private enum Day {
		
	    //each constant implicity calls a constructor :

		SUNDAY(0), 
		MONDAY(1), 
		TUESDAY(2), 
		WEDNESDAY(3);

		int dayValue;
		
		// constructor
		// You cannot invoke an enum constructor yourself.
		// thus we can have any values like MONDAY(1,2,3) then create 3 types like dayValue and have a construtor for them
		// if it is only MONDAY without any value, default construtor is called.
		// enum constructor can never be public
		
		private Day(int v) {
			dayValue = v;
		}
	}
	
	
	public static void main(String[] args) {
		
		// Can never use "new" with any enum, even inside the enum class itself
		// here is how to access enum values

		System.out.println(Day.MONDAY);
		
		System.out.println(Day.MONDAY.dayValue);
		
		
		Day myDay = Day.TUESDAY;
		
		System.out.println(myDay);
		
		System.out.println(myDay.dayValue);
		
		// this is how to access enums created in a class
		Enums myEnumClass = new Enums(Day.SUNDAY);
		System.out.println(myEnumClass.myDayEnumObj);
	}
	
	
	
	
	// another type - enums with abstract method
	
	public enum Day2 {
		
	    //each constant implicity calls a constructor :

		SUNDAY(0){ 
		     String getInformation(){ //Method overriden by the Enum constants
		         return "day 12344";
		       }
		     },
		MONDAY(1){ 
		     String getInformation(){ //Method overriden by the Enum constants
		         return "day 2222";
		       }
		     };

		int dayValue;
		
		// constructor
		// You cannot invoke an enum constructor yourself.
		// thus we can have any values like MONDAY(1,2,3) then create 3 types like dayValue and have a construtor for them
		// if it is only MONDAY without any value, default construtor is called.
		// enum constructor can never be public
		
		private Day2(int v) {
			dayValue = v;
		}
		
		
		// if there is an abstract method within the enum class, then all the enums (MINDAY, TUESDAY) should implement that abstract method
		abstract String getInformation();
	}

}
