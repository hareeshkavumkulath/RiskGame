package com.risk.model;

import com.risk.controller.GameController;

/**
 * Strategy interface for Strategy Pattern for Players
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 *
 */
public interface Strategy {
	
	/**
	 * Strategy pattern function for reinforce
	 * 
	 * @param currentPlayer Player
	 * @param territory reinforcement Territory
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController
	 */
	public void reinforce(Player currentPlayer, Territory territory, GameInstructions gameInstructions, GameController controller);
	
	/**
	 * Strategy pattern function for attack
	 * 
	 * @param currentPlayer Player
	 * @param attackerTerritory Attacker Territory
	 * @param opponentTerritory Opponent Territory
	 * @param allOutMode boolean
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController
	 */
	public void attack(Player currentPlayer, Territory attackerTerritory, Territory opponentTerritory, boolean allOutMode, 
			GameInstructions gameInstructions, GameController controller);
	
	/**
	 * Strategy pattern function for fortify
	 * 
	 * @param currentPlayer Player
	 * @param fromTerritory Territory from
	 * @param toTerritory Territory To
	 * @param fortifyNum integer Fortify Number
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController 
	 */
	public void fortify(Player currentPlayer, Territory fromTerritory, Territory toTerritory, int fortifyNum, GameInstructions gameInstructions, 
			GameController controller);

}
