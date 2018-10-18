package com.risk.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.risk.controller.GameController;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

/**
 * GameControllerTests
 * 
 * This Class will run tests on GameController.class
 * 
 * @author Hamid
 * @version 1.0
 */

class GameControllerTests{

    private GameController gc;

    /**
     * Before Each Test it Creates a new object from Game Controller 
     */
    @BeforeEach
    void init() {
        gc = new GameController();
    }

}