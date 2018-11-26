package com.risk.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
/**
 * select Single Game Mode or Tournament Game Mode
 * 
 * @author Jingya Pan
 * @version 1.0
 */
public class SelectModeWindow {
	
	/**
	 * Logger object setup for the log file
	 */
	static Logger logger = Logger.getLogger(StartWindow.class.getName());

	@SuppressWarnings("javadoc")
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectModeWindow window = new SelectModeWindow();
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
	public SelectModeWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSingleGameMode = new JButton("Single Game Mode");
		btnSingleGameMode.setBounds(99, 57, 224, 50);
		frame.getContentPane().add(btnSingleGameMode);
		
		JButton btnTournamentGameMode = new JButton("Tournament Game Mode");
		btnTournamentGameMode.setBounds(99, 123, 224, 50);
		frame.getContentPane().add(btnTournamentGameMode);
		
		btnSingleGameMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logger.log(Level.INFO, "Player clicked Single Game Mode Button, proceeding to Single Mode - Select Map Window");
				SelectMapWindow gameStart = new SelectMapWindow();		
				gameStart.main();
			}
		});
		
		btnTournamentGameMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.log(Level.INFO, "Player clicked Single Game Mode Button, proceeding to Tournament Mode");
				SetupWindow window = new SetupWindow();
				window.initialize();
			}
		});
		
	}
}
