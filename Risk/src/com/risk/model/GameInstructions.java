package com.risk.model;

import java.util.Observable;
/**
 * Class GameInstructions an observable class for the player to view the game instructions and logs
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 */
public class GameInstructions extends Observable{
	
	/**
	 * Instructions and logs of the game
	 */
	public String instructions;

	/**
	 * Constructor
	 * 
	 * @param instructions instructions to players or log for the player inputs 
	 */
	public GameInstructions(String instructions) {
		super();
		this.instructions = instructions;
	}

	/**
	 * Get instructions
	 * 
	 * @return String instructions
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * Set instructions
	 * 
	 * @param instructions instructions
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
		setChanged();
		notifyObservers(instructions);
	}	

}
