package com.risk.tests.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

	
    @Test
    @DisplayName("Territory(...) => Right Values for Class Members")
    void territoryObjectTest() {
        
    	Territory territory = new Territory("testTerritory", "testContinent", 10);

        assertEquals(territory.getName(),"testTerritory");
        assertEquals(territory.getContinent(),"testContinent");
        assertEquals(territory.getNumberOfArmies(), 10);
    }

}