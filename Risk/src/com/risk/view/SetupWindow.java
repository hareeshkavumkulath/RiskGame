package com.risk.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;

import com.risk.controller.GameController;
import com.risk.controller.MapController;
import com.risk.model.AggressivePlayer;
import com.risk.model.BenevolentPlayer;
import com.risk.model.Card;
import com.risk.model.CheaterPlayer;
import com.risk.model.Game;
import com.risk.model.GameInstructions;
import com.risk.model.Map;
import com.risk.model.MapMessage;
import com.risk.model.Player;
import com.risk.model.RandomPlayer;
import com.risk.model.TournamentGame;
/**
 * Tournament mode player set up
 * 
 * @author Jingya Pan
 * @version 1.0
 */
public class SetupWindow extends JFrame {

	/**
	 * set the serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("javadoc")
	private JTextField textField;
	@SuppressWarnings("javadoc")
	private JList<String> mapList;
	@SuppressWarnings("javadoc")
	private String[] fileNames;
	@SuppressWarnings("javadoc")
	private JTextField numTurnTxtField;
	@SuppressWarnings("javadoc")
	private JCheckBox chckbxAggressive;
	@SuppressWarnings("javadoc")
	private JCheckBox chckbxBenevolent;
	@SuppressWarnings("javadoc")
	private JCheckBox chckbxRandom;
	@SuppressWarnings("javadoc")
	private JCheckBox chckbxCheater;
	
	/**
	 * Create the frame.
	 */
	public SetupWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 514);
		getContentPane().setLayout(null);
		setVisible(true);
		
		mapList = new JList<String>();
		mapList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		mapList.setBounds(25, 47, 243, 331);
		getContentPane().add(mapList);
		
		File folder = new File(".\\Maps\\");
		File[] listOfFiles = folder.listFiles();
		fileNames = new String[listOfFiles.length];
		
		int k = 0;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String fileExtension = listOfFiles[i].getName().substring((listOfFiles[i].getName().lastIndexOf(".")+1));
				if(fileExtension.equals("txt")) {
					fileNames[k] = listOfFiles[i].getName();
					k++;
				}
			}
		}
		mapList.setListData(fileNames);
	}
	/**
	 * initialize
	 */
	public void initialize() {
		
		chckbxAggressive = new JCheckBox("Aggressive");
		chckbxAggressive.setBounds(310, 69, 139, 29);
		chckbxAggressive.setActionCommand("AGGRESSIVE");
		getContentPane().add(chckbxAggressive);
		
		chckbxBenevolent = new JCheckBox("Benevolent");
		chckbxBenevolent.setBounds(310, 114, 139, 29);
		chckbxBenevolent.setActionCommand("BENEVOLENT");
		getContentPane().add(chckbxBenevolent);
		
		chckbxRandom = new JCheckBox("Random");
		chckbxRandom.setBounds(310, 165, 139, 29);
		chckbxRandom.setActionCommand("RANDOM");
		getContentPane().add(chckbxRandom);
		
		chckbxCheater = new JCheckBox("Cheater");
		chckbxCheater.setBounds(310, 215, 139, 29);
		chckbxCheater.setActionCommand("CHEATER");
		getContentPane().add(chckbxCheater);
		
		JLabel lblNewLabel = new JLabel("Number of Games:");
		lblNewLabel.setBounds(310, 266, 152, 36);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNumberOfTurns = new JLabel("Number of Turns:");
		lblNumberOfTurns.setBounds(310, 318, 152, 36);
		getContentPane().add(lblNumberOfTurns);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinner.setBounds(477, 271, 32, 26);
		getContentPane().add(spinner);
		
		JLabel lblSelectPlayerStrategies = new JLabel("Select Player Strategies:");
		lblSelectPlayerStrategies.setBounds(310, 23, 256, 36);
		getContentPane().add(lblSelectPlayerStrategies);
		
		numTurnTxtField = new JTextField();
		numTurnTxtField.setBounds(477, 323, 53, 26);
		getContentPane().add(numTurnTxtField);
		numTurnTxtField.setColumns(10);
		
		JLabel lblNumberOenterNumber = new JLabel("* (Enter number between 10 and 50)");
		lblNumberOenterNumber.setForeground(Color.GRAY);
		lblNumberOenterNumber.setBounds(545, 318, 270, 36);
		getContentPane().add(lblNumberOenterNumber);
		
		JButton btnNewButton = new JButton("Start Tournament");
		btnNewButton.setBounds(612, 394, 186, 42);
		getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int numTurns = 0;
				ArrayList<Player> players = new ArrayList<Player>();
				ArrayList<Map> maps = new ArrayList<Map>();
				int numGames = 1;
				maps = getMaps();
				if(maps != null) {
					players = getPlayers();
					if(players != null) {
						numTurns = getNumTurns();
						if(numTurns > 0) {
							numGames = Integer.parseInt(spinner.getValue().toString());
							startTournament(maps, players, numGames, numTurns);
						}else {
							JOptionPane.showMessageDialog(null, "Number of turns must be number between 10 and 50", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Select 2 Players atleast", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Select 1-5 Maps", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
	}
	/**
	 * tournament mode set up
	 * 
	 * @param maps list of maps
	 * @param players list of player
	 * @param numGames number of games
	 * @param numTurns numbers of turns
	 */
	protected void startTournament(ArrayList<Map> maps, ArrayList<Player> players, int numGames, int numTurns) {
		int rows = maps.size();
		int columns = numGames;
		String[][] winners = new String[rows][columns];
		for(int i=0;i<rows;i++) {
			ArrayList<Map> newMaps = getMaps();
			Map map = newMaps.get(i);
			for(int j=0;j<columns;j++) {
 				ArrayList<Player> newPlayers = getPlayers(); 
				GameController controller = new GameController();
				ArrayList<Card> cards = controller.loadCards(map.getTerritories().size());
				Game game = new Game(map, newPlayers, cards, newPlayers.get(0));
				GameInstructions gameInstructions = new GameInstructions("Risk Game\r\n");				
				GameController gameController = new GameController(game, gameInstructions);
				game.setCurrentPlayer(gameController.assignTerritories());
				TournamentGame tournament = new TournamentGame(game, gameController, numTurns);
				tournament.onGame();
				winners[i][j] = tournament.getWinner();	
			}
		}
		
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				System.out.print(winners[i][j]);
			}
			System.out.println();
		}
		
	}
	
	/**
	 * get the number of turns
	 * 
	 * @return int number of turns
	 */
	protected int getNumTurns() {
		int numTurns = 0;
		try {
			numTurns = Integer.parseInt(numTurnTxtField.getText());
			if(numTurns > 50 || numTurns < 10) {
				numTurns = 0;
			}
		}catch(Exception e) {
			numTurns = 0;
		}
		return numTurns;
	}
	
	/**
	 * Getters of the player
	 * 
	 * @return ArrayList list of players
	 */
	protected ArrayList<Player> getPlayers() {
		int count = 0;
		if(chckbxAggressive.isSelected()) {
			count++;
		}
		if(chckbxBenevolent.isSelected()) {
			count++;
		}
		if(chckbxRandom.isSelected()) {
			count++;
		}
		if(chckbxCheater.isSelected()) {
			count++;
		}
		if(count > 1) {
			ArrayList<Player> players = new ArrayList<Player>();
			GameController controller = new GameController();
			int numArmies = controller.getPlayersArmies(count);
			if(chckbxAggressive.isSelected()) {
				Player player1 = new Player("Aggressive", true, numArmies, "ADD");
				player1.setStrategy(new AggressivePlayer());
				players.add(player1);
			}
			if(chckbxBenevolent.isSelected()) {
				Player player2 = new Player("Benevolent", true, numArmies, "ADD");
				player2.setStrategy(new BenevolentPlayer());
				players.add(player2);
			}
			if(chckbxRandom.isSelected()) {
				Player player3 = new Player("Random", true, numArmies, "ADD");
				player3.setStrategy(new RandomPlayer());
				players.add(player3);
			}
			if(chckbxCheater.isSelected()) {
				Player player4 = new Player("Cheater", true, numArmies, "ADD");
				player4.setStrategy(new CheaterPlayer());
				players.add(player4);
			}
			return players;
		}else {
			return null;
		}
	}
	
	/**
	 * Get the selected map and load it
	 * 
	 * @return ArrayList list of maps
	 */
	protected ArrayList<Map> getMaps() {
		int[] indices = mapList.getSelectedIndices();
		if(indices.length > 5 || indices.length < 1) {
			return null;
		}else {
			ArrayList<Map> maps = new ArrayList<Map>();
			for(int i=0;i<indices.length;i++) {
				MapController controller = new MapController();
				File file = new File(".\\Maps\\" + fileNames[indices[i]]); 
				MapMessage message = controller.processFile(file);
				if(message.isValidMap()) {
					Map map = message.getMap();
					maps.add(map);
				}
			}
			return maps;
		}
	}
}
