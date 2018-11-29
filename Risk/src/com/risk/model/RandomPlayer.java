package com.risk.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.risk.controller.GameController;
import com.risk.view.StartWindow;

/**
 * This class implements the Random Player functionalities for this game
 * 
 * @author Anqi Wang
 * @version 1.1
 */
public class RandomPlayer implements Strategy, Serializable {

	/**
	 * Logger object setup for the log file
	 */
	static Logger logger = Logger.getLogger(StartWindow.class.getName());

	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = -5235779546189131503L;

	/**
	 * This is reinforce method for this current random player
	 * 
	 * @param currentPlayer Player
	 * @param territory reinforcement Territory
	 * @param gameInstructions  GameInstructions message
	 * @param controller GameController
	 */
	@Override
	public void reinforce(Player currentPlayer, Territory territory, GameInstructions gameInstructions,
			GameController controller) {
		if (currentPlayer.getCards() != null) {
			if (currentPlayer.getCards().size() >= 5) {
				int turnInNumArmies = controller.validateCards(currentPlayer);
				if (turnInNumArmies > 0) {
					gameInstructions.setInstructions(currentPlayer.getName() + " has turned in cards");
				}
			}
		}
		int reinforcementArmy = controller.getNumReinforcements(currentPlayer);
		int numArmiesFromContinents = controller.getNumArmiesFromContinents(currentPlayer);
		int playerNumArmies = currentPlayer.getNumberOfArmies();
		reinforcementArmy = reinforcementArmy + numArmiesFromContinents + playerNumArmies;
		currentPlayer.setNumberOfArmies(reinforcementArmy);
		gameInstructions
				.setInstructions(currentPlayer.getName() + " has " + reinforcementArmy + " reinforcement armies.\n");
		logger.log(Level.INFO, currentPlayer.getName() + " has " + reinforcementArmy + " reinforcement armies.");
		while (currentPlayer.getNumberOfArmies() > 0) {
			controller.addArmyRandom(currentPlayer);
		}
	}

	/**
	 * This implements attack method for this random player to attack
	 * 
	 * @param currentPlayer Player
	 * @param attackerTerritory Attacker Territory
	 * @param opponentTerritory Opponent Territory
	 * @param allOutMode true or false
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController
	 */
	@Override
	public void attack(Player currentPlayer, Territory attackerTerritory, Territory opponentTerritory,
			boolean allOutMode, GameInstructions gameInstructions, GameController controller) {
		Territory attackerTerr = controller.getRandomAttacker(currentPlayer);
		if (attackerTerr != null) {
			Territory opponentTerr = controller.getOpponent(currentPlayer, attackerTerr);
			int numAttacks = controller.getRandomNumber(10);
			int count = 0;
			gameInstructions.setInstructions(attackerTerr.getName() + " is attacking " + opponentTerr.getName() + " "
					+ numAttacks + " times\r\n");
			AttackStatus status = new AttackStatus();
			int numAttackerArmies, numOpponentArmies;
			while (attackerTerr.getNumberOfArmies() > 1 && !status.hasWon && numAttacks != count) {
				numAttackerArmies = controller.getNumAttackerArmies(attackerTerr);
				numOpponentArmies = controller.getNumOpponentArmies(opponentTerr, numAttackerArmies, false);
				status = controller.attack(attackerTerr, opponentTerr, numAttackerArmies, numOpponentArmies);
				gameInstructions.setInstructions(status.getStatusMessage().toString());
				controller.updateGame(attackerTerr, opponentTerr);
				// game.update();
				count++;
			}
			if (status.hasWon) {
				currentPlayer.hasWon = true;
			}
		}
	}

	/**
	 * This is fortify method and its implements the random player at fortify step
	 * 
	 * @param currentPlayer Player
	 * @param fromTerritory Territory from
	 * @param toTerritory Territory To
	 * @param fortifyNum int fortification number
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController
	 */
	@Override
	public void fortify(Player currentPlayer, Territory fromTerritory, Territory toTerritory, int fortifyNum,
			GameInstructions gameInstructions, GameController controller) {
		int totalNumTerritories = currentPlayer.getOwnedTerritories().size();
		if (totalNumTerritories > 1) {
			Territory territoryFrom = currentPlayer.getOwnedTerritories()
					.get(controller.getRandomNumber(totalNumTerritories));
			while (territoryFrom.getNumberOfArmies() <= 1) {
				territoryFrom = currentPlayer.getOwnedTerritories()
						.get(controller.getRandomNumber(totalNumTerritories));
			}
			if (territoryFrom != null) {
				fortifyNum = territoryFrom.getNumberOfArmies() - 1;
				Territory territoryTo = currentPlayer.getOwnedTerritories()
						.get(controller.getRandomNumber(totalNumTerritories));
				while (territoryFrom == territoryTo) {
					territoryTo = currentPlayer.getOwnedTerritories()
							.get(controller.getRandomNumber(totalNumTerritories));
				}
				int fromTerrNumArmies = territoryFrom.getNumberOfArmies();
				int toTerrNumArmies = territoryTo.getNumberOfArmies();
				fromTerrNumArmies = fromTerrNumArmies - fortifyNum;
				toTerrNumArmies = toTerrNumArmies + fortifyNum;
				territoryFrom.setNumberOfArmies(fromTerrNumArmies);
				territoryTo.setNumberOfArmies(toTerrNumArmies);
				currentPlayer.setFortificationStatus(true);
				gameInstructions.setInstructions(currentPlayer.getName() + " has fortified " + territoryTo.getName()
						+ " from " + territoryFrom.getName() + " with " + fortifyNum + " armies\n");
				logger.log(Level.INFO, currentPlayer.getName() + " has fortified " + territoryTo.getName() + " from "
						+ territoryFrom.getName() + " with " + fortifyNum + " armies");
			}
		}
		if (currentPlayer.hasWon) {
			controller.addCard(currentPlayer);
			currentPlayer.hasWon = false;
		}
	}

}
