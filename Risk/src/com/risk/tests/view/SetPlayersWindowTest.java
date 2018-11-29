package com.risk.tests.view;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import com.risk.controller.GameController;
import com.risk.controller.MapController;
import com.risk.model.AggressivePlayer;
import com.risk.model.BenevolentPlayer;
import com.risk.model.Card;
import com.risk.model.CheaterPlayer;
import com.risk.model.HumanPlayer;
import com.risk.model.MapMessage;
import com.risk.model.Player;
import com.risk.model.RandomPlayer;
import com.risk.view.SetPlayersWindow;

/**
 * SetPlayersWindowTests
 * 
 * This Class will test the  SetPlayersWindow functionalities
 * (For validation of correct startup phase)
 * 
 * @author Hamid
 * @version 1.0
 */
public class SetPlayersWindowTest {

	@SuppressWarnings("javadoc")
    private  static String mapFolder;
	@SuppressWarnings("javadoc")
    private  static String world;
	@SuppressWarnings("javadoc")
	private MapMessage worldResult;
	@SuppressWarnings("javadoc")
	private SetPlayersWindow setPlayersWindow;
	
	/**
     * Before All sets the local address for Valid and Invalid maps 
     */
    @BeforeAll
    static void initAll() {
        mapFolder = System.getProperty("user.dir") + "/src/com/risk/tests/controller/maps/";
        world = mapFolder + "world.map";
    }
    /**
     * Before Each Test it :
     * Reads a Valid map
     * Creates an instance of the SetPlayersWindow for testing
     */
    @BeforeEach
    void init() {
    	//Setting MAP
    	worldResult = mapToMapMessage(world);
    	//Creating a setPlayersWindow using the map
    	setPlayersWindow = new SetPlayersWindow(worldResult.getMap());
    }
    
    // =====================setStartupPhase()=====================
	 /**
     * Testing setStartupPhase function return value for Valid Startup Phase
     * Cards, Players and their Strategies, Map, CurrentPlayer
     */
    @Test
    @DisplayName("setStartupPhase() => GameContoller Should Contain all Elements")
    void setStartupPhaseValid() {
    	//Number of armies for 5 players
    	GameController gc = new GameController();
		int numArmies = gc.getPlayersArmies(5);
		
    	//Setting Players
    	//5 Players with different strategies
    	 ArrayList<Player> playerList = new ArrayList<Player>();
    	 playerList.add(setPlayer("Player1",false,numArmies,"ADD","Human"));
    	 playerList.add(setPlayer("Player2",true,numArmies,"ADD","AGGRESSIVE"));
    	 playerList.add(setPlayer("Player3",true,numArmies,"ADD","BENEVOLENT"));
    	 playerList.add(setPlayer("Player4",true,numArmies,"ADD","RANDOM"));
    	 playerList.add(setPlayer("Player5",true,numArmies,"ADD","CHEATER"));
    	
    	 //Setting Cards
    	 ArrayList<Card> cards = gc.loadCards(worldResult.map.getTerritories().size());
    	 
    	//Passing all the Game elements to the function
    	 GameController gameController = setPlayersWindow.setStartupPhase(worldResult.map,cards,playerList);
    	 
    	//testing the result
    	
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

    /**
     * 
     * Function for processing a map and returning 
     * the result as a MapMessage
     * 
     * @param String mapName for the Name of the map 
     * @return MapMessage mapMessage which is the processed result 
     */

    private static MapMessage mapToMapMessage(String mapName) {
    	
    		MapController mpc = new MapController();
    		mpc.processContinents(mapToString(mapName));
    		mpc.processTerritories(mapToString(mapName));
    		mpc.territoriesToContinents();
        MapMessage mapMessage = mpc.processFile(new File(mapName));
        return mapMessage;
        
    }

    /**
     * 
     * Function for Setting Player Elements 
     * 
     * @param name string for the player name
     * @param isComputer a boolean to differentiate between human and a computer
     * @param numArmies which depends on number of the players in the game
     * @param phase which is ADD in the  beginning
     * @param Type cane be Human,AGGRESSIVE,BENEVOLENT,RANDOM or CHEATER
     * @return Player object from all those inputs
     */

    private Player setPlayer(String name, boolean isComputer, int numArmies, String phase, String Type) {

    	

    	Player player = new Player(name, isComputer, numArmies,phase);

    	

    	if (Type.equals("Human")) {

    		player.setStrategy(new HumanPlayer());

    	}else if (Type.equals("AGGRESSIVE")) {

    		player.setStrategy(new AggressivePlayer());

    	}else if (Type.equals("BENEVOLENT")) {

    		player.setStrategy(new BenevolentPlayer());

    	}else if (Type.equals("RANDOM")) {

    		player.setStrategy(new RandomPlayer());

    	}else if (Type.equals("CHEATER")) {

    		player.setStrategy(new CheaterPlayer());

    	}

    	

    	return player;

    }
    
}
