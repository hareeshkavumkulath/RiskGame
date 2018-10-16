package com.risk.tests;

import com.risk.controller.MapController;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.Transient;
import java.io.BufferedReader;
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
 * @author Hamid
 */

public class mapControllerTests {

    private  static String mapFolder;
    private  static String map1;
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
        map1 = mapFolder + "valid.map";
        noTag = mapFolder + "noTag.map";
        noTerritories = mapFolder + "noTerritories.map";
        notConnected = mapFolder + "notConnected.map";
        noContinent = mapFolder + "noContinent.map";
    }

    /**
     * Before Each Test it Creates a new object from Map Controller 
     */
    @BeforeEach
    void init() {
        mc = new MapController();
    }

    @Test
    @DisplayName("Process Continent => TRUE for Valid MAP")
    void ProceeeContinentValid() {
        // System.out.println(map1);
        assertTrue(mc.processContinents(mapToString(map1)));
    }

    @Test
    @DisplayName("Process Continent => FALSE for noTag MAP")
    void ProceeeContinentInValid() {
        assertTrue(mc.processContinents(mapToString(noTag)));
    }

    @Test
    @DisplayName("Process Territories => TRUE for Valid MAP")
    void ProceeeTerritoriesValid() {
        mc.processContinents(mapToString(map1));
        assertTrue(mc.processTerritories(mapToString(map1)));
    }

    @Test
    @DisplayName("Process Territories => FALSE for noTerritories MAP")
    void ProceeeTerritoriesInValid() {
        mc.processContinents(mapToString(noTerritories));
        assertTrue(mc.processTerritories(mapToString(noTerritories)));
    }

    @Test
    @DisplayName("continentArray => NotNULL for Valid MAP")
    void territoriesToContinentsValid() {
        mc.processContinents(mapToString(map1));
        mc.processTerritories(mapToString(map1));
        mc.territoriesToContinents();
        assertNotNull(mc.continentArray);
    }
    
    @Test
    @DisplayName("continentArray => NULL for noContinent MAP")
    void territoriesToContinentsInValid() {
        mc.processContinents(mapToString(noContinent));
        mc.processTerritories(mapToString(noContinent));
        mc.territoriesToContinents();
        assertNotNull(mc.continentArray);
    }

    @Test
    @DisplayName("createContinentConnection => TRUE for NotConnected MAP")
    void createContinentConnectionInValid() {
        mc.processContinents(mapToString(notConnected));
        mc.processTerritories(mapToString(notConnected));
        mc.territoriesToContinents();
        assertTrue(mc.createContinentConnection());
    }

    @Test
    @DisplayName("validateMap => TRUE for Valid MAP")
    void validateMapValid() {
        mc.processContinents(mapToString(map1));
        mc.processTerritories(mapToString(map1));
        mc.territoriesToContinents();
        mc.createContinentConnection();
        assertTrue(mc.validateMap(mc.territoriesArray));
    }

    @Test
    @DisplayName("validateMap => FALSE for NotConnected MAP")
    void validateMapInValid() {
        mc.processContinents(mapToString(notConnected));
        mc.processTerritories(mapToString(notConnected));
        mc.territoriesToContinents();
        mc.createContinentConnection();
        assertTrue(mc.validateMap(mc.territoriesArray));
    }

    /**
     * 
     * Function for reading a map file and convert that into String
     * 
     * @param mapFile Map File Address as a String
     * @return Map Content to Strings
     * @exception IOException if there is a problem with the Map File Address
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