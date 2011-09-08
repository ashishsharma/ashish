package main.java.algo;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

public class FileOperations implements Serializable {
	
	public static void main(String[] args) {
		System.out.println("Starting... " + new Date());
		FileOperations fo = new FileOperations();
		fo.renameAllFilesInDirectory();
		System.out.println("...finished! " + new Date());

	}
	
	
	
	public void renameAllFilesInDirectory() {
		String path = "/Users/ashishsharma/Music/Hindi/";
		processFiles(path);
	}
	
	public void processFiles(String path) {
		File file = new File(path);
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File f : files) {
				processFiles(f.getPath());
			}
		} else {
			changeFileName(file);
		}
	}
	
	private void changeFileName(File f) {
		String junk = "( @ Fmw11.com|(DesiSong.com)|_Coke_Studio_Season_[0-9]{1}|[0-9]{2,}| - Coke Studio|-(Muskurahat.Com)|-\\(Muskurahat.Com\\)| - Muskurahat.Com|-\\[www.DesiMp3Music.Com\\]| \\[songsrush.com\\]|\\(.*\\))";
		String oldName = f.getName();
		String newName = camelCase(oldName.replaceAll(junk, ""));
		String newPath = f.getPath().replace(oldName, newName);
		f.renameTo(new File(newPath));
	}
	
	public String camelCase(String s) {
		if (s == null || s.equals(""))
			return "";
		int words =0;
		String result = "";
		
		int extnIdx = s.lastIndexOf(".");
		String extn = s.substring(extnIdx + 1);
		s = s.substring(0, extnIdx);
		
		
		String[] tokens = cleanup(s).split(" ");
		for (String t : tokens) {
			t = capitalize(t);
			if (!t.equals("") && words < 3) {
				if (result.equals(""))
					result += t;
				else
					result += " " + t;
				words++;
			}
		}
		return result + "." + extn;
	}
		
	private String cleanup(String s) {
		if(s == null || s.equals(""))
			return "";
		String junk = "[^A-Za-z0-9]";
		s = s.replaceAll(junk, " ").replaceAll("//s+", " ").trim();
		return s;
	}
	
	private String capitalize(String s) {
		if(s == null || s.equals(""))
			return "";
		if(s.length() > 1)
			return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
		return s.toUpperCase();
		
	}

}
