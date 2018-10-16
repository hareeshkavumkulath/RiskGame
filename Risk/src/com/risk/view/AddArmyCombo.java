/**
 * 
 */
package com.risk.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.risk.model.Player;

/**
 * @author Hareesh Kavumkulath
 *
 */
public class AddArmyCombo extends JComboBox<String>{

	/**
	 * @param player 
	 * 
	 */
	public AddArmyCombo(Player player) {
		String[] territories = new String[player.getOwnedTerritories().size()];
		for(int i=0;i<player.getOwnedTerritories().size();i++) {
			territories[i] = player.getOwnedTerritories().get(i).getName() + "(" + player.getOwnedTerritories().get(i).getNumberOfArmies() + ")";
		}
		setModel(new DefaultComboBoxModel(territories));
		setBounds(184, 378, 211, 26);
	}

}
