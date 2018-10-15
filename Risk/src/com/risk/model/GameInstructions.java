package com.risk.model;

import java.util.Observable;

public class GameInstructions extends Observable{
	
	public String instructions;

	public GameInstructions(String instructions) {
		super();
		this.instructions = instructions;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
		setChanged();
		notifyObservers(instructions);
	}	

}
