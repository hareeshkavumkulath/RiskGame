package com.risk.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.risk.model.Player;

/**
 * AddArmyCombo Class implements player adding armies on their territories.  
 * 
 * @author Hareesh Kavumkulath
 * @version 1.1
 */
public class AddArmyCombo extends JComboBox<String>{

	/**
	 * The method AddArmyCombo has assigned territories' name, and number of armies associated to this territories for player.
	 * 
	 * @param player This helps to assign information on every player
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
