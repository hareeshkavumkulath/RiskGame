/**
 * 
 */
package com.risk.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.risk.model.Player;
import com.risk.model.Territory;

import javax.swing.JTextPane;
import java.util.ArrayList;

/**
 * Attack View Window JFrame
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 *
 */
public class AttackView extends JFrame {

	@SuppressWarnings("javadoc")
	private JPanel contentPane;
	
	@SuppressWarnings("javadoc")
	private JTextField attackingArmy;
	@SuppressWarnings("javadoc")
	private JTextField attackedArmy;
	@SuppressWarnings("javadoc")
	private Player player;
	@SuppressWarnings("javadoc")
	public ArrayList<Territory> attackingTerritories;
	
	/**
	 * Create the frame. This is a constructor assigning values.
	 * 
	 * @param player Define the player information.
	 */
	public AttackView(Player player) {
		this.player = player;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1228, 598);
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
		
		JLabel lblPlayer = new JLabel(player.getName());
		lblPlayer.setBounds(15, 57, 69, 20);
		contentPane.add(lblPlayer);
		
		JList<String> attackingTerr = new JList<String>();
		attackingTerr.setBounds(1, 1, 219, 301);
		attackingTerritories = player.getOwnedTerritories();
		String[] attackingTerrNames = new String[attackingTerritories.size()];
		for(int i = 0;i<attackingTerritories.size();i++) {
			attackingTerrNames[i] = attackingTerritories.get(i).getName() + "(" + attackingTerritories.get(i).getNumberOfArmies() + ")";
		}
		attackingTerr.setListData(attackingTerrNames);
		contentPane.add(attackingTerr);
		
		JScrollPane attackingTerrPane = new JScrollPane(attackingTerr);
		attackingTerrPane.setBounds(244, 51, 247, 303);
		attackingTerrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(attackingTerrPane);
		
		JList<String> attackedTerr = new JList<String>();
		attackedTerr.setBounds(1, 1, 219, 301);
		contentPane.add(attackedTerr);
		
		JScrollPane attackedTerrPane = new JScrollPane(attackedTerr);
		attackedTerrPane.setBounds(563, 52, 247, 303);
		attackedTerrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(attackedTerrPane);
		
		attackingArmy = new JTextField();
		attackingArmy.setBounds(244, 371, 247, 26);
		contentPane.add(attackingArmy);
		attackingArmy.setColumns(10);
		
		attackedArmy = new JTextField();
		attackedArmy.setBounds(563, 371, 247, 26);
		attackedArmy.setColumns(10);
		contentPane.add(attackedArmy);
		
		JLabel lblNumberOfDices = new JLabel("Number of Dices");
		lblNumberOfDices.setBounds(15, 374, 167, 20);
		contentPane.add(lblNumberOfDices);
		
		JButton btnAttack = new JButton("Attack");
		btnAttack.setBounds(695, 428, 115, 29);
		contentPane.add(btnAttack);
		
		JButton btnEndAttack = new JButton("End Attack");
		btnEndAttack.setBounds(856, 428, 123, 29);
		contentPane.add(btnEndAttack);
		
		JTextPane statusPanel = new JTextPane();
		statusPanel.setText("");
		
		JScrollPane StatusScrollPane = new JScrollPane(statusPanel);
		StatusScrollPane.setBounds(891, 51, 268, 303);
		StatusScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		StatusScrollPane.setViewportView(statusPanel);
		contentPane.add(StatusScrollPane);
		
		JLabel lblStatus = new JLabel("Status information");
		lblStatus.setBounds(891, 16, 195, 21);
		contentPane.add(lblStatus);
		
		attackingTerr.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int selectedIndex = attackingTerr.getSelectedIndex();
				ArrayList<Territory> attackedTerritories = player.getOwnedTerritories().get(selectedIndex).getAdjacentTerritories();
				String[] attackedTerrNames = new String[attackedTerritories.size()];
				for(int i = 0;i<attackedTerrNames.length;i++) {
					Territory tempTerritory = attackedTerritories.get(i);
					attackedTerrNames[i] = tempTerritory.getName() + "(" + tempTerritory.getRuler().getName() + "-" + tempTerritory.getNumberOfArmies() + ")";
				}
				attackedTerr.setListData(attackedTerrNames);
			}
		});
		
		
		
		
	}
}
