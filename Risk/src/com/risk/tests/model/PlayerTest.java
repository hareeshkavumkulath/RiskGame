package com.risk.tests.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.risk.model.Player;
import com.risk.model.Territory;

/**
 * PlayerTest
 * 
 * This Class will run tests on model/Player
 * 
 * @author Hamid
 * @version 1.0
 */

public class PlayerTest{

    /**
     * Testing Player(....) [Constructor for the Model];
     * <code>Player("testPlayer", true, 10)</code>
     * 
     * Should set the Right values for:
     * <li>player.name => testPlayer </li>
     * <li>player.isComputer => true </li>
     * <li>player.numberOfArmies => 10 </li>
     */
    @Test
    @DisplayName("Player Object => Right Values for Class Members")
    void playerObjectTest() {
        
        Player player = new Player("testPlayer", true, 10,"REINFORCEMENT");

        assertEquals(player.name,"testPlayer");
        assertTrue(player.isComputer);
        assertEquals(player.numberOfArmies, 10);
    }
 // =====================compareDices()=====================
    /**
     * Testing compareDices() function return value
     */
    @Test
    @DisplayName("compareDices() => TRUE test case")
    void compareDicesTRUETest() {
    		
    		Player player = new Player("testPlayer", true, 10,"REINFORCEMENT");
    		assertTrue(player.compareDices(3, 2));
    		assertTrue(player.compareDices(2, 1));
    		
    }
    /**
     * Testing compareDices() function return value
     */
    @Test
    @DisplayName("compareDices() => FALSE test case")
    void compareDicesFALSETest() {
    		
    		Player player = new Player("testPlayer", true, 10,"REINFORCEMENT");
    		assertTrue(player.compareDices(2, 3));
    		assertTrue(player.compareDices(1, 2));
    		
    }
 // =====================fortify()=====================
    /**
     * Testing fortify() function return value
     * moving 2 armies from territory t1 to territory t2
     * t1.armies = 4
     * t2.armies = 2
     */
    @Test
    @DisplayName("fortify() => should return TRUE")
    void fortifyTRUETest() {
    		
    		Player player = new Player("testPlayer", true, 10,"REINFORCEMENT");
    		Territory t1= new Territory("testTerritory", "testContinent", 4);
    		Territory t2= new Territory("testTerritory", "testContinent", 2);
    		
    		assertTrue(player.fortify(t1, t2,2));
    		
    		
    }

}