package com.risk.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import com.risk.controller.GameController;
import com.risk.model.Player;
import com.risk.model.Territory;

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

    // =====================getPlayersArmies()=====================
    /**
     * Testing getPlayersArmies function return value for 2 players
     */
    @Test
    @DisplayName("getPlayersArmies => 2 for 40")
    void getPlayersArmies2() {
        assertEquals(40, gc.getPlayersArmies(2));
    }

    /**
     * Testing getPlayersArmies function return value for 4 players
     */
    @Test
    @DisplayName("getPlayersArmies => 4 for 30")
    void getPlayersArmies4() {
        assertEquals(30, gc.getPlayersArmies(4));
    }

    /**
     * Testing getPlayersArmies function return value for 6 players
     */
    @Test
    @DisplayName("getPlayersArmies => 6 for 20")
    void getPlayersArmies6() {
        assertEquals(20, gc.getPlayersArmies(6));
    }

    // =====================getNumReinforcements()=====================
    /**
     * Testing getNumReinforcements function return value for less than 3 Territories
     */
    @Test
    @DisplayName("getNumReinforcements => less than 3 Territories should equals 3")
    void getNumReinforcements3() {
        Player p = new Player("testPlayer", false, 10);
        ArrayList<Territory> t = new ArrayList<Territory>();
        Territory temp;
        for (int i = 0; i < 3; i++) {
            temp = new Territory("testTerritory" + i, "testContinent" + i, i);
            t.add(temp);
        }
        p.ownedTerritories = t;

        assertEquals(3, gc.getNumReinforcements(p));
    }

    /**
     * Testing getNumReinforcements function return value for 17 Territories
     */
    @Test
    @DisplayName("getNumReinforcements => for 17 Territories should equals 4")
    void getNumReinforcements17() {
        Player p = new Player("testPlayer", false, 10);
        ArrayList<Territory> t = new ArrayList<Territory>();
        Territory temp;
        for (int i = 0; i < 17; i++) {
            temp = new Territory("testTerritory" + i, "testContinent" + i, i);
            t.add(temp);
        }
        p.ownedTerritories = t;

        assertEquals(4, gc.getNumReinforcements(p));
    }

    @Test
    @DisplayName("territoriesToPlayers => Sum of players.ownedTerritories Should = territories.size()")
    void territoriesToPlayers() {
        //Creating 5 Test Players
        ArrayList<Player> playerList = new ArrayList<Player>();
        Player p;
        for (int j = 0; j < 5; j++) {
            p = new Player("testPlayer"+j, false, 10+j);
            playerList.add(p);
        }
        
        //Creating 20 Test Territories
        ArrayList<Territory> territories = new ArrayList<Territory>();
        Territory temp;
        for (int i = 0; i < 20; i++) {
            temp = new Territory("testTerritory" + i, "testContinent" + i, i);
            territories.add(temp);
        }

        ArrayList<Player> result = gc.territoriesToPlayers(playerList,territories);
        int finalAssignedTerritories = 0;
        for (Player pl : result){
            finalAssignedTerritories+=pl.ownedTerritories.size();
        }

        assertEquals(territories.size(), finalAssignedTerritories);
    }

}