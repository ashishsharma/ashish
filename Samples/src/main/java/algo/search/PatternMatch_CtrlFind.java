package main.java.algo.search;

/*
 * PROBLEM:
 * Implement Ctrl + F - Find functionality in a text editor or Browser
 * */
public class PatternMatch_CtrlFind {

	public static void main(String[] args) {
		PatternMatch_CtrlFind f = new PatternMatch_CtrlFind();
		System.out.println("Hi there, how are you doing?".indexOf("ow"));
		System.out.println(f.findAndReturnPosition("ow", "Hi there, how are you doing?"));
	}

	private int findAndReturnPosition(String pattern, String text) {
		return findAndReturnPosition(pattern.toCharArray(), text.toCharArray());
	}

	private int findAndReturnPosition(char[] pattern, char[] text) {

		int i, j;
		int textLen = text.length;
		int patternLen = pattern.length;

		// loop is upto (textLen - patternLen) - the last possible position may
		// be the last word in the text. 
		for (i = 0; i < (textLen - patternLen); i++) {
			j = 0;
			//loop required only to match the pattern length
			while (j < patternLen && text[i + j] == pattern[j]) {
				j++;
			}
			if (j == patternLen)
				return i;
		}
		return -1;
	}

}

/* COMPLEXITY:
 * Say test len is m. pattern len is m.
 * Outer loop goes in (n-m)
 * Inner loop goes in 0 -to-> len of pattren, which is m.
 * thus complexity = (n-m)m
 * pattern.toCharArray() complecity will be liner O(m) and text.toCharArray() will be liner O(n)
 * Thus -> n + m + (n-m)m = n + m + nm - m Square
 * since m > 1, nm is most dominent among n + m + nm. 
 * so -> nm - m square
 * sine -mSquare is the lowering factor. For complexity, we consider the worst case, so we can ignore the lowering factors
 * Thus complexity = O(mn)
 * */

