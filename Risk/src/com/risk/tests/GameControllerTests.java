package com.risk.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.risk.controller.GameController;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

/**
 * GameControllerTests
 * 
 * This Class will run tests on GameController.class
 * 
 * @author Hamid
 * @version 1.0
 */

class GameControllerTests{

    private GameController gc;

    /**
     * Before Each Test it Creates a new object from Game Controller 
     */
    @BeforeEach
    void init() {
        gc = new GameController();
    }

    /**
     * Testing getPlayersArmies function return value for 2 players
     */
    @Test
    @DisplayName("getPlayersArmies => 2 for 40")
    void getPlayersArmies2() {
        assertEquals(40,gc.getPlayersArmies(2));
    }
    
    /**
     * Testing getPlayersArmies function return value for 4 players
     */
    @Test
    @DisplayName("getPlayersArmies => 4 for 30")
    void getPlayersArmies4() {
        assertEquals(30,gc.getPlayersArmies(4));
    }
    

    /**
     * Testing getPlayersArmies function return value for more than 6 players
     */
    @Test
    @DisplayName("getPlayersArmies => Greater than 6 for 20")
    void getPlayersArmies6() {
        assertEquals(20,gc.getPlayersArmies(7));
    }

}