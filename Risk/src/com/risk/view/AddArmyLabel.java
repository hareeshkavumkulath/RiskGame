/**
 * 
 */
package com.risk.view;

import javax.swing.JLabel;

import com.risk.model.Player;

/**
 * @author Hareesh Kavumkulath
 *
 */
public class AddArmyLabel extends JLabel{

	/**
	 * @param player 
	 * 
	 */
	public AddArmyLabel(Player player) {
		super(player.getName());
		setBounds(25, 381, 69, 20);
	}

}
