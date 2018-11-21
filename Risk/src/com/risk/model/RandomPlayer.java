package com.risk.model;

import java.io.Serializable;

import com.risk.controller.GameController;

public class RandomPlayer implements Strategy, Serializable {

	@Override
	public void reinforce(Player currentPlayer, GameInstructions gameInstructions, GameController controller) {
		if(currentPlayer.getCards() != null) {
			if(currentPlayer.getCards().size() >= 5) {
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
		while(currentPlayer.getNumberOfArmies() > 0) {
			controller.addArmyRandom(currentPlayer);
		}
	}

	@Override
	public void attack(Player currentPlayer, GameInstructions gameInstructions, GameController controller, Game game) {
		Territory attackerTerr = controller.getRandomAttacker(currentPlayer);
		Territory opponentTerr = controller.getOpponent(currentPlayer, attackerTerr);
		int numAttacks = controller.getRandomNumber(10);
		int count = 0;
		gameInstructions.setInstructions(attackerTerr.getName() + " is attacking " + opponentTerr.getName() + " " + numAttacks +" times\r\n");
		AttackStatus status = new AttackStatus();
		int numAttackerArmies, numOpponentArmies;
		while(attackerTerr.getNumberOfArmies() > 1 && !status.hasWon && numAttacks != count) {
			numAttackerArmies = controller.getNumAttackerArmies(attackerTerr);
			numOpponentArmies = controller.getNumOpponentArmies(opponentTerr, numAttackerArmies);
			status = controller.attack(attackerTerr, opponentTerr, numAttackerArmies, numOpponentArmies);
			gameInstructions.setInstructions(status.getStatusMessage().toString());
			controller.updateGame(attackerTerr, opponentTerr);
			game.update();
			count++;
		}
		if(status.hasWon) {
			currentPlayer.hasWon = true;
		}
	}

	@Override
	public void fortify(Player currentPlayer, GameInstructions gameInstructions, GameController controller) {
		int totalNumTerritories = currentPlayer.getOwnedTerritories().size();
		if(totalNumTerritories > 1) {
			Territory territoryFrom = currentPlayer.getOwnedTerritories().get(controller.getRandomNumber(totalNumTerritories));
			while(territoryFrom.getNumberOfArmies() <= 1) {
				territoryFrom = currentPlayer.getOwnedTerritories().get(controller.getRandomNumber(totalNumTerritories));
			}
			if(territoryFrom != null) {
				int fortifyNum = territoryFrom.getNumberOfArmies() - 1;
				Territory territoryTo = currentPlayer.getOwnedTerritories().get(controller.getRandomNumber(totalNumTerritories));
				while(territoryFrom == territoryTo) {
					territoryTo = currentPlayer.getOwnedTerritories().get(controller.getRandomNumber(totalNumTerritories));
				}
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
