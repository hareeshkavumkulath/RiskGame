package com.risk.tests.view;

import com.risk.model.Player;
import com.risk.model.Territory;
import com.risk.view.FortifyWindow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

/**
 * FortifyWindowTest
 * 
 * This Class will run tests on view/FortifyWindow
 * 
 * @author Hamid
 * @version 1.0
 */

public class FortifyWindowTest{

    @SuppressWarnings("javadoc")
    private  FortifyWindow fortifyWindow;
    @SuppressWarnings("javadoc")
    private  Player player;

     /**
     * Before Each Test it Creates following new objects
     * <li>testPlayer from Player</li>
     * <li>fortifyWindow from FortifyWindow </li>
     */
    @BeforeEach
    void init() {
        player = new Player("testPlayer",false,10);
        fortifyWindow = new FortifyWindow(player);
    }

     // =====================playersToTerritories()=====================
    
    void getTerritoryArrayTest() {
        
        //Creating 20 Test Territories
        Territory temp;
        for (int i = 0; i < 20; i++) {
            temp = new Territory("testTerritory" + i, "testContinent" + i, i);
            //Adding territories to player    
            player.ownedTerritories.add(temp);
        }

        String[] result = fortifyWindow.getTerritoryArray(player);

        for(int i=0;i<player.ownedTerritories.size();i++){
            String res = player.getOwnedTerritories().get(i).getName() + "(" + player.getOwnedTerritories().get(i).getNumberOfArmies() + ")";
            assertEquals(result[i],res);
        }
        
    }

}