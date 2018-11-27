package com.risk.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.risk.controller.GameController;
import com.risk.model.Card;
import com.risk.model.Game;
import com.risk.model.GameInstructions;
import com.risk.model.Player;
import com.risk.model.Territory;

/**
 * This class implements Game Window pages functionalities and design user interface.
 * 
 * @author Hareesh Kavumkulath
 * @version 1.1
 */
public class GameWindow extends JFrame{
	
	/**
	 * Logger object setup for the log file
	 */
	static Logger logger = Logger.getLogger(StartWindow.class.getName());

	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("javadoc")
	private Game game;
	@SuppressWarnings("javadoc")
	private GameController controller;
	@SuppressWarnings("javadoc")
	private GameInstructions gameInstructions;
	@SuppressWarnings("javadoc")
	private Player currentPlayer;
	@SuppressWarnings("javadoc")
	private InstructionsView instructionsPane;
	@SuppressWarnings("javadoc")
	private AddArmyPanel addArmyPanel;
	@SuppressWarnings("javadoc")
	private AttackPanel attackPanel;
	@SuppressWarnings("javadoc")
	private JList<String> attackingTerr;
	@SuppressWarnings("javadoc")
	private JList<String> attackedTerr;
	@SuppressWarnings("javadoc")
	private JPanel fortifyPanel;
	@SuppressWarnings("javadoc")
	private JLabel lblFortPlayer;
	@SuppressWarnings("javadoc")
	private JTextField fortifyNumberField;
	@SuppressWarnings("javadoc")
	private JList<String> fromTerr;
	@SuppressWarnings("javadoc")
	private JList<String> toTerr;
	@SuppressWarnings("javadoc")
	private JButton btnFortify;
	@SuppressWarnings("javadoc")
	private JButton btnEndFortify;
	@SuppressWarnings("javadoc")
	private JButton btnSave;
	@SuppressWarnings("javadoc")
	private JTextField gameName;
	
	/**
	 * Create the application.
	 * 
	 * @param currentGame Game Object 
	 * @param controller GameController
	 * 
	 */
	public GameWindow(Game currentGame, GameController controller) {
		this.game = currentGame;
		this.controller = controller;
		this.gameInstructions = controller.getGameInstructions(); 
		this.currentPlayer = game.getCurrentPlayer();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Risk");
		getContentPane().setForeground(Color.GREEN);
		setBounds(100, 100, 1954, 920);
		getContentPane().setLayout(null);
		
		// Map View Panel
		MapViewPanel mapViewPanel = new MapViewPanel(game);
		mapViewPanel.setBounds(15, 16, 632, 302);
		getContentPane().add(mapViewPanel);
		game.addObserver(mapViewPanel);
		
		//Display Player World Domination
		MapDominationView worldDomViewPanel = new MapDominationView(game);
		worldDomViewPanel.setBounds(15, 369, 618, 371);
		getContentPane().add(worldDomViewPanel);
		game.addObserver(worldDomViewPanel);
		
		// Display Instructions Pane
		instructionsPane = new InstructionsView(gameInstructions.getInstructions());
		gameInstructions.addObserver(instructionsPane);
				
		JScrollPane instructionsScrollPane = new JScrollPane(instructionsPane);
		instructionsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		instructionsScrollPane.setBounds(1390, 16, 499, 302);
		getContentPane().add(instructionsScrollPane);
				
		setVisible(true);
		
		// Add Army Panel
		addArmyPanel = new AddArmyPanel(game);
		getContentPane().add(addArmyPanel);
		game.addObserver(addArmyPanel);
		
		//Attack Panel
		attackPanel = new AttackPanel(game);
		getContentPane().add(attackPanel);
		game.addObserver(attackPanel);
		
		//Fortify Panel
		fortifyPanel = new JPanel();
		fortifyPanel.setBounds(647, 369, 720, 468);
		getContentPane().add(fortifyPanel);
		fortifyPanel.setLayout(null);
		fortifyPanel.setVisible(false);
		
		JLabel lblFortify = new JLabel("Fortify Panel");
		lblFortify.setBounds(15, 16, 138, 20);
		fortifyPanel.add(lblFortify);
		
		JLabel lblFromTerr = new JLabel("From Territory");
		lblFromTerr.setBounds(183, 52, 151, 20);
		fortifyPanel.add(lblFromTerr);		
		
		JLabel labelFortName = new JLabel("Player Name");
		labelFortName.setBounds(15, 52, 151, 20);
		fortifyPanel.add(labelFortName);
		
		lblFortPlayer = new JLabel("");
		lblFortPlayer.setBounds(15, 88, 69, 20);
		fortifyPanel.add(lblFortPlayer);
		
		fortifyNumberField = new JTextField();
		fortifyNumberField.setBounds(318, 373, 108, 26);
		fortifyPanel.add(fortifyNumberField);
		fortifyNumberField.setColumns(5);
		
		fromTerr = new JList<String>();
		fromTerr.setBounds(1, 1, 219, 301);
		fortifyPanel.add(fromTerr);
		
		JScrollPane fromTerrPane = new JScrollPane(fromTerr);
		fromTerrPane.setBounds(179, 88, 247, 271);
		fromTerrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		fortifyPanel.add(fromTerrPane);
		
		toTerr = new JList<String>();
		toTerr.setBounds(1, 1, 219, 301);
		fortifyPanel.add(toTerr);
		
		JScrollPane toTerrPane = new JScrollPane(toTerr);
		toTerrPane.setBounds(441, 88, 247, 267);
		toTerrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		fortifyPanel.add(toTerrPane);
		
		btnFortify = new JButton("Fortify");
		btnFortify.setBounds(451, 370, 115, 29);
		fortifyPanel.add(btnFortify);
		
		btnEndFortify = new JButton("End Fortify");
		btnEndFortify.setBounds(582, 370, 123, 29);
		fortifyPanel.add(btnEndFortify);
		
		JLabel lblToTerritory = new JLabel("To Territory");
		lblToTerritory.setBounds(441, 52, 151, 20);
		fortifyPanel.add(lblToTerritory);
		
		JLabel labelNumArmies = new JLabel("Enter Number of Armies:");
		labelNumArmies.setBounds(115, 376, 188, 20);
		fortifyPanel.add(labelNumArmies);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(490, 774, 115, 29);
		getContentPane().add(btnSave);
		
		gameName = new JTextField();
		gameName.setToolTipText("Save the game with a name");
		gameName.setBounds(69, 775, 406, 26);
		getContentPane().add(gameName);
		gameName.setColumns(10);
		
		//Map Ruler Display Panel
		MapRulerPanel rulerPanel = new MapRulerPanel(game);
		getContentPane().add(rulerPanel);
		game.addObserver(rulerPanel);
		
		//Button Actions
		btnSave.addActionListener(new ActionListener() {
			private Component frame;

			public void actionPerformed(ActionEvent arg0) {
				
				// write object to file
				if(!gameName.getText().equals("")) {
					game.setCurrentPlayer(currentPlayer);
					String name = gameName.getText() + ".game";
					if (saveToFile(name))
					{
						JOptionPane.showMessageDialog(frame, "Game Saved Successfully.");
						gameName.setText("");
					}else {
						JOptionPane.showMessageDialog(frame, "Game Couldn't be saved. Please try Again!!");
					}
					//FileOutputStream fos = new FileOutputStream(".\\Games\\" + name);
					//ObjectOutputStream oos = new ObjectOutputStream(fos);
					//oos.writeObject(game);
					//oos.close();
				}else {
					JOptionPane.showMessageDialog(frame, "Enter a name");
				}
			}
		});
		
		addArmyPanel.getBtnAddArmy().addActionListener(new ActionListener() {
			
			private Component frame;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedIndex = addArmyPanel.getAddArmyTerrlist().getSelectedIndex();
					if(selectedIndex < 0) {
						JOptionPane.showMessageDialog(frame, "Select a territory from the list");
					}else {
						controller.addArmyToTerritory(currentPlayer, currentPlayer.getOwnedTerritories().get(selectedIndex), 1);
						nextPlayer();
						game.update();
						onGame();
					}
				}catch(Exception ex) {
					logger.log(Level.INFO, "Exception in adding armies to territory" + ex.toString());
				}
			}
		});
		
		addArmyPanel.getBtnReinforceArmy().addActionListener(new ActionListener() {
			
			private Component frame;

			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentPlayer.getCards().size() >= 5) {
					JOptionPane.showMessageDialog(frame, "Player, " + currentPlayer.getName() + " has 5 or more cards turn in cards before clicking reinforcement");
				}else {
					int numArmy = controller.getNumReinforcements(currentPlayer);
					gameInstructions.setInstructions(currentPlayer.getName() + " has " + numArmy + " reinforce armies\r\n");
					currentPlayer.setNumberOfArmies(numArmy);
					addArmyPanel.getBtnReinforceAddArmy().setVisible(true);
					addArmyPanel.getBtnReinforceArmy().setVisible(false);
				}	
				game.update();
			}
		});
		
		addArmyPanel.getBtnReinforceAddArmy().addActionListener(new ActionListener() {
			
			private Component frame;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selectedIndex = addArmyPanel.getAddArmyTerrlist().getSelectedIndex();
				if(selectedIndex < 0) {
					JOptionPane.showMessageDialog(frame, "Select a territory from the list");
				}else {
					currentPlayer.reinforce(currentPlayer.getOwnedTerritories().get(selectedIndex), gameInstructions, controller);
					game.update();				
					if(currentPlayer.getNumberOfArmies() == 0) {
						onGame();
					}
				}
			}
		});
		
		addArmyPanel.getBtnTurnInCards().addActionListener(new ActionListener() {			
			private Component frame;

			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentPlayer.getCards().size() < 3) {
					JOptionPane.showMessageDialog(frame, "Player, " + currentPlayer.getName() + ", don't have sufficient cards.");
				}else {
					if(addArmyPanel.getAddArmyCardslist().getSelectedIndices().length < 3 || addArmyPanel.getAddArmyCardslist().getSelectedIndices().length > 3) {
						JOptionPane.showMessageDialog(frame, "Select 3 cards.");
					}else {
						if(controller.validateSelectedCards(addArmyPanel.getAddArmyCardslist().getSelectedValuesList())) {
							ArrayList<Card> currentCards = currentPlayer.getCards();
							currentPlayer = controller.turnInCards(currentPlayer, addArmyPanel.getAddArmyCardslist().getSelectedValuesList());
							if(currentPlayer.getCards().size() < currentCards.size()) {
								for(int i=0;i<currentCards.size();i++) {
									currentPlayer.getCards().add(currentCards.get(i));
								}
							}
							game.update();
						}else {
							JOptionPane.showMessageDialog(frame, "Select 3 different cards or 3 same cards");
						}
					}
				}				
			}
		});
		
		attackPanel.getBtnAttack().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex1 = attackPanel.getAttackingTerr().getSelectedIndex();
				int selectedIndex2 = attackPanel.getAttackedTerr().getSelectedIndex();
				Territory attackerTerr = currentPlayer.getOwnedTerritories().get(selectedIndex1);
				Territory opponentTerr = attackerTerr.getAdjacentTerritories().get(selectedIndex2);
				boolean allOutMode = attackPanel.getChckbxAllOutMode().isSelected();
				currentPlayer.attack(attackerTerr, opponentTerr, allOutMode, gameInstructions, controller);
				game.update();
				if(controller.isWinner()) {
					attackPanel.setVisible(false);
					JOptionPane.showMessageDialog(null, game.getPlayers().get(0).getName() + " has conquered the Map", "Winner", JOptionPane.INFORMATION_MESSAGE);
				}
				updateAttackStatus(attackerTerr, selectedIndex1, selectedIndex2);
			}
		});
		
		attackPanel.getBtnEndAttack().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentPlayer.setPhase("FORTIFY");
				attackPanel.setVisible(false);
				onGame();
			}
		});
		
		btnFortify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fromTerr.getSelectedIndex() == toTerr.getSelectedIndex()) {
					JOptionPane.showMessageDialog(null, "Select a different territory", "Alert", JOptionPane.ERROR_MESSAGE);
				}else {
					boolean isValid = controller.validateFortifyMove(currentPlayer, fromTerr.getSelectedIndex());
					if(isValid) {
						try {
							int fortifyNum = Integer.parseInt(fortifyNumberField.getText());
							if(fortifyNum > 0) {
								boolean isValidNumber = controller.validateFortifyNumber(currentPlayer, fromTerr.getSelectedIndex(), fortifyNum);
								Territory fromTerritory = currentPlayer.getOwnedTerritories().get(fromTerr.getSelectedIndex());
								Territory toTerritory = currentPlayer.getOwnedTerritories().get(toTerr.getSelectedIndex());
								if(isValidNumber) {
									currentPlayer.fortify(gameInstructions, controller, fromTerritory, toTerritory, fortifyNum);
									fortifyPanel.setVisible(false);
									if(currentPlayer.hasWon) {
										controller.addCard(currentPlayer);
										currentPlayer.hasWon = false;
									}
									nextPlayer();
									onGame();
								}else {
									JOptionPane.showMessageDialog(null, "Please enter a small number", "Alert", JOptionPane.ERROR_MESSAGE);
								}
							}else {
								JOptionPane.showMessageDialog(null, "Please enter a valid number", "Alert", JOptionPane.ERROR_MESSAGE);
							}
						}catch(Exception ex) {
							logger.log(Level.INFO, ex.toString());
							JOptionPane.showMessageDialog(null, "Please enter a valid number", "Alert", JOptionPane.ERROR_MESSAGE);
						}						
					}else {
						JOptionPane.showMessageDialog(null, "Not enough armies", "Alert", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		btnEndFortify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentPlayer.setFortificationStatus(true);
				fortifyPanel.setVisible(false);
				if(currentPlayer.hasWon) {
					controller.addCard(currentPlayer);
					currentPlayer.hasWon = false;
				}
				currentPlayer.setPhase("REINFORCEMENT");
				nextPlayer();
				onGame();			
			}
		});
	}
	/**
	 *  set the addArmyPanel visible
	 */
	private void displayAddArmyPanel() {		
		addArmyPanel.setVisible(true);
		addArmyPanel.getBtnTurnInCards().setVisible(true);
	}
	
	/**
	 * Saves The Game to the File
	 * @param fileName String file name 
	 * @return boolean true/false
	 */
	public boolean saveToFile(String fileName) {
		boolean saveResult = true;
		try {
			
			FileOutputStream fos = new FileOutputStream(".\\Games\\" + fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(game);
			oos.close();
			
		} catch (FileNotFoundException e) {
			
			saveResult = false;
			e.printStackTrace();
			
		} catch (IOException e) {
			
			saveResult = false;
			e.printStackTrace();
			
		}
		
		return saveResult;
	}
	/**
	 * set the addArmyPanel invisible
	 */
	private void hideAddArmyPanel() {
		addArmyPanel.setVisible(false);
	}
	/**
	 * show the attack info in the panel
	 */
	private void displayAttackPanel() {
		addArmyPanel.getBtnTurnInCards().setVisible(false);
		addArmyPanel.getBtnAddArmy().setVisible(false);
		addArmyPanel.getBtnReinforceArmy().setVisible(false);
		addArmyPanel.getBtnReinforceAddArmy().setVisible(false);
		attackPanel.getChckbxAllOutMode().setSelected(false);
		attackPanel.setVisible(true);
		gameInstructions.setInstructions("It's " + currentPlayer.getName() + "'s turn!!! Select territories and click Attack Button");
	}
	/**
	 * show the fortify info in the panel
	 */
	private void displayFortify() {
		fortifyPanel.setVisible(true);
		attackPanel.setVisible(false);
		ArrayList<Territory> fromTerritories;
		
		lblFortPlayer.setText(currentPlayer.getName());
		
		fromTerritories = currentPlayer.getOwnedTerritories();
		String[] fromTerrNames = new String[fromTerritories.size()];
		for(int i = 0;i<fromTerritories.size();i++) {
			fromTerrNames[i] = fromTerritories.get(i).getName() + "(" + fromTerritories.get(i).getNumberOfArmies() + ")";
		}
		fromTerr.setListData(fromTerrNames);
		toTerr.setListData(fromTerrNames);
	}
	/**
	 * Update the attack status
	 * @param attackerTerr attacker territory
	 * @param selectedIndex1 index
	 * @param selectedIndex2 index
	 */
	protected void updateAttackStatus(Territory attackerTerr, int selectedIndex1, int selectedIndex2) {
		//updateJList(selectedIndex1, -1);		
	}
	/**
	 * update the attacked territories list
	 * @param selectedIndex1 index
	 * @param selectedIndex2 index
	 */
	protected void updateJList(int selectedIndex1, int selectedIndex2) {
		String[] emptyArray = {};
		attackingTerr.setListData(emptyArray);
		attackedTerr.setListData(emptyArray);
		
		ArrayList<Territory> attackingTerritories = currentPlayer.getOwnedTerritories();
		String[] attackingTerrNames = new String[attackingTerritories.size()];
		for(int i = 0;i<attackingTerritories.size();i++) {
			attackingTerrNames[i] = attackingTerritories.get(i).getName() + "-" + attackingTerritories.get(i).getContinent() + "(" + attackingTerritories.get(i).getNumberOfArmies() + ")";
		}
		attackingTerr.setListData(attackingTerrNames);
		attackingTerr.setSelectedIndex(selectedIndex1);
		
		if(selectedIndex2 == -1) {
			String[] attackedTerrNames = {};
			attackedTerr.setListData(attackedTerrNames);
		}else {
			ArrayList<Territory> attackedTerritories = currentPlayer.getOwnedTerritories().get(selectedIndex1).getAdjacentTerritories();
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
	 * check the winner
	 */
	protected void checkWinner() {
		if(game.getPlayers().size() == 1) {
			JOptionPane.showMessageDialog(null, game.getPlayers().get(0).getName() + " has conquered the Map.", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * set the next player by index
	 */
	protected void nextPlayer() {
		logger.log(Level.INFO, "Inside Next player function");
		int indexOfCurrentPlayer = game.getPlayers().indexOf(currentPlayer);
		if(indexOfCurrentPlayer + 1 >= game.getPlayers().size()) {
			currentPlayer = game.getPlayers().get(0);
		}else {
			currentPlayer = game.getPlayers().get(indexOfCurrentPlayer + 1);
		}
		game.setCurrentPlayer(currentPlayer);
	}
	
	/**
	 * check the strategy of the current player
	 */
	public void onGame() {
		logger.log(Level.INFO, "Inside onGame function");
		if(currentPlayer.getPhase().equals("ADD")) {
			logger.log(Level.INFO, "Add Armies Phase");
			if(currentPlayer.isComputer) {
				logger.log(Level.INFO, currentPlayer.getName() + " is Computer");
				controller.addArmyRandom(currentPlayer);
				nextPlayer();
				game.update();
				onGame();
			}else {
				addArmyPanel.getBtnAddArmy().setVisible(true);
				addArmyPanel.getBtnReinforceArmy().setVisible(false);
				logger.log(Level.INFO, currentPlayer.getName() + " is Human");
				displayAddArmyPanel();
			}
			game.update();
		}else if(currentPlayer.getPhase().equals("REINFORCEMENT")) {
			logger.log(Level.INFO, "Reinforcement Phase");
			gameInstructions.setInstructions("");
			gameInstructions.setInstructions("Reinforcement Phase\n");
			gameInstructions.setInstructions("***************************************\n");
			hideAddArmyPanel();
			addArmyPanel.getBtnReinforceAddArmy().setVisible(false);
			addArmyPanel.getBtnReinforceArmy().setVisible(true);
			if(currentPlayer.isComputer) {
				logger.log(Level.INFO, currentPlayer.getName() + " is Computer");
				currentPlayer.reinforce(null, gameInstructions, controller);
				game.update();
				onGame();
			}else {
				addArmyPanel.getBtnAddArmy().setVisible(false);
				addArmyPanel.getBtnReinforceArmy().setVisible(true);
				displayAddArmyPanel();
			}
			game.update();
		}else if(currentPlayer.getPhase().equals("ATTACK")) {
			logger.log(Level.INFO, "Attack Phase");
			gameInstructions.setInstructions("");
			gameInstructions.setInstructions("Attack Phase\n");
			gameInstructions.setInstructions("***************************************\n");
			if(currentPlayer.isComputer) {
				currentPlayer.attack(null, null, false, gameInstructions, controller);
				game.update();
				if(controller.isWinner()) {
					JOptionPane.showMessageDialog(null, game.getPlayers().get(0).getName() + " has conquered the Map", "Winner", JOptionPane.INFORMATION_MESSAGE);
				}else {
					onGame();
				}
			}else {
				displayAttackPanel();
			}
			game.update();
		}else if(currentPlayer.getPhase().equals("FORTIFY")) {
			logger.log(Level.INFO, "Fortification Phase");
			currentPlayer.setFortificationStatus(false);
			gameInstructions.setInstructions("");
			gameInstructions.setInstructions("Fortification Phase\n");
			gameInstructions.setInstructions("***************************************\n");
			if(currentPlayer.isComputer) {
				currentPlayer.fortify(gameInstructions, controller, null, null, 0);
				nextPlayer();
				onGame();
			}else {
				displayFortify();
			}
			game.update();
		}
	}
}
