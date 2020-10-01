package main.java.todo;
package com.compare;
import java.util.Scanner;

public class ReverseString {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		int n = 0;
		String rev="";
		System.out.println("Enter original String: ");
		String org = sc.nextLine();
		n=org.length();
		
		for(int i=n-1;i>=0;i--)
		{
			rev =  rev + org.charAt(i);
		}
		System.out.println("Duplicate String: \n"+rev);
	}
}
