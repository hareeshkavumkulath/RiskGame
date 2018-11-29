  package com.risk.tests.model;


import org.junit.jupiter.api.Test;

import com.risk.controller.GameController;
import com.risk.controller.MapController;
import com.risk.model.AggressivePlayer;
import com.risk.model.BenevolentPlayer;
import com.risk.model.Card;
import com.risk.model.CheaterPlayer;
import com.risk.model.Continent;
import com.risk.model.GameInstructions;
import com.risk.model.HumanPlayer;
import com.risk.model.MapMessage;
import com.risk.model.Player;
import com.risk.model.RandomPlayer;
import com.risk.model.Territory;
import com.risk.view.SetPlayersWindow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import static org.junit.Assert.assertEquals;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;


/**
 * CheaterPlayerTest
 * 
 * This Class will test the  Cheater Player functionalities
 * 
 * @author Hamid
 * @version 1.0
 */

public class CheaterPlayerTest {
	
	@SuppressWarnings("javadoc")
    private  static String mapFolder;
	@SuppressWarnings("javadoc")
    private  static String world;
	@SuppressWarnings("javadoc")
	private MapMessage worldResult;
	@SuppressWarnings("javadoc")
	private SetPlayersWindow setPlayersWindow;
	@SuppressWarnings("javadoc")
	private GameController gameController;
	@SuppressWarnings("javadoc")
	private ArrayList<Player> playerList;
	
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
     * Loads a Map,
     * Assign players and cards
     */
    @BeforeEach
    void init() {
    	//Setting MAP
    	worldResult = mapToMapMessage(world);
    	setPlayersWindow = new SetPlayersWindow(worldResult.getMap());
    	
    	//Number of armies for 5 players
    	GameController gc = new GameController();
		int numArmies = gc.getPlayersArmies(5);
		
    	//Setting Players
    	//5 Players with different strategies
    	 playerList = new ArrayList<Player>();
    	 playerList.add(setPlayer("Player1",false,numArmies,"ADD","Human"));
    	 playerList.add(setPlayer("Player2",true,numArmies,"ADD","AGGRESSIVE"));
    	 playerList.add(setPlayer("Player3",true,numArmies,"ADD","BENEVOLENT"));
    	 playerList.add(setPlayer("Player4",true,numArmies,"ADD","RANDOM"));
    	 playerList.add(setPlayer("Player5",true,numArmies,"ADD","CHEATER"));
    	
    	 //Setting Cards
    	 ArrayList<Card> cards = gc.loadCards(worldResult.map.getTerritories().size());
    	 
    	//Passing all the Game elements to the function
    	 gameController = setPlayersWindow.setStartupPhase(worldResult.map,cards,playerList);
    	 gameController.game.setCurrentPlayer(gameController.assignTerritories()); 
    	 
    }

    /**
     * Testing reinforce() function for Cheater
     */
    @Test
    @DisplayName("Cheater reinforcement")
    void reinforceTest(){
    	
    	ArrayList<Integer> numOfArmiesBefore = new ArrayList<Integer>();
    	for (Territory t :playerList.get(4).getOwnedTerritories()) {
    		numOfArmiesBefore.add(t.getNumberOfArmies()*2);
    	}
    	
    	CheaterPlayer cheater = new CheaterPlayer();
    	cheater.reinforce(playerList.get(4),playerList.get(4).getOwnedTerritories().get(0),gameController.getGameInstructions(), gameController);
    	
    	for (int i=0;i<playerList.get(4).getOwnedTerritories().size();i++) {
    		Integer currentArmies = playerList.get(4).getOwnedTerritories().get(i).getNumberOfArmies();
    		assertEquals(numOfArmiesBefore.get(i),currentArmies);
    	}
    	
    }
    
    /**
     * Testing fortify() function for Cheater
     */
    @Test
    @DisplayName("Cheater fortify")
    void fortifyTest(){
    	
    	playerList.get(4).getOwnedTerritories().get(0).setNumberOfArmies(5);
    	playerList.get(4).getOwnedTerritories().get(1).setNumberOfArmies(2);
    	
    	ArrayList<Integer> numOfArmiesBefore = new ArrayList<Integer>();
    	for (Territory t :playerList.get(4).getOwnedTerritories()) {
    		numOfArmiesBefore.add(t.getNumberOfArmies()*2);
    	}
    	
    	
    	
    	CheaterPlayer cheater = new CheaterPlayer();
    	cheater.fortify(playerList.get(4), 
    			playerList.get(4).getOwnedTerritories().get(0), 
    			playerList.get(4).getOwnedTerritories().get(1), 
    			2, 
    			gameController.getGameInstructions(), 
    			gameController);
    	
    	
    	
    }

    /**
     * Testing attack() function for Cheater
     */
    
    @Test
    @DisplayName("Cheater Attack")
    void attackTest(){

    	for (Territory t : playerList.get(4).getOwnedTerritories()){
    		t.setNumberOfArmies(5);
    	}
    	
    	CheaterPlayer cheater = new CheaterPlayer();
    	cheaterattack(playerList.get(4),
    			playerList.get(4).getOwnedTerritories().get(0),
    			playerList.get(4).getOwnedTerritories().get(0).getAdjacentTerritories().get(0),
    			false,
    			gameController.getGameInstructions(), 
    			gameController);
    	
    	for (Territory t : playerList.get(4).getOwnedTerritories().get(0).getAdjacentTerritories())
	   	 {
	    		assertEquals(t.getRuler().getName(), playerList.get(4).getName());
	   	 }
    	
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
     private void cheaterattack (Player currentPlayer, Territory attackerTerritory, Territory opponentTerritory, 
    		 boolean allOutMode, GameInstructions gameInstructions, GameController controller) {
    	 
    	 for (Territory t : currentPlayer.getOwnedTerritories().get(0).getAdjacentTerritories())
    	 {
    		 t.setRuler(currentPlayer);
    	 }
     }
}
