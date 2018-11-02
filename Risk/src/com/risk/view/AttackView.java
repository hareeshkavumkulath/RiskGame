/**
 * 
 */
package com.risk.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
public class AttackView extends JPanel {

	@SuppressWarnings("javadoc")
	private JTextField attackingArmy;
	@SuppressWarnings("javadoc")
	private JTextField attackedArmy;
	@SuppressWarnings("javadoc")
	public ArrayList<Territory> attackingTerritories;
	
	/**
	 * Create the frame. This is a constructor assigning values.
	 * 
	 * @param player Define the player information.
	 */
	public AttackView(Player player) {
		setBounds(100, 100, 846, 494);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Attacking Territory");
		lblNewLabel.setBounds(244, 16, 151, 20);
		add(lblNewLabel);
		
		JLabel label = new JLabel("Player Name");
		label.setBounds(15, 16, 151, 20);
		add(label);
		
		JLabel lblAttackedCountry = new JLabel("Attacked Territory");
		lblAttackedCountry.setBounds(563, 16, 151, 20);
		add(lblAttackedCountry);
		
		JLabel lblPlayer = new JLabel(player.getName());
		lblPlayer.setBounds(15, 57, 69, 20);
		add(lblPlayer);
		
		JList<String> attackingTerr = new JList<String>();
		attackingTerr.setBounds(1, 1, 219, 301);
		attackingTerritories = player.getOwnedTerritories();
		String[] attackingTerrNames = new String[attackingTerritories.size()];
		for(int i = 0;i<attackingTerritories.size();i++) {
			attackingTerrNames[i] = attackingTerritories.get(i).getName() + "(" + attackingTerritories.get(i).getNumberOfArmies() + ")";
		}
		attackingTerr.setListData(attackingTerrNames);
		add(attackingTerr);
		
		JScrollPane attackingTerrPane = new JScrollPane(attackingTerr);
		attackingTerrPane.setBounds(244, 51, 247, 303);
		attackingTerrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(attackingTerrPane);
		
		JList<String> attackedTerr = new JList<String>();
		attackedTerr.setBounds(1, 1, 219, 301);
		add(attackedTerr);
		
		JScrollPane attackedTerrPane = new JScrollPane(attackedTerr);
		attackedTerrPane.setBounds(563, 52, 247, 303);
		attackedTerrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(attackedTerrPane);
		
		attackingArmy = new JTextField();
		attackingArmy.setBounds(244, 371, 247, 26);
		add(attackingArmy);
		attackingArmy.setColumns(10);
		
		attackedArmy = new JTextField();
		attackedArmy.setBounds(563, 371, 247, 26);
		attackedArmy.setColumns(10);
		add(attackedArmy);
		
		JLabel lblNumberOfDices = new JLabel("Number of Dices");
		lblNumberOfDices.setBounds(15, 374, 167, 20);
		add(lblNumberOfDices);
		
		JButton btnAttack = new JButton("Attack");
		btnAttack.setBounds(502, 428, 115, 29);
		add(btnAttack);
		
		JButton btnEndAttack = new JButton("End Attack");
		btnEndAttack.setBounds(686, 428, 123, 29);
		add(btnEndAttack);
		
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
		
		attackedTerr.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				try {
					int selectedIndexAttacker = attackingTerr.getSelectedIndex();
					int selectedIndexAttacked = attackedTerr.getSelectedIndex();
					Player attackedPlayer = attackingTerritories.get(selectedIndexAttacker).getAdjacentTerritories().get(selectedIndexAttacked).getRuler();
					if(player == attackedPlayer) {
						JOptionPane.showMessageDialog(null, "Both Attacking and Attacked Players should not be same. Choose a different territory", "Alert", JOptionPane.ERROR_MESSAGE);
						attackedTerr.clearSelection();
					}
				}catch(Exception e) {
					System.out.println("Exception in attacked Territory list selection handler:"+e.toString());
				}
			}
			
		});
		
		
	}
}
