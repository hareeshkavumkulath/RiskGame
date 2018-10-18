package com.risk.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.risk.controller.GameController;
import com.risk.model.Continent;
import com.risk.model.GameInstructions;
import com.risk.model.Map;
import com.risk.model.Player;
import com.risk.model.Territory;

import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Window;

/**
 * @author Hareesh Kavumkulath
 * @version 1.1
 *
 */
public class GameWindow {

	private JFrame frame;
	private Map map;
	private int numberOfPlayers;
	private JButton playerButton; 
	private JButton beginGame;
	
	private JTextField player1;
	private JTextField player2;
	private JTextField player3 = null;
	private JTextField player4 = null;
	private JTextField player5;
	private JTextField player6;
	private JComboBox playerType3 = null;
	private JComboBox playerType4 = null;
	private JComboBox playerType5;
	private JComboBox playerType6;
	private JList<String> territoriesJList;
	private PlayerListView playerJList;
	private JList<String> ownedTerritories;
	
	public ArrayList<Player> playerList = new ArrayList<Player>();
	public ArrayList<Continent> continents;
	public ArrayList<Territory> territories;
	private String instructionsMsg = "Let's Start Conquering the world.\r\nPlease select the number of Players";
	private GameInstructions instructions = new GameInstructions(instructionsMsg);
	private AbstractButton btnAddArmy;
	private JButton btnReinforcement;
	private JButton btnFortify;
	private boolean reinforceStatus = false;
	private JButton btnEndFortify;

	
	/**
	 * Launch the application.
	 */
	public void main(Map map) {
		EventQueue.invokeLater(new Runnable() {
			private Map newMap = map;

			public void run() {
				try {
					GameWindow window = new GameWindow(newMap);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param newMap 
	 */
	public GameWindow(Map map) {
		this.map = map;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.GREEN);
		frame.setBounds(100, 100, 1859, 824);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel continentlabel = new JLabel("Continents");
		continentlabel.setBounds(38, 16, 115, 20);
		frame.getContentPane().add(continentlabel);
		
		JLabel territoriesLabel = new JLabel("Territories");
		territoriesLabel.setBounds(247, 16, 115, 20);
		frame.getContentPane().add(territoriesLabel);
		
		JLabel adjTerritoriesLabel = new JLabel("Adjacent Territories");
		adjTerritoriesLabel.setBounds(445, 16, 201, 20);
		frame.getContentPane().add(adjTerritoriesLabel);
		
		JList<String> continentJList = new JList<String>();
		continentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		continentJList.setBorder(new LineBorder(Color.BLUE));
		continentJList.setBounds(15, 40, 154, 313);
		frame.getContentPane().add(continentJList);
		
		territoriesJList = new JList<String>();
		territoriesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		territoriesJList.setBorder(new LineBorder(Color.BLUE));
		territoriesJList.setBounds(184, 40, 211, 313);
		frame.getContentPane().add(territoriesJList);
		
		JList<String> adjTerritoriesJList = new JList<String>();
		adjTerritoriesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		adjTerritoriesJList.setBorder(new LineBorder(Color.BLUE));
		adjTerritoriesJList.setBounds(410, 40, 211, 313);
		frame.getContentPane().add(adjTerritoriesJList);
		
		// Loading values in JLists
		continents = map.getContinents();
		territories = map.getTerritories();
		String[] continentNames = new String[continents.size()];
		for(int i = 0; i < continents.size(); i++) {
			Continent thisContinent = (Continent) continents.get(i);
			continentNames[i] = thisContinent.getName(); 
		}
		continentJList.setListData(continentNames);
		
		continentJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				JList list = (JList) listSelectionEvent.getSource();
				int selections[] = list.getSelectedIndices();
				ArrayList<Territory> territories = map.getContinents().get(selections[0]).getTerritories();
				String[] territoryNames = new String[territories.size()];
				for(int i=0;i<territories.size();i++) {
					territoryNames[i] = territories.get(i).getName();
				}
				territoriesJList.setListData(territoryNames);				
			}
		});
		
		territoriesJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				JList list = (JList) listSelectionEvent.getSource();
				String selection = (String) list.getSelectedValue();
				ArrayList<Territory> territories = map.getTerritories();
				Territory selectedTerritory = null;
				for(int i=0;i<territories.size();i++) {
					if(territories.get(i).getName().equals(selection)) {
						selectedTerritory = territories.get(i);
					}
				}
				try {
					String[] adjTerritoryNames = new String[selectedTerritory.getAdjacentTerritories().size()];
					for(int i=0;i<selectedTerritory.getAdjacentTerritories().size();i++) {
						adjTerritoryNames[i] = selectedTerritory.getAdjacentTerritories().get(i);
					}
					adjTerritoriesJList.setListData(adjTerritoryNames);
				} catch(Exception e) {
					System.out.println(e.toString());
				}
			}
		});
		
		// Select number of Players and create Players
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(2, 2, 6, 1));
		spinner.setBounds(885, 39, 46, 26);
		spinner.setValue(5);
		frame.getContentPane().add(spinner);
		
		JLabel lblSelectTheNumber = new JLabel("Select the number of Players:");
		lblSelectTheNumber.setBounds(648, 42, 256, 20);
		frame.getContentPane().add(lblSelectTheNumber);
		
		JButton btnOk = new JButton(">>");
		btnOk.setBounds(946, 38, 64, 29);
		frame.getContentPane().add(btnOk);
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//btnOk.setVisible(false);
				numberOfPlayers = Integer.parseInt(spinner.getValue().toString());
				showPlayerSetupPanel(numberOfPlayers);
				btnOk.setVisible(false);
			}
		});
		
		playerJList = new PlayerListView(numberOfPlayers);
		frame.getContentPane().add(playerJList);
		
		beginGame = new JButton("Begin \r\nConquest");
		beginGame.setBackground(Color.WHITE);
		beginGame.setForeground(Color.GREEN);
		beginGame.setFont(new Font("Tahoma", Font.BOLD, 22));
		beginGame.setBounds(1118, 16, 235, 48);
		beginGame.setVisible(false);
		frame.getContentPane().add(beginGame);
		
		JLabel label = new JLabel("Players");
		label.setBounds(700, 72, 69, 20);
		frame.getContentPane().add(label);
		
		ownedTerritories = new JList<String>();
		ownedTerritories.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ownedTerritories.setBorder(new LineBorder(Color.BLUE));
		ownedTerritories.setBounds(817, 96, 211, 257);
		frame.getContentPane().add(ownedTerritories);
		
		JLabel lblOwnedTerritories = new JLabel("Owned Territories");
		lblOwnedTerritories.setBounds(844, 72, 164, 20);
		frame.getContentPane().add(lblOwnedTerritories);
		
		InstructionsView instructionsPane = new InstructionsView(instructionsMsg);
		instructions.addObserver(instructionsPane);
		
		JScrollPane instructionsScrollPane = new JScrollPane(instructionsPane);
		instructionsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		instructionsScrollPane.setBounds(1390, 16, 432, 736);
		frame.getContentPane().add(instructionsScrollPane);
		
		btnAddArmy = new JButton("Add Army");
		btnAddArmy.setBounds(1051, 201, 115, 29);
		frame.getContentPane().add(btnAddArmy);
		
		btnReinforcement = new JButton("Reinforce");
		btnReinforcement.setBounds(1051, 201, 115, 29);
		frame.getContentPane().add(btnReinforcement);
		btnReinforcement.setVisible(false);
		btnAddArmy.setVisible(false);
		
		playerJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				JList list = (JList) listSelectionEvent.getSource();
				int selections[] = list.getSelectedIndices();
				try {
					ArrayList<Territory> territories = playerList.get(selections[0]).getOwnedTerritories();
					String[] territoryNames = new String[territories.size()];
					for(int i=0;i<territories.size();i++) {
						territoryNames[i] = territories.get(i).getName() + "(" + territories.get(i).getNumberOfArmies() + ")";
					}
					ownedTerritories.setListData(territoryNames);
				}catch (Exception e) {
					// TODO: handle exception
				}
								
			}
		});
		
	}

	/**
	 * @param number
	 */
	protected void showPlayerSetupPanel(int number) {
		
		GameController controller = new GameController();
		int numArmies; // Number of armies each player get
		numArmies = controller.getPlayersArmies(number);
		
		JFrame playerFrame = new JFrame();
		playerFrame.setBounds(100, 100, 450, 439);
		playerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		playerFrame.getContentPane().setLayout(null);
		
		JLabel lblEnterPlayerDetails = new JLabel("Enter Player Details");
		lblEnterPlayerDetails.setBounds(15, 16, 175, 20);
		playerFrame.getContentPane().add(lblEnterPlayerDetails);
		
		player1 = new JTextField();
		player1.setText("Player1");
		player1.setBounds(15, 52, 152, 26);
		playerFrame.getContentPane().add(player1);
		player1.setColumns(10);
		
		player2 = new JTextField();
		player2.setText("Player2");
		player2.setBounds(15, 94, 152, 26);
		playerFrame.getContentPane().add(player2);
		player2.setColumns(10);
		
		JComboBox playerType1 = new JComboBox();
		playerType1.setModel(new DefaultComboBoxModel(new String[] {"Human", "Computer"}));
		playerType1.setBounds(182, 52, 117, 26);
		playerFrame.getContentPane().add(playerType1);
		
		JComboBox playerType2 = new JComboBox();
		playerType2.setModel(new DefaultComboBoxModel(new String[] {"Human", "Computer"}));
		playerType2.setBounds(182, 94, 117, 26);
		playerFrame.getContentPane().add(playerType2);
		
		if (number > 2) {
			player3 = new JTextField();
			player3.setText("Player3");
			player3.setColumns(10);
			player3.setBounds(15, 136, 152, 26);
			playerFrame.getContentPane().add(player3);

			playerType3 = new JComboBox();
			playerType3.setModel(new DefaultComboBoxModel(new String[] {"Human", "Computer"}));
			playerType3.setBounds(182, 136, 117, 26);
			playerFrame.getContentPane().add(playerType3);
		}
		
		if (number > 3) {
			player4 = new JTextField();
			player4.setText("Player4");
			player4.setColumns(10);
			player4.setBounds(15, 178, 152, 26);
			playerFrame.getContentPane().add(player4);	
			
			playerType4 = new JComboBox();
			playerType4.setModel(new DefaultComboBoxModel(new String[] {"Human", "Computer"}));
			playerType4.setBounds(182, 178, 117, 26);
			playerFrame.getContentPane().add(playerType4);
		}
		
		if(number > 4) {
			player5 = new JTextField();
			player5.setText("Player5");
			player5.setColumns(10);
			player5.setBounds(15, 220, 152, 26);
			playerFrame.getContentPane().add(player5);
			
			playerType5 = new JComboBox();
			playerType5.setModel(new DefaultComboBoxModel(new String[] {"Human", "Computer"}));
			playerType5.setBounds(182, 220, 117, 26);
			playerFrame.getContentPane().add(playerType5);
		}
		
		if(number > 5) {
			player6 = new JTextField();
			player6.setText("Player6");
			player6.setColumns(10);
			player6.setBounds(15, 262, 152, 26);
			playerFrame.getContentPane().add(player6);
			
			playerType6 = new JComboBox();
			playerType6.setModel(new DefaultComboBoxModel(new String[] {"Human", "Computer"}));
			playerType6.setBounds(182, 262, 117, 26);
			playerFrame.getContentPane().add(playerType6);
		}	
		playerFrame.setVisible(true);
		
		playerButton = new JButton("Enter");
		playerButton.setBounds(298, 304, 81, 34);
		playerFrame.getContentPane().add(playerButton);
		
		playerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name1 = player1.getText();
				boolean isComputer1 = isComputer(playerType1.getSelectedIndex());
				Player firstPlayer = new Player(name1, isComputer1, numArmies);
				String name2 = player2.getText();
				boolean isComputer2 = isComputer(playerType2.getSelectedIndex());
				Player secondPlayer = new Player(name2, isComputer2, numArmies);
				playerList.add(firstPlayer);
				playerList.add(secondPlayer);
				if(number > 2) {
					String name = player3.getText();
					boolean isComputer = isComputer(playerType3.getSelectedIndex());
					Player newPlayer = new Player(name, isComputer, numArmies);
					playerList.add(newPlayer);
				}
				if(number > 3) {
					String name = player4.getText();
					boolean isComputer = isComputer(playerType4.getSelectedIndex());
					Player newPlayer = new Player(name, isComputer, numArmies);
					playerList.add(newPlayer);
				}
				if(number > 4) {
					String name = player5.getText();
					boolean isComputer = isComputer(playerType5.getSelectedIndex());
					Player newPlayer = new Player(name, isComputer, numArmies);
					playerList.add(newPlayer);
				}
				if(number > 5) {
					String name = player5.getText();
					boolean isComputer = isComputer(playerType5.getSelectedIndex());
					Player newPlayer = new Player(name, isComputer, numArmies);
					playerList.add(newPlayer);
				}
				
				// Assigning territories to players
				GameController controller = new GameController();
				playerList = controller.territoriesToPlayers(playerList, territories);
				territories = controller.playersToTerritories(playerList, territories);
				
				playerFrame.setVisible(false);
				String[] playerNames = new String[numberOfPlayers];
				for(int i=0;i<playerList.size();i++) {
					playerNames[i] = playerList.get(i).getName() + "(" + playerList.get(i).getNumberOfArmies() + ")";
				}
				playerJList.setListData(playerNames);
				//Set new instruction
				instructions.setInstructions("Players and countries are assigned.\r\nPlease click Begin Conquest to Start the game");
				//Display begin conquest button
				beginGame.setVisible(true);
			}

			private boolean isComputer(int selectedIndex) {
				if(selectedIndex == 1)
					return false;
				else
					return true;
			}
			
		});
		
		
		// Begin Game - Start 
		beginGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameController controller = new GameController();
				//Assign one armies to each countries
				String returnMessage = controller.assignOneArmyToEachCountry(playerList, territories);
				updatePlayerJList();
				instructions.setInstructions(returnMessage);
				beginGame.setVisible(false);
				btnAddArmy.setVisible(true);
				instructions.setInstructions("Select a player and a territory and add armies to it, one bye one");
			}
		});
		/**
		 * Add Armies to territories
		 */
		btnAddArmy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameController controller = new GameController();
				boolean isSelected = isSelected();
				if(isSelected) {
					boolean isAdded = addArmyToTerritory(playerJList.getSelectedIndex(), ownedTerritories.getSelectedIndex());
					int selectedPlayerIndex = playerJList.getSelectedIndex();
					int selectedTerIndex = ownedTerritories.getSelectedIndex();
					if(isAdded) {
						String instrMessage = "Player, " + playerList.get(selectedPlayerIndex).getName() + "("+ playerList.get(selectedPlayerIndex).getNumberOfArmies() +")" + 
											  " Added an army to territory, " + playerList.get(selectedPlayerIndex).getOwnedTerritories().get(selectedTerIndex).getName() +
											  "("+ playerList.get(selectedPlayerIndex).getOwnedTerritories().get(selectedTerIndex).getNumberOfArmies() +")";
						instructions.setInstructions(instrMessage);
					}else {
						instructions.setInstructions("Player, " + playerJList.getSelectedValue() + " Invalid Move");
					}
				}
				boolean isAddingCompleted = controller.isAddingCompleted(playerList);
				if(isAddingCompleted) {
					instructions.setInstructions("Adding is completed. Click on Reinforcement button");
					btnAddArmy.setVisible(false);
					btnReinforcement.setVisible(true);
				}else {
					btnAddArmy.setVisible(true);
				}
				updateOwnedTerritories(playerJList.getSelectedIndex());
				updatePlayerJList();
			}
		});
	}
	/**
	 * 
	 * @param playerIndex
	 * @param territoryIndex
	 * @return
	 */
	protected boolean addArmyToTerritory(int playerIndex, int territoryIndex) {
		boolean isAdded = false;
		GameController controller = new GameController();		
		isAdded = controller.addArmyToTerritory(playerList.get(playerIndex), playerList.get(playerIndex).getOwnedTerritories().get(territoryIndex));
		return isAdded;
	}
	/**
	 * 
	 * @return
	 */
	protected boolean isSelected() {
		boolean isSelected = false;
		if(playerJList.getSelectedIndex() >= 0) {
			if(ownedTerritories.getSelectedIndex() >= 0) {
				isSelected = true;
			}else {
				JOptionPane.showMessageDialog(frame, "Select a territory as well");
			}
		}else {
			JOptionPane.showMessageDialog(frame, "Select a Player");
		}
		return isSelected;
	}
	/**
	 * 
	 * @param playerIndex
	 */
	protected void updateOwnedTerritories(int playerIndex) {
		try {
			ArrayList<Territory> territories = playerList.get(playerIndex).getOwnedTerritories();
			String[] territoryNames = new String[territories.size()];
			for(int i=0;i<territories.size();i++) {
				territoryNames[i] = territories.get(i).getName() + "(" + territories.get(i).getNumberOfArmies() + ")";
			}
			ownedTerritories.setListData(territoryNames);
		}catch(Exception e) {
			
		}
	}
	/**
	 * 
	 */
	protected void updatePlayerJList() {
		String[] playerNames = new String[numberOfPlayers];
		for(int i=0;i<playerList.size();i++) {
			playerNames[i] = playerList.get(i).getName() + "(" + playerList.get(i).getNumberOfArmies() + ")";
		}
		playerJList.setListData(playerNames);
	}
}
