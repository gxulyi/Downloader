package com.casegeek.ProgressBar;

import javax.swing.JFrame;

public class Main extends JFrame {
	
	private static ProgressBar pb = new ProgressBar("http://textfiles.com/apple/DOCUMENTATION/acos.tutor.3", "example.txt");
	
	Main() {
		
		super("Progress Test");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);		
		
		add(pb);
		
		setVisible(true);
		
	}

	public static void main(String[] args) {
		Main main = new Main();
		Thread t = new Thread(pb,"ProgressBar");
		t.start();
		
	}


}
