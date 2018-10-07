package com.risk.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Starting window of the Risk Game where the user can start the game or upload the Map
 * 
 * @author Hareesh Kavumkulath
 * @author Jingya Pan
 * @version 1.0
 * 
 * Created Date: 06-10-2018
 * Last modified Date:07-10-2018
 * Last modified By:jingya Pan
 */
public class StartWindow {

	private JFrame frame;

	/**
	 * Launch the application.
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
		frame.setBounds(100, 100, 1207, 726);
		frame.setMinimumSize(new Dimension(200, 200));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JButton btnStart = new JButton("Start game");
		/**
		 * event for the button startGame
		 */
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnStart.setFont(new Font("Arial", Font.PLAIN, 20));
		btnStart.setBounds(1500, 700, 128, 50);
		btnStart.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		JButton btnUpload = new JButton("Upload");
		/**
		 * event for the button upload 
		 */
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//direct to the UploadWindow page
				UploadWindow window = new UploadWindow();
				window.main(null);
			}
		});
		btnUpload.setFont(new Font("Arial", Font.PLAIN, 20));
		btnUpload.setBounds(1500, 700, 128, 50);
		btnUpload.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		panel.add(Box.createVerticalGlue());
		panel.add(btnStart);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));
		panel.add(btnUpload);
		
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
