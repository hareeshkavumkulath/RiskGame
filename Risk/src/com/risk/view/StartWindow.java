package com.risk.view;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Starting window of the Risk Game where the user can start the game or upload the Map
 * 
 * @author Hareesh Kavumkulath
 * @author Jingya Pan
 * @version 1.0
 * 
 */
public class StartWindow {

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
	 */
	public StartWindow() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame,and location the components.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Risk Game");
		frame.setBounds(100, 100, 419, 244);
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
		
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UploadWindow uploadWindow = new UploadWindow();
				uploadWindow.main();
			}
		});
		
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectMapWindow gameStart = new SelectMapWindow();		
				gameStart.main();
			}
		});
		frame.setVisible(true);
	}
}
