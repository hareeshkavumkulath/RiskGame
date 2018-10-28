/**
 * 
 */
package com.risk.controller;

import com.risk.model.Map;
import com.risk.view.GameWindow;

/**
 * Main class which controls the game in phase by phase
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 *
 */
public class GameEngine {

	@SuppressWarnings("javadoc")
	private Map map;

	/**
	 * Constructor for GameEngine
	 * 
	 * @param map Map input from select map window
	 */
	public GameEngine(Map map) {
		this.map = map;
	}

	/**
	 * 
	 */
	public void start() {
		GameWindow gameWindow = new GameWindow(map);
		gameWindow.main();
	}
	
	
	
}
