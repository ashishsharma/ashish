package main.java.strings;

//http://www.lucidtechnologies.info/ascii.htm
//when we say num -  it means the number 0-9
//num =  char - '0'

public class CharToNumber {

	public static void main(String[] args) {
		char c = ' ';

		int d = c - '0';

		if (d < 0 || d > 9)
			System.err.println("It is not a digit");

		System.out.println("Number of Char :'" + c + "' is: " + d);
	}
}
