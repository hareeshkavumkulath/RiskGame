package com.risk.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.risk.controller.GameController;
import com.risk.model.AttackStatus;
import com.risk.model.Card;
import com.risk.model.Continent;
import com.risk.model.Game;
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

/**
 * This class implements Game Window pages functionalities and design user interface.
 * 
 * @author Hareesh Kavumkulath
 * @version 1.1
 */
public class GameWindow {

	@SuppressWarnings("javadoc")
	private JFrame frame;
	@SuppressWarnings("javadoc")
	private Game game;
	@SuppressWarnings("javadoc")
	private Map map;
	@SuppressWarnings("javadoc")
	private int numberOfPlayers;
	@SuppressWarnings("javadoc")
	private JButton playerButton; 
	@SuppressWarnings("javadoc")
	private JButton beginGame;
	
	@SuppressWarnings("javadoc")
	private JTextField player1;
	@SuppressWarnings("javadoc")
	private JTextField player2;
	@SuppressWarnings("javadoc")
	private JTextField player3 = null;
	@SuppressWarnings("javadoc")
	private JTextField player4 = null;
	@SuppressWarnings("javadoc")
	private JTextField player5;
	@SuppressWarnings("javadoc")
	private JTextField player6;
	@SuppressWarnings("javadoc")
	private JComboBox<String> playerType3 = null;
	@SuppressWarnings("javadoc")
	private JComboBox<String> playerType4 = null;
	@SuppressWarnings("javadoc")
	private JComboBox<String> playerType5;
	@SuppressWarnings("javadoc")
	private JComboBox<String> playerType6;
	@SuppressWarnings("javadoc")
	private JList<String> territoriesJList;
	@SuppressWarnings("javadoc")
	private PlayerListView playerJList;
	@SuppressWarnings("javadoc")
	private JList<String> ownedTerritories;
	private ArrayList<Card> cards;
	int currentIndex = 0;
	boolean won = false;
	
	@SuppressWarnings("javadoc")
	public ArrayList<Player> playerList = new ArrayList<Player>();
	@SuppressWarnings("javadoc")
	public ArrayList<Continent> continents;
	@SuppressWarnings("javadoc")
	public ArrayList<Territory> territories;
	@SuppressWarnings("javadoc")
	private String instructionsMsg = "Let's Start Conquering the world.\r\nPlease select the number of Players";
	@SuppressWarnings("javadoc")
	private GameInstructions instructions = new GameInstructions(instructionsMsg);
	@SuppressWarnings("javadoc")
	private AbstractButton btnAddArmy;
	@SuppressWarnings("javadoc")
	private AbstractButton btnAddReinforceArmy;
	@SuppressWarnings("javadoc")
	private JButton btnReinforcement;
	@SuppressWarnings("javadoc")
	private JButton btnFortify;
	@SuppressWarnings("javadoc")
	private boolean reinforceStatus = false;
	@SuppressWarnings("javadoc")
	private JButton btnEndFortify;
	@SuppressWarnings("javadoc")
	private JTextField numArmiesText;
	@SuppressWarnings("javadoc")
	private InstructionsView instructionsPane;
	@SuppressWarnings("javadoc")
	private JPanel attackPanel;
	@SuppressWarnings("javadoc")
	private JTextField attackingArmy;
	@SuppressWarnings("javadoc")
	private JTextField attackedArmy;
	@SuppressWarnings("javadoc")
	Player currentPlayer = null;
	@SuppressWarnings("javadoc")
	private JLabel lblPlayer;
	@SuppressWarnings("javadoc")
	private JList<String> attackingTerr;
	@SuppressWarnings("javadoc")
	private JList<String> attackedTerr;
	@SuppressWarnings("javadoc")
	private JButton btnAttack;
	@SuppressWarnings("javadoc")
	private JButton btnEndAttack;
	
	/**
	 * Launch the application.
	 * 
	 * @param map Map input selected map from the SelectMapWindow
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
	 * 
	 * @param map Map pass map model to GameWindow.java
	 */
	public GameWindow(Map map) {
		this.map = map;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		game = new Game(map);
		game.setMap(map);
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.GREEN);
		frame.setBounds(100, 100, 1926, 909);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel continentLabel = new JLabel("Continents");
		continentLabel.setBounds(38, 16, 115, 20);
		frame.getContentPane().add(continentLabel);
		
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
		//frame.getContentPane().add(continentJList);
		
		//Adding scroll pane to continents List
		JScrollPane continentScrollPane = new JScrollPane(continentJList);
		continentScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		continentScrollPane.setBounds(15, 40, 173, 313);
		frame.getContentPane().add(continentScrollPane);
		
		territoriesJList = new JList<String>();
		territoriesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		territoriesJList.setBorder(new LineBorder(Color.BLUE));
		territoriesJList.setBounds(196, 41, 211, 313);
		frame.getContentPane().add(territoriesJList);
		
		
		JList<String> adjTerritoriesJList = new JList<String>();
		adjTerritoriesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		adjTerritoriesJList.setBorder(new LineBorder(Color.BLUE));
		adjTerritoriesJList.setBounds(422, 41, 211, 313);
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
				int selections[] = continentJList.getSelectedIndices();
				ArrayList<Territory> territories = map.getContinents().get(selections[0]).getTerritories();
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
					ArrayList<Territory> territories = map.getTerritories();
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
		ownedTerritories.setBounds(812, 108, 211, 257);
		frame.getContentPane().add(ownedTerritories);
		
		JLabel lblOwnedTerritories = new JLabel("Owned Territories");
		lblOwnedTerritories.setBounds(844, 72, 164, 20);
		frame.getContentPane().add(lblOwnedTerritories);
		
		instructionsPane = new InstructionsView(instructionsMsg);
		instructions.addObserver(instructionsPane);
		
		JScrollPane instructionsScrollPane = new JScrollPane(instructionsPane);
		instructionsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		instructionsScrollPane.setBounds(1390, 16, 432, 337);
		frame.getContentPane().add(instructionsScrollPane);
		
		numArmiesText = new JTextField();
		numArmiesText.setEditable(true);
		numArmiesText.setText("1");
		numArmiesText.setBounds(1078, 202, 80, 26);
		frame.getContentPane().add(numArmiesText);
		numArmiesText.setColumns(10);
		numArmiesText.setVisible(false);
		
		btnAddArmy = new JButton("Add Army");
		btnAddArmy.setBounds(1194, 201, 115, 29);
		frame.getContentPane().add(btnAddArmy);
		btnAddArmy.setVisible(false);
		
		btnAddReinforceArmy = new JButton("Add Reinforce Army");
		btnAddReinforceArmy.setBounds(1194, 201, 183, 29);
		frame.getContentPane().add(btnAddReinforceArmy);
		btnAddReinforceArmy.setVisible(false);
		
		btnReinforcement = new JButton("Reinforce");
		btnReinforcement.setBounds(1051, 201, 115, 29);
		frame.getContentPane().add(btnReinforcement);
		btnReinforcement.setVisible(false);
		
		btnFortify = new JButton("Fortify");
		btnFortify.setBounds(1051, 201, 115, 29);
		frame.getContentPane().add(btnFortify);
		
		btnEndFortify = new JButton("End Fortify");
		btnEndFortify.setBounds(1194, 201, 115, 29);
		frame.getContentPane().add(btnEndFortify);
		
		JScrollPane scrollPane = new JScrollPane(territoriesJList);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(196, 40, 211, 313);
		frame.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane(adjTerritoriesJList);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(422, 40, 211, 313);
		frame.getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane(ownedTerritories);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(809, 96, 211, 257);
		frame.getContentPane().add(scrollPane_2);
		
		attackPanel = new JPanel();
		attackPanel.setBounds(635, 369, 824, 468);
		frame.getContentPane().add(attackPanel);
		attackPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Attack View");
		lblNewLabel_1.setBounds(15, 16, 138, 20);
		attackPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Attacking Territory");
		lblNewLabel.setBounds(253, 52, 151, 20);
		attackPanel.add(lblNewLabel);
		
		JLabel labelName = new JLabel("Player Name");
		labelName.setBounds(15, 52, 151, 20);
		attackPanel.add(labelName);
		
		JLabel lblAttackedCountry = new JLabel("Attacked Territory");
		lblAttackedCountry.setBounds(560, 52, 151, 20);
		attackPanel.add(lblAttackedCountry);
		
		lblPlayer = new JLabel("");
		lblPlayer.setBounds(15, 88, 69, 20);
		attackPanel.add(lblPlayer);
		
		attackingTerr = new JList<String>();
		attackingTerr.setBounds(1, 1, 219, 301);
		attackPanel.add(attackingTerr);
		
		JScrollPane attackingTerrPane = new JScrollPane(attackingTerr);
		attackingTerrPane.setBounds(247, 88, 247, 271);
		attackingTerrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		attackPanel.add(attackingTerrPane);
		
		attackedTerr = new JList<String>();
		attackedTerr.setBounds(1, 1, 219, 301);
		attackPanel.add(attackedTerr);
		
		JScrollPane attackedTerrPane = new JScrollPane(attackedTerr);
		attackedTerrPane.setBounds(563, 88, 247, 267);
		attackedTerrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		attackPanel.add(attackedTerrPane);
		
		attackingArmy = new JTextField();
		attackingArmy.setBounds(247, 371, 247, 26);
		attackPanel.add(attackingArmy);
		attackingArmy.setColumns(10);
		
		attackedArmy = new JTextField();
		attackedArmy.setBounds(563, 371, 247, 26);
		attackedArmy.setColumns(10);
		attackPanel.add(attackedArmy);
		
		JLabel lblNumberOfDices = new JLabel("Number of Dices");
		lblNumberOfDices.setBounds(15, 374, 167, 20);
		attackPanel.add(lblNumberOfDices);
		
		btnAttack = new JButton("Attack");
		btnAttack.setBounds(502, 428, 115, 29);
		attackPanel.add(btnAttack);
		
		btnEndAttack = new JButton("End Attack");
		btnEndAttack.setBounds(686, 428, 123, 29);
		attackPanel.add(btnEndAttack);
		attackPanel.setVisible(false);
		
		btnFortify.setVisible(false);
		btnEndFortify.setVisible(false);
		
		playerJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				int selections[] = playerJList.getSelectedIndices();
				try {
					ArrayList<Territory> territories = playerList.get(selections[0]).getOwnedTerritories();
					String[] territoryNames = new String[territories.size()];
					for(int i=0;i<territories.size();i++) {
						territoryNames[i] = territories.get(i).getName() + "(" + territories.get(i).getNumberOfArmies() + ")";
					}
					ownedTerritories.setListData(territoryNames);
				}catch (Exception e) {
					System.out.println("Exception in player selection from list:"+e.toString());
				}
								
			}
		});
		
	}

	/**
	 * It displays the setup player panel based on the number of players
	 * 
	 * @param number int number of players
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
		
		JComboBox<String> playerType1 = new JComboBox<String>();
		playerType1.setModel(new DefaultComboBoxModel<String>(new String[] {"Human", "Computer"}));
		playerType1.setBounds(182, 52, 117, 26);
		playerFrame.getContentPane().add(playerType1);
		
		JComboBox<String> playerType2 = new JComboBox<String>();
		playerType2.setModel(new DefaultComboBoxModel<String>(new String[] {"Human", "Computer"}));
		playerType2.setBounds(182, 94, 117, 26);
		playerFrame.getContentPane().add(playerType2);
		
		if (number > 2) {
			player3 = new JTextField();
			player3.setText("Player3");
			player3.setColumns(10);
			player3.setBounds(15, 136, 152, 26);
			playerFrame.getContentPane().add(player3);

			playerType3 = new JComboBox<String>();
			playerType3.setModel(new DefaultComboBoxModel<String>(new String[] {"Human", "Computer"}));
			playerType3.setBounds(182, 136, 117, 26);
			playerFrame.getContentPane().add(playerType3);
		}
		
		if (number > 3) {
			player4 = new JTextField();
			player4.setText("Player4");
			player4.setColumns(10);
			player4.setBounds(15, 178, 152, 26);
			playerFrame.getContentPane().add(player4);	
			
			playerType4 = new JComboBox<String>();
			playerType4.setModel(new DefaultComboBoxModel<String>(new String[] {"Human", "Computer"}));
			playerType4.setBounds(182, 178, 117, 26);
			playerFrame.getContentPane().add(playerType4);
		}
		
		if(number > 4) {
			player5 = new JTextField();
			player5.setText("Player5");
			player5.setColumns(10);
			player5.setBounds(15, 220, 152, 26);
			playerFrame.getContentPane().add(player5);
			
			playerType5 = new JComboBox<String>();
			playerType5.setModel(new DefaultComboBoxModel<String>(new String[] {"Human", "Computer"}));
			playerType5.setBounds(182, 220, 117, 26);
			playerFrame.getContentPane().add(playerType5);
		}
		
		if(number > 5) {
			player6 = new JTextField();
			player6.setText("Player6");
			player6.setColumns(10);
			player6.setBounds(15, 262, 152, 26);
			playerFrame.getContentPane().add(player6);
			
			playerType6 = new JComboBox<String>();
			playerType6.setModel(new DefaultComboBoxModel<String>(new String[] {"Human", "Computer"}));
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
				Player firstPlayer = new Player(name1, isComputer1, numArmies, "REINFORCEMENT");
				String name2 = player2.getText();
				boolean isComputer2 = isComputer(playerType2.getSelectedIndex());
				Player secondPlayer = new Player(name2, isComputer2, numArmies, "REINFORCEMENT");
				playerList.add(firstPlayer);
				playerList.add(secondPlayer);
				if(number > 2) {
					String name = player3.getText();
					boolean isComputer = isComputer(playerType3.getSelectedIndex());
					Player newPlayer = new Player(name, isComputer, numArmies, "REINFORCEMENT");
					playerList.add(newPlayer);
				}
				if(number > 3) {
					String name = player4.getText();
					boolean isComputer = isComputer(playerType4.getSelectedIndex());
					Player newPlayer = new Player(name, isComputer, numArmies, "REINFORCEMENT");
					playerList.add(newPlayer);
				}
				if(number > 4) {
					String name = player5.getText();
					boolean isComputer = isComputer(playerType5.getSelectedIndex());
					Player newPlayer = new Player(name, isComputer, numArmies, "REINFORCEMENT");
					playerList.add(newPlayer);
				}
				if(number > 5) {
					String name = player5.getText();
					boolean isComputer = isComputer(playerType5.getSelectedIndex());
					Player newPlayer = new Player(name, isComputer, numArmies, "REINFORCEMENT");
					playerList.add(newPlayer);
				}
				
				// Assigning territories to players
				GameController controller = new GameController();
				playerList = controller.territoriesToPlayers(playerList, territories);
				territories = controller.playersToTerritories(playerList, territories);
				
				game.setPlayers(playerList);
				
				playerFrame.setVisible(false);
				String[] playerNames = new String[numberOfPlayers];
				for(int i=0;i<playerList.size();i++) {
					playerNames[i] = playerList.get(i).getName() + "(" + playerList.get(i).getNumberOfArmies() + ")";
				}
				playerJList.setListData(playerNames);
				// Set territories and Adjacent territories with Player details
				
				//Set new instruction
				instructions.setInstructions("Players and territories are assigned.\r\nPlease click Begin Conquest to Start the game");
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
				instructions.setInstructions("");
				instructions.setInstructions("Add Armies Phase");
				instructions.setInstructions("****************");
				instructions.setInstructions("Select a player and a territory and add armies to it, one bye one");
				GameController controller = new GameController();
				//Assign one armies to each territories
				String returnMessage = controller.assignOneArmyToEachTerritory(playerList, territories);
				cards = controller.loadCards(territories.size(), playerList.size());
				game.setCards(cards);
				updatePlayerJList();
				instructions.setInstructions(returnMessage);
				beginGame.setVisible(false);
				btnAddArmy.setVisible(true);
				numArmiesText.setVisible(true);
				int nextIndex = territories.size() % numberOfPlayers;
				playerJList.setSelectedIndex(nextIndex);
				
				//Display Player World Domination
				WorldDominationView worldDomViewPanel = new WorldDominationView(game);
				worldDomViewPanel.setBounds(15, 381, 618, 371);
				frame.getContentPane().add(worldDomViewPanel);
				
				game.addObserver(worldDomViewPanel);
				
				for(int i=0;i<playerList.size();i++) {
					playerList.get(i).setStrategy("REINFORCEMENT");
				}
				
				currentPlayer = playerList.get(0);
				
			}
		});
		
		// Add Armies to territories
		btnAddArmy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isAdded = false;
				GameController controller = new GameController();
				boolean isSelected = isSelected();
				int selectedPlayerIndex = playerJList.getSelectedIndex();
				if(isSelected) {
					int numArmies = Integer.parseInt(numArmiesText.getText());
					int selectedTerIndex = ownedTerritories.getSelectedIndex();
					if(playerList.get(selectedPlayerIndex).getNumberOfArmies() >= numArmies) {
						isAdded = addArmyToTerritory(playerJList.getSelectedIndex(), ownedTerritories.getSelectedIndex(),numArmies);
						if(isAdded) {
							String instrMessage = "Player, " + playerList.get(selectedPlayerIndex).getName() + "("+ playerList.get(selectedPlayerIndex).getNumberOfArmies() +")" + 
												  " Added an army to territory, " + playerList.get(selectedPlayerIndex).getOwnedTerritories().get(selectedTerIndex).getName() +
												  "("+ playerList.get(selectedPlayerIndex).getOwnedTerritories().get(selectedTerIndex).getNumberOfArmies() +")";
							instructions.setInstructions(instrMessage);
							game.update();
							numArmiesText.setText("1");
						}else {
							instructions.setInstructions("Player, " + playerJList.getSelectedValue() + " Invalid Move");
						}
					}else {
						instructions.setInstructions("Player, " + playerJList.getSelectedValue() + " doesn't have enough armies");
						numArmiesText.setText("1");
					}
				}
				
				updateOwnedTerritories(playerJList.getSelectedIndex());
				updatePlayerJList();
				int nextIndex;
				if(isAdded) {
					if(selectedPlayerIndex == (playerList.size() -1)) {
						nextIndex = 0;
					}else {
						nextIndex = selectedPlayerIndex+1;
					}
				}else {
					nextIndex = selectedPlayerIndex;
				}
				playerJList.setSelectedIndex(nextIndex);
				
				boolean isAddingCompleted = controller.isAddingCompleted(playerList);
				if(isAddingCompleted) {
					
					onGame();
					
				}else {
					btnAddArmy.setVisible(true);
					numArmiesText.setVisible(true);
				}
			}
		});
		
		// Add Armies to territories
		btnAddReinforceArmy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isAdded = false;
				GameController controller = new GameController();
				boolean isSelected = isSelected();
				int selectedPlayerIndex = playerJList.getSelectedIndex();
				if(isSelected) {
					int numArmies = Integer.parseInt(numArmiesText.getText());
					int selectedTerIndex = ownedTerritories.getSelectedIndex();
					if(playerList.get(selectedPlayerIndex).getNumberOfArmies() >= numArmies) {
						isAdded = addArmyToTerritory(playerJList.getSelectedIndex(), ownedTerritories.getSelectedIndex(),numArmies);
						if(isAdded) {
							String instrMessage = "Player, " + playerList.get(selectedPlayerIndex).getName() + "("+ playerList.get(selectedPlayerIndex).getNumberOfArmies() +")" + 
												  " Added an army to territory, " + playerList.get(selectedPlayerIndex).getOwnedTerritories().get(selectedTerIndex).getName() +
												  "("+ playerList.get(selectedPlayerIndex).getOwnedTerritories().get(selectedTerIndex).getNumberOfArmies() +")";
							instructions.setInstructions(instrMessage);
							game.update();
							numArmiesText.setText("1");
						}else {
							instructions.setInstructions("Player, " + playerJList.getSelectedValue() + " Invalid Move");
						}
					}else {
						instructions.setInstructions("Player, " + playerJList.getSelectedValue() + " doesn't have enough armies");
						numArmiesText.setText("1");
					}
				}
				
				updateOwnedTerritories(playerJList.getSelectedIndex());
				updatePlayerJList();
				playerJList.setSelectedIndex(selectedPlayerIndex);
				
				boolean isAddingCompleted = controller.isAddingCompleted(playerList);
				currentPlayer.setStrategy("ATTACK");
				if(isAddingCompleted) {
					
					onGame();
					
				}else {
					btnAddArmy.setVisible(true);
					numArmiesText.setVisible(true);
				}
			}
		});
		
		btnEndFortify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnFortify.setVisible(false);
				btnEndFortify.setVisible(false);
				currentPlayer.setStrategy("REINFORCEMENT");
				currentPlayer = nextPlayer();
				int nextIndex;
				int selectedPlayerIndex = playerJList.getSelectedIndex();
				if(selectedPlayerIndex == (playerList.size() -1)) {
					nextIndex = 0;					
				}else {
					nextIndex = selectedPlayerIndex+1;
				}
				playerJList.setSelectedIndex(nextIndex);
				onGame();
			}
		});
		
		btnReinforcement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentPlayer = currentPlayer.reinforce(game);
				GameController controller = new GameController();
				playerList = controller.getOwnedContinents(continents, playerList);
				btnAddReinforceArmy.setVisible(true);
				numArmiesText.setVisible(true);
				updateOwnedTerritories(playerJList.getSelectedIndex());
				updatePlayerJList();
				reinforceStatus = true;
				btnReinforcement.setVisible(false);
				playerJList.setSelectedIndex(playerList.indexOf(currentPlayer));
			}
		});
		
		btnFortify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameController controller = new GameController(); 
				boolean isSelected = isSelectedPlayer();
				if(isSelected) {
					int selectedPlayerIndex = playerJList.getSelectedIndex();
					Player player = playerList.get(selectedPlayerIndex);
					if(player.fortificationStatus) {
						instructions.setInstructions("Player, " + player.getName() + ", sorry only one fortification is permitted.");
						JOptionPane.showMessageDialog(frame, "Player, " + player.getName() + ", sorry only one fortification is permitted.");
					}else {
						boolean isValidFortify = controller.isValidFortify(player);
						if(isValidFortify) {
							try {
								FortifyWindow frame = new FortifyWindow(player);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else {
							instructions.setInstructions("Invalid Fortify Option for Player, " + player.getName());
						}
					}
				}else {
					instructions.setInstructions("Please select a Player");
				}
			}
		});
		
	}

	/**
	 * @return
	 */
	protected Player nextPlayer() {
		int index = playerList.indexOf(currentPlayer);
		if(index == (playerList.size() - 1)) {
			index = 0;
		}else {
			index++;
		}
		return playerList.get(index);
	}

	/**
	 * Add armies to the territory
	 * 
	 * @param playerIndex int index of player selected
	 * @param territoryIndex int index of territory selected
	 * @param numArmies int the number of armies selected
	 * @return boolean true if added army to the territory else false
	 */
	protected boolean addArmyToTerritory(int playerIndex, int territoryIndex, int numArmies) {
		boolean isAdded = false;
		GameController controller = new GameController();		
		isAdded = controller.addArmyToTerritory(playerList.get(playerIndex), playerList.get(playerIndex).getOwnedTerritories().get(territoryIndex), numArmies);
		return isAdded;
	}

	/**
	 * Returns true if selected both territory and player
	 * 
	 * @return boolean if both player and territory selected then return true, else false
	 */
	protected boolean isSelected() {
		boolean isSelected = false;
		if(playerJList.getSelectedIndex() >= 0) {
			if(ownedTerritories.getSelectedIndex() >= 0) {
				isSelected = isEnteredNumber();
			}else {
				JOptionPane.showMessageDialog(frame, "Select a territory as well");
			}
		}else {
			JOptionPane.showMessageDialog(frame, "Select a Player");
		}
		return isSelected;
	}

	/**
	 * Updates the JList of players
	 * 
	 */
	protected void updatePlayerJList() {
		String[] playerNames = new String[numberOfPlayers];
		for(int i=0;i<playerList.size();i++) {
			playerNames[i] = playerList.get(i).getName() + "(" + playerList.get(i).getNumberOfArmies() + ")";
		}
		playerJList.setListData(playerNames);
	}	
	
	/**
	 * Updates the list of owned territories with new number of armies
	 * 
	 * @param playerIndex int index of the player selected
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
	 * Checks whether you selected a player or not
	 * 
	 * @return boolean true if selected, else false
	 */
	protected boolean isSelectedPlayer() {
		boolean isSelected = false;
		if(playerJList.getSelectedIndex() >= 0) {
			isSelected = true;
		}else {
			JOptionPane.showMessageDialog(frame, "Select a Player");
		}
		return isSelected;
	}
	
	/**
	 * Checks the number of armies text field is null or not
	 * 
	 * @return boolean true if it is not null(number) false if it is empty or contains any string
	 */
	private boolean isEnteredNumber() {
		try {
			if(Integer.parseInt(numArmiesText.getText()) > 0) {
				return true;
			}else {
				JOptionPane.showMessageDialog(frame, "Enter a number greater than 0");
				numArmiesText.setText("1");
				return false;
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(frame, "Enter a number");
			numArmiesText.setText("1");
			return false;
		}
	}
	
	/**
	 * Update strategy of the player
	 * 
	 * @param strategy strategy string
	 */
	protected void updatePlayerStrategy(String strategy) {
		for(int i=0;i<playerList.size();i++) {
			playerList.get(i).setStrategy(strategy);
		}
	}
	
	/**
	 * 
	 * Display current player's attack panel
	 * 
	 */
	protected void displayAttackPanel() {
		playerJList.setEnabled(true);
		hideAddButtons();
		won = false;
		currentIndex = playerList.indexOf(currentPlayer);
		if(currentPlayer != null) {
			System.out.println("Current Player:" + currentPlayer.getName());
			attackPanel.setVisible(true);
			instructions.setInstructions("It's " + currentPlayer.getName() + "'s turn!!! Select territories and click Attack Button");
			ArrayList<Territory> attackingTerritories;
			
			lblPlayer.setText(currentPlayer.getName());
			
			attackingTerritories = currentPlayer.getOwnedTerritories();
			String[] attackingTerrNames = new String[attackingTerritories.size()];
			for(int i = 0;i<attackingTerritories.size();i++) {
				attackingTerrNames[i] = attackingTerritories.get(i).getName() + "(" + attackingTerritories.get(i).getNumberOfArmies() + ")";
			}
			attackingTerr.setListData(attackingTerrNames);
			
			attackingTerr.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					try {
						int selectedIndex = attackingTerr.getSelectedIndex();
						ArrayList<Territory> attackedTerritories = currentPlayer.getOwnedTerritories().get(selectedIndex).getAdjacentTerritories();
						String[] attackedTerrNames = new String[attackedTerritories.size()];
						for(int i = 0;i<attackedTerrNames.length;i++) {
							Territory tempTerritory = attackedTerritories.get(i);
							attackedTerrNames[i] = tempTerritory.getName() + "(" + tempTerritory.getRuler().getName() + "-" + tempTerritory.getNumberOfArmies() + ")";
						}
						attackedTerr.setListData(attackedTerrNames);
					}catch(Exception ex) {
						System.out.println("Exception in attacking territory JList" + ex.toString());
					}
				}
			});
			
			btnAttack.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					GameController controller = new GameController();
					int numAttackerArmies = 0;
					int numOpponentArmies = 0;
					int selectedIndex1 = attackingTerr.getSelectedIndex();
					int selectedIndex2 = attackedTerr.getSelectedIndex();
					Territory attackerTerr = currentPlayer.getOwnedTerritories().get(selectedIndex1);
					Territory opponentTerr = attackerTerr.getAdjacentTerritories().get(selectedIndex2);
					Player attacker = attackerTerr.getRuler();
					boolean canAttack = false;
					try {
						numAttackerArmies = Integer.parseInt(attackingArmy.getText());
						numOpponentArmies = Integer.parseInt(attackedArmy.getText());
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Please enter numbers", "Alert", JOptionPane.ERROR_MESSAGE);
					}
					if(numAttackerArmies > 3 || numAttackerArmies < 1) {
						JOptionPane.showMessageDialog(null, "Please enter either 1,2 or 3 for Attacking Armies", "Alert", JOptionPane.ERROR_MESSAGE);
					}else {
						if(numAttackerArmies == 3) {
							if(numOpponentArmies < 1 || numOpponentArmies > 2) {
								JOptionPane.showMessageDialog(null, "Please enter either 2 or 1 for Attacked Armies", "Alert", JOptionPane.ERROR_MESSAGE);
							}else {
								canAttack = true;
							}
						}else {
							if(numOpponentArmies == 1) {
								canAttack = true;
							}else {
								JOptionPane.showMessageDialog(null, "Please enter 1 for Attacked Armies", "Alert", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					if(samePlayer(attackerTerr, opponentTerr)) {
						if(canAttack) {
							if(controller.hasEnoughArmies(attackerTerr, opponentTerr, numAttackerArmies, numOpponentArmies)) {
								btnEndAttack.setVisible(false);
								attackingTerr.setEnabled(false);
								attackedTerr.setEnabled(false);
								instructions.setInstructions(attackerTerr.getName() + "(" + currentPlayer.getName() +") is attacking, " + opponentTerr.getName() + "(" + opponentTerr.getRuler().getName() + ")");
								AttackStatus status = Player.attack(attackerTerr, opponentTerr, numAttackerArmies, numOpponentArmies, game);
								game = status.getGame();
								instructions.setInstructions(status.getStatusMessage().toString());
								game.update();
								if(status.hasWon) {
									Player winner = status.getWinner();
									System.out.println("Winner is "+ winner);
									if(attacker == winner) {
										playerList.get(currentIndex).hasWon = true;
									}
									attackingTerr.setEnabled(true);
									attackedTerr.setEnabled(true);
									btnEndAttack.setVisible(true);
									updateJList(currentIndex, selectedIndex1, -1);
								}else {
									updateJList(currentIndex, selectedIndex1, selectedIndex2);
									if(game.getPlayers().get(currentIndex).getOwnedTerritories().get(selectedIndex1).getNumberOfArmies() <= 1) {
										attackingTerr.setEnabled(true);
										attackedTerr.setEnabled(true);
										btnEndAttack.setVisible(true);
									}else {
										btnEndAttack.setVisible(false);
									}
								}
							}else {
								JOptionPane.showMessageDialog(null, "Not enough armies", "Alert", JOptionPane.ERROR_MESSAGE);
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "Both Attacking and Attacked Players should not be same. Choose a different territory", "Alert", JOptionPane.ERROR_MESSAGE);
						attackedTerr.clearSelection();
					}
				}
			});
			
			btnEndAttack.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					GameController controller = new GameController();
					game = controller.getCard(playerList.get(currentIndex), cards, game);
					playerList.get(currentIndex).hasWon = false;
					System.out.println(playerList.get(currentIndex).getCards().get(0).getArmyType());
					btnFortify.setVisible(true);
					btnEndFortify.setVisible(true);
					playerList.get(currentIndex).setStrategy("FORTIFY");
					attackPanel.setVisible(false);
					instructions.setInstructions("");
					instructions.setInstructions("Fortification Phase");
					instructions.setInstructions("*******************");
					instructions.setInstructions("It is time to fortify... Click End Fortify if you finished fortification");
				}
			});
			
		}else {
			attackPanel.setVisible(false);
		}
	}
	
	/**
	 * Hide add buttons
	 * 
	 */
	public void hideAddButtons() {
		btnFortify.setVisible(false);
		btnEndFortify.setVisible(false);
		btnAddArmy.setVisible(false);
		btnAddReinforceArmy.setVisible(false);
		numArmiesText.setVisible(false);
	}

	/**
	 * @param attackerTerr
	 * @param opponentTerr
	 * @return
	 */
	protected boolean samePlayer(Territory attackerTerr, Territory opponentTerr) {
		System.out.println("Attacker: " + attackerTerr.getName() + ", " + attackerTerr.getRuler().getName());
		System.out.println("Opponent: " + opponentTerr.getName() + ", " + opponentTerr.getRuler().getName());
		if(attackerTerr.getRuler() == opponentTerr.getRuler()) {
			return false;
		}else {
			return true;
		}
	}

	/**
	 * @param currPlayer 
	 * @param currentPlayer2
	 * @param selectedIndex1
	 * @param selectedIndex2 
	 * @param i
	 */
	protected void updateJList(int currentIndex, int selectedIndex1, int selectedIndex2) {
		String[] emptyArray = {};
		attackingTerr.setListData(emptyArray);
		attackedTerr.setListData(emptyArray);
		
		ArrayList<Territory> attackingTerritories = game.getPlayers().get(currentIndex).getOwnedTerritories();
		String[] attackingTerrNames = new String[attackingTerritories.size()];
		for(int i = 0;i<attackingTerritories.size();i++) {
			attackingTerrNames[i] = attackingTerritories.get(i).getName() + "(" + attackingTerritories.get(i).getNumberOfArmies() + ")";
		}
		attackingTerr.setListData(attackingTerrNames);
		attackingTerr.setSelectedIndex(selectedIndex1);
		
		if(selectedIndex2 == -1) {
			String[] attackedTerrNames = {};
			attackedTerr.setListData(attackedTerrNames);
		}else {
			ArrayList<Territory> attackedTerritories = game.getPlayers().get(currentIndex).getOwnedTerritories().get(selectedIndex1).getAdjacentTerritories();
			String[] attackedTerrNames = new String[attackedTerritories.size()];
			for(int i = 0;i<attackedTerrNames.length;i++) {
				Territory tempTerritory = attackedTerritories.get(i);
				attackedTerrNames[i] = tempTerritory.getName() + "(" + tempTerritory.getRuler().getName() + "-" + tempTerritory.getNumberOfArmies() + ")";
			}
			attackedTerr.setListData(attackedTerrNames);
			attackedTerr.setSelectedIndex(selectedIndex2);
		}
	}
	
	/**
	 * Display Reinforcement buttons
	 */
	protected void displayReinforcement() {
		playerJList.setEnabled(false);
		btnAddArmy.setVisible(false);
		numArmiesText.setVisible(false);
		btnReinforcement.setVisible(true);
		instructions.setInstructions("");
		instructions.setInstructions("Reinforcement Phase");
		instructions.setInstructions("*******************");
		instructions.setInstructions("Adding is completed. Click on Reinforcement button");		
	}
	
	/**
	 * Game in step by step
	 */
	protected void onGame() {
		System.out.println("Current Player:" +currentPlayer.getName());
		System.out.println("Strategy:" + currentPlayer.getStrategy());
		if(currentPlayer.getStrategy().equals("REINFORCEMENT")) {
			displayReinforcement();
		}else if(currentPlayer.getStrategy().equals("ATTACK")) {
			displayAttackPanel();
		}else if(currentPlayer.getStrategy().equals("FORTIFY")) {
			
		}
	}

}
