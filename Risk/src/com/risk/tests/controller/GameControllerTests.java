package com.risk.tests.controller;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import com.risk.controller.GameController;
import com.risk.model.Player;
import com.risk.model.Territory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

    // =====================getPlayersArmies()=====================
    /**
     * Testing getPlayersArmies function return value for 2 players
     */
    @Test
    @DisplayName("getPlayersArmies => 2 for 40")
    void getPlayersArmies2() {
        assertEquals(40, gameController.getPlayersArmies(2));
    }

    /**
     * Testing getPlayersArmies function return value for 4 players
     */
    @Test
    @DisplayName("getPlayersArmies => 4 for 30")
    void getPlayersArmies4() {
        assertEquals(30, gameController.getPlayersArmies(4));
    }

    /**
     * Testing getPlayersArmies function return value for 6 players
     */
    @Test
    @DisplayName("getPlayersArmies => 6 for 20")
    void getPlayersArmies6() {
        assertEquals(20, gameController.getPlayersArmies(6));
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

        assertEquals(3, gameController.getNumReinforcements(p));
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

        assertEquals(4, gameController.getNumReinforcements(p));
    }

    // =====================territoriesToPlayers()=====================
    /**
     * Testing territoriesToPlayers function return value
     * For 5 test players and 10 test Territories,
     * The Sum of players.ownedTerritories in returned ArrayList should equal the number of territories
     */

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

        ArrayList<Player> result = gameController.territoriesToPlayers(playerList,territories);
        int finalAssignedTerritories = 0;
        for (Player pl : result){
            finalAssignedTerritories+=pl.ownedTerritories.size();
        }

        assertEquals(territories.size(), finalAssignedTerritories);
    }


     // =====================playersToTerritories()=====================
    /**
     * Testing playersToTerritories function return value
     * ruler for returned territory list should exist in Player List
     */
    @Test
    @DisplayName("playersToTerritories => ruler for returned territory list should exist in PlayerList")
    void playersToTerritories() {
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

        ArrayList<Player> result = gameController.territoriesToPlayers(playerList,territories);
        
        for (Territory ter : gameController.playersToTerritories(result,territories)){
            assertNotEquals(-1,result.indexOf(ter.getRuler()));
        }
    }

    // =====================playersToTerritories()=====================
    /**
     * Testing assignOneArmyToEachCountry function return value
     * Number of Assigned armies for  all territories returned territory list 
     * should be Equal to 1
     */
    @Test
    @DisplayName("assignOneArmyToEachCountry => Assigned armies for each returned territory list => = 1")
    void assignOneArmyToEachTerritory() {
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

        ArrayList<Player> result = gameController.territoriesToPlayers(playerList,territories);
        gameController.assignOneArmyToEachTerritory(result,territories);
        for (Player pll : result){
            for (Territory ter : pll.getOwnedTerritories()){
                assertEquals(1,ter.getNumberOfArmies());
            }
        }
    }

    // =====================isValidFortify()=====================
    /**
     * Testing isValidFortify() function return value
     * if (player.ownedTerritories() == 1)
     * should be Equal to false
     */
    @Test
    @DisplayName("For player.ownedTerritories() == 1 => False")
    void isValidFortify1() {
        Player p = new Player("testPlayer", false, 10);
        Territory temp= new Territory("testTerritory", "testContinent", 10);
        p.ownedTerritories.add(temp);

        assertTrue(gameController.isValidFortify(p));
    }

    /**
     * Testing isValidFortify() function return value
     * if (player.ownedTerritories() is greater than 1)
     * should be Equal to true
     */
    @Test
    @DisplayName("For player.ownedTerritories() > 1 => True")
    void isValidFortify10() {
        Player p = new Player("testPlayer", false, 10);
        Territory temp;
        for (int i=0;i<10;i++){
            temp= new Territory("testTerritory"+i, "testContinent", 10+i);
            p.ownedTerritories.add(temp);
        }

        assertTrue(gameController.isValidFortify(p));
    }

    // =====================validateFortifyMove()=====================
    /**
     * Testing validateFortifyMove() function return value
     * if (player.ownedTerritories[i].NumberOfArmies is greater than 1)
     * should be Equal to true
     */

    @Test
    @DisplayName("For player.ownedTerritories[i].NumberOfArmies > 1 => TRUE")
    void validateFortifyMove2() {
        Player p = new Player("testPlayer", false, 10);
        Territory temp= new Territory("testTerritory", "testContinent", 2);
        p.ownedTerritories.add(temp);

        assertTrue(gameController.validateFortifyMove(p,0));
    }

    /**
     * Testing validateFortifyMove() function return value
     * if (player.ownedTerritories[i].NumberOfArmies is less and equal to 1)
     * should be Equal to false
     */

    @Test
    @DisplayName("For player.ownedTerritories[i].NumberOfArmies <= 1 => False")
    void validateFortifyMove1() {
        Player p = new Player("testPlayer", false, 10);
        Territory temp= new Territory("testTerritory", "testContinent", 1);
        p.ownedTerritories.add(temp);

        assertTrue(gameController.validateFortifyMove(p,0));
    }
     // =====================validateFortifyNumber()=====================
    /**
     * Testing validateFortifyNumber() function return value
     * if (player.ownedTerritories[i].NumberOfArmies - 1 is greater than fortifyNum)
     * should be Equal to TRUE
     */
    @Test
    @DisplayName("player.ownedTerritories[i].NumberOfArmies - 1 is greater than fortifyNum => TRUE")
    void validateFortifyNumberGreaterThanFortifyNum(){
        Player p = new Player("testPlayer", false, 10);
        Territory temp= new Territory("testTerritory", "testContinent", 10);
        p.ownedTerritories.add(temp);

        assertTrue(gameController.validateFortifyNumber(p,0,3));
    }

    /**
     * Testing validateFortifyNumber() function return value
     * if (player.ownedTerritories[i].NumberOfArmies - 1 is less than fortifyNum)
     * should be Equal to FALSE
     */
    @Test
    @DisplayName("player.ownedTerritories[i].NumberOfArmies - 1 < fortifyNum => FALSE")
    void validateFortifyNumberLessThanFortifyNum(){
        Player p = new Player("testPlayer", false, 10);
        Territory temp= new Territory("testTerritory", "testContinent", 2);
        p.ownedTerritories.add(temp);

        assertTrue(gameController.validateFortifyNumber(p,0,3));
    }
    // =====================fortify()=====================
    /**
     * Testing fortify() function return value
     */
    @Test
    @DisplayName("fortifytest() should return => TRUE")
    void fortifytest(){

        Player p = new Player("testPlayer", false, 10);
        
        Territory temp= new Territory("testTerritory1", "testContinent", 10);
        p.ownedTerritories.add(temp);

        Territory temp1= new Territory("testTerritory2", "testContinent", 3);
        p.ownedTerritories.add(temp1);

        assertTrue(gameController.fortify(p,0,1,2));
    }

}