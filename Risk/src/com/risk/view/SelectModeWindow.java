package com.risk.view;

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
public class SelectModeWindow extends JFrame{
	
	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = 1L;
	/**
	 * Logger object setup for the log file
	 */
	static Logger logger = Logger.getLogger(StartWindow.class.getName());

	/**
	 * Create the application.
	 */
	public SelectModeWindow() {
	}

	/**
	 * Initialize the contents of the 
	 */
	public void initialize() {
		setTitle("Select Mode");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnSingleGameMode = new JButton("Single Game Mode");
		btnSingleGameMode.setBounds(99, 57, 224, 50);
		getContentPane().add(btnSingleGameMode);
		
		JButton btnTournamentGameMode = new JButton("Tournament Game Mode");
		btnTournamentGameMode.setBounds(99, 123, 224, 50);
		getContentPane().add(btnTournamentGameMode);
		
		btnSingleGameMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logger.log(Level.INFO, "Player clicked Single Game Mode Button, proceeding to Single Mode - Select Map Window");
				setVisible(false);
	            dispose();
				SelectMapWindow gameStart = new SelectMapWindow();		
				gameStart.main();
			}
		});
		
		btnTournamentGameMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.log(Level.INFO, "Player clicked Single Game Mode Button, proceeding to Tournament Mode");
				setVisible(false);
	            dispose();
				SetupWindow window = new SetupWindow();
				window.initialize();
			}
		});
		
		setVisible(true);
		
	}
}
