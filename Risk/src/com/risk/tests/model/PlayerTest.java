package com.risk.tests.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.risk.model.Player;

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
     * <ul>
     * <li>player.name = testPlayer </li>
     * <li>player.isComputer = true </li>
     * <li>player.numberOfArmies = 10 </li>
     * </ul>
     */
    @Test
    @DisplayName("Player Object => Right Values for Class Members")
    void playerObjectTest() {
        
        Player player = new Player("testPlayer", true, 10,"REINFORCEMENT");

        assertEquals(player.name,"testPlayer");
        assertTrue(player.isComputer);
        assertEquals(player.numberOfArmies, 10);
    }

}