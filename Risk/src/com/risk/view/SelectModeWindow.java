package com.risk.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * select Single Game Mode or Tournament Game Mode
 * 
 * @author Jingya Pan
 * @version 1.0
 */
public class SelectModeWindow {

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
		
		JButton btnNewButton = new JButton("Single Game Mode");
		btnNewButton.setBounds(99, 57, 224, 50);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnTournamentGameMode = new JButton("Tournament Game Mode");
		btnTournamentGameMode.setBounds(99, 123, 224, 50);
		frame.getContentPane().add(btnTournamentGameMode);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectMapWindow gameStart = new SelectMapWindow();		
				gameStart.main();
			}
		});
		
		btnTournamentGameMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetupWindow window = new SetupWindow();
				window.initialize();
			}
		});
		
	}
}
