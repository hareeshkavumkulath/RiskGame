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

public class PlayerListView extends JList<String> {

	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = 5574954950632961945L;
	@SuppressWarnings("javadoc")
	int numberOfPlayers;

	/**
	 * constructor of player list view class.
	 * 
	 * @param numberOfPlayers number of players.
	 */
	public PlayerListView(int numberOfPlayers) {
		setEnabled(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setBorder(new LineBorder(Color.BLUE));
		setBounds(648, 96, 154, 257);
		this.numberOfPlayers = numberOfPlayers;
	}
}
