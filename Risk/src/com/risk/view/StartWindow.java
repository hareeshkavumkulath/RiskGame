package com.risk.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.risk.log.RiskLogger;

/**
 * Starting window of the Risk Game where the user can start the game or upload the Map
 * 
 * @author Hareesh Kavumkulath
 * @author Jingya Pan
 * @version 1.1
 * 
 */
public class StartWindow {
	
	/**
	 * Logger object setup for the log file
	 */
	static Logger logger = Logger.getLogger(StartWindow.class.getName());

	@SuppressWarnings("javadoc")
	private JFrame frame;

	/**
	 * Launch the application.
	 * 
	 * @param args default parameter for main function  
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow window = new StartWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException Throws exception when there is any problem in creating the Log file
	 */
	public StartWindow() throws IOException {
		RiskLogger custLogger = new RiskLogger();
		custLogger.setLogger();
		logger = custLogger.getLogger();
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame,and location the components.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Risk Game");
		frame.setBounds(100, 100, 422, 316);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnStart = new JButton("Start Game");
		btnStart.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnStart.setBounds(114, 34, 161, 45);
		frame.getContentPane().add(btnStart);
		
		JButton btnUpload = new JButton("Upload Map");
		btnUpload.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnUpload.setBounds(114, 111, 161, 45);
		frame.getContentPane().add(btnUpload);
		
		JButton loadGame = new JButton("Load Game");
		loadGame.setFont(new Font("Calibri", Font.PLAIN, 20));
		loadGame.setBounds(114, 186, 161, 45);
		frame.getContentPane().add(loadGame);
		
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.log(Level.INFO, "Player clicked Upload Map Button, proceeding to Map Upload");
				UploadWindow uploadWindow = new UploadWindow();
				uploadWindow.initialize();
			}
		});
		
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.log(Level.INFO, "Player clicked Start Game Button, proceeding to Game");
				SelectModeWindow gameStart = new SelectModeWindow();		
				gameStart.initialize();
			}
		});
		
		loadGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.log(Level.INFO, "Player clicked Load Game Button, proceeding to Load Game");
				SelectGameWindow gameStart = new SelectGameWindow();		
				gameStart.initialize();
			}
		});
		frame.setVisible(true);
	}
}
