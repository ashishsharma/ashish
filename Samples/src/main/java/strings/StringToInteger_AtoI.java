package main.java.strings;

public class StringToInteger_AtoI {

	public static void main(String[] args) {
		StringToInteger_AtoI atoi = new StringToInteger_AtoI();
		System.out.println(atoi.doAtoI("12345678901122334455"));
	}

	public int doAtoI(String num) {
		
		if (num == null || num.length() < 1)
			throw new NumberFormatException();

		char[] c = num.toCharArray();

		int result = 0;
		boolean isNegative = false;

		for (int i = 0; i < c.length; i++) {
			
			if (c[i] == ' ')
				throw new NumberFormatException();
			else if (i == 0 && c[i] == '-') {
				isNegative = true;
				continue;
			} else if (i == 0 && c[i] == '+')
				continue;
			
			int n = c[i] - '0';
			if(n < 0 || n >9)
				throw new NumberFormatException();
			
			result = (result * 10) + n;
		}
		return isNegative ? -1 * result : result;
	}

}
