package com.risk.view;

import java.awt.Color;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;


/**
 * Player list view is used to show all players.
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PlayerListView extends JList<String> {
	
	int numberOfPlayers;
	/**
	 * constructor of player list view class.
	 * 
	 * @param numberOfPlayers number of players.
	 */
	public PlayerListView(int numberOfPlayers) {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setBorder(new LineBorder(Color.BLUE));
		setBounds(648, 96, 154, 257);
		this.numberOfPlayers = numberOfPlayers;
	}
}
