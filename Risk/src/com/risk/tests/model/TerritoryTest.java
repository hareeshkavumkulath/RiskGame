package com.risk.tests.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.risk.model.Territory;

/**
 * TerritoryTest
 * 
 * This Class will run tests on model/Territory
 * 
 * @author Hamid
 * @version 1.0
 */

public class TerritoryTest{

	/**
     * Testing Territory(....) [Constructor for the Model];
     * <code>Territory("testTerritory", "testContinent", 10)</code>
     * 
     * Should set the Right values for:
     * <ul>
     * <li>territory.name = testTerritory </li>
     * <li>territory.continent = testContinent </li>
     * <li>territory.numberOfArmies = 10 </li>
     * </ul>
     */
    @Test
    @DisplayName("Territory Object => Right Values for Class Members")
    void territoryObjectTest() {
        
    	Territory territory = new Territory("testTerritory", "testContinent", 10);

        assertEquals(territory.getName(),"testTerritory");
        assertEquals(territory.getContinent(),"testContinent");
        assertEquals(territory.getNumberOfArmies(), 10);
    }

}