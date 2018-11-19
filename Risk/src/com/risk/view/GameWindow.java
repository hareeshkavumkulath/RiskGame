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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.risk.controller.GameController;
import com.risk.model.AttackStatus;
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
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game;
	private GameController controller;
	private GameInstructions gameInstructions;
	private Player currentPlayer;
	private InstructionsView instructionsPane;
	private AddArmyPanel addArmyPanel;
	private JPanel attackPanel;
	private JLabel lblPlayer;
	private JList<String> attackingTerr;
	private JList<String> attackedTerr;
	private JTextField attackingArmy;
	private JTextField attackedArmy;
	private JButton btnAttack;
	private JButton btnEndAttack;
	private JCheckBox chckbxAllOutMode;
	private JPanel fortifyPanel;
	private JLabel lblFortPlayer;
	private JTextField fortifyNumberField;
	private JList<String> fromTerr;
	private JList<String> toTerr;
	private JButton btnFortify;
	private JButton btnEndFortify;
	private JButton btnSave;
	private JTextField gameName;
	private JPanel panel;

	/**
	 * Create the application.
	 * 
	 * @param map Map pass map model to GameWindow.java
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
		attackPanel = new JPanel();
		attackPanel.setBounds(648, 369, 726, 468);
		getContentPane().add(attackPanel);
		attackPanel.setLayout(null);
		attackPanel.setVisible(false);
		
		JLabel lblNewLabel_1 = new JLabel("Attack View");
		lblNewLabel_1.setBounds(15, 16, 138, 20);
		attackPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Attacking Territory");
		lblNewLabel.setBounds(181, 52, 151, 20);
		attackPanel.add(lblNewLabel);
		
		JLabel labelName = new JLabel("Player Name");
		labelName.setBounds(15, 52, 151, 20);
		attackPanel.add(labelName);
		
		JLabel lblAttackedCountry = new JLabel("Attacked Territory");
		lblAttackedCountry.setBounds(442, 52, 151, 20);
		attackPanel.add(lblAttackedCountry);
		
		lblPlayer = new JLabel("");
		lblPlayer.setBounds(15, 88, 69, 20);
		attackPanel.add(lblPlayer);
		
		attackingTerr = new JList<String>();
		attackingTerr.setBounds(1, 1, 219, 301);
		attackPanel.add(attackingTerr);
		
		JScrollPane attackingTerrPane = new JScrollPane(attackingTerr);
		attackingTerrPane.setBounds(181, 88, 247, 271);
		attackingTerrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		attackPanel.add(attackingTerrPane);
		
		attackedTerr = new JList<String>();
		attackedTerr.setBounds(1, 1, 219, 301);
		attackPanel.add(attackedTerr);
		
		JScrollPane attackedTerrPane = new JScrollPane(attackedTerr);
		attackedTerrPane.setBounds(442, 88, 247, 267);
		attackedTerrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		attackPanel.add(attackedTerrPane);
		
		attackingArmy = new JTextField();
		attackingArmy.setBounds(181, 371, 247, 26);
		attackPanel.add(attackingArmy);
		attackingArmy.setColumns(10);
		
		attackedArmy = new JTextField();
		attackedArmy.setBounds(442, 371, 247, 26);
		attackedArmy.setColumns(10);
		attackPanel.add(attackedArmy);
		
		JLabel lblNumberOfDices = new JLabel("Number of Dices");
		lblNumberOfDices.setBounds(15, 374, 167, 20);
		attackPanel.add(lblNumberOfDices);
		
		btnAttack = new JButton("Attack");
		btnAttack.setBounds(458, 423, 115, 29);
		attackPanel.add(btnAttack);
		
		btnEndAttack = new JButton("End Attack");
		btnEndAttack.setBounds(588, 423, 123, 29);
		attackPanel.add(btnEndAttack);
		
		chckbxAllOutMode = new JCheckBox("All Out Mode");
		chckbxAllOutMode.setBounds(296, 423, 139, 29);
		attackPanel.add(chckbxAllOutMode);
		
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
				try {
					// write object to file
					if(!gameName.getText().equals("")) {
						game.setCurrentPlayer(currentPlayer);
						String name = gameName.getText() + ".game";
						FileOutputStream fos = new FileOutputStream(".\\Games\\" + name);
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(game);
						oos.close();
					}else {
						JOptionPane.showMessageDialog(frame, "Enter a name");
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		addArmyPanel.getBtnAddArmy().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedIndex = addArmyPanel.getAddArmyTerrlist().getSelectedIndex();
					controller.addArmyToTerritory(currentPlayer, currentPlayer.getOwnedTerritories().get(selectedIndex), 1);
					nextPlayer();
					game.update();
					onGame();
				}catch(Exception ex) {
					System.out.println("Exception in adding armies to territory" + ex.toString());
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
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selectedIndex = addArmyPanel.getAddArmyTerrlist().getSelectedIndex();
				controller.addArmyToTerritory(currentPlayer, currentPlayer.getOwnedTerritories().get(selectedIndex), 1);
				game.update();
				
				if(currentPlayer.getNumberOfArmies() == 0) {
					onGame();
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
		
		btnAttack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int numAttackerArmies = 0;
				int numOpponentArmies = 0;
				int selectedIndex1 = attackingTerr.getSelectedIndex();
				int selectedIndex2 = attackedTerr.getSelectedIndex();
				Territory attackerTerr = currentPlayer.getOwnedTerritories().get(selectedIndex1);
				Territory opponentTerr = attackerTerr.getAdjacentTerritories().get(selectedIndex2);
				boolean canAttack = false;
				boolean allOutMode = chckbxAllOutMode.isSelected();
				if(allOutMode) {
					canAttack = true;
				}else {
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
				}
				if(attackerTerr.getRuler() != opponentTerr.getRuler()) {
					if(canAttack) {
						if(controller.hasEnoughArmies(attackerTerr, opponentTerr, numAttackerArmies, numOpponentArmies)) {
							if(allOutMode) {
								gameInstructions.setInstructions(attackerTerr.getName() + "(" + currentPlayer.getName() +") is attacking, " + opponentTerr.getName() + "(" + opponentTerr.getRuler().getName() + ")");
								AttackStatus status = new AttackStatus();
								while(attackerTerr.getNumberOfArmies() > 1 && !status.hasWon) {
									numAttackerArmies = controller.getNumAttackerArmies(attackerTerr);
									numOpponentArmies = controller.getNumOpponentArmies(opponentTerr, numAttackerArmies);
									status = controller.attack(attackerTerr, opponentTerr, numAttackerArmies, numOpponentArmies);
									gameInstructions.setInstructions(status.getStatusMessage().toString());
									controller.updateGame(attackerTerr, opponentTerr);
								}
								if(status.hasWon) {
									currentPlayer.hasWon = true;
								}
								game.update();
								updateAttackStatus(status, attackerTerr, selectedIndex1, selectedIndex2);
							}else {
								gameInstructions.setInstructions(attackerTerr.getName() + "(" + currentPlayer.getName() +") is attacking, " + opponentTerr.getName() + "(" + opponentTerr.getRuler().getName() + ")");
								AttackStatus status = controller.attack(attackerTerr, opponentTerr, numAttackerArmies, numOpponentArmies);
								gameInstructions.setInstructions(status.getStatusMessage().toString());
								controller.updateGame(attackerTerr, opponentTerr);
								game.update();
								updateAttackStatus(status, attackerTerr, selectedIndex1, selectedIndex2);
								if(status.hasWon) {
									currentPlayer.hasWon = true;
								}
							}
							if(controller.isWinner()) {
								attackPanel.setVisible(false);
								JOptionPane.showMessageDialog(null, game.getPlayers().get(0).getName() + " has conquered the Map", "Winner", JOptionPane.INFORMATION_MESSAGE);
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
							boolean isValidNumber = controller.validateFortifyNumber(currentPlayer, fromTerr.getSelectedIndex(), fortifyNum);
							Territory fromTerritory = currentPlayer.getOwnedTerritories().get(fromTerr.getSelectedIndex());
							Territory toTerritory = currentPlayer.getOwnedTerritories().get(toTerr.getSelectedIndex());
							if(isValidNumber) {
								controller.fortify(currentPlayer, fromTerritory, toTerritory, fortifyNum);
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
						}catch(Exception ex) {
							System.out.println(ex.toString());
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
		attackPanel.setVisible(true);
		gameInstructions.setInstructions("It's " + currentPlayer.getName() + "'s turn!!! Select territories and click Attack Button");
		ArrayList<Territory> attackingTerritories;
		
		lblPlayer.setText(currentPlayer.getName());
		
		attackingTerritories = currentPlayer.getOwnedTerritories();
		String[] attackingTerrNames = new String[attackingTerritories.size()];
		for(int i = 0;i<attackingTerritories.size();i++) {
			attackingTerrNames[i] = attackingTerritories.get(i).getName() + "-" + attackingTerritories.get(i).getContinent() + "(" + attackingTerritories.get(i).getNumberOfArmies() + ")";
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
						attackedTerrNames[i] = tempTerritory.getName() + "-" + tempTerritory.getContinent() + "(" + tempTerritory.getRuler().getName() + "-" + tempTerritory.getNumberOfArmies() + ")";
					}
					attackedTerr.setListData(attackedTerrNames);
				}catch(Exception ex) {
					System.out.println("Exception in attacking territory JList" + ex.toString());
				}
			}
		});
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
	 * @param status attack status
	 * @param attackerTerr attacker territory
	 * @param selectedIndex1 index
	 * @param selectedIndex2 index
	 */
	protected void updateAttackStatus(AttackStatus status, Territory attackerTerr, int selectedIndex1, int selectedIndex2) {
		if(status.hasWon) {
			checkWinner();
			Player winner = status.getWinner();
			System.out.println("Winner is "+ winner);
			if(currentPlayer == winner) {
				currentPlayer.hasWon = true;
			}
			attackingTerr.setEnabled(true);
			attackedTerr.setEnabled(true);
			btnEndAttack.setVisible(true);
			updateJList(selectedIndex1, -1);
		}else if(attackerTerr.getNumberOfArmies() == 1){
			gameInstructions.setInstructions(attackerTerr.getName() + " has only one army left, choose a different territory");
			attackingTerr.setEnabled(true);
			attackedTerr.setEnabled(true);
			btnEndAttack.setVisible(true);
			updateJList(selectedIndex1, -1);
		}
		else {
			updateJList(selectedIndex1, selectedIndex2);
			if(currentPlayer.getOwnedTerritories().get(selectedIndex1).getNumberOfArmies() <= 1) {
				attackingTerr.setEnabled(true);
				attackedTerr.setEnabled(true);
				btnEndAttack.setVisible(true);
			}else {
				btnEndAttack.setVisible(false);
			}
		}
		
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
			System.out.println(game.getPlayers().get(0).getName() + " has conquered the Map.");
			JOptionPane.showMessageDialog(null, game.getPlayers().get(0).getName() + " has conquered the Map.", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * set the next player by index
	 */
	protected void nextPlayer() {
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
		if(currentPlayer.getPhase().equals("ADD")) {
			if(currentPlayer.isComputer) {
				System.out.println(currentPlayer.getName() + " is Computer");
				controller.addArmyRandom(currentPlayer);
				nextPlayer();
				game.update();
				onGame();
			}else {
				addArmyPanel.getBtnAddArmy().setVisible(true);
				addArmyPanel.getBtnReinforceArmy().setVisible(false);
				System.out.println(currentPlayer.getName() + " is Human");
				displayAddArmyPanel();
			}
			game.update();
		}else if(currentPlayer.getPhase().equals("REINFORCEMENT")) {
			gameInstructions.setInstructions("Reinforcement Phase\r\n");
			hideAddArmyPanel();
			addArmyPanel.getBtnReinforceAddArmy().setVisible(false);
			addArmyPanel.getBtnReinforceArmy().setVisible(true);
			if(currentPlayer.isComputer) {
				System.out.println(currentPlayer.getName() + " is Computer");
				currentPlayer.reinforce(gameInstructions, controller);
				game.update();
				onGame();
			}else {
				addArmyPanel.getBtnAddArmy().setVisible(false);
				addArmyPanel.getBtnReinforceArmy().setVisible(true);
				System.out.println(currentPlayer.getName() + " is Human");
				displayAddArmyPanel();
			}
			game.update();
		}else if(currentPlayer.getPhase().equals("ATTACK")) {
			System.out.println("Attack Phase");
			if(currentPlayer.isComputer) {
				currentPlayer.attack(gameInstructions, controller, game);
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
			System.out.println("Fortification Phase");
			currentPlayer.setFortificationStatus(false);
			gameInstructions.setInstructions("Fortification Phase\n");
			gameInstructions.setInstructions("***************************************\n");
			if(currentPlayer.isComputer) {
				currentPlayer.fortify(gameInstructions, controller);
				nextPlayer();
				onGame();
			}else {
				displayFortify();
			}
			game.update();
		}
	}
}
