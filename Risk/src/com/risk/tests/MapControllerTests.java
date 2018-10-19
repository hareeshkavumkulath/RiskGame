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
	@SuppressWarnings("javadoc")
    private  static String mapFolder;
	@SuppressWarnings("javadoc")
    private  static String valid;
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
        mapController = new MapController();
    }

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
        assertTrue(mapController.processContinents(mapToString(noTag)));
    }

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
        mapController.processContinents(mapToString(noTerritories));
        assertTrue(mapController.processTerritories(mapToString(noTerritories)));
    }

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

    /**
     * Testing createContinentConnection function return value for Valid Map
     */
    @Test
    @DisplayName("createContinentConnection => TRUE for NotConnected MAP")
    void createContinentConnectionInValid() {
        mapController.processContinents(mapToString(notConnected));
        mapController.processTerritories(mapToString(notConnected));
        mapController.territoriesToContinents();
        assertTrue(mapController.createContinentConnection());
    }

    /**
     * Testing validateMap function return value for Valid Map
     */
    @Test
    @DisplayName("validateMap => TRUE for Valid MAP")
    void validateMapValid() {
        mapController.processContinents(mapToString(valid));
        mapController.processTerritories(mapToString(valid));
        mapController.territoriesToContinents();
        mapController.createContinentConnection();
        MapMessage anything = mapController.processFile(new File(valid));
        assertTrue(mapController.validateMap(anything.getTerritories()));
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
        mapController.createContinentConnection();
        MapMessage anything = mapController.processFile(new File(notConnected));
        assertTrue(mapController.validateMap(anything.getTerritories()));
    }

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