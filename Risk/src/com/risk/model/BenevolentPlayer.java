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

	/**
	 * BenevolentPlayer player reinforce
	 * 
	 * @param currentPlayer player object
	 * @param gameInstructions object
	 * @param controller GameController
	 */
	@Override
	public void reinforce(Player currentPlayer, GameInstructions gameInstructions, GameController controller) {
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
	 * @param currentPlayer player object
	 * @param gameInstructions object
	 * @param controller GameController
	 * @param game object
	 */
	
	@Override
	public void attack(Player currentPlayer, GameInstructions gameInstructions, GameController controller, Game game) {
		gameInstructions.setInstructions(currentPlayer.getName() + " is not attacking any other territory.\n");	
	}
	
	/**
	 * BenevolentPlayer player fortify
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
	}

}
