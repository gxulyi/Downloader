package com.casegeek.ProgressBar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class Main extends JFrame implements ActionListener {
	
	private static ProgressBar pb = new ProgressBar();
	private JButton chooseDest, launchBtnStart;
	private JTextArea optionsTxtUrl;
	private JFileChooser fc = new JFileChooser();
	private String saveTo = "";
	
	Main() {
		
		super("Downloader by GeneralBrae");
		setSize(500,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		
		//Setup menu bar
		JButton menu_File = new JButton("File");
		JButton menu_Edit = new JButton("Edit");
		JToolBar toolBar = new JToolBar();
		toolBar.add(menu_File);
		toolBar.add(menu_Edit);
		
		//Setup options area
		JPanel options = new JPanel();
		options.setBorder(BorderFactory.createTitledBorder("Options"));
		BoxLayout layout_Options = new BoxLayout(options, BoxLayout.Y_AXIS);
		options.setLayout(layout_Options);
		JLabel optionsLblURL = new JLabel("Enter URL:");
		optionsTxtUrl = new JTextArea(1,15);		
		JLabel chooseDestLbl = new JLabel("Choose save location:");
		chooseDest = new JButton("Browse");
		chooseDest.addActionListener(this);
		options.add(optionsLblURL);
		options.add(optionsTxtUrl);
		options.add(chooseDestLbl);
		options.add(chooseDest);
		
		//Setup launch area
		JPanel launch = new JPanel();
		launch.setBorder(BorderFactory.createTitledBorder("Launch"));
		launchBtnStart = new JButton("Start Download");
		launchBtnStart.setVerticalAlignment(SwingConstants.CENTER);
		launchBtnStart.setHorizontalAlignment(SwingConstants.CENTER);
		launchBtnStart.addActionListener(this);
		launch.add(launchBtnStart);
		
		//Setup reporting area
		JPanel logging = new JPanel();
		logging.setBorder(BorderFactory.createTitledBorder("Log"));
		BoxLayout layout_Logging = new BoxLayout(logging, BoxLayout.Y_AXIS);
		logging.setLayout(layout_Logging);
		JTextArea loggingTxt = new JTextArea(3,10);
		loggingTxt.setEditable(false);
		logging.add(pb);
		logging.add(loggingTxt);
		
		//Add components to window		
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);
		add("North", toolBar);
		add("West", options);
		add("East", launch);
		add("South", logging);
		
		setVisible(true);
		
	}

	public static void main(String[] args) {
		Main main = new Main();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == chooseDest) {
			int returnVal = fc.showOpenDialog(Main.this);			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				saveTo = file.getAbsolutePath();
				System.out.println(saveTo);
			}
		} else if (event.getSource() == launchBtnStart) {
			System.out.println("hello");
			System.out.println(optionsTxtUrl.getText());
			pb.setUrl(optionsTxtUrl.getText());
			pb.setName(saveTo);			
			Thread t = new Thread(pb,"ProgressBar");
			t.start();
		}
		
	}


}
