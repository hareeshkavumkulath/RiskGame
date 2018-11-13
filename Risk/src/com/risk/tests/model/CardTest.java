package com.risk.tests.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;


import com.risk.model.Card;

/**
 * CardTest
 * 
 * This Class will run tests on model/Card
 * 
 * @author Hamid
 * @version 1.0
 */

public class CardTest{

    /**
     * Testing Card(....) [Constructor for the Model];
     * <code>Card("Infantry")</code>
     */
    @Test
    @DisplayName("Card => Right Values for Class Members")
    void CardObjectTest() {
        
        Card card = new Card("Infantry");

        assertEquals(card.armyType,"Infantry");
    }
 // =====================addArmy()=====================
    /**
     * Testing addArmy() function return value
     * addArmy() => 1 for infantry
     */
    @Test
    @DisplayName("addArmy() => 1 for infantry")
    void addArmyInfantry() {
    		Card card = new Card("Infantry");
    		assertEquals(card.armyType,"Infantry");
    		
    		card.addArmy("Infantry");
    		assertEquals(1,card.numArmies);
    		
    }
    /**
     * Testing addArmy() function return value
     * addArmy() => 5 for Cavalry
     */
    @Test
    @DisplayName("addArmy() => 5 for Cavalry")
    void addArmyCavalry() {
    		Card card = new Card("Cavalry");
    		assertEquals(card.armyType,"Cavalry");
    		
    		card.addArmy("Cavalry");
    		assertEquals(5,card.numArmies);
    		
    }
    /**
     * Testing addArmy() function return value
     * addArmy() => 5 for Artillery
     */
    @Test
    @DisplayName("addArmy() => 10 for Artillery")
    void addArmyArtillary() {
    		Card card = new Card("Artillery");
    		assertEquals(card.armyType,"Artillery");
    		
    		card.addArmy("Artillery");
    		assertEquals(10,card.numArmies);
    		
    }
}