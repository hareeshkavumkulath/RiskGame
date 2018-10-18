package com.risk.tests;
import com.risk.controller.MapController;
import com.risk.model.MapMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.Transient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
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

    private  static String mapFolder;
    private  static String valid;
    private  static String noTag;
    private  static String noTerritories;
    private  static String noContinent;
    private  static String notConnected;
    private  MapController mc;

    /**
     * Before All sets the local address for Valid and Invalid maps 
     */
    @BeforeAll
    static void initAll() {
        mapFolder = System.getProperty("user.dir") + "/src/com/risk/tests/maps/";
        valid = mapFolder + "valid.map";
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
        mc = new MapController();
    }

    /**
     * Testing processContinent function return value for Valid Map
     */
    @Test
    @DisplayName("Process Continent => TRUE for Valid MAP")
    void processContinentValid() {
        assertTrue(mc.processContinents(mapToString(valid)));
    }

    /**
     * Testing processContinent function return value for inValid Map
     * which is a map with no [territories] tag
     */
    @Test
    @DisplayName("Process Continent => FALSE for noTag MAP")
    void processContinentInValid() {
        assertTrue(mc.processContinents(mapToString(noTag)));
    }

    /**
     * Testing processTerritories function return value for Valid Map
     */
    @Test
    @DisplayName("Process Territories => TRUE for Valid MAP")
    void proceeeTerritoriesValid() {
        mc.processContinents(mapToString(valid));
        assertTrue(mc.processTerritories(mapToString(valid)));
    }

    /**
     * Testing processTerritories function return value for InValid Map
     * Which is map with no territories
     */
    @Test
    @DisplayName("Process Territories => FALSE for noTerritories MAP")
    void proceeeTerritoriesInValid() {
        mc.processContinents(mapToString(noTerritories));
        assertTrue(mc.processTerritories(mapToString(noTerritories)));
    }

    /**
     * Testing territoriesToContinents function return value for Valid Map
     */
    @Test
    @DisplayName("continentArray => NotNULL for Valid MAP")
    void territoriesToContinentsValid() {
        mc.processContinents(mapToString(valid));
        mc.processTerritories(mapToString(valid));
        mc.territoriesToContinents();
        assertNotNull(mc.continentArray);
    }
    
    /**
     * Testing territoriesToContinents function return value for InValid Map
     * Which is a map with no Continent
     */
    @Test
    @DisplayName("continentArray => NULL for noContinent MAP")
    void territoriesToContinentsInValid() {
        mc.processContinents(mapToString(noContinent));
        mc.processTerritories(mapToString(noContinent));
        mc.territoriesToContinents();
        assertNotNull(mc.continentArray);
    }

    /**
     * Testing createContinentConnection function return value for Valid Map
     */
    @Test
    @DisplayName("createContinentConnection => TRUE for NotConnected MAP")
    void createContinentConnectionInValid() {
        mc.processContinents(mapToString(notConnected));
        mc.processTerritories(mapToString(notConnected));
        mc.territoriesToContinents();
        assertTrue(mc.createContinentConnection());
    }

    /**
     * Testing validateMap function return value for Valid Map
     */
    @Test
    @DisplayName("validateMap => TRUE for Valid MAP")
    void validateMapValid() {
        mc.processContinents(mapToString(valid));
        mc.processTerritories(mapToString(valid));
        mc.territoriesToContinents();
        mc.createContinentConnection();
        MapMessage anything = mc.processFile(new File(valid));
        assertTrue(mc.validateMap(anything.getTerritories()));
    }

    /**
     * Testing validateMap function return value for InValid Map
     * Which is a not connected map
     */
    @Test
    @DisplayName("validateMap => FALSE for NotConnected MAP")
    void validateMapInValid() {
        mc.processContinents(mapToString(notConnected));
        mc.processTerritories(mapToString(notConnected));
        mc.territoriesToContinents();
        mc.createContinentConnection();
        MapMessage anything = mc.processFile(new File(notConnected));
        assertTrue(mc.validateMap(anything.getTerritories()));
    }

    /**
     * 
     * Function for reading a map file and convert that into String
     * 
     * @param mapFile Map File Address as a String
     * @return Map Content to Strings
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