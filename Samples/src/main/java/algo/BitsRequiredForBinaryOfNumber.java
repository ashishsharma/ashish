package main.java.algo;

public class BitsRequiredForBinaryOfNumber {

	/*
	 * PROBLEM: How many bits are required to represent binary of a Number n.
	 * 
	 * TIP: Number of bits patterns double (multiplied by 2) if number of bits
	 * increase by 1. They double every n = 2, 4, 8, 16.. that is power of 2.
	 * 
	 * If there is any number N. Then N may be represented as 2^x + y. Now if we
	 * see the binary representation of numbers. Numbers from 2^(x) to 2(^x+1) -
	 * 1 [example 4 to 7, OR 8 to 15] use the same number of bits as used by
	 * (highest power of 2 less than that number) + 1] [example 4 to 7 use 3 bits, OR
	 * 8 to 15 use 4 bits].
	 * 
	 * Thus number of bits used by 13 i.e. (2^3 + 5) is same as by 2^3 plus 1 = 3 + 1 = 4;
	 * 
	 * 2(^x) uses x bits. 2(^x) + y uses x+1 bits.
	 * where 2(^x) < y < 2(^x+1) - 1
	 */
	public static void main(String[] args) {
		BitsRequiredForBinaryOfNumber b = new BitsRequiredForBinaryOfNumber();
		System.out.println(b.findNumOfBits(15));

	}

	private int findNumOfBits(int n) {
		HighestPowerOf2LessThanN h = new HighestPowerOf2LessThanN();
		int bits = h.highestPowerOf2LessThanN(n) + 1;
		return bits;
	}

}
