package com.risk.model;

import java.io.Serializable;

import com.risk.controller.GameController;

/**
 * BenevolentPlayer class
 * 
 * @author Hamid
 * @version 1.0
 *
 */
public class BenevolentPlayer implements Strategy, Serializable {

	
	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = 1260591218179482532L;

	/**
	 * BenevolentPlayer player reinforce
	 * 
	 * @param currentPlayer Player
	 * @param territory reinforcement Territory
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController
	 */
	@Override
	public void reinforce(Player currentPlayer, Territory territory, GameInstructions gameInstructions, GameController controller) {
		int reinforcementArmy = controller.getNumReinforcements(currentPlayer);
		int numArmiesFromContinents = controller.getNumArmiesFromContinents(currentPlayer);
		int playerNumArmies = currentPlayer.getNumberOfArmies();
		reinforcementArmy = reinforcementArmy + numArmiesFromContinents + playerNumArmies;
		currentPlayer.setNumberOfArmies(reinforcementArmy);
		gameInstructions.setInstructions(currentPlayer.getName() + " has " + reinforcementArmy + " reinforcement armies.\n");
		while(currentPlayer.getNumberOfArmies() > 0) {
			Territory weakTerritory = controller.getWeakTerritory(currentPlayer);
			controller.addArmyToTerritory(currentPlayer, weakTerritory, 1);
		}
	}

	/**
	 * BenevolentPlayer player attack
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
	public void attack(Player currentPlayer, Territory attackerTerritory, Territory opponentTerritory,
			boolean allOutMode, GameInstructions gameInstructions, GameController controller) {
		gameInstructions.setInstructions(currentPlayer.getName() + " is not attacking any other territory.\n");	
	}
	
	/**
	 * BenevolentPlayer player fortify
	 * 
	 * @param currentPlayer Player
	 * @param fromTerritory Territory from
	 * @param toTerritory Territory To
	 * @param fortifyNum int fortification number
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController 
	 */
	@Override
	public void fortify(Player currentPlayer, Territory fromTerritory, Territory toTerritory, int fortifyNum, GameInstructions gameInstructions, GameController controller) {
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
	}

}
