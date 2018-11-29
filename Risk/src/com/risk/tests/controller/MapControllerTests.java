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
import static org.junit.Assert.assertEquals;
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
    private  static String noTag;
	@SuppressWarnings("javadoc")
    private  static String noTerritories;
	@SuppressWarnings("javadoc")
    private  static String noContinent;
	@SuppressWarnings("javadoc")
    private  MapController mapController;
	@SuppressWarnings("javadoc")
	//MAPS
	private  static String aussiWorld;
	@SuppressWarnings("javadoc")
    private  static String world;
	@SuppressWarnings("javadoc")
	private  static String twinVolcano;
	@SuppressWarnings("javadoc")
	private  static String threeDCliff;
	@SuppressWarnings("javadoc")
	private  static String ContinentsConnectedMapNot;
	@SuppressWarnings("javadoc")
	private  static String MapConnectedContinetsNot;
    

    /**
     * Before All sets the local address for Valid and Invalid maps 
     */
    @BeforeAll
    static void initAll() {
        mapFolder = System.getProperty("user.dir") + "/src/com/risk/tests/controller/maps/";
        noTag = mapFolder + "no_tag.map";
        noTerritories = mapFolder + "no_territories.map";
        //notConnected = mapFolder + "not_connected.map";
        noContinent = mapFolder + "no_continent.map";
        
        aussiWorld = mapFolder + "aussiWorld.map";
        world = mapFolder + "world.map";
	    	twinVolcano = mapFolder + "twinVolcano.map";
	    	threeDCliff = mapFolder + "threeDCliff.map";
	    	ContinentsConnectedMapNot = mapFolder + "ContinentsConnectedMapNot.map";
	    	MapConnectedContinetsNot = mapFolder + "MapConnectedContinetsNot.map";
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
        assertTrue(mapController.processContinents(mapToString(world)));
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
        mapController.processContinents(mapToString(world));
        assertTrue(mapController.processTerritories(mapToString(world)));
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
        mapController.processContinents(mapToString(world));
        mapController.processTerritories(mapToString(world));
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
    //Valid Maps Test
    /**
     * 
     * Testing validateMap function return value for following VALID maps
     * World, 3DCliff, aussiWorld
     * 
     */
    @Test
    @DisplayName("validateMap => TRUE for Valid MAPs")
    void validateMapValid() {
    	//aussiWorld
    	MapMessage aussiWorldResult = mapToMapMessage(aussiWorld);
        assertTrue(aussiWorldResult.isValidMap);

        //World
   		MapMessage worldResult = mapToMapMessage(world);
        assertTrue(worldResult.isValidMap);

        //3DCliff
        MapMessage threeDCliffResult = mapToMapMessage(threeDCliff);
        assertTrue(threeDCliffResult.isValidMap);
    }
    
    //Invalid Maps Test
    /**
     * 
     * Testing validateMap function return value for following INVALID maps
     * TwinVolcano, MapConnectedContinetsNot, ContinentsConnectedMapNot
     * 
     */
    @Test
    @DisplayName("validateMap => FALSE for InValid MAPs")
    void validateMapInValid() {
    	//twinVolcano
        MapMessage twinVolcanoResult = mapToMapMessage(twinVolcano);
        assertFalse(twinVolcanoResult.isValidMap);
        assertEquals("The continent Barren Rocks is not a connected subgraph",twinVolcanoResult.message.toString());
        
        //MapConnectedContinetsNot
        MapMessage MapConnectedContinetsNotResult = mapToMapMessage(MapConnectedContinetsNot);
        assertFalse(MapConnectedContinetsNotResult.isValidMap);
        assertEquals("The continent 1 is not a connected subgraph",MapConnectedContinetsNotResult.message.toString());
        
        //ContinentsConnectedMapNot
        MapMessage ContinentsConnectedMapNotResult = mapToMapMessage(ContinentsConnectedMapNot);
        assertFalse(ContinentsConnectedMapNotResult.isValidMap);
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
        
        assertTrue(mapController.addAdjacentTerritories(mapToString(world),terrList));
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
    /**
     * 
     * Function for processing a map and returning 
     * the result as a MapMessage
     * 
     * @param mapName String for the Name of the map 
     * @return MapMessage mapMessage which is the processed result 
     */
    // 	=====================mapToMapMessage()=====================
    private static MapMessage mapToMapMessage(String mapName) {
    	
    		MapController mpc = new MapController();
    		mpc.processContinents(mapToString(mapName));
    		mpc.processTerritories(mapToString(mapName));
    		mpc.territoriesToContinents();
        MapMessage mapMessage = mpc.processFile(new File(mapName));
        return mapMessage;
        
    }
}