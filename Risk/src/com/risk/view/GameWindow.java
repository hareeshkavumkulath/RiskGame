package com.risk.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

/**
 * @author Hareesh Kavumkulath
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
	private JList<String> playerJList;
	
	public ArrayList<Player> playerList = new ArrayList<Player>();
	public ArrayList<Continent> continents;
	public ArrayList<Territory> territories;
	private String instructionsMsg = "Let's Start Conquering the world.\r\nPlease select the number of Players";
	private GameInstructions instructions = new GameInstructions(instructionsMsg);
	
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
			}
		});
		
		playerJList = new JList<String>();
		playerJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playerJList.setBorder(new LineBorder(Color.BLUE));
		playerJList.setBounds(648, 96, 154, 257);
		
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
		
		JList<String> ownedTerritories = new JList<String>();
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
		
		beginGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		playerJList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				JList list = (JList) listSelectionEvent.getSource();
				int selections[] = list.getSelectedIndices();
				ArrayList<Territory> territories = playerList.get(selections[0]).getOwnedTerritories();
				String[] territoryNames = new String[territories.size()];
				for(int i=0;i<territories.size();i++) {
					territoryNames[i] = territories.get(i).getName();
				}
				ownedTerritories.setListData(territoryNames);				
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
		
	}
}
