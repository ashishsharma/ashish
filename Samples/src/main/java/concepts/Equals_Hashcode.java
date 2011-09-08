package main.java.concepts;


// 1) If two objects are equal according to the equals(object) , then hashCode() method on each of the two objects must produce the same integer result.
//	-- Safest is to use the same fields for equals and hashcode. It makes sure above property is valid.
// 2) If two objects are UNEQUAL according to the equals() method, then hashCode() method on each of the two objects may OR may not give same integer results
// 3) Invoking on the same object more than once, hashcode() method must consistently return the same integer, provided no information used in equals() comparisons on the object is modified.

// Whenever a.equals(b), then a.hashCode() must be same as b.hashCode().

// If you override one, then you should override the other.

// Use the same set of fields that you use to compute equals() to compute hashCode().


public class Equals_Hashcode {
	
	private String employeeId = null;
	private String name = null;
	private int age = 0;
	
	public boolean equals(Object obj) {
		
		if(this == obj)
			return true;
		
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		
		Equals_Hashcode otherObj = (Equals_Hashcode) obj;
		
		if(this.employeeId.equals(otherObj.employeeId) && this.name.equals(otherObj.name) && this.age == otherObj.age)
			return true;
		
		return false;
	}

	//  FYI - hashCode value of a Java String is computed as (String.hashCode()): s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
	public int hashCode() {
		int hashCode = 7;
		hashCode = (31 * hashCode) + (this.employeeId == null ? 0 : this.employeeId.hashCode());
		hashCode = (31 * hashCode) + (this.name == null ? 0 : this.name.hashCode());
		hashCode = (31 * hashCode) + (this.age == 0 ? 0 : new Integer(this.age).hashCode());
		return hashCode;
	}

}


