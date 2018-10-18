package com.risk.tests;
import com.risk.controller.MapController;
import com.risk.model.MapMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    private  static String mapFolder;
    private  static String valid;
    private  static String noTag;
    private  static String noTerritories;
    private  static String noContinent;
    private  static String notConnected;
    private  MapController map_controller;

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
        map_controller = new MapController();
    }

    /**
     * Testing processContinent function return value for Valid Map
     */
    @Test
    @DisplayName("Process Continent => TRUE for Valid MAP")
    void processContinentValid() {
        assertTrue(map_controller.processContinents(mapToString(valid)));
    }

    /**
     * Testing processContinent function return value for inValid Map
     * which is a map with no [territories] tag
     */
    @Test
    @DisplayName("Process Continent => FALSE for noTag MAP")
    void processContinentInValid() {
        assertTrue(map_controller.processContinents(mapToString(noTag)));
    }

    /**
     * Testing processTerritories function return value for Valid Map
     */
    @Test
    @DisplayName("Process Territories => TRUE for Valid MAP")
    void proceeeTerritoriesValid() {
        map_controller.processContinents(mapToString(valid));
        assertTrue(map_controller.processTerritories(mapToString(valid)));
    }

    /**
     * Testing processTerritories function return value for InValid Map
     * Which is map with no territories
     */
    @Test
    @DisplayName("Process Territories => FALSE for noTerritories MAP")
    void proceeeTerritoriesInValid() {
        map_controller.processContinents(mapToString(noTerritories));
        assertTrue(map_controller.processTerritories(mapToString(noTerritories)));
    }

    /**
     * Testing territoriesToContinents function return value for Valid Map
     */
    @Test
    @DisplayName("continentArray => NotNULL for Valid MAP")
    void territoriesToContinentsValid() {
        map_controller.processContinents(mapToString(valid));
        map_controller.processTerritories(mapToString(valid));
        map_controller.territoriesToContinents();
        assertNotNull(map_controller.continentArray);
    }
    
    /**
     * Testing territoriesToContinents function return value for InValid Map
     * Which is a map with no Continent
     */
    @Test
    @DisplayName("continentArray => NULL for noContinent MAP")
    void territoriesToContinentsInValid() {
        map_controller.processContinents(mapToString(noContinent));
        map_controller.processTerritories(mapToString(noContinent));
        map_controller.territoriesToContinents();
        assertNotNull(map_controller.continentArray);
    }

    /**
     * Testing createContinentConnection function return value for Valid Map
     */
    @Test
    @DisplayName("createContinentConnection => TRUE for NotConnected MAP")
    void createContinentConnectionInValid() {
        map_controller.processContinents(mapToString(notConnected));
        map_controller.processTerritories(mapToString(notConnected));
        map_controller.territoriesToContinents();
        assertTrue(map_controller.createContinentConnection());
    }

    /**
     * Testing validateMap function return value for Valid Map
     */
    @Test
    @DisplayName("validateMap => TRUE for Valid MAP")
    void validateMapValid() {
        map_controller.processContinents(mapToString(valid));
        map_controller.processTerritories(mapToString(valid));
        map_controller.territoriesToContinents();
        map_controller.createContinentConnection();
        MapMessage anything = map_controller.processFile(new File(valid));
        assertTrue(map_controller.validateMap(anything.getTerritories()));
    }

    /**
     * Testing validateMap function return value for InValid Map
     * Which is a not connected map
     */
    @Test
    @DisplayName("validateMap => FALSE for NotConnected MAP")
    void validateMapInValid() {
        map_controller.processContinents(mapToString(notConnected));
        map_controller.processTerritories(mapToString(notConnected));
        map_controller.territoriesToContinents();
        map_controller.createContinentConnection();
        MapMessage anything = map_controller.processFile(new File(notConnected));
        assertTrue(map_controller.validateMap(anything.getTerritories()));
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