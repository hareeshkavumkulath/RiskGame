package com.risk.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.risk.model.Continent;
import com.risk.model.Game;
import com.risk.model.Territory;

import javax.swing.JList;
import javax.swing.JLabel;
/**
 * MapViewPanel shows the map
 * 
 * @author Jingya
 *
 */
public class MapViewPanel extends JPanel implements Observer{

	private JList<String> continentJList;
	private JList<String> territoriesJList;
	private JList<String> adjTerrJList;
	private Game game;

	/**
	 * Create the panel.
	 */
	public MapViewPanel(Game game) {
		this.game = game;
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(15, 41, 191, 249);
		add(scrollPane);
		
		continentJList = new JList<String>();
		scrollPane.setViewportView(continentJList);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(219, 41, 191, 249);
		add(scrollPane_1);
		
		territoriesJList = new JList<String>();
		scrollPane_1.setViewportView(territoriesJList);
		
		JScrollPane scrollPane_2 = new JScrollPane((Component) null);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(425, 41, 192, 249);
		add(scrollPane_2);
		
		adjTerrJList = new JList<String>();
		scrollPane_2.setViewportView(adjTerrJList);
		
		JLabel lblContinents = new JLabel("Continents");
		lblContinents.setBounds(15, 16, 104, 20);
		add(lblContinents);
		
		JLabel lblTerritories = new JLabel("Territories");
		lblTerritories.setBounds(219, 16, 104, 20);
		add(lblTerritories);
		
		JLabel lblAdjacentTerritories = new JLabel("Adjacent Territories");
		lblAdjacentTerritories.setBounds(425, 16, 154, 20);
		add(lblAdjacentTerritories);
		
		ArrayList<Continent> continents = game.getMap().getContinents();
		String[] continentNames = new String[continents.size()];
		for(int i = 0; i < continents.size(); i++) {
			Continent thisContinent = (Continent) continents.get(i);
			continentNames[i] = thisContinent.getName(); 
		}
		continentJList.setListData(continentNames);
		
		continentJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				int selections[] = continentJList.getSelectedIndices();
				ArrayList<Territory> territories = continents.get(selections[0]).getTerritories();
				String[] territoryNames = new String[territories.size()];
				for(int i=0;i<territories.size();i++) {
					territoryNames[i] = territories.get(i).getName();
					if(territories.get(i).getRuler() != null) {
						territoryNames[i] = territoryNames[i] + "(" + territories.get(i).getRuler().getName() + " - " + territories.get(i).getNumberOfArmies() +")";
					}
				}
				territoriesJList.setListData(territoryNames);				
			}
		});
		
		territoriesJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				String selection = (String) territoriesJList.getSelectedValue();
				try {
					selection = selection.replaceAll("\\(.+?\\)", "");
					ArrayList<Territory> territories = game.getMap().getTerritories();
					Territory selectedTerritory = null;
					for(int i=0;i<territories.size();i++) {
						if(territories.get(i).getName().equals(selection)) {
							selectedTerritory = territories.get(i);
						}
					}
				
					String[] adjTerritoryNames = new String[selectedTerritory.getAdjacentTerritories().size()];
					for(int i=0;i<selectedTerritory.getAdjacentTerritories().size();i++) {
						Territory adjTerritory = selectedTerritory.getAdjacentTerritories().get(i);
						adjTerritoryNames[i] = adjTerritory.getName();
						if(adjTerritory.getRuler() != null) {
							adjTerritoryNames[i] = adjTerritoryNames[i] + "(" + adjTerritory.getRuler().getName() + " - " + adjTerritory.getNumberOfArmies() +")";
						}
					}
					adjTerrJList.setListData(adjTerritoryNames);
				} catch(Exception e) {
					System.out.println(e.toString());
				}
			}
		});

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		int continentsSelectedIndex = continentJList.getSelectedIndex();
		int territoriesSelectedIndex = territoriesJList.getSelectedIndex();
		int adjTerrSelectedIndex = adjTerrJList.getSelectedIndex();
		continentJList.setSelectedIndex(continentsSelectedIndex);
		if(continentsSelectedIndex >= 0) {
			int selections[] = continentJList.getSelectedIndices();
			ArrayList<Territory> territories = game.getMap().getContinents().get(selections[0]).getTerritories();
			String[] territoryNames = new String[territories.size()];
			for(int i=0;i<territories.size();i++) {
				territoryNames[i] = territories.get(i).getName();
				if(territories.get(i).getRuler() != null) {
					territoryNames[i] = territoryNames[i] + "(" + territories.get(i).getRuler().getName() + " - " + territories.get(i).getNumberOfArmies() +")";
				}
			}
			territoriesJList.setListData(territoryNames);	
			territoriesJList.setSelectedIndex(territoriesSelectedIndex);
		}
		if(territoriesSelectedIndex >= 0) {
			String selection = (String) territoriesJList.getSelectedValue();
			try {
				selection = selection.replaceAll("\\(.+?\\)", "");
				ArrayList<Territory> territories = game.getMap().getTerritories();
				Territory selectedTerritory = null;
				for(int i=0;i<territories.size();i++) {
					if(territories.get(i).getName().equals(selection)) {
						selectedTerritory = territories.get(i);
					}
				}
			
				String[] adjTerritoryNames = new String[selectedTerritory.getAdjacentTerritories().size()];
				for(int i=0;i<selectedTerritory.getAdjacentTerritories().size();i++) {
					Territory adjTerritory = selectedTerritory.getAdjacentTerritories().get(i);
					adjTerritoryNames[i] = adjTerritory.getName();
					if(adjTerritory.getRuler() != null) {
						adjTerritoryNames[i] = adjTerritoryNames[i] + "(" + adjTerritory.getRuler().getName() + " - " + adjTerritory.getNumberOfArmies() +")";
					}
				}
				adjTerrJList.setListData(adjTerritoryNames);
				adjTerrJList.setSelectedIndex(adjTerrSelectedIndex);
			} catch(Exception e) {
				System.out.println(e.toString());
			}
		}
	}
}
