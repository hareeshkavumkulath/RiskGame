package com.risk.tests.controller;
import com.risk.controller.MapController;
import com.risk.model.MapMessage;
import com.risk.model.Territory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

/**
 * MapControllerTests
 * 
 * This Class will run tests on MapController.class
 * 
 * @author Hamid
 * @version 1.0
 */

public class MapControllerTests {
	@SuppressWarnings("javadoc")
    private  static String mapFolder;
	@SuppressWarnings("javadoc")
    private  static String valid;
	@SuppressWarnings("javadoc")
    private  static String valid2;
	@SuppressWarnings("javadoc")
    private  static String noTag;
	@SuppressWarnings("javadoc")
    private  static String noTerritories;
	@SuppressWarnings("javadoc")
    private  static String noContinent;
	@SuppressWarnings("javadoc")
    private  static String notConnected;
	@SuppressWarnings("javadoc")
    private  MapController mapController;

    /**
     * Before All sets the local address for Valid and Invalid maps 
     */
    @BeforeAll
    static void initAll() {
        mapFolder = System.getProperty("user.dir") + "/src/com/risk/tests/controller/maps/";
        valid = mapFolder + "valid.map";
        valid2 = mapFolder + "valid2.map";
        noTag = mapFolder + "no_tag.map";
        noTerritories = mapFolder + "no_territories.map";
        notConnected = mapFolder + "not_connected.map";
        noContinent = mapFolder + "no_continent.map";
    }

    /**
     * Before Each Test it Creates a new object from Map Controller 
     */
    @BeforeEach
    void init() {
        mapController = new MapController();
    }
 // =====================processContinent()=====================
    /**
     * Testing processContinent function return value for Valid Map
     */
    @Test
    @DisplayName("Process Continent => TRUE for Valid MAP")
    void processContinentValid() {
        assertTrue(mapController.processContinents(mapToString(valid)));
    }

    /**
     * Testing processContinent function return value for inValid Map
     * which is a map with no [territories] tag
     */
    @Test
    @DisplayName("Process Continent => FALSE for noTag MAP")
    void processContinentInValid() {
        assertFalse(mapController.processContinents(mapToString(noTag)));
    }
 // =====================processTerritories()=====================
    /**
     * Testing processTerritories function return value for Valid Map
     */
    @Test
    @DisplayName("Process Territories => TRUE for Valid MAP")
    void proceeeTerritoriesValid() {
        mapController.processContinents(mapToString(valid));
        assertTrue(mapController.processTerritories(mapToString(valid)));
    }

    /**
     * Testing processTerritories function return value for InValid Map
     * Which is map with no territories
     */
    
    @Test
    @DisplayName("Process Territories => FALSE for noTerritories MAP")
    void proceeeTerritoriesInValid() {
        mapController.processTerritories(mapToString(noTerritories));
        assertFalse(mapController.processTerritories(mapToString(noTerritories)));
    }
    // =====================territoriesToContinents()=====================
    /**
     * Testing territoriesToContinents function return value for Valid Map
     */
    @Test
    @DisplayName("continentArray => NotNULL for Valid MAP")
    void territoriesToContinentsValid() {
        mapController.processContinents(mapToString(valid));
        mapController.processTerritories(mapToString(valid));
        mapController.territoriesToContinents();
        assertNotNull(mapController.continentArray);
    }
    
    /**
     * Testing territoriesToContinents function return value for InValid Map
     * Which is a map with no Continent
     */
    @Test
    @DisplayName("continentArray => NULL for noContinent MAP")
    void territoriesToContinentsInValid() {
        mapController.processContinents(mapToString(noContinent));
        mapController.processTerritories(mapToString(noContinent));
        mapController.territoriesToContinents();
        assertNotNull(mapController.continentArray);
    }
    // =====================validateMapValid()=====================
    /**
     * Testing validateMap function return value for Valid Map
     */
    @Test
    @DisplayName("validateMap => TRUE for Valid MAP")
    void validateMapValid() {
        mapController.processContinents(mapToString(valid));
        mapController.processTerritories(mapToString(valid));
        mapController.territoriesToContinents();
        MapMessage anything = mapController.processFile(new File(valid));
        assertTrue(mapController.validateMap(anything.getMap().getTerritories()));
    }

    /**
     * Testing validateMap function return value for InValid Map
     * Which is a not connected map
     */
    @Test
    @DisplayName("validateMap => FALSE for NotConnected MAP")
    void validateMapInValid() {
        mapController.processContinents(mapToString(notConnected));
        mapController.processTerritories(mapToString(notConnected));
        mapController.territoriesToContinents();
        MapMessage anything = mapController.processFile(new File(notConnected));
        assertFalse(mapController.validateMap(anything.getMap().getTerritories()));
    }
    // =====================addAdjacentTerritoriesTest()=====================
    /**
     * Testing addAdjacentTerritories function return value for InValid Map
     * Which is a not connected map
     */
    @Test
    @DisplayName("addAdjacentTerritories => TRUE for valid2 MAP")
    void addAdjacentTerritoriesTest() {
        
        ArrayList<Territory> terrList = new ArrayList<Territory>();
        Territory t1= new Territory("Alaska", "North America", 4);
        Territory t2= new Territory("Northwest Territory", "North America", 4);
        Territory t3= new Territory("Venezuala", "South America", 4);
        Territory t4= new Territory("Peru", "South America", 4);
        terrList.add(t1);terrList.add(t2);terrList.add(t3);terrList.add(t4);
        
        assertTrue(mapController.addAdjacentTerritories(mapToString(valid2),terrList));
    }
    
    // =====================mapToString()=====================
    
    /**
     * 
     * Function for reading a map file and convert that into String
     * 
     * @param mapFile Map File Address as a String
     * @return String Map Content to Strings
     */
    private static String mapToString(String mapFile) {
        String mapInString = "";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(mapFile));

            String line = reader.readLine();
            mapInString += line;

            while (line != null) {
                mapInString += line;
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            mapInString = null;
        }
        return mapInString;
    }

}