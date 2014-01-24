package com.casegeek.ProgressBar;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ProgressBar extends JPanel implements Runnable {
	
	JProgressBar pBar = new JProgressBar(0,100);
	private String url = "";
	private String fileName = "";
	private int fileSize = 0;
	
	ProgressBar() {
		super();
		add(pBar);
		
		setVisible(true);		
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setName(String name) {
		this.fileName = name;
	}
	

	@Override
	public void run() {
		try {
			saveFromUrl(fileName, url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveFromUrl(String filename, String url) throws MalformedURLException, IOException {
		BufferedInputStream in = null;
		FileOutputStream fos = null;
		try {
			in = new BufferedInputStream(new URL(url).openStream());
			fos = new FileOutputStream(filename);
			fileSize = in.available();
			
			byte data[] = new byte[fileSize];
			
			int count;
			pBar.setMaximum(fileSize);
			pBar.setValue(0);
			while ((count = in.read(data, 0, fileSize)) != -1) {
				pBar.setValue(count);
				fos.write(data, 0, count);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (fos != null) {
				fos.close();
			}
			System.out.println("Complete");
			pBar.setValue(0);
		}
	}
	
	

}
