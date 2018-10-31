/**
 * 
 */
package com.risk.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.risk.model.Player;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Hareesh Kavumkulath
 *
 */
public class AttackView extends JFrame {

	private JPanel contentPane;
	private JTextField attackingArmy;
	private JTextField attackedArmy;
	
	/**
	 * Create the frame.
	 */
	public AttackView(Player player) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1215, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Attacking Territory");
		lblNewLabel.setBounds(244, 16, 151, 20);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("Player Name");
		label.setBounds(15, 16, 151, 20);
		contentPane.add(label);
		
		JLabel lblAttackedCountry = new JLabel("Attacked Territory");
		lblAttackedCountry.setBounds(563, 16, 151, 20);
		contentPane.add(lblAttackedCountry);
		
		JLabel playerName = new JLabel(player.getName());
		playerName.setBounds(15, 57, 69, 20);
		contentPane.add(playerName);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(244, 52, 247, 303);
		contentPane.add(scrollPane);
		
		JTextPane attackingTerr = new JTextPane();
		attackingTerr.setText("");
		scrollPane.setViewportView(attackingTerr);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(563, 52, 247, 303);
		contentPane.add(scrollPane_1);
		
		JTextPane attackedTerr = new JTextPane();
		attackedTerr.setText("");
		scrollPane_1.setViewportView(attackedTerr);
		
		attackingArmy = new JTextField();
		attackingArmy.setBounds(244, 371, 247, 26);
		contentPane.add(attackingArmy);
		attackingArmy.setColumns(10);
		
		attackedArmy = new JTextField();
		attackedArmy.setColumns(10);
		attackedArmy.setBounds(563, 371, 247, 26);
		contentPane.add(attackedArmy);
		
		JLabel lblNumberOfDices = new JLabel("Number of Dices");
		lblNumberOfDices.setBounds(15, 374, 167, 20);
		contentPane.add(lblNumberOfDices);
		
		JButton btnAttack = new JButton("Attack");
		btnAttack.setBounds(655, 442, 151, 56);
		contentPane.add(btnAttack);
		
		JTextPane statusPanel = new JTextPane();
		statusPanel.setText("");
		
		JScrollPane scrollPane_2 = new JScrollPane(statusPanel);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(879, 52, 299, 345);
		contentPane.add(scrollPane_2);
		
		JButton btnEndAttack = new JButton("End Attack");
		btnEndAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEndAttack.setBounds(832, 442, 151, 56);
		contentPane.add(btnEndAttack);
		
		JButton btnFortify = new JButton("Fortify");
		btnFortify.setBounds(1011, 442, 151, 56);
		contentPane.add(btnFortify);
		
		setVisible(true);
	}
}
