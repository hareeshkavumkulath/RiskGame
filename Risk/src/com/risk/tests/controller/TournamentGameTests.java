package com.risk.tests.controller;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.risk.controller.GameController;
import com.risk.controller.MapController;
import com.risk.model.TournamentGame;
import com.risk.model.AggressivePlayer;
import com.risk.model.BenevolentPlayer;
import com.risk.model.Card;
import com.risk.model.CheaterPlayer;
import com.risk.model.Game;
import com.risk.model.GameInstructions;
import com.risk.model.Map;
import com.risk.model.MapMessage;
import com.risk.model.Player;
import com.risk.model.RandomPlayer;

/**
 * Tests for Tournament Game Mode
 * 
 * @author Anqi Wang
 *
 */
public class TournamentGameTests {
	
	/**
     * Tournament Mode Test
     */
    @Test
    @DisplayName("Tournament Mode 2X2 Mode")
    public void tournamentModeTest2X2() {
    	String[][] winners = playGames(2, 3, 2, 20);
    	List<String> winnersList = twoDimensionalArrayToList(winners);
    	assertTrue(winnersList.contains("Draw"));
    }
    
    /**
     * Tournament Mode Test
     */
    @Test
    @DisplayName("Tournament Mode 3X3 Mode")
    public void tournamentModeTest3X3() {
    	String[][] winners = playGames(3, 4, 3, 10);
    	List<String> winnersList = twoDimensionalArrayToList(winners);
    	assertTrue(winnersList.contains("Cheater"));
    }
    
    /**
     * Tournament Mode Test
     */
    @Test
    @DisplayName("Tournament Mode 4X4 Mode")
    public void tournamentModeTest4X4() {
    	String[][] winners = playGames(4, 3, 4, 20);
    	List<String> winnersList = twoDimensionalArrayToList(winners);
    	assertTrue(winnersList.contains("Draw"));
    }
    
    /**
     * Tournament Mode Test
     */
    @Test
    @DisplayName("Tournament Mode 5X5 Mode")
    public void tournamentModeTest5X5() {
    	String[][] winners = playGames(5, 4, 5, 10);
    	List<String> winnersList = twoDimensionalArrayToList(winners);
    	assertTrue(winnersList.contains("Cheater"));
    }
    
    /**
     * Play the games 
     * 
     * @param numGames Integer Number of Games
     * @param numPlayers Integer Number of Players
     * @param numMaps Integer Number of Maps
	 * @param numTurns Integer Number of Turns
	 * 
	 * @return String[][] Two Dimensional Array with the name of the winners
	 */
	private String[][] playGames(int numGames, int numPlayers, int numMaps, int numTurns) {
		String[][] winners;
		int rows = numMaps;
		int columns = numGames;
		winners = new String[rows][columns];
		for(int i=0;i<rows;i++) {
			ArrayList<Map> newMaps = getMaps(numMaps);
			Map map = newMaps.get(i);
			for(int j=0;j<columns;j++) {
 				ArrayList<Player> newPlayers = getPlayers(numPlayers); 
				GameController controller = new GameController();
				ArrayList<Card> cards = controller.loadCards(map.getTerritories().size());
				Game game = new Game(map, newPlayers, cards, newPlayers.get(0));
				GameInstructions gameInstructions = new GameInstructions("Risk Game\r\n");				
				GameController gameController = new GameController(game, gameInstructions);
				game.setCurrentPlayer(gameController.assignTerritories());
				TournamentGame tournament = new TournamentGame(game, gameController, numTurns);
				tournament.onGame();
				winners[i][j] = tournament.getWinner();	
				System.out.println(winners[i][j]);
			}
		}
		return winners;
	}

	/**
	 * Sets the ArrayList of Players
	 * @param numPlayers Integer Number of Players
	 * 
	 * @return ArrayList ArrayList of Players
	 */
	private ArrayList<Player> getPlayers(int numPlayers) {
		ArrayList<Player> players = new ArrayList<Player>();
		GameController controller = new GameController();
		int numArmies = controller.getPlayersArmies(4);
		if(numPlayers > 1) {
			//Aggressive Player
			Player player1 = new Player("Aggressive", true, numArmies, "ADD");
			player1.setStrategy(new AggressivePlayer());
			players.add(player1);
			//Benevolent Player
			Player player2 = new Player("Benevolent", true, numArmies, "ADD");
			player2.setStrategy(new BenevolentPlayer());
			players.add(player2);
		}
		if(numPlayers > 2) {
			//Random Player
			Player player3 = new Player("Random", true, numArmies, "ADD");
			player3.setStrategy(new RandomPlayer());
			players.add(player3);
		}
		if(numPlayers > 3) {
			//Cheater Player
			Player player4 = new Player("Cheater", true, numArmies, "ADD");
			player4.setStrategy(new CheaterPlayer());
			players.add(player4);
		}
		return players;
	}

	/**
     * Sets the ArrayList of Maps
     * 
	 * @param num Integer Number of Maps
	 * @return ArrayList ArrayList of Maps
     */
    private ArrayList<Map> getMaps(int num) {
    	ArrayList<Map> maps = new ArrayList<Map>();
    	File folder = new File(".\\Maps\\");
    	File[] listOfFiles = folder.listFiles();
		for(int i=0;i<num;i++) {
			MapController controller = new MapController();
			File file = listOfFiles[i]; 
			MapMessage message = controller.processFile(file);
			if(message.isValidMap()) {
				Map map = message.getMap();
				maps.add(map);
			}			
		}
		return maps;
    }
    
    /**
     * Convert Two Dimensional Array to ArrayList
     * 
     * @param twoDArray
     * @return
     */
    public <T> List<T> twoDimensionalArrayToList(T[][] twoDArray) {
        List<T> list = new ArrayList<T>();
        for (T[] array : twoDArray) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }

}
