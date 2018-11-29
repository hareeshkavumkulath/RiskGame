package com.risk.tests.view;

/**
 * SelectGameWindowTests
 * 
 * This Class will test the  SelectGameWindow functionalities
 * 
 * @author Hamid
 * @version 1.0
 */

import org.junit.jupiter.api.Test;

import com.risk.model.Game;
import com.risk.view.SelectGameWindow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import java.io.File;
import static org.junit.Assert.assertEquals;


/**
 * Tests class for the SelectGameWindow functions
 * 
 * @author Anqi Wang
 * @version 1.0
 * 
 */
public class SelectGameWindowTest {

	@SuppressWarnings("javadoc")
    private  SelectGameWindow selectGameWindow;
	

    /**
     * Before Each Test it Creates a new object from Map Controller 
     */
    @BeforeEach
    void init() {
    	selectGameWindow = new SelectGameWindow();
    }
    
    // =====================loadGameFromFile()=====================
    /**
     * Testing loadGameFromFile function return value for Valid Saved File
     * FOR Test.game file:
     * Number Of Continents should equals 4
     * NUmber of Territories should equal 58
     * Current Player.name should Equal to Player1
     * 
     */
    @Test
    @DisplayName("loadGameFromFile() => TRUE for Existing GameFile")
    void loadGameFromFileValid() {
    	File file = new File(".\\Games\\" + "ForTestClass.game");
    	Game savedGame = selectGameWindow.loadGameFromFile(file);
    	
    	assertEquals(7,savedGame.map.getContinents().size());
    	assertEquals(58,savedGame.map.getTerritories().size());
    	assertEquals("Player1",savedGame.currentPlayer.name);
   
    }
    
    /**
     * Testing loadGameFromFile function return value for InValid SavedGame
     * For NotValid.game file should RETURN NULL
     */
    @Test
    @DisplayName("loadGameFromFile() => NULL for Non Existing GameFile")
    void loadGameFromFileInValid() {
    	File file = new File(".\\Games\\" + "NotValid.game");
    	Game savedGame = selectGameWindow.loadGameFromFile(file);
    	
    	assertEquals(null,savedGame);
   
    }
 
}
