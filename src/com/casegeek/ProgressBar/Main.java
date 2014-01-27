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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class Main extends JFrame implements ActionListener {
	
	private static ProgressBar pb = new ProgressBar();
	private JButton chooseDest, launchBtnStart;
	private JTextArea optionsTxtUrl;
	private static JTextArea loggingTxt;
	private JFileChooser fc = new JFileChooser();
	private String saveTo = "";
	private static Logger LOGGER = new Logger();
	
	Main() {
		
		super("Downloader by GeneralBrae");
		setSize(500,230);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		//Setup options area
		JPanel options = new JPanel();
		options.setBorder(BorderFactory.createTitledBorder("Options"));
		BoxLayout layout_Options = new BoxLayout(options, BoxLayout.Y_AXIS);
		options.setLayout(layout_Options);
		JLabel optionsLblURL = new JLabel("Enter URL:");
		optionsTxtUrl = new JTextArea(1,15);	
		optionsTxtUrl.setLineWrap(true);
		optionsTxtUrl.setWrapStyleWord(true);
		JScrollPane scrollUrl = new JScrollPane(optionsTxtUrl);
		JLabel chooseDestLbl = new JLabel("Choose save location:");
		chooseDest = new JButton("Browse");
		chooseDest.addActionListener(this);
		options.add(optionsLblURL);
		options.add(scrollUrl);
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
		loggingTxt = new JTextArea(3,10);
		loggingTxt.setLineWrap(true);
		loggingTxt.setWrapStyleWord(true);
		JScrollPane scrollLog = new JScrollPane(loggingTxt);
		loggingTxt.setEditable(false);
		logging.add(pb);
		logging.add(scrollLog);
		
		//Add components to window		
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);
		add("West", options);
		add("East", launch);
		add("South", logging);
		
		setVisible(true);
		
	}

	public static void main(String[] args) {
		Main main = new Main();	
		LogLoop readlog = new LogLoop();
		Thread log = new Thread(readlog, "Log");
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == chooseDest) {
			int returnVal = fc.showOpenDialog(Main.this);			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				saveTo = file.getAbsolutePath();
				LOGGER.Log("Changed save destination to " + saveTo);
			}
		} else if (event.getSource() == launchBtnStart) {
			LOGGER.Log("Downloading file\n - Source: " + optionsTxtUrl.getText() + "\n - Destination: " + saveTo);
			pb.setUrl(optionsTxtUrl.getText());
			pb.setName(saveTo);			
			Thread t = new Thread(pb,"ProgressBar");
			t.start();
		}
		
	}
	
	public static class LogLoop implements Runnable {

		@Override
		public void run() {
			while(true) {
				loggingTxt.setText(LOGGER.Read());
			}
			
		}
		
	}


}


