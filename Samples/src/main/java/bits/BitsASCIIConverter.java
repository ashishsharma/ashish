package main.java.bits;

public class BitsASCIIConverter {

	public static int binaryToDecimal(String binary) {
		int i = Integer.parseInt(binary, 2);
		return i;
	}

	public static String decimalToBinary(int i) {
		String b = Integer.toBinaryString(i);
		return b;
	}

	public static int hexToDecimal(String binary) {
		int i = Integer.parseInt(binary, 16);
		return i;
	}

	public static String decimalToHex(int i) {
		String b = Integer.toHexString(i);
		// another way
		// Integer.toHexString(0x10000 | i).substring(1).toUpperCase();
		return b;
	}

	//  convert the number B3AD to decimal 
	// one can split the conversion into D (1310), A (1010), 3 (310) and B (1110) then get the final result by 
	// multiplying each decimal representation by 16p, where 'p' is the corresponding position from right to left, 
	// beginning with 0. In this case we have 13*(160) + 10*(161) + 3*(162) + 11*(163), which is equal 45997 in the decimal system.
	
	public static int hexTodecimal(String s) {
		String digits = "0123456789ABCDEF";
		s = s.toUpperCase();
		int val = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int d = digits.indexOf(c);
			val = 16 * val + d;
		}
		return val;
	}

	// precondition: d is a nonnegative integer
	public static String decimal2hex(int d) {
		String digits = "0123456789ABCDEF";
		if (d == 0)
			return "0";
		String hex = "";
		while (d > 0) {
			int digit = d % 16; // rightmost digit
			hex = digits.charAt(digit) + hex; // string concatenation
			d = d / 16;
		}
		return hex;
	}

	public static void main(String[] args) {
		System.out.println(hexToDecimal("FFFFFFF"));
	}

}
