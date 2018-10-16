package com.risk.tests;

import com.risk.controller.MapController;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.Transient;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author Hamid
 */

public class mapControllerTests {

    private static String mapFolder;
    private static String map1;
    private static String map2;
    private static String map3;
    private static MapController mc;

    @BeforeEach
    // Should get the current path and locate the map folder
    void init() {
        mapFolder = System.getProperty("user.dir") + "/src/com/risk/tests/maps/";
        map1 = mapFolder + "valid.map";
        map2 = mapFolder + "invalid.map";
        mc = new MapController();
    }

    @Test
    @DisplayName ("Process Continent => TRUE for Valid MAP")
    void ProceeeContinentValid(){
    		//System.out.println(map1);
        assertTrue(mc.processContinents(mapToString(map1)));
    }
    
    @Test
    @DisplayName ("Process Continent => TRUE for InValid MAP")
    void ProceeeContinentInValid(){
    		//System.out.println(map1);
        assertTrue(mc.processContinents(mapToString(map2)));
    }

    /**
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