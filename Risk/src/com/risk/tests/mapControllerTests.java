package com.risk.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;

/**
 * @author Hamid
 */

public class mapControllerTests{
    

    private static String mapFolder;
    private static String map1;
    private static String map2;
    private static String map3;
    

    @BeforeAll
    //Should get the current path and locate the map folder
    static void initAll() {
        mapFolder = System.getProperty("user.dir")+"/maps/";
        map1 = mapFolder + "map1.map";
        map2 = mapFolder + "map2.map";
        map3 = mapFolder + "map3.map";
    }
    


}