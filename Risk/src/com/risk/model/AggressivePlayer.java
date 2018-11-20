package com.risk.model;

import java.io.Serializable;

import com.risk.controller.GameController;

public class AggressivePlayer implements Strategy, Serializable {

	/**
	 * set a Serializable
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Aggressive player attack
	 * 
	 * @param currentPlayer player object
	 * @param gameInstructions object
	 * @param controller GameController
	 * @param game object
	 */
	@Override
	public void attack(Player currentPlayer, GameInstructions gameInstructions, GameController controller, Game game) {
		Territory attackerTerr = controller.getAttacker(currentPlayer);
		Territory opponentTerr = controller.getOpponent(currentPlayer, attackerTerr);
		while(opponentTerr != null) {
			gameInstructions.setInstructions(attackerTerr.getName() + " is attacking " + opponentTerr.getName() + "\r\n");
			AttackStatus status = new AttackStatus();
			int numAttackerArmies, numOpponentArmies;
			while(attackerTerr.getNumberOfArmies() > 1 && !status.hasWon) {
				numAttackerArmies = controller.getNumAttackerArmies(attackerTerr);
				numOpponentArmies = controller.getNumOpponentArmies(opponentTerr, numAttackerArmies);
				status = controller.attack(attackerTerr, opponentTerr, numAttackerArmies, numOpponentArmies);
				gameInstructions.setInstructions(status.getStatusMessage().toString());
				controller.updateGame(attackerTerr, opponentTerr);
				game.update();
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
	 * @param currentPlayer player object
	 * @param gameInstructions object
	 * @param controller GameController
	 */
	@Override
	public void fortify(Player currentPlayer, GameInstructions gameInstructions, GameController controller) {
		int totalNumTerritories = currentPlayer.getOwnedTerritories().size();
		if(totalNumTerritories > 1) {
			Territory territoryFrom = controller.getSafeTerritory(currentPlayer);
			if(territoryFrom != null) {
				int fortifyNum = territoryFrom.getNumberOfArmies() - 1;
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
	
	/**
	 * Aggressive player reinforce
	 * 
	 * @param currentPlayer player object
	 * @param gameInstructions object
	 * @param controller GameController
	 */
	@Override
	public void reinforce(Player currentPlayer, GameInstructions gameInstructions, GameController controller) {
		if(currentPlayer.getCards() != null) {
			if(currentPlayer.getCards().size() >= 3) {
				int turnInNumArmies = controller.validateCards(currentPlayer);
				if(turnInNumArmies > 0) {
					gameInstructions.setInstructions(currentPlayer.getName() + " has turned in cards");
				}
			}
		}
		int reinforcementArmy = controller.getNumReinforcements(currentPlayer);
		int numArmiesFromContinents = controller.getNumArmiesFromContinents(currentPlayer);
		int playerNumArmies = currentPlayer.getNumberOfArmies();
		reinforcementArmy = reinforcementArmy + numArmiesFromContinents + playerNumArmies;
		currentPlayer.setNumberOfArmies(reinforcementArmy);
		gameInstructions.setInstructions(currentPlayer.getName() + " has " + reinforcementArmy + " reinforcement armies.\n");
		Territory strongTerritory = controller.getStrongTerritory(currentPlayer);
		System.out.println(strongTerritory.getName());
		controller.addArmyToTerritory(currentPlayer, strongTerritory, reinforcementArmy);
	}

}
