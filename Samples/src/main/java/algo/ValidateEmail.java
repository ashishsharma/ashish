package main.java.algo;


public class ValidateEmail {

	  public static void main(String[] args) {
	    System.out.println("isValid = " + isValidEmailAddress(".as@domain.com"));
	  }

	  static boolean isValidEmailAddress(String email) {
	    String EMAIL_REGEX = "^[\\w]+[\\w-_\\.]*\\@([\\w]+\\.)+[\\w]+[\\w]$";

	    return email.matches(EMAIL_REGEX);
	  }
	}
