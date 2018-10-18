/**
 * 
 */
package com.risk.view;

import java.awt.Color;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;


/**
 * @author Hareesh Kavumkulath
 *
 */
public class PlayerListView extends JList<String> {
	
	int numberOfPlayers;
	
	public PlayerListView(int numberOfPlayers) {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setBorder(new LineBorder(Color.BLUE));
		setBounds(648, 96, 154, 257);
		this.numberOfPlayers = numberOfPlayers;
	}
}
