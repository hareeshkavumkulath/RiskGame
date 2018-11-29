package com.risk.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.risk.controller.GameController;
import com.risk.model.Continent;
import com.risk.model.Game;
import com.risk.model.Player;
import com.risk.model.Territory;
import java.awt.Font;

/**
 * Observable view for Player World Domination
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 *
 */
public class MapDominationView extends JPanel implements Observer{
	
	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = -863572094027587585L;
	@SuppressWarnings("javadoc")
	private Game game;
	@SuppressWarnings("javadoc")
	private JTextPane continentDomination;
	@SuppressWarnings("javadoc")
	private JTextPane mapDomination;
	@SuppressWarnings("javadoc")
	private JTextPane playerInfo;
	
	/**
	 * Constructor for Player World Domination View
	 * 
	 * @param game set the game profile
	 */
	public MapDominationView(Game game) {
		super();
		this.game = game;
		
		setBounds(15, 381, 618, 371);
		setLayout(null);
		
		JLabel panelName = new JLabel("Player World Domination View");
		panelName.setBounds(15, 16, 255, 20);
		add(panelName);
		
		continentDomination = new JTextPane();
		continentDomination.setFont(new Font("Calibri", Font.PLAIN, 18));
		continentDomination.setBounds(15, 52, 247, 303);
		add(continentDomination);
		
		mapDomination = new JTextPane();
		mapDomination.setFont(new Font("Calibri", Font.PLAIN, 18));
		mapDomination.setBounds(277, 52, 326, 145);
		add(mapDomination);
		
		playerInfo = new JTextPane();
		playerInfo.setFont(new Font("Calibri", Font.PLAIN, 18));
		playerInfo.setBounds(277, 213, 326, 142);
		add(playerInfo);
		
		JScrollPane scrollPaneContinent = new JScrollPane(continentDomination);
		scrollPaneContinent.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneContinent.setBounds(15, 52, 247, 303);
		add(scrollPaneContinent);
		
		JScrollPane scrollPaneMap = new JScrollPane(mapDomination);
		scrollPaneMap.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMap.setBounds(277, 52, 326, 145);
		add(scrollPaneMap);
		
		JScrollPane scrollPanePlayer = new JScrollPane(playerInfo);
		scrollPanePlayer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanePlayer.setBounds(277, 210, 326, 145);
		add(scrollPanePlayer);
		setVisible(true);
		
		editField();
		
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		this.game = (Game) arg1;
		editField();
	}
	
	/**
	 * Populates fields with Game Values
	 */
	public void editField() {
		GameController controller = new GameController();
		ArrayList<Continent> continents = game.getMap().getContinents();
		ArrayList<Territory> territories = game.getMap().getTerritories();
		ArrayList<Player> players = game.getPlayers();
		//Continent Domination View
		StringBuffer continentDomStr = new StringBuffer();
		for(int i=0;i<continents.size();i++) {
			Continent continent = continents.get(i);
			continentDomStr.append(continent.getName()+"\n");
			continentDomStr.append("******************************\n");
			for(int j=0;j<players.size();j++) {
				Player player = players.get(j);
				double percentage = controller.getDomination(continent, player);
				percentage = Double.parseDouble(new DecimalFormat("##.##").format(percentage));
				continentDomStr.append(player.getName()+ " - " + percentage + "%\n");
			}
			continentDomStr.append("******************************\n");
		}
		continentDomination.setText(continentDomStr.toString());
		
		//Map Domination View
		StringBuffer mapDomStr = new StringBuffer();
		mapDomStr.append("Map Domination View\n");
		mapDomStr.append("**************************************\n");
		for(int i=0;i<players.size();i++) {
			Player player = players.get(i);
			double percentage = controller.getDomination(player, territories);
			percentage = Double.parseDouble(new DecimalFormat("##.##").format(percentage));
			mapDomStr.append(player.getName()+ " - " + percentage + "%\n");
		}
		mapDomination.setText(mapDomStr.toString());
		
		//Player and No. of Armies
		StringBuffer playerArmyInfo = new StringBuffer();
		playerArmyInfo.append("Players and Number of Armies\n");
		playerArmyInfo.append("**************************************\n");
		for(int i=0;i<players.size();i++) {
			Player player = players.get(i);
			playerArmyInfo.append(player.getName()+ " - " + player.getNumberOfArmies() + "\n");
		}
		playerInfo.setText(playerArmyInfo.toString());
	}
}
