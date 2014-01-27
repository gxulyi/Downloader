package com.casegeek.ProgressBar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Logger {
	final static String FILE_NAME = "log.txt";
	final static String OUTPUT_FILE_NAME = "log.txt";
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	public void Log(String entry) {
		Path path = Paths.get(FILE_NAME);
	    try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)){	 
	    	BufferedReader reader = Files.newBufferedReader(path, ENCODING);
	    	writer.write(reader.read());
	    	writer.append(entry);
	    	writer.newLine();	      
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String Read() {
		Path path = Paths.get(FILE_NAME);
		String output = "";
	    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
	      while (scanner.hasNextLine()){
	        //process each line in some way
	        output += scanner.nextLine() + "\n";
	      }      
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return output;
	}
	
	public void setPercent(int value) {
		File file = new File("/log.txt");
		String tempChar = String.valueOf(value);
		char newChar;

	    int fileLength = (int) file.length();

	    byte[] bt = new byte[(int) fileLength];
	    
	    try {
	    	FileInputStream fis = new FileInputStream(file);
			fis.read(bt);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    StringBuffer sb = new StringBuffer(new String(bt));
	    
	    if (value < 10) {	    	
	    	newChar = tempChar.charAt(0);
	    	sb.setCharAt(fileLength - 1, newChar);
	    } else {
	    	newChar = tempChar.charAt(0);
	    	char newChar2 = tempChar.charAt(1);
	    	sb.setCharAt(fileLength - 1, newChar2);
	    	sb.setCharAt(fileLength - 2, newChar);
	    }
	    
	    try {
	    	FileOutputStream fos = new FileOutputStream(file);
			fos.write(sb.toString().getBytes());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
}