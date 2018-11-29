package com.risk.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.risk.controller.GameController;

/**
 * @author Hareesh Kavumkulath
 *
 */
public class HumanPlayer implements Strategy, Serializable {

	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = 1260591218179582532L;

	/**
	 * Logger object setup for the log file
	 */
	static Logger logger = Logger.getLogger(Player.class.getName());

	/**
	 * Reinforce function for Human Player
	 * 
	 * @param currentPlayer Player Current Player
	 * @param territory Reinforce territory
	 * @param gameInstructions GameInstructions
	 * @param controller GameController
	 */
	@Override
	public void reinforce(Player currentPlayer, Territory territory, GameInstructions gameInstructions,
			GameController controller) {
		logger.log(Level.INFO, currentPlayer.getName() + " is Human");
		controller.addArmyToTerritory(currentPlayer, territory, 1);
	}

	/**
	 * Attack function for Human Player
	 * 
	 * @param currentPlayer Player
	 * @param attackerTerritory  Attacker Territory
	 * @param opponentTerritory Opponent Territory
	 * @param allOutMode boolean
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController
	 */
	@Override
	public void attack(Player currentPlayer, Territory attackerTerritory, Territory opponentTerritory,
			boolean allOutMode, GameInstructions gameInstructions, GameController controller) {
		logger.log(Level.INFO, currentPlayer.getName() + " is Human");
		int numAttackerArmies = 0;
		int numOpponentArmies = 0;
		boolean canAttack = false;
		if (allOutMode) {
			canAttack = true;
		} else {
			boolean isValidNumber = false;
			while (!isValidNumber) {
				try {
					String input = JOptionPane.showInputDialog(null,
							"Enter the number of armies for the attacker, " + attackerTerritory.getName(),
							"Dialog for Input", JOptionPane.WARNING_MESSAGE);
					if (input == null) {
						canAttack = false;
						isValidNumber = true;
					} else {
						numAttackerArmies = Integer.parseInt(input);
						if (numAttackerArmies > 0
								&& numAttackerArmies <= controller.getNumAttackerArmies(attackerTerritory)) {
							isValidNumber = true;
						}
						if (numAttackerArmies > controller.getNumAttackerArmies(attackerTerritory)) {
							JOptionPane.showMessageDialog(null,
									"Insufficient Number of armies for " + attackerTerritory.getName(), "Alert",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please enter either 1,2 or 3 for Attacking Armies", "Alert",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			if (numAttackerArmies > 0) {
				numOpponentArmies = controller.getNumOpponentArmies(opponentTerritory, numAttackerArmies, false);
				if (attackerTerritory.getRuler() != opponentTerritory.getRuler()) {
					canAttack = true;
				}
				if (numAttackerArmies > 3 || numAttackerArmies < 1) {
					JOptionPane.showMessageDialog(null, "Please enter either 1,2 or 3 for Attacking Armies", "Alert",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (numAttackerArmies == 3) {
						if (numOpponentArmies < 1 || numOpponentArmies > 2) {
							JOptionPane.showMessageDialog(null, "Please enter either 2 or 1 for Opponent Armies",
									"Alert", JOptionPane.ERROR_MESSAGE);
						} else {
							canAttack = true;
						}
					} else {
						if (numOpponentArmies == 1) {
							canAttack = true;
						} else {
							JOptionPane.showMessageDialog(null, "Please enter 1 for Attacked Armies", "Alert",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		}
		if (canAttack) {
			if (controller.hasEnoughArmies(attackerTerritory, opponentTerritory, numAttackerArmies,
					numOpponentArmies)) {
				if (allOutMode) {
					gameInstructions.setInstructions(attackerTerritory.getName() + "(" + currentPlayer.getName()
							+ ") is attacking, " + opponentTerritory.getName() + "("
							+ opponentTerritory.getRuler().getName() + ")\n");
					AttackStatus status = new AttackStatus();
					while (attackerTerritory.getNumberOfArmies() > 1 && !status.hasWon) {
						numAttackerArmies = controller.getNumAttackerArmies(attackerTerritory);
						numOpponentArmies = controller.getNumOpponentArmies(opponentTerritory, numAttackerArmies,
								allOutMode);
						status = controller.attack(attackerTerritory, opponentTerritory, numAttackerArmies,
								numOpponentArmies);
						gameInstructions.setInstructions(status.getStatusMessage().toString());
						controller.updateGame(attackerTerritory, opponentTerritory);
					}
					if (status.hasWon) {
						currentPlayer.hasWon = true;
					}
				} else {
					gameInstructions.setInstructions(attackerTerritory.getName() + "(" + currentPlayer.getName()
							+ ") is attacking, " + opponentTerritory.getName() + "("
							+ opponentTerritory.getRuler().getName() + ")\n");
					AttackStatus status = controller.attack(attackerTerritory, opponentTerritory, numAttackerArmies,
							numOpponentArmies);
					gameInstructions.setInstructions(status.getStatusMessage().toString());
					controller.updateGame(attackerTerritory, opponentTerritory);
					if (status.hasWon) {
						currentPlayer.hasWon = true;
					}
				}
			}

		}
	}

	/**
	 * Human player fortify
	 * 
	 * @param currentPlayer Player
	 * @param fromTerritory Territory from
	 * @param toTerritory Territory To
	 * @param fortifyNum integer fortification number
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController
	 */
	@Override
	public void fortify(Player currentPlayer, Territory fromTerritory, Territory toTerritory, int fortifyNum,
			GameInstructions gameInstructions, GameController controller) {
		logger.log(Level.INFO, currentPlayer.getName() + " is Human");
		int fromTerrNumArmies = fromTerritory.getNumberOfArmies();
		int toTerrNumArmies = toTerritory.getNumberOfArmies();
		fromTerrNumArmies = fromTerrNumArmies - fortifyNum;
		toTerrNumArmies = toTerrNumArmies + fortifyNum;
		fromTerritory.setNumberOfArmies(fromTerrNumArmies);
		toTerritory.setNumberOfArmies(toTerrNumArmies);
		currentPlayer.setFortificationStatus(true);
		currentPlayer.setPhase("REINFORCEMENT");
		gameInstructions.setInstructions(currentPlayer.getName() + " has fortified " + toTerritory.getName() + " from "
				+ fromTerritory.getName() + " with " + fortifyNum + " armies\n");
		logger.log(Level.INFO, currentPlayer.getName() + " has fortified " + toTerritory.getName() + " from "
				+ fromTerritory.getName() + " with " + fortifyNum + " armies");
	}

}
