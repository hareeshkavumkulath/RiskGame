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
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController
	 */
	public void reinforce(Player currentPlayer, GameInstructions gameInstructions, GameController controller);
	
	/**
	 * Strategy pattern function for attack
	 * 
	 * @param currentPlayer Player
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController
	 * @param game Game current game
	 */
	public void attack(Player currentPlayer, GameInstructions gameInstructions, GameController controller, Game game);
	
	/**
	 * Strategy pattern function for fortify
	 * 
	 * @param currentPlayer Player
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController 
	 */
	public void fortify(Player currentPlayer, GameInstructions gameInstructions, GameController controller);

}
