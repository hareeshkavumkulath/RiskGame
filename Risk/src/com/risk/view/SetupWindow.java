package com.risk.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.risk.controller.GameController;
import com.risk.controller.MapController;
import com.risk.controller.TournamentGame;
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

import javax.swing.JPanel;
import javax.swing.JTable;
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
	@SuppressWarnings("javadoc")
	private String[] columnNames;
	@SuppressWarnings("javadoc")
	private String[][] mapNames;
	@SuppressWarnings("javadoc")
	private String[][] winners;
	@SuppressWarnings("javadoc")
	private JTable table;

	@SuppressWarnings("javadoc")
	private String[][] results;

	@SuppressWarnings("javadoc")
	private JPanel panel;

	@SuppressWarnings("javadoc")
	private JLabel mapLabel;

	@SuppressWarnings("javadoc")
	private JLabel playerLabel;

	@SuppressWarnings("javadoc")
	private JLabel gameLabel;

	@SuppressWarnings("javadoc")
	private JLabel turnLabel;
	
	/**
	 * Create the frame. - Constructor
	 */
	public SetupWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 887);
		getContentPane().setLayout(null);
		setVisible(true);
		
		mapList = new JList<String>();
		mapList.setBounds(25, 47, 243, 331);
		getContentPane().add(mapList);
		
		panel = new JPanel();
		panel.setBounds(15, 440, 804, 375);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout());
		
		JLabel resultLabel = new JLabel("Results");
		resultLabel.setBounds(15, 16, 144, 29);
		panel.add(resultLabel);
		
		mapLabel = new JLabel("");
		mapLabel.setBounds(15, 61, 763, 20);
		panel.add(mapLabel);
		
		playerLabel = new JLabel("");
		playerLabel.setBounds(15, 86, 353, 20);
		panel.add(playerLabel);
		
		gameLabel = new JLabel("");
		gameLabel.setBounds(15, 114, 371, 20);
		panel.add(gameLabel);
		
		turnLabel = new JLabel("");
		turnLabel.setBounds(15, 142, 396, 20);
		panel.add(turnLabel);
		
		panel.setVisible(false);
		
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
						turnLabel.setText("D: " + numTurns);
						if(numTurns > 0) {
							numGames = Integer.parseInt(spinner.getValue().toString());
							gameLabel.setText("G: " + numGames);
							columnNames = new String[numGames + 1];
							columnNames[0] = "";
							for(int i=1;i<=numGames;i++) {
								columnNames[i] = "Game" + i;
							}
							mapNames = getMapNames();
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
	 * Returns the selected map names
	 * 
	 * @return String[][] two dimensional array
	 * 
	 */
	protected String[][] getMapNames() {
		int[] indices = mapList.getSelectedIndices();
		String[][] mapNames = new String[indices.length][1];
		for(int i=0;i<indices.length;i++) {
			mapNames[i][0] = fileNames[indices[i]]; 
		}
		return mapNames;
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
		winners = new String[rows][columns];
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
		
		results = new String[rows][columns + 1];
		
		for(int i=0;i<rows;i++) {
			results[i][0] = mapNames[i][0];
			for(int j=0;j<columns;j++) {				
				results[i][j+1] =  winners[i][j];
			}
		}
		
		for(int i=0;i<rows;i++) {
			for(int j=0;j<=columns;j++) {
				System.out.print(results[i][j]);
			}
			System.out.println();
		}
		
		displayResult();
		
	}
	
	/**
	 * Display End Result
	 */
	private void displayResult() {
		table = new JTable(results,columnNames);	
		table.setBounds(15, 177, 774, 182);
		panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		panel.add(table, BorderLayout.CENTER);
		panel.setVisible(true);
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
			playerLabel.setText("P: ");
			ArrayList<Player> players = new ArrayList<Player>();
			GameController controller = new GameController();
			int numArmies = controller.getPlayersArmies(count);
			if(chckbxAggressive.isSelected()) {
				Player player1 = new Player("Aggressive", true, numArmies, "ADD");
				player1.setStrategy(new AggressivePlayer());
				players.add(player1);
				playerLabel.setText(playerLabel.getText() + player1.getName() + " ");
			}
			if(chckbxBenevolent.isSelected()) {
				Player player2 = new Player("Benevolent", true, numArmies, "ADD");
				player2.setStrategy(new BenevolentPlayer());
				players.add(player2);
				playerLabel.setText(playerLabel.getText() + player2.getName() + " ");
			}
			if(chckbxRandom.isSelected()) {
				Player player3 = new Player("Random", true, numArmies, "ADD");
				player3.setStrategy(new RandomPlayer());
				players.add(player3);
				playerLabel.setText(playerLabel.getText() + player3.getName() + " ");
			}
			if(chckbxCheater.isSelected()) {
				Player player4 = new Player("Cheater", true, numArmies, "ADD");
				player4.setStrategy(new CheaterPlayer());
				players.add(player4);
				playerLabel.setText(playerLabel.getText() + player4.getName() + " ");
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
			mapLabel.setText("M: ");
			ArrayList<Map> maps = new ArrayList<Map>();
			for(int i=0;i<indices.length;i++) {
				MapController controller = new MapController();
				File file = new File(".\\Maps\\" + fileNames[indices[i]]); 
				MapMessage message = controller.processFile(file);
				if(message.isValidMap()) {
					Map map = message.getMap();
					maps.add(map);
				}
				mapLabel.setText(mapLabel.getText() + fileNames[indices[i]] + " ");
			}
			return maps;
		}
	}
}
