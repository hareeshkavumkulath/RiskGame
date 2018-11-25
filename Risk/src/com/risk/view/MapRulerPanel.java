/**
 * 
 */
package com.risk.view;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import com.risk.model.Continent;
import com.risk.model.Game;
import com.risk.model.Territory;

import java.awt.Font;
import java.awt.Color;

/**
 * @author DELL
 *
 */
public class MapRulerPanel extends JPanel implements Observer {
	
	/**
	 * Set serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("javadoc")
	private Game game;
	@SuppressWarnings("javadoc")
	private JTextPane textPane;

	/**
	 * Set the layout
	 * 
	 * @param game object
	 */
	public MapRulerPanel(Game game) {	
		this.game = game;
		setBounds(1390, 336, 519, 513);	
		setLayout(null);		
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(15, 15, 489, 483);
		add(scrollPane);
		
		textPane = new JTextPane();
		textPane.setForeground(Color.BLACK);
		textPane.setFont(new Font("Calibri", Font.PLAIN, 16));
		textPane.setText("");
		scrollPane.setViewportView(textPane);
		
		displayMapRuler();
		
	}

	/**
	 * Display MapRuler
	 */
	private void displayMapRuler() {
		ArrayList<Continent> continents = game.getMap().getContinents();
		StringBuffer mapDisplay = new StringBuffer("Map\n");
		for(int i=0;i<continents.size();i++) {
			Continent continent = continents.get(i);
			mapDisplay.append(continent.getName() + "\n");
			mapDisplay.append("**************************\n");
			for(int j=0;j<continent.getTerritories().size();j++) {
				Territory territory = continent.getTerritories().get(j);
				mapDisplay.append(territory.getName() + "-" + territory.getRuler().getName() + "-" + territory.getNumberOfArmies() +"\n");
			}
		}
		textPane.setText(mapDisplay.toString());
	}

	/**
	 * Observer design pattern 
	 * 
	 * @param arg0 Observable
	 * @param arg1 Object
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		displayMapRuler();
	}
}
