package com.risk.tests.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import com.risk.model.Player;
import com.risk.model.Territory;
import com.risk.model.Game;
import com.risk.model.Map;
import com.risk.model.Continent;

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
 // =====================compareDices()=====================
    /**
     * Testing compareDices() function return value
     */
    @Test
    @DisplayName("compareDices() => TRUE test case")
    void compareDicesTRUETest() {
    		
    		
    		assertTrue(Player.compareDices(3, 2));
    		assertTrue(Player.compareDices(2, 1));
    		
    }
    /**
     * Testing compareDices() function return value
     */
    @Test
    @DisplayName("compareDices() => FALSE test case")
    void compareDicesFALSETest() {
    		
    		
    		assertFalse(Player.compareDices(2, 3));
    		assertFalse(Player.compareDices(1, 2));
    		
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
 // =====================attack()=====================
    /**
     * Testing attack() function return value
     * attack territory t1 to territory t2
     * t1.armies = 4, Ruller = Player1
     * t2.armies = 2, Ruller = Player2
     */
    @Test
    @DisplayName("attack()=> Normal Run => No Error")
    void attackTRUETest() {
    		Player player = new Player("testPlayer", true, 10,"REINFORCEMENT");
    		Player player2 = new Player("testPlayer", true, 10,"REINFORCEMENT");
    		Territory t1= new Territory("testTerritory", "testContinent", 4);
    		t1.setRuler(player);
    		Territory t2= new Territory("testTerritory", "testContinent", 2);
    		t2.setRuler(player2);
    		
    		assertFalse(player.attack(t1, t2,2,1).hasWon);
    		
    		
    }
    // =====================reinforce()=====================
    /**
     * Testing reinforce() function return value
     */
    @Test
    @DisplayName("reinforce()")
    void reinforceTest() {
    		
    		
    		
    		
    		Player player = new Player("testPlayer", true, 10,"REINFORCEMENT");
    		Player player2 = new Player("testPlayer2", true, 10,"REINFORCEMENT");
    		ArrayList<Player> pArray= new  ArrayList<Player>();
    		pArray.add(player);pArray.add(player2);
    		
    		Territory t1= new Territory("testTerritory", "testContinent1", 4);
    		Territory t2= new Territory("testTerritory", "testContinent1", 4);
    		Territory t3= new Territory("testTerritory", "testContinent2", 4);
    		Territory t4= new Territory("testTerritory", "testContinent2", 4);
    		t1.setRuler(player);t2.setRuler(player);t3.setRuler(player);t4.setRuler(player);
    		
    		Territory t5= new Territory("testTerritory", "testContinent1", 2);
    		Territory t6= new Territory("testTerritory", "testContinent", 2);
    		t5.setRuler(player2);t6.setRuler(player2);
    		
    		ArrayList<Territory> TerArray= new  ArrayList<Territory>();
    		TerArray.add(t1);TerArray.add(t2);TerArray.add(t3);TerArray.add(t4);
    		TerArray.add(t5);TerArray.add(t6);
    		
    		Continent continent1 = new Continent("testContinent1",3);
    		Continent continent2 = new Continent("testContinent2",3);
    		ArrayList<Continent> ConArray= new  ArrayList<Continent>();
    		ConArray.add(continent1);ConArray.add(continent2);
    		
    		Map map = new Map();
    		map.setContinents(ConArray);
    		map.setTerritories(TerArray);
    		Game game = new Game(map);
    		game.setPlayers(pArray);
    		game.setCurrentPlayer(player);
    		
    		assertEquals("testPlayer",player.reinforce(game).name);
    		
    		
    }

}