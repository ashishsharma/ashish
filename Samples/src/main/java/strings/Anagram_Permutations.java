package main.java.strings;

import java.util.ArrayList;
import java.util.List;

public class Anagram_Permutations {
	
	public static void main(String[] args) {
		Anagram_Permutations a = new Anagram_Permutations();
		List<String> anagrams = a.findPermutations("abc");
		for (String s : anagrams) {
			System.out.println(s);
		}
	}
	
	
	
	// THIS IS WRONG LOGIC 
	// SEE CRACKING INTERVIEW BOOK PAGE 35, 173
	
	public List<String> findPermutations(String inStr) {
		List<String> result = new ArrayList<String>();
		char[] chArray = inStr.toCharArray();
		for (int i = 0; i < chArray.length; i++, chArray = inStr.toCharArray()) {
			for (int j = 0; j < chArray.length; j++, chArray = inStr.toCharArray()) {
				swap(chArray, i, j);
				result.add(String.valueOf(chArray));
			}
		}		
		return result;
	}
	
	public void swap(char[] c, int fromIdx, int toIdx) {
		char tmp = c[fromIdx];
		c[fromIdx] = c[toIdx];
		c[toIdx] = tmp;
	}

}
