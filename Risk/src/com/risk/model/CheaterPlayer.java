/**
 * 
 */
package com.risk.model;

import com.risk.controller.GameController;

/**
 * Strategy class for Cheater Player
 * 
 * @author Angeline Anqi Wang
 *
 */
public class CheaterPlayer implements Strategy {

	@Override
	public void reinforce(Player currentPlayer, GameInstructions gameInstructions, GameController controller) {
		int reinforcementArmy = controller.calculateReinforcementArmiesForCheater(currentPlayer);
		currentPlayer.setNumberOfArmies(reinforcementArmy);
		gameInstructions.setInstructions(currentPlayer.getName() + " has " + reinforcementArmy + " reinforcement armies.\n");	
		for(int i=0;i<currentPlayer.getOwnedTerritories().size();i++) {
			controller.addArmyToTerritory(currentPlayer, currentPlayer.getOwnedTerritories().get(i), currentPlayer.getOwnedTerritories().get(i).getNumberOfArmies());
		}
	}

	@Override
	public void attack(Player currentPlayer, GameInstructions gameInstructions, GameController controller, Game game) {
		for(int i=0;i<currentPlayer.getOwnedTerritories().size();i++) {
			Territory attackerTerr = currentPlayer.getOwnedTerritories().get(i);
			for(int j=0;j<attackerTerr.getAdjacentTerritories().size();j++) {
				Territory opponentTerr = attackerTerr.getAdjacentTerritories().get(j);
				if(opponentTerr.getRuler() != currentPlayer) {
					gameInstructions.setInstructions(currentPlayer.getName() + " has conquered " + opponentTerr.getName() +"\n");
					Player opponentRuler = opponentTerr.getRuler();
					controller.updateTerritoryRuler(opponentTerr, attackerTerr.getRuler(), 1);
					controller.updateTerritoryRuler(attackerTerr, attackerTerr.getRuler(), attackerTerr.getNumberOfArmies());
					controller.updateAddPlayerList(opponentTerr, attackerTerr.getRuler());
					controller.updateRemovePlayerList(opponentTerr, opponentRuler);
					controller.updatePlayerList();
				}
			}
		}
	}

	@Override
	public void fortify(Player currentPlayer, GameInstructions gameInstructions, GameController controller) {
		for(int i=0;i<currentPlayer.getOwnedTerritories().size();i++) {
			Territory territory = currentPlayer.getOwnedTerritories().get(i);
			Territory opponentTerr = controller.getOpponent(currentPlayer, territory);
			if(opponentTerr != null) {
				int numArmy = territory.getNumberOfArmies();
				territory.setNumberOfArmies(numArmy*2);
				gameInstructions.setInstructions(currentPlayer.getName() + " has fortified " + territory.getName() + " with " + numArmy + " armies\n");
			}
		}
	}

}
