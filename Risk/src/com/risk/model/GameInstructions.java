package com.risk.model;

import java.io.Serializable;
import java.util.Observable;
/**
 * Class GameInstructions an observable class for the player to view the game instructions and logs
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 */
public class GameInstructions extends Observable implements Serializable{
	
	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = -7087833662915982490L;
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

	/**
	 * Append Instructions will append messages during assign territories
	 * 
	 * @param instructions String gameInstructions 
	 */
	public void appendInstructions(String instructions) {
		this.instructions = this.instructions + instructions;
		setChanged();
		notifyObservers(instructions);
	}	

}
