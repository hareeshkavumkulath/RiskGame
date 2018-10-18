package com.risk.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import com.risk.controller.GameController;
import com.risk.model.Player;
import com.risk.model.Territory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

/**
 * GameControllerTests
 * 
 * This Class will run tests on GameController.class
 * 
 * @author Hamid
 * @version 1.0
 */

class GameControllerTests{

    @SuppressWarnings("javadoc")
	private GameController gameController;

    /**
     * Before Each Test it Creates a new object from Game Controller 
     */
    @BeforeEach
    void init() {
    	gameController = new GameController();
    }

    /**
     * Testing getPlayersArmies function return value for 2 players
     */
    @Test
    @DisplayName("getPlayersArmies => 2 for 40")
    void getPlayersArmies2() {
        assertEquals(40,gameController.getPlayersArmies(2));
    }
    
    /**
     * Testing getPlayersArmies function return value for 4 players
     */
    @Test
    @DisplayName("getPlayersArmies => 4 for 30")
    void getPlayersArmies4() {
        assertEquals(30,gameController.getPlayersArmies(4));
    }

     /**
     * Testing getPlayersArmies function return value for 6 players
     */
    @Test
    @DisplayName("getPlayersArmies => 6 for 20")
    void getPlayersArmies6() {
        assertEquals(20,gameController.getPlayersArmies(4));
    }
    
    
    /**
     * Testing getPlayersArmies function return value for less than 3 Territories
     */
    @Test
    @DisplayName("getPlayersArmies => less than 3 Territories should equals 3")
    void getNumReinforcements3() {
    		Player p = new Player("testPlayer",false,10);
    		ArrayList<Territory> t = new ArrayList<Territory>();
    		Territory temp;
    		for (int i=0;i<3;i++)
    		{
    			temp = new Territory("testTerritory"+i,"testContinent"+i,i);
    			t.add(temp);
    		}
    		p.ownedTerritories = t;
        
    		assertEquals(3,gameController.getNumReinforcements(p));
    }

   /**
     * Testing getPlayersArmies function return value for 17 Territories
     */
    @Test
    @DisplayName("getPlayersArmies => for 17 Territories should equals 4")
    void getNumReinforcements17() {
    		Player p = new Player("testPlayer",false,10);
    		ArrayList<Territory> t = new ArrayList<Territory>();
    		Territory temp;
    		for (int i=0;i<17;i++)
    		{
    			temp = new Territory("testTerritory"+i,"testContinent"+i,i);
    			t.add(temp);
    		}
    		p.ownedTerritories = t;
        
    		assertEquals(4,gameController.getNumReinforcements(p));
    }

}