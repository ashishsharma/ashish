package main.java.algo;

public class HighestPowerOf2LessThanN {

	public static void main(String[] args) {
		HighestPowerOf2LessThanN h = new HighestPowerOf2LessThanN();
		System.out.println(h.highestPowerOf2LessThanN(64));
	}

	public int highestPowerOf2LessThanN(int num) {
		int power = 0;
		double quotient = 0;
		while (quotient == 0 || quotient >= 1) {
			power++;
			quotient = num / (Math.pow(2, power));
		}
		return power - 1;
	}
}
