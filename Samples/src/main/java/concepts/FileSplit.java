package main.java.concepts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileSplit {
	
	
	public static void main(String[] args) throws IOException {
		FileSplit f = new FileSplit();
		
		String path = "/Users/ashishsharma/Desktop/splitdir/TestSplit";
		
		f.split(path, 300);
	}
	
	// split [OPTION] [INPUT [PREFIX]]
	
	// it creates file-parts suffixed by aa ab ac, etc
	
	// split -l 300 /Desktop/mydir/TestSplit.txt TestSplitPart_
	// split -b 50M filename part
	
	// to join the files back
	// cat xaa xab xac > filename


	
	public void split(String filePath, int splitByLines) throws IOException {
		
		File f = new File(filePath);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		
		String NEWLINE = System.getProperty("line.separator");
		
		String line = null;
		StringBuilder sb = new StringBuilder();
		int lineCount = 0;
		int fileCount = 0;
		while((line = br.readLine()) != null) {
			sb.append(line).append(NEWLINE);
			lineCount++;
			if(lineCount >= splitByLines) {
				write(sb.toString(), filePath + "_" + ++fileCount );
				
				lineCount = 0;
				sb = null;
				sb = new StringBuilder();
				br = null;
				br = new BufferedReader(fr);
			}
		}
	}
	
	public void write(String text, String filePath) throws IOException {
		File f = new File(filePath);
		FileWriter writer = new FileWriter(f);
		writer.write(text);
		writer.close();
	}

}




// with bytes

class FileSplitter {

	  public static void main(String args[]) throws Exception {
	    FileInputStream fis = new FileInputStream(args[0]);
	    int size = 1024;
	    byte buffer[] = new byte[size];

	    int count = 0;
	    while (true) {
	      int i = fis.read(buffer, 0, size);
	      if (i == -1)
	        break;

	      String filename = args[1] + count;
	      FileOutputStream fos = new FileOutputStream(filename);
	      fos.write(buffer, 0, i);
	      fos.flush();
	      fos.close();

	      ++count;
	    }
	  }
	}