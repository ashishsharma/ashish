package main.java.strings;

// http://www.lucidtechnologies.info/ascii.htm
// when we say ascii -  it means the dec value of a char in ascii table
// ascii = (int) char
// dec ascii values live between 0-127

public class CharToASCII {
	
	public static void main(String[] args) {
		char c = 'a';
		int ascii = (int) c;
		
		if(ascii < 0 || ascii > 127)
			System.err.println("It is not a valid char.");
		
		System.out.println("ASCII of Char :'" + c + "' is: " + ascii);
	}
	
}
