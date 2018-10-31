/**
 * 
 */
package com.risk.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * @author Hareesh Kavumkulath
 *
 */
public class AttackView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttackView frame = new AttackView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AttackView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1148, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Attacking Country");
		lblNewLabel.setBounds(244, 16, 151, 20);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("Player Name");
		label.setBounds(15, 16, 151, 20);
		contentPane.add(label);
		
		JLabel lblAttackedCountry = new JLabel("Attacked Country");
		lblAttackedCountry.setBounds(563, 16, 151, 20);
		contentPane.add(lblAttackedCountry);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(15, 57, 69, 20);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(244, 52, 247, 303);
		contentPane.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("");
		scrollPane.setViewportView(textPane);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(563, 52, 247, 303);
		contentPane.add(scrollPane_1);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("");
		scrollPane_1.setViewportView(textPane_1);
		
		textField = new JTextField();
		textField.setBounds(244, 371, 247, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(563, 371, 247, 26);
		contentPane.add(textField_1);
		
		JLabel lblNumberOfDices = new JLabel("Number of Dices");
		lblNumberOfDices.setBounds(15, 374, 167, 20);
		contentPane.add(lblNumberOfDices);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(695, 428, 115, 29);
		contentPane.add(btnNewButton);
	}
}
