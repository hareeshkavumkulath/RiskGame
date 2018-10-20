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
    
}