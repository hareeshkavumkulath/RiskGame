/**
 * 
 */
package com.risk.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.risk.controller.GameController;
import com.risk.view.StartWindow;

/**
 * Strategy class for Cheater Player
 * 
 * @author Angeline Anqi Wang
 *
 */
public class CheaterPlayer implements Strategy, Serializable  {
	
	/**
	 * Logger object setup for the log file
	 */
	static Logger logger = Logger.getLogger(StartWindow.class.getName());

	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = 1260591218189482532L;
	
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
		int reinforcementArmy = controller.calculateReinforcementArmiesForCheater(currentPlayer);
		currentPlayer.setNumberOfArmies(reinforcementArmy);
		gameInstructions.setInstructions(currentPlayer.getName() + " has " + reinforcementArmy + " reinforcement armies.\n");
		logger.log(Level.INFO, currentPlayer.getName() + " has " + reinforcementArmy + " reinforcement armies.");
		for(int i=0;i<currentPlayer.getOwnedTerritories().size();i++) {
			controller.addArmyToTerritory(currentPlayer, currentPlayer.getOwnedTerritories().get(i), currentPlayer.getOwnedTerritories().get(i).getNumberOfArmies());
		}
	}

	/**
	 * Cheater player attack
	 * 
	 * @param currentPlayer player object
	 * @param gameInstructions object
	 * @param controller GameController
	 * @param game object
	 */
	@Override
	public void attack(Player currentPlayer, Territory attackerTerritory, Territory opponentTerritory,
			boolean allOutMode, GameInstructions gameInstructions, GameController controller) {
		ArrayList<Territory> territories = currentPlayer.getOwnedTerritories();
		for(int i=0;i<territories.size();i++) {
			Territory attackerTerr = territories.get(i);
			for(int j=0;j<attackerTerr.getAdjacentTerritories().size();j++) {
				Territory opponentTerr = attackerTerr.getAdjacentTerritories().get(j);
				if(opponentTerr.getRuler() != currentPlayer) {
					gameInstructions.setInstructions(currentPlayer.getName() + " has conquered " + opponentTerr.getName() +"\n");
					logger.log(Level.INFO, currentPlayer.getName() + " has conquered " + opponentTerr.getName());
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

	/**
	 * Cheater player fortify
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
		for(int i=0;i<currentPlayer.getOwnedTerritories().size();i++) {
			Territory territory = currentPlayer.getOwnedTerritories().get(i);
			Territory opponentTerr = controller.getOpponent(currentPlayer, territory);
			if(opponentTerr != null) {
				int numArmy = territory.getNumberOfArmies();
				territory.setNumberOfArmies(numArmy*2);
				logger.log(Level.INFO, currentPlayer.getName() + " has fortified " + territory.getName() + " with " + numArmy + " armies");
				gameInstructions.setInstructions(currentPlayer.getName() + " has fortified " + territory.getName() + " with " + numArmy + " armies\n");
			}
		}
	}

}
