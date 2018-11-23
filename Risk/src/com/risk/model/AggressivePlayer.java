package com.risk.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.risk.controller.GameController;
import com.risk.view.StartWindow;

/**
 * model for the aggressive player
 * 
 * @author Jingya Pan
 * @version 1.0
 */
public class AggressivePlayer implements Strategy, Serializable {
	
	/**
	 * Logger object setup for the log file
	 */
	static Logger logger = Logger.getLogger(StartWindow.class.getName());

	/**
	 * set a Serializable
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Aggressive player reinforce
	 * 
	 * @param currentPlayer Player
	 * @param attackerTerritory Attacker Territory
	 * @param opponentTerritory Opponent Territory
	 * @param allOutMode boolean
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController
	 * @param game Game current game
	 */
	@Override
	public void reinforce(Player currentPlayer, Territory territory, GameInstructions gameInstructions, GameController controller) {
		if(currentPlayer.getCards() != null) {
			if(currentPlayer.getCards().size() >= 3) {
				int turnInNumArmies = controller.validateCards(currentPlayer);
				if(turnInNumArmies > 0) {
					logger.log(Level.INFO, currentPlayer.getName() + " has turned in cards and got " + turnInNumArmies + "armies");
					gameInstructions.setInstructions(currentPlayer.getName() + " has turned in cards and got " + turnInNumArmies + "armies");
				}
			}
		}
		int reinforcementArmy = controller.getNumReinforcements(currentPlayer);
		logger.log(Level.INFO, "Armies from reinforcements" + reinforcementArmy);
		int numArmiesFromContinents = controller.getNumArmiesFromContinents(currentPlayer);
		logger.log(Level.INFO, "Armies from Continents" + numArmiesFromContinents);
		int playerNumArmies = currentPlayer.getNumberOfArmies();
		reinforcementArmy = reinforcementArmy + numArmiesFromContinents + playerNumArmies;
		currentPlayer.setNumberOfArmies(reinforcementArmy);
		logger.log(Level.INFO, currentPlayer.getName() + " has " + reinforcementArmy + " reinforcement armies.");
		gameInstructions.setInstructions(currentPlayer.getName() + " has " + reinforcementArmy + " reinforcement armies.\n");
		Territory strongTerritory = controller.getStrongTerritory(currentPlayer);
		logger.log(Level.INFO, currentPlayer.getName() + " has strong territory " + strongTerritory.getName());
		controller.addArmyToTerritory(currentPlayer, strongTerritory, reinforcementArmy);
	}
	
	/**
	 * Aggressive player attack
	 * 
	 * @param currentPlayer player object
	 * @param gameInstructions object
	 * @param controller GameController
	 * @param game object
	 */
	@Override
	public void attack(Player currentPlayer, Territory attackerTerritory, Territory opponentTerritory, boolean allOutMode,
			GameInstructions gameInstructions, GameController controller) {
		Territory attackerTerr = controller.getAttacker(currentPlayer);
		Territory opponentTerr = controller.getOpponent(currentPlayer, attackerTerr);
		while(opponentTerr != null) {
			logger.log(Level.INFO, attackerTerr.getName() + " is attacking " + opponentTerr.getName());
			gameInstructions.setInstructions(attackerTerr.getName() + " is attacking " + opponentTerr.getName() + "\r\n");
			AttackStatus status = new AttackStatus();
			int numAttackerArmies, numOpponentArmies;
			while(attackerTerr.getNumberOfArmies() > 1 && !status.hasWon) {
				numAttackerArmies = controller.getNumAttackerArmies(attackerTerr);
				numOpponentArmies = controller.getNumOpponentArmies(opponentTerr, numAttackerArmies);
				status = controller.attack(attackerTerr, opponentTerr, numAttackerArmies, numOpponentArmies);
				gameInstructions.setInstructions(status.getStatusMessage().toString());
				controller.updateGame(attackerTerr, opponentTerr);
				//game.update();
			}
			if(attackerTerr.getNumberOfArmies() == 1) {
				opponentTerr = null;
			}else {
				opponentTerr = controller.getOpponent(currentPlayer, attackerTerr);
			}
			if(status.hasWon) {
				currentPlayer.hasWon = true;
			}
		}
	}
	
	/**
	 * Aggressive player fortify
	 * 
	 * @param currentPlayer Player
	 * @param fromTerritory Territory from
	 * @param toTerritory Territory To
	 * @param fortifyNum int fortification number
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController 
	 */
	@Override
	public void fortify(Player currentPlayer, Territory fromTerritory, Territory toTerritory, int fortifyNum, GameInstructions gameInstructions, 
			GameController controller) {
		int totalNumTerritories = currentPlayer.getOwnedTerritories().size();
		if(totalNumTerritories > 1) {
			Territory territoryFrom = controller.getSafeTerritory(currentPlayer);
			if(territoryFrom != null) {
				fortifyNum = territoryFrom.getNumberOfArmies() - 1;
				Territory territoryTo = controller.getWeakTerritory(currentPlayer);
				int fromTerrNumArmies = territoryFrom.getNumberOfArmies();
				int toTerrNumArmies = territoryTo.getNumberOfArmies();
				fromTerrNumArmies = fromTerrNumArmies - fortifyNum;
				toTerrNumArmies = toTerrNumArmies + fortifyNum;
				territoryFrom.setNumberOfArmies(fromTerrNumArmies);
				territoryTo.setNumberOfArmies(toTerrNumArmies);
				currentPlayer.setFortificationStatus(true);
				gameInstructions.setInstructions(currentPlayer.getName() + " has fortified " + territoryTo.getName() + " from " + territoryFrom.getName() + " with " + fortifyNum + " armies");
			}
		}
		if(currentPlayer.hasWon) {
			controller.addCard(currentPlayer);
			currentPlayer.hasWon = false;
		}
	}

}
