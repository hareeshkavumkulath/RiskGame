package com.risk.tests.view;

import org.junit.jupiter.api.Test;
import com.risk.controller.GameController;
import com.risk.model.Game;
import com.risk.model.GameInstructions;
import com.risk.view.GameWindow;
import com.risk.view.SelectGameWindow;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.File;

/**
 * Tests class for the GameWindow functions
 * 
 * @author Anqi Wang
 * @version 1.0
 *
 */
public class GameWindowTest {

	// =====================saveToFile()=====================
    /**
     * Testing saveToFile function return value for Valid Game Status
     * FOR ForTestClass.game file:
     * Number Of Continents equals 4
     * NUmber of Territories equal 58
     * Current Player.name Equal to Player1
     * 
     */
    @Test
    @DisplayName("saveToFileTest() => TRUE for Save Process")
    void saveToFileTest() {
    	
    	SelectGameWindow selectGameWindow = new SelectGameWindow();
    	File file = new File(".\\Games\\" + "ForTestClass.game");
    	Game savedGame = selectGameWindow.loadGameFromFile(file);
  
    	assertEquals(7,savedGame.map.getContinents().size());
    	assertEquals(58,savedGame.map.getTerritories().size());
    	assertEquals("Player1",savedGame.currentPlayer.name);
    	
    	// Adding 1 to Washington's Number of Armies
    	int num = savedGame.map.getTerritories().get(3).getNumberOfArmies();
    	num++;
    	savedGame.map.getTerritories().get(3).setNumberOfArmies(num);
    	
    	//Using new data => creates and loads GameWindow
    	GameController gameController = new GameController(savedGame,
				new GameInstructions("Risk Game\r\n"));

		GameWindow gameWindow = new GameWindow(savedGame, gameController);
		
    	//The Save Process should be successful
		assertTrue(gameWindow.saveToFile("ForTestClass.game"));
  
		// Loading the newly saved File AGAIN
		
		file = new File(".\\Games\\" + "ForTestClass.game");
		Game savedGame2 = selectGameWindow.loadGameFromFile(file);
  
    	assertEquals(7,savedGame2.map.getContinents().size());
    	assertEquals(58,savedGame2.map.getTerritories().size());
    	
    	//Should be Equal to => savedGame.map.getTerritories().get(3).getNumberOfArmies() +1
    	int newArmies = savedGame.map.getTerritories().get(3).getNumberOfArmies();
    	assertEquals(newArmies,savedGame2.map.getTerritories().get(3).getNumberOfArmies());
    	
    	assertEquals("Player1",savedGame2.currentPlayer.name);
		
   
    }

	
}
