package main.java.strings;

public class DuplicateChars {

	public static void main(String[] args) {
		System.out.println(1 << 25);
		//System.out.println(isUniqueChars("abad"));
	}

	public static boolean isUniqueChars(String str) {
		int checker = 0;
		for (int i = 0; i < str.length(); ++i) {
			int val = str.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0)
				return false;
			checker |= (1 << val);
		}
		return true;
	}
}
