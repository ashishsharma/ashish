package main.java.concepts;

import java.io.IOException;

public class ExecuteUnix {
	
	// It is done using Runtime.getRuntime().exec(..cmd..) -- But is not working for me. Need to check.
	
	public static void main(String[] args) throws IOException {
//		for (int i = 1; i <= 1000; i++) {
//			System.out.println("==========@@@@@@@@@@@@@@==========" + i + "==========@@@@@@@@@@@@@@==========");
//		}
		String[] cmdArray = {"rm -rf testUnix"};
		
		
		 String splitCmd = "split -l 300 /Users/ashishsharma/Desktop/splitdir/TestSplit siles";
		//String cmd = "mkdir /Users/ashishsharma/testU3";
		
		Runtime.getRuntime().exec(splitCmd);
		
		//Runtime.getRuntime().exec("cd /Users/ashishsharma");

	}

}
