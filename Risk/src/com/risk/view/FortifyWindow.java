package com.risk.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.risk.controller.GameController;
import com.risk.model.Player;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
/**
 * 
 * @author Hareesh Kavumkulath
 *
 */
public class FortifyWindow extends JFrame {

	private JPanel contentPane;
	private Player player;
	private JTextField fortifyNumberField;

	 /**
	 * Create the frame.
	 */
	public FortifyWindow(Player player) {
		setBounds(100, 100, 912, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.player = player;
		String[] territoryArray = new String[player.getOwnedTerritories().size()];
		territoryArray = getTerritoryArray(player);
		
		JLabel playerName = new JLabel(player.getName());
		playerName.setBounds(15, 60, 69, 20);
		contentPane.add(playerName);
		
		JLabel lblNameOfThe = new JLabel("Name of the Player");
		lblNameOfThe.setBounds(15, 24, 170, 20);
		contentPane.add(lblNameOfThe);
		
		JLabel lblTerritoryFrom = new JLabel("Territory: From");
		lblTerritoryFrom.setBounds(217, 24, 135, 20);
		contentPane.add(lblTerritoryFrom);
		
		JLabel lblTerritoryTo = new JLabel("Territory: To");
		lblTerritoryTo.setBounds(459, 24, 135, 20);
		contentPane.add(lblTerritoryTo);
		
		JLabel lblNewLabel = new JLabel("Number of Armies");
		lblNewLabel.setBounds(695, 24, 221, 20);
		contentPane.add(lblNewLabel);
		
		JComboBox territoryFrom = new JComboBox();
		territoryFrom.setModel(new DefaultComboBoxModel(territoryArray));
		territoryFrom.setBounds(217, 57, 190, 26);
		contentPane.add(territoryFrom);
		
		JComboBox territoryTo = new JComboBox();
		territoryTo.setModel(new DefaultComboBoxModel(territoryArray));
		territoryTo.setBounds(459, 57, 190, 26);
		contentPane.add(territoryTo);
		
		fortifyNumberField = new JTextField();
		fortifyNumberField.setBounds(695, 57, 146, 26);
		contentPane.add(fortifyNumberField);
		fortifyNumberField.setColumns(10);
		
		JButton btnFortify = new JButton("Fortify");
		btnFortify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(territoryFrom.getSelectedIndex() == territoryTo.getSelectedIndex()) {
					JOptionPane.showMessageDialog(null, "Select a different territory", "Alert", JOptionPane.ERROR_MESSAGE);
				}else {
					GameController controller = new GameController();
					boolean isValid = controller.validateFortifyMove(player, territoryFrom.getSelectedIndex());
					if(isValid) {
						try {
							int fortifyNum = Integer.parseInt(fortifyNumberField.getText());
							boolean isValidNumber = controller.validateFortifyNumber(player, territoryFrom.getSelectedIndex(), fortifyNum);
							if(isValidNumber) {
								boolean fortifyStatus = controller.fortify(player, territoryFrom.getSelectedIndex(), territoryTo.getSelectedIndex(), fortifyNum);
								if(fortifyStatus) {
									JOptionPane.showMessageDialog(null, "Fortification is done", "Success Message", JOptionPane.INFORMATION_MESSAGE);
								}else {
									JOptionPane.showMessageDialog(null, "Please try again", "Alert", JOptionPane.ERROR_MESSAGE);
								}
							}else {
								JOptionPane.showMessageDialog(null, "Please enter a small number", "Alert", JOptionPane.ERROR_MESSAGE);
							}
						}catch(Exception ex) {
							JOptionPane.showMessageDialog(null, "Please enter a valid number", "Alert", JOptionPane.ERROR_MESSAGE);
						}						
					}else {
						JOptionPane.showMessageDialog(null, "Not enough armies", "Alert", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnFortify.setBounds(726, 147, 115, 29);
		contentPane.add(btnFortify);		
		
	}
	
	public String[] getTerritoryArray(Player player) {
		String[] territoryArray = new String[player.getOwnedTerritories().size()];
		for(int i=0;i<player.getOwnedTerritories().size();i++) {
			territoryArray[i] = player.getOwnedTerritories().get(i).getName() + "(" + player.getOwnedTerritories().get(i).getNumberOfArmies() + ")";
		}
		return territoryArray;
	}
}