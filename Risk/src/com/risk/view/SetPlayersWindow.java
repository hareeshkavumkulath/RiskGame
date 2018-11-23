package com.risk.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;

import com.risk.controller.GameController;
import com.risk.model.AggressivePlayer;
import com.risk.model.BenevolentPlayer;
import com.risk.model.Card;
import com.risk.model.CheaterPlayer;
import com.risk.model.Game;
import com.risk.model.GameInstructions;
import com.risk.model.HumanPlayer;
import com.risk.model.Map;
import com.risk.model.Player;
import com.risk.model.RandomPlayer;

import javax.swing.JTextField;
import javax.swing.JRadioButton;

/**
 * View class for the player window
 * 
 * @author Angeline Anqi Wang
 * @version 1.0
 */
public class SetPlayersWindow {
	
	/**
	 * Logger object setup for the log file
	 */
	static Logger logger = Logger.getLogger(StartWindow.class.getName());

	@SuppressWarnings("javadoc")
	private JFrame frame;
	@SuppressWarnings("javadoc")
	private JTextField playerName1;
	@SuppressWarnings("javadoc")
	private JTextField playerName2;
	@SuppressWarnings("javadoc")
	private JTextField playerName3;
	@SuppressWarnings("javadoc")
	private JTextField playerName4;
	@SuppressWarnings("javadoc")
	private JTextField playerName5;
	@SuppressWarnings("javadoc")
	private JTextField playerName6;
	
	@SuppressWarnings("javadoc")
	private Map map;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnHuman1;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnAggressive1;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnBenevolent1;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnRandom1;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnCheater1;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnHuman2;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnAggressive2;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnBenevolent2;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnRandom2;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnCheater2;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnHuman3;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnAggressive3;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnBenevolent3;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnRandom3;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnCheater3;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnHuman4;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnAggressive4;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnBenevolent4;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnRandom4;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnCheater4;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnHuman5;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnAggressive5;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnBenevolent5;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnRandom5;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnCheater5;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnHuman6;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnAggressive6;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnBenevolent6;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnRandom6;
	@SuppressWarnings("javadoc")
	private JRadioButton rdbtnCheater6;
	
	/**
	 * Main function for the Set Players Window class
	 * 
	 * @param map the player selected map 
	 */
	public void main(Map map) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetPlayersWindow window = new SetPlayersWindow(map);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor for the class with parameter
	 * 
	 * @param map 
	 */
	public SetPlayersWindow(Map map) {
		this.map = map;
		initialize();
	}

	/**
	 * Empty constructor for the class
	 */
	public SetPlayersWindow() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 833, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Select the number of Players:");
		label.setBounds(29, 20, 256, 20);
		frame.getContentPane().add(label);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(2, 2, 6, 1));
		spinner.setBounds(266, 17, 46, 26);
		frame.getContentPane().add(spinner);
		
		JButton setButton = new JButton(">>");
		setButton.setBounds(327, 16, 64, 29);
		frame.getContentPane().add(setButton);
		
		playerName1 = new JTextField("Player1");
		playerName1.setBounds(21, 72, 146, 26);
		frame.getContentPane().add(playerName1);
		playerName1.setColumns(10);
		
		rdbtnHuman1 = new JRadioButton("Human");
		rdbtnHuman1.setSelected(true);
		rdbtnHuman1.setBounds(195, 71, 95, 29);
		rdbtnHuman1.setActionCommand("HUMAN");
		frame.getContentPane().add(rdbtnHuman1);
		
		rdbtnAggressive1 = new JRadioButton("Aggressive");
		rdbtnAggressive1.setBounds(314, 71, 120, 29);
		rdbtnAggressive1.setActionCommand("AGGRESSIVE");
		frame.getContentPane().add(rdbtnAggressive1);
		
		rdbtnBenevolent1 = new JRadioButton("Benevolent");
		rdbtnBenevolent1.setBounds(453, 71, 120, 29);
		rdbtnBenevolent1.setActionCommand("BENEVOLENT");
		frame.getContentPane().add(rdbtnBenevolent1);
		
		rdbtnRandom1 = new JRadioButton("Random");
		rdbtnRandom1.setBounds(581, 72, 95, 29);
		rdbtnRandom1.setActionCommand("RANDOM");
		frame.getContentPane().add(rdbtnRandom1);
		
		rdbtnCheater1 = new JRadioButton("Cheater");
		rdbtnCheater1.setBounds(700, 72, 120, 29);
		rdbtnCheater1.setActionCommand("CHEATER");
		frame.getContentPane().add(rdbtnCheater1);
		
		ButtonGroup strategy1 = new ButtonGroup();
		strategy1.add(rdbtnHuman1);
		strategy1.add(rdbtnAggressive1);
		strategy1.add(rdbtnBenevolent1);
		strategy1.add(rdbtnRandom1);
		strategy1.add(rdbtnCheater1); 
		
		playerName2 = new JTextField("Player2");
		playerName2.setColumns(10);
		playerName2.setBounds(21, 121, 146, 26);
		frame.getContentPane().add(playerName2);
		
		rdbtnHuman2 = new JRadioButton("Human");
		rdbtnHuman2.setSelected(true);
		rdbtnHuman2.setBounds(195, 117, 95, 29);
		rdbtnHuman2.setActionCommand("HUMAN");
		frame.getContentPane().add(rdbtnHuman2);
		
		rdbtnAggressive2 = new JRadioButton("Aggressive");
		rdbtnAggressive2.setBounds(314, 117, 120, 29);
		rdbtnAggressive2.setActionCommand("AGGRESSIVE");
		frame.getContentPane().add(rdbtnAggressive2);
		
		rdbtnBenevolent2 = new JRadioButton("Benevolent");
		rdbtnBenevolent2.setBounds(453, 117, 120, 29);
		rdbtnBenevolent2.setActionCommand("BENEVOLENT");
		frame.getContentPane().add(rdbtnBenevolent2);
		
		rdbtnRandom2 = new JRadioButton("Random");
		rdbtnRandom2.setBounds(581, 120, 95, 29);
		rdbtnRandom2.setActionCommand("RANDOM");
		frame.getContentPane().add(rdbtnRandom2);
		
		rdbtnCheater2 = new JRadioButton("Cheater");
		rdbtnCheater2.setBounds(700, 118, 120, 29);
		rdbtnCheater2.setActionCommand("CHEATER");
		frame.getContentPane().add(rdbtnCheater2);
		
		ButtonGroup strategy2 = new ButtonGroup();
		strategy2.add(rdbtnHuman2);
		strategy2.add(rdbtnAggressive2);
		strategy2.add(rdbtnBenevolent2);
		strategy2.add(rdbtnRandom2);
		strategy2.add(rdbtnCheater2);
		
		playerName3 = new JTextField("Player3");
		playerName3.setColumns(10);
		playerName3.setBounds(21, 171, 146, 26);
		frame.getContentPane().add(playerName3);
		playerName3.setVisible(false);
		
		rdbtnHuman3 = new JRadioButton("Human");
		rdbtnHuman3.setSelected(true);
		rdbtnHuman3.setBounds(195, 167, 95, 29);
		rdbtnHuman3.setActionCommand("HUMAN");
		frame.getContentPane().add(rdbtnHuman3);
		rdbtnHuman3.setVisible(false);
		
		rdbtnAggressive3 = new JRadioButton("Aggressive");
		rdbtnAggressive3.setBounds(314, 167, 120, 29);
		rdbtnAggressive3.setActionCommand("AGGRESSIVE");
		frame.getContentPane().add(rdbtnAggressive3);
		rdbtnAggressive3.setVisible(false);
		
		rdbtnBenevolent3 = new JRadioButton("Benevolent");
		rdbtnBenevolent3.setBounds(453, 167, 120, 29);
		rdbtnBenevolent3.setActionCommand("BENEVOLENT");
		frame.getContentPane().add(rdbtnBenevolent3);
		rdbtnBenevolent3.setVisible(false);
		
		rdbtnRandom3 = new JRadioButton("Random");
		rdbtnRandom3.setBounds(581, 168, 95, 29);
		rdbtnRandom3.setActionCommand("RANDOM");
		frame.getContentPane().add(rdbtnRandom3);
		rdbtnRandom3.setVisible(false);
		
		rdbtnCheater3 = new JRadioButton("Cheater");
		rdbtnCheater3.setBounds(700, 168, 120, 29);
		rdbtnCheater3.setActionCommand("CHEATER");
		frame.getContentPane().add(rdbtnCheater3);
		rdbtnCheater3.setVisible(false);
		
		ButtonGroup strategy3 = new ButtonGroup();
		strategy3.add(rdbtnHuman3);
		strategy3.add(rdbtnAggressive3);
		strategy3.add(rdbtnBenevolent3);
		strategy3.add(rdbtnRandom3);
		strategy3.add(rdbtnCheater3);
		
		playerName4 = new JTextField("Player4");
		playerName4.setColumns(10);
		playerName4.setBounds(21, 224, 146, 26);
		frame.getContentPane().add(playerName4);
		playerName4.setVisible(false);
		
		rdbtnHuman4 = new JRadioButton("Human");
		rdbtnHuman4.setSelected(true);
		rdbtnHuman4.setBounds(195, 220, 95, 29);
		rdbtnHuman4.setActionCommand("HUMAN");
		frame.getContentPane().add(rdbtnHuman4);
		rdbtnHuman4.setVisible(false);
		
		rdbtnAggressive4 = new JRadioButton("Aggressive");
		rdbtnAggressive4.setBounds(314, 220, 120, 29);
		rdbtnAggressive4.setActionCommand("AGGRESSIVE");
		frame.getContentPane().add(rdbtnAggressive4);
		rdbtnAggressive4.setVisible(false);
		
		rdbtnBenevolent4 = new JRadioButton("Benevolent");
		rdbtnBenevolent4.setBounds(453, 220, 120, 29);
		rdbtnBenevolent4.setActionCommand("BENEVOLENT");
		frame.getContentPane().add(rdbtnBenevolent4);
		rdbtnBenevolent4.setVisible(false);
		
		rdbtnRandom4 = new JRadioButton("Random");
		rdbtnRandom4.setBounds(581, 221, 95, 29);
		rdbtnRandom4.setActionCommand("RANDOM");
		frame.getContentPane().add(rdbtnRandom4);
		rdbtnRandom4.setVisible(false);
		
		rdbtnCheater4 = new JRadioButton("Cheater");
		rdbtnCheater4.setBounds(700, 221, 120, 29);
		rdbtnCheater4.setActionCommand("CHEATER");
		frame.getContentPane().add(rdbtnCheater4);
		rdbtnCheater4.setVisible(false);
		
		ButtonGroup strategy4 = new ButtonGroup();
		strategy4.add(rdbtnHuman4);
		strategy4.add(rdbtnAggressive4);
		strategy4.add(rdbtnBenevolent4);
		strategy4.add(rdbtnRandom4);
		strategy4.add(rdbtnCheater4);
		
		playerName5 = new JTextField("Player5");
		playerName5.setColumns(10);
		playerName5.setBounds(21, 276, 146, 26);
		frame.getContentPane().add(playerName5);
		playerName5.setVisible(false);
		
		rdbtnHuman5 = new JRadioButton("Human");
		rdbtnHuman5.setSelected(true);
		rdbtnHuman5.setBounds(195, 272, 95, 29);
		rdbtnHuman5.setActionCommand("HUMAN");
		frame.getContentPane().add(rdbtnHuman5);
		rdbtnHuman5.setVisible(false);
		
		rdbtnAggressive5 = new JRadioButton("Aggressive");
		rdbtnAggressive5.setBounds(314, 272, 120, 29);
		rdbtnAggressive5.setActionCommand("AGGRESSIVE");
		frame.getContentPane().add(rdbtnAggressive5);
		rdbtnAggressive5.setVisible(false);
		
		rdbtnBenevolent5 = new JRadioButton("Benevolent");
		rdbtnBenevolent5.setBounds(453, 272, 120, 29);
		rdbtnBenevolent5.setActionCommand("BENEVOLENT");
		frame.getContentPane().add(rdbtnBenevolent5);
		rdbtnBenevolent5.setVisible(false);
		
		rdbtnRandom5 = new JRadioButton("Random");
		rdbtnRandom5.setBounds(581, 273, 95, 29);
		rdbtnRandom5.setActionCommand("RANDOM");
		frame.getContentPane().add(rdbtnRandom5);
		rdbtnRandom5.setVisible(false);
		
		rdbtnCheater5 = new JRadioButton("Cheater");
		rdbtnCheater5.setBounds(700, 273, 120, 29);
		rdbtnCheater5.setActionCommand("CHEATER");
		frame.getContentPane().add(rdbtnCheater5);
		rdbtnCheater5.setVisible(false);
		
		ButtonGroup strategy5 = new ButtonGroup();
		strategy5.add(rdbtnHuman5);
		strategy5.add(rdbtnAggressive5);
		strategy5.add(rdbtnBenevolent5);
		strategy5.add(rdbtnRandom5);
		strategy5.add(rdbtnCheater5);
		
		playerName6 = new JTextField("Player6");
		playerName6.setColumns(10);
		playerName6.setBounds(21, 330, 146, 26);
		frame.getContentPane().add(playerName6);
		playerName6.setVisible(false);
		
		rdbtnHuman6 = new JRadioButton("Human");
		rdbtnHuman6.setSelected(true);
		rdbtnHuman6.setBounds(195, 326, 95, 29);
		rdbtnHuman6.setActionCommand("HUMAN");
		frame.getContentPane().add(rdbtnHuman6);
		rdbtnHuman6.setVisible(false);
		
		rdbtnAggressive6 = new JRadioButton("Aggressive");
		rdbtnAggressive6.setBounds(314, 326, 120, 29);
		rdbtnAggressive6.setActionCommand("AGGRESSIVE");
		frame.getContentPane().add(rdbtnAggressive6);
		rdbtnAggressive6.setVisible(false);
		
		rdbtnBenevolent6 = new JRadioButton("Benevolent");
		rdbtnBenevolent6.setBounds(453, 326, 120, 29);
		rdbtnBenevolent6.setActionCommand("BENEVOLENT");
		frame.getContentPane().add(rdbtnBenevolent6);
		rdbtnBenevolent6.setVisible(false);
		
		rdbtnRandom6 = new JRadioButton("Random");
		rdbtnRandom6.setBounds(581, 327, 95, 29);
		rdbtnRandom6.setActionCommand("RANDOM");
		frame.getContentPane().add(rdbtnRandom6);
		rdbtnRandom6.setVisible(false);
		
		rdbtnCheater6 = new JRadioButton("Cheater");
		rdbtnCheater6.setBounds(700, 327, 120, 29);
		rdbtnCheater6.setActionCommand("CHEATER");
		frame.getContentPane().add(rdbtnCheater6);
		rdbtnCheater6.setVisible(false);
		
		ButtonGroup strategy6 = new ButtonGroup();
		strategy6.add(rdbtnHuman6);
		strategy6.add(rdbtnAggressive6);
		strategy6.add(rdbtnBenevolent6);
		strategy6.add(rdbtnRandom6);
		strategy6.add(rdbtnCheater6);
		
		JButton okButton = new JButton("OK");
		okButton.setBounds(670, 396, 115, 39);
		frame.getContentPane().add(okButton);
		
		//Button Actions
		setButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int numberOfPlayers = Integer.parseInt(spinner.getValue().toString());
				int numTerritories = map.getTerritories().size();
				if(numTerritories > numberOfPlayers) {
					showPlayerSetupPanel(numberOfPlayers);
				}else {
					
				}
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int number = Integer.parseInt(spinner.getValue().toString());
				ArrayList<Player> playerList = new ArrayList<Player>();
				GameController controller = new GameController();
				int numArmies; // Number of armies each player get
				numArmies = controller.getPlayersArmies(number);
				
				String name1 = playerName1.getText();
				boolean isComputer1 = isComputer(strategy1.getSelection().getActionCommand());
				Player firstPlayer = new Player(name1, isComputer1, numArmies, "ADD");
				setStrategy(firstPlayer, strategy1.getSelection().getActionCommand());
				String name2 = playerName2.getText();
				boolean isComputer2 = isComputer(strategy2.getSelection().getActionCommand());
				Player secondPlayer = new Player(name2, isComputer2, numArmies, "ADD");
				setStrategy(secondPlayer, strategy2.getSelection().getActionCommand());
				playerList.add(firstPlayer);
				playerList.add(secondPlayer);
				if(number > 2) {
					String name = playerName3.getText();
					boolean isComputer = isComputer(strategy3.getSelection().getActionCommand());
					Player newPlayer = new Player(name, isComputer, numArmies, "ADD");
					setStrategy(newPlayer, strategy3.getSelection().getActionCommand());
					playerList.add(newPlayer);
				}
				if(number > 3) {
					String name = playerName4.getText();
					boolean isComputer = isComputer(strategy4.getSelection().getActionCommand());
					Player newPlayer = new Player(name, isComputer, numArmies, "ADD");
					setStrategy(newPlayer, strategy4.getSelection().getActionCommand());
					playerList.add(newPlayer);
				}
				if(number > 4) {
					String name = playerName5.getText();
					boolean isComputer = isComputer(strategy5.getSelection().getActionCommand());
					Player newPlayer = new Player(name, isComputer, numArmies, "ADD");
					setStrategy(newPlayer, strategy5.getSelection().getActionCommand());
					playerList.add(newPlayer);
				}
				if(number > 5) {
					String name = playerName6.getText();
					boolean isComputer = isComputer(strategy6.getSelection().getActionCommand());
					Player newPlayer = new Player(name, isComputer, numArmies, "ADD");
					setStrategy(newPlayer, strategy6.getSelection().getActionCommand());
					playerList.add(newPlayer);
				}
				
				logger.log(Level.INFO, "OK button is pressed. "+ playerList.size() + " are created.");
				
				//Game Objects initialization
				ArrayList<Card> cards = controller.loadCards(map.getTerritories().size());
				Game game = new Game(map, playerList, cards, playerList.get(0));
				GameInstructions gameInstructions = new GameInstructions("Risk Game\r\n");
				
				GameController gameController = new GameController(game, gameInstructions);
				game.setCurrentPlayer(gameController.assignTerritories());
				
				GameWindow gameWindow = new GameWindow(game, gameController);
				logger.log(Level.INFO, "Proceeding to Game Window");
				gameWindow.onGame();
				
			}
			
			private void setStrategy(Player player, String actionCommand) {
				if(actionCommand.equals("AGGRESSIVE")) {
					player.setStrategy(new AggressivePlayer());
				}else if(actionCommand.equals("BENEVOLENT")) {
					player.setStrategy(new BenevolentPlayer());
				}else if(actionCommand.equals("RANDOM")) {
					player.setStrategy(new RandomPlayer());
				}else if(actionCommand.equals("CHEATER")) {
					player.setStrategy(new CheaterPlayer());
				}else {
					player.setStrategy(new HumanPlayer());
				}
			}

			private boolean isComputer(String actionCommand) {
				if(actionCommand.equals("HUMAN")) {
					return false;
				}else {
					return true;
				}
			}
		});
		
	}

	/**
	 * Display user interface for player setup panel
	 * 
	 * @param numberOfPlayers pass the number of players to display
	 */
	protected void showPlayerSetupPanel(int numberOfPlayers) {
		if (numberOfPlayers == 2) {
			playerName3.setVisible(false);
			playerName4.setVisible(false);
			playerName5.setVisible(false);
			playerName6.setVisible(false);
			
			rdbtnHuman3.setVisible(false);
			rdbtnHuman4.setVisible(false);
			rdbtnHuman5.setVisible(false);
			rdbtnHuman6.setVisible(false);
			
			rdbtnAggressive3.setVisible(false);
			rdbtnAggressive4.setVisible(false);
			rdbtnAggressive5.setVisible(false);
			rdbtnAggressive6.setVisible(false);
			
			rdbtnBenevolent3.setVisible(false);
			rdbtnBenevolent4.setVisible(false);
			rdbtnBenevolent5.setVisible(false);
			rdbtnBenevolent6.setVisible(false);
			
			rdbtnRandom3.setVisible(false);
			rdbtnRandom4.setVisible(false);
			rdbtnRandom5.setVisible(false);
			rdbtnRandom6.setVisible(false);
			
			rdbtnCheater3.setVisible(false);
			rdbtnCheater4.setVisible(false);
			rdbtnCheater5.setVisible(false);
			rdbtnCheater6.setVisible(false);
			
		}
		
		if (numberOfPlayers > 2) {
			playerName3.setVisible(true);
			playerName4.setVisible(false);
			playerName5.setVisible(false);
			playerName6.setVisible(false);
			
			rdbtnHuman3.setVisible(true);
			rdbtnHuman4.setVisible(false);
			rdbtnHuman5.setVisible(false);
			rdbtnHuman6.setVisible(false);
			
			rdbtnAggressive3.setVisible(true);
			rdbtnAggressive4.setVisible(false);
			rdbtnAggressive5.setVisible(false);
			rdbtnAggressive6.setVisible(false);
			
			rdbtnBenevolent3.setVisible(true);
			rdbtnBenevolent4.setVisible(false);
			rdbtnBenevolent5.setVisible(false);
			rdbtnBenevolent6.setVisible(false);
			
			rdbtnRandom3.setVisible(true);
			rdbtnRandom4.setVisible(false);
			rdbtnRandom5.setVisible(false);
			rdbtnRandom6.setVisible(false);
			
			rdbtnCheater3.setVisible(true);
			rdbtnCheater4.setVisible(false);
			rdbtnCheater5.setVisible(false);
			rdbtnCheater6.setVisible(false);
		}
		
		if (numberOfPlayers > 3) {
			playerName4.setVisible(true);
			playerName5.setVisible(false);
			playerName6.setVisible(false);
			
			rdbtnHuman4.setVisible(true);
			rdbtnHuman5.setVisible(false);
			rdbtnHuman6.setVisible(false);
			
			rdbtnAggressive4.setVisible(true);
			rdbtnAggressive5.setVisible(false);
			rdbtnAggressive6.setVisible(false);
			
			rdbtnBenevolent4.setVisible(true);
			rdbtnBenevolent5.setVisible(false);
			rdbtnBenevolent6.setVisible(false);
			
			rdbtnRandom4.setVisible(true);
			rdbtnRandom5.setVisible(false);
			rdbtnRandom6.setVisible(false);
			
			rdbtnCheater4.setVisible(true);
			rdbtnCheater5.setVisible(false);
			rdbtnCheater6.setVisible(false);
		}
		
		if (numberOfPlayers > 4) {		
			playerName5.setVisible(true);
			playerName6.setVisible(false);
			
			rdbtnHuman5.setVisible(true);
			rdbtnHuman6.setVisible(false);
			
			rdbtnAggressive5.setVisible(true);
			rdbtnAggressive6.setVisible(false);
			
			rdbtnBenevolent5.setVisible(true);
			rdbtnBenevolent6.setVisible(false);
			
			rdbtnRandom5.setVisible(true);
			rdbtnRandom6.setVisible(false);
			
			rdbtnCheater5.setVisible(true);
			rdbtnCheater6.setVisible(false);
		}
		
		if (numberOfPlayers > 5) {
			playerName6.setVisible(true);
			
			rdbtnHuman6.setVisible(true);
			
			rdbtnAggressive6.setVisible(true);
			
			rdbtnBenevolent6.setVisible(true);
			
			rdbtnRandom6.setVisible(true);
			
			rdbtnCheater6.setVisible(true);
		}
	}
}
