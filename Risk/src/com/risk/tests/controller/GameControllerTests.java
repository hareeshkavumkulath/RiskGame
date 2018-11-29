package com.risk.tests.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.risk.controller.GameController;
import com.risk.controller.MapController;
import com.risk.model.Player;
import com.risk.model.RandomPlayer;
import com.risk.model.Territory;
import com.risk.view.SetPlayersWindow;
import com.risk.model.AggressivePlayer;
import com.risk.model.BenevolentPlayer;
import com.risk.model.Card;
import com.risk.model.CheaterPlayer;
import com.risk.model.AttackStatus;
import com.risk.model.Continent;
import com.risk.model.HumanPlayer;
import com.risk.model.MapMessage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * GameControllerTests
 * 
 * This Class will run tests on GameController.class
 * 
 * @author Hamid
 * @version 1.0
 */

public class GameControllerTests {
	@SuppressWarnings("javadoc")
	private GameController gameController;

	/**
	 * Before Each Test it Creates a new object from Game Controller
	 */
	@BeforeEach
	void init() {
		gameController = new GameController();
	}

	// =====================getPlayersArmies()=====================
	/**
	 * Testing getPlayersArmies function return value for 2 players
	 */
	@Test
	@DisplayName("getPlayersArmies => 2 for 40")
	void getPlayersArmies2() {
		assertEquals(40, gameController.getPlayersArmies(2));
	}

	/**
	 * Testing getPlayersArmies function return value for 4 players
	 */
	@Test
	@DisplayName("getPlayersArmies => 4 for 30")
	void getPlayersArmies4() {
		assertEquals(30, gameController.getPlayersArmies(4));
	}

	/**
	 * Testing getPlayersArmies function return value for 6 players
	 */
	@Test
	@DisplayName("getPlayersArmies => 6 for 20")
	void getPlayersArmies6() {
		assertEquals(20, gameController.getPlayersArmies(6));
	}

	// =====================getNumReinforcements()=====================
	/**
	 * Testing getNumReinforcements function return value for less than 3
	 * Territories
	 */
	@Test
	@DisplayName("getNumReinforcements => less than 3 Territories should equals 3")
	void getNumReinforcements3() {
		Player p = new Player("testPlayer", false, 10, "REINFORCEMENT");
		ArrayList<Territory> t = new ArrayList<Territory>();
		Territory temp;
		for (int i = 0; i < 3; i++) {
			temp = new Territory("testTerritory" + i, "testContinent" + i, i);
			t.add(temp);
		}
		p.ownedTerritories = t;

		assertEquals(3, gameController.getNumReinforcements(p));
	}

	/**
	 * Testing getNumReinforcements function return value for 17 Territories
	 */
	@Test
	@DisplayName("getNumReinforcements => for 17 Territories should equals 4")
	void getNumReinforcements17() {
		Player p = new Player("testPlayer", false, 10, "REINFORCEMENT");
		ArrayList<Territory> t = new ArrayList<Territory>();
		Territory temp;
		for (int i = 0; i < 17; i++) {
			temp = new Territory("testTerritory" + i, "testContinent" + i, i);
			t.add(temp);
		}
		p.ownedTerritories = t;

		assertEquals(4, gameController.getNumReinforcements(p));
	}

	// =====================isValidFortify()=====================
	/**
	 * Testing isValidFortify() function return value if (player.ownedTerritories()
	 * == 1) should be Equal to false
	 */
	@Test
	@DisplayName("For player.ownedTerritories() == 1 => False")
	void isValidFortify1() {
		Player p = new Player("testPlayer", false, 10, "REINFORCEMENT");
		Territory temp = new Territory("testTerritory", "testContinent", 10);
		p.ownedTerritories.add(temp);

		assertFalse(gameController.isValidFortify(p));
	}

	/**
	 * Testing isValidFortify() function return value if (player.ownedTerritories()
	 * is greater than 1) should be Equal to true
	 */
	@Test
	@DisplayName("For player.ownedTerritories() > 1 => True")
	void isValidFortify10() {
		Player p = new Player("testPlayer", false, 10, "REINFORCEMENT");
		Territory temp;
		for (int i = 0; i < 10; i++) {
			temp = new Territory("testTerritory" + i, "testContinent", 10 + i);
			p.ownedTerritories.add(temp);
		}

		assertTrue(gameController.isValidFortify(p));
	}

	// =====================validateFortifyMove()=====================
	/**
	 * Testing validateFortifyMove() function return value if
	 * (player.ownedTerritories[i].NumberOfArmies is greater than 1) should be Equal
	 * to true
	 */

	@Test
	@DisplayName("For player.ownedTerritories[i].NumberOfArmies > 1 => TRUE")
	void validateFortifyMove2() {
		Player p = new Player("testPlayer", false, 10, "REINFORCEMENT");
		Territory temp = new Territory("testTerritory", "testContinent", 2);
		p.ownedTerritories.add(temp);

		assertTrue(gameController.validateFortifyMove(p, 0));
	}

	/**
	 * Testing validateFortifyMove() function return value if
	 * (player.ownedTerritories[i].NumberOfArmies is less and equal to 1) should be
	 * Equal to false
	 */

	@Test
	@DisplayName("For player.ownedTerritories[i].NumberOfArmies <= 1 => False")
	void validateFortifyMove1() {
		Player p = new Player("testPlayer", false, 10, "REINFORCEMENT");
		Territory temp = new Territory("testTerritory", "testContinent", 1);
		p.ownedTerritories.add(temp);

		assertFalse(gameController.validateFortifyMove(p, 0));
	}

	// =====================validateFortifyNumber()=====================
	/**
	 * Testing validateFortifyNumber() function return value if
	 * (player.ownedTerritories[i].NumberOfArmies - 1 is greater than fortifyNum)
	 * should be Equal to TRUE
	 */
	@Test
	@DisplayName("player.ownedTerritories[i].NumberOfArmies - 1 is greater than fortifyNum => TRUE")
	void validateFortifyNumberGreaterThanFortifyNum() {
		Player p = new Player("testPlayer", false, 10, "REINFORCEMENT");
		Territory temp = new Territory("testTerritory", "testContinent", 10);
		p.ownedTerritories.add(temp);

		assertTrue(gameController.validateFortifyNumber(p, 0, 3));
	}

	/**
	 * Testing validateFortifyNumber() function return value if
	 * (player.ownedTerritories[i].NumberOfArmies - 1 is less than fortifyNum)
	 * should be Equal to FALSE
	 */
	@Test
	@DisplayName("player.ownedTerritories[i].NumberOfArmies - 1 < fortifyNum => FALSE")
	void validateFortifyNumberLessThanFortifyNum() {
		Player p = new Player("testPlayer", false, 10, "REINFORCEMENT");
		Territory temp = new Territory("testTerritory", "testContinent", 2);
		p.ownedTerritories.add(temp);

		assertFalse(gameController.validateFortifyNumber(p, 0, 3));
	}

	// =====================hasEnoughArmies()=====================
	/**
	 * Testing hasEnoughArmies() function return value
	 */
	@Test
	@DisplayName("hasEnoughArmies() should return => TRUE")
	void hasEnoughArmiesTest() {
		Territory attacker = new Territory("testTerritory", "testContinent", 10);
		Territory opponent = new Territory("testTerritory", "testContinent", 10);
		int attacker_armies = 2;
		int opponent_armies = 2;
		assertTrue(gameController.hasEnoughArmies(attacker, opponent, attacker_armies, opponent_armies));
	}

	/**
	 * Testing hasEnoughArmies() function return value
	 */
	@Test
	@DisplayName("hasEnoughArmies() Opponent Army < Chosen Dice => FALSE")
	void hasEnoughArmiesOpponentLessDice() {
		Territory attacker = new Territory("testTerritory", "testContinent", 10);
		Territory opponent = new Territory("testTerritory", "testContinent", 1);
		int attacker_armies = 2;
		int opponent_armies = 2;
		assertFalse(gameController.hasEnoughArmies(attacker, opponent, attacker_armies, opponent_armies));
	}

	/**
	 * Testing hasEnoughArmies() function return value
	 */
	@Test
	@DisplayName("hasEnoughArmies() Attacker Army < Chosen Dice => FALSE")
	void hasEnoughArmiesAttackerLessDice() {
		Territory attacker = new Territory("testTerritory", "testContinent", 1);
		Territory opponent = new Territory("testTerritory", "testContinent", 10);
		int attacker_armies = 2;
		int opponent_armies = 2;
		assertFalse(gameController.hasEnoughArmies(attacker, opponent, attacker_armies, opponent_armies));
	}

	// =====================getNumberOfArmiesTest()=====================
	/**
	 * Testing getNumberOfArmies() function return value Testing for Turn = 1-6
	 * (TRUE cases)
	 */
	@Test
	@DisplayName("getNumberOfArmiesTRUETest() => TRUE cases")
	void getNumberOfArmiesTrueTest() {
		assertEquals(4, gameController.getNumberOfArmies(1));
		assertEquals(6, gameController.getNumberOfArmies(2));
		assertEquals(8, gameController.getNumberOfArmies(3));
		assertEquals(10, gameController.getNumberOfArmies(4));
		assertEquals(12, gameController.getNumberOfArmies(5));
		assertEquals(15, gameController.getNumberOfArmies(6));
	}

	/**
	 * Testing getNumberOfArmies() function return value Testing for Turn =
	 * 20,25,30,35 (FALSE cases)
	 */
	@Test
	@DisplayName("getNumberOfArmiesFALSETest() => FALSE cases")
	void getNumberOfArmiesFalseTest() {
		assertNotEquals(20, gameController.getNumberOfArmies(3));
		assertNotEquals(25, gameController.getNumberOfArmies(4));
		assertNotEquals(30, gameController.getNumberOfArmies(5));
		assertNotEquals(35, gameController.getNumberOfArmies(6));
	}

	// =====================isValidFortify()=====================
	/**
	 * Testing isValidFortify() function return value Should return TRUE for
	 * territories More than 1
	 */
	@Test
	@DisplayName("isValidFortify() for 3 Territory => TRUE")
	void isValidFortifyTRUETest() {
		Player player = new Player("testPlayer", true, 10, "REINFORCEMENT");
		Territory t1 = new Territory("testTerritory1", "testContinent", 10);
		Territory t2 = new Territory("testTerritory2", "testContinent", 10);
		Territory t3 = new Territory("testTerritory3", "testContinent", 10);
		player.ownedTerritories.add(t1);
		player.ownedTerritories.add(t2);
		player.ownedTerritories.add(t3);

		assertTrue(gameController.isValidFortify(player));
	}

	/**
	 * Testing isValidFortify() function return value Should return FALSE for
	 * territories less than 2
	 */
	@Test
	@DisplayName("isValidFortify() for 1 Territory => FALSE")
	void isValidFortifyFALSETest() {
		Player player = new Player("testPlayer", true, 10, "REINFORCEMENT");
		Territory t1 = new Territory("testTerritory1", "testContinent", 10);
		player.ownedTerritories.add(t1);

		assertFalse(gameController.isValidFortify(player));
	}

	// =====================validateFortifyMove()=====================
	/**
	 * Testing validateFortifyMove() function return value Should return TRUE for
	 * getNumberOfArmies is greater than 1
	 */
	@Test
	@DisplayName("validateFortifyMove() for Armies > 1 => TRUE")
	void validateFortifyMoveTRUETest() {
		Player player = new Player("testPlayer", true, 10, "REINFORCEMENT");
		Territory t1 = new Territory("testTerritory1", "testContinent", 0);
		Territory t2 = new Territory("testTerritory2", "testContinent", 10);
		player.ownedTerritories.add(t1);
		player.ownedTerritories.add(t2);

		assertTrue(gameController.validateFortifyMove(player, 1));
	}

	/**
	 * Testing validateFortifyMove() function return value Should return FALSE for
	 * getNumberOfArmies is less than 1
	 */
	@Test
	@DisplayName("validateFortifyMove() for Armies < 1 => FALSE")
	void validateFortifyMoveFALSETest() {
		Player player = new Player("testPlayer", true, 10, "REINFORCEMENT");
		Territory t1 = new Territory("testTerritory1", "testContinent", 0);
		Territory t2 = new Territory("testTerritory2", "testContinent", 10);
		player.ownedTerritories.add(t1);
		player.ownedTerritories.add(t2);

		assertFalse(gameController.validateFortifyMove(player, 0));
	}

	// =====================validateFortifyNumber()=====================
	/**
	 * Testing validateFortifyNumber() function return value Should return TRUE for
	 * currentNumArmies is greater than fortifyNum
	 */
	@Test
	@DisplayName("validateFortifyNumber() => TRUE")
	void validateFortifyNumberTRUETest() {
		Player player = new Player("testPlayer", true, 10, "REINFORCEMENT");
		Territory t1 = new Territory("testTerritory1", "testContinent", 3);
		player.ownedTerritories.add(t1);

		assertTrue(gameController.validateFortifyNumber(player, 0, 2));
	}

	/**
	 * Testing validateFortifyNumber() function return value Should return FALSE for
	 * currentNumArmies is less than fortifyNum
	 */
	@Test
	@DisplayName("validateFortifyNumber() => FALSE")
	void validateFortifyNumberFALSETest() {
		Player player = new Player("testPlayer", true, 10, "REINFORCEMENT");
		Territory t1 = new Territory("testTerritory", "testContinent", 2);
		player.ownedTerritories.add(t1);

		assertFalse(gameController.validateFortifyNumber(player, 0, 8));
	}

	// =====================getDomination()=====================
	/**
	 * Testing getDomination(continent,player) function return value Should return
	 * 25.0 for following condition
	 */
	@Test
	@DisplayName("getDomination(continent,player) => TRUE")
	void getDominationTest() {
		Continent continent = new Continent("testContinent", 16);

		Player player = new Player("testPlayer", false, 4, "REINFORCEMENT");
		Player player2 = new Player("testPlayer2", false, 12, "REINFORCEMENT");
		Territory t1 = new Territory("testTerritory", "testContinent", 4);
		t1.setRuler(player);
		Territory t2 = new Territory("testTerritory2", "testContinent", 4);
		Territory t3 = new Territory("testTerritory3", "testContinent", 4);
		Territory t4 = new Territory("testTerritory4", "testContinent", 4);
		t2.setRuler(player2);
		t3.setRuler(player2);
		t4.setRuler(player2);

		continent.addTerritories(t1);
		continent.addTerritories(t2);
		continent.addTerritories(t3);
		continent.addTerritories(t4);

		assertEquals(0, Double.compare(25.0, gameController.getDomination(continent, player)));
		assertEquals(0, Double.compare(75.0, gameController.getDomination(continent, player2)));
	}

	/**
	 * Testing getDomination(player, territories) function return value Should
	 * return 25.0 for following condition
	 */
	@Test
	@DisplayName("getDomination2(player, territories) => TRUE")
	void getDomination2Test() {
		Player player = new Player("testPlayer", false, 4, "REINFORCEMENT");
		Player player2 = new Player("testPlayer2", false, 12, "REINFORCEMENT");
		Territory t1 = new Territory("testTerritory", "testContinent", 4);
		t1.setRuler(player);
		Territory t2 = new Territory("testTerritory2", "testContinent", 4);
		Territory t3 = new Territory("testTerritory3", "testContinent", 4);
		Territory t4 = new Territory("testTerritory4", "testContinent", 4);
		t2.setRuler(player2);
		t3.setRuler(player2);
		t4.setRuler(player2);
		ArrayList<Territory> t = new ArrayList<Territory>();

		t.add(t1);
		t.add(t2);
		t.add(t3);
		t.add(t4);

		assertEquals(0, Double.compare(25.0, gameController.getDomination(player, t)));
		assertEquals(0, Double.compare(75.0, gameController.getDomination(player2, t)));
	}

	// =====================Cards Scenarios=====================
	// =====================loadCards()=====================
	/**
	 * Testing loadCards() function return value Should return 6,9,9,9 for 6,7,8,9
	 */
	@Test
	@DisplayName("loadCards() => return 6,9,9,9")
	void loadCardsTest() {

		assertEquals(6, gameController.loadCards(6).size());
		assertEquals(9, gameController.loadCards(7).size());
		assertEquals(9, gameController.loadCards(8).size());
		assertEquals(9, gameController.loadCards(9).size());

	}

	// =====================getStrongTerritory()=====================

	/**
	 * Testing getStrongTerritory() function return value
	 */
	@Test
	@DisplayName("getStrongTerritory()")
	void getStrongTerritory() {

		Player player = new Player("testPlayer", false, 8, "ADD");
		Territory t1 = new Territory("testTerritory", "testContinent", 6);
		Territory t2 = new Territory("testTerritory2", "testContinent", 4);
		t1.setRuler(player);
		t2.setRuler(player);
		player.getOwnedTerritories().add(t1);
		player.getOwnedTerritories().add(t2);

		Player player2 = new Player("testPlayer2", false, 8, "ADD");
		Territory t3 = new Territory("testTerritory3", "testContinent", 2);
		Territory t4 = new Territory("testTerritory4", "testContinent", 3);
		t3.setRuler(player2);
		t4.setRuler(player2);
		player2.getOwnedTerritories().add(t3);
		player2.getOwnedTerritories().add(t4);

		ArrayList<Territory> t = new ArrayList<Territory>();
		t.add(t1);
		t.add(t2);

		t3.setAdjacentTerritories(t);
		t4.setAdjacentTerritories(t);

		ArrayList<Territory> tt = new ArrayList<Territory>();
		tt.add(t3);
		tt.add(t4);

		t1.setAdjacentTerritories(tt);
		t2.setAdjacentTerritories(tt);

		assertEquals(t1, gameController.getStrongTerritory(player));

	}

	// =====================getWeakTerritory()=====================

	/**
	 * Testing getWeakTerritory() function return value
	 */
	@Test
	@DisplayName("getWeakTerritory()")
	void getWeakTerritory() {

		Player player = new Player("testPlayer", false, 8, "REINFORCEMENT");
		Territory t1 = new Territory("testTerritory", "testContinent", 6);
		Territory t2 = new Territory("testTerritory2", "testContinent", 4);
		Territory t3 = new Territory("testTerritory3", "testContinent", 1);
		t1.setRuler(player);
		t2.setRuler(player);
		t3.setRuler(player);
		player.getOwnedTerritories().add(t1);
		player.getOwnedTerritories().add(t2);
		player.getOwnedTerritories().add(t3);

		assertEquals(t3, gameController.getWeakTerritory(player));

	}

	// =====================calculateReinforcementArmiesForCheater()=====================

	/**
	 * Testing calculateReinforcementArmiesForCheater() function return value
	 */
	@Test
	@DisplayName("calculateReinforcementArmiesForCheater()")
	void calculateReinforcementArmiesForCheater() {

		Player player = new Player("testPlayer", false, 8, "REINFORCEMENT");
		Territory t1 = new Territory("testTerritory", "testContinent", 6);
		Territory t2 = new Territory("testTerritory2", "testContinent", 4);
		t1.setRuler(player);
		t2.setRuler(player);
		player.getOwnedTerritories().add(t1);
		player.getOwnedTerritories().add(t2);

		assertEquals(10, gameController.calculateReinforcementArmiesForCheater(player));

	}

	// =====================AttackStatus()=====================
	/**
	 * Testing AttackStatus() function return value
	 */
	@Test
	@DisplayName("AttackStatus()")
	void AttackStatus() {

		Player player = new Player("testPlayer", false, 8, "REINFORCEMENT");
		Territory t1 = new Territory("testTerritory", "testContinent", 6);
		t1.setRuler(player);

		Player player2 = new Player("testPlayer2", false, 12, "REINFORCEMENT");
		Territory t2 = new Territory("testTerritory2", "testContinent", 4);
		t2.setRuler(player2);

		AttackStatus attackStatus = gameController.attack(t1, t2, 3, 2);

		if (attackStatus.isHasWon()) {
			assertEquals(attackStatus.winner.getName(), player.getName());
		} else {
			assertFalse(attackStatus.isHasWon());
		}
	}

	// =====================EndGame=====================
	// =====================isWinner()=====================
	/**
	 * Testing isWinner() function return value
	 */
	@Test
	@DisplayName("isWinner()=>TRUE")
	void isWinnerTestTrue() {

		String mapFolder = System.getProperty("user.dir") + "/src/com/risk/tests/controller/maps/";
		String world = mapFolder + "world.map";
		MapMessage worldResult = mapToMapMessage(world);
		SetPlayersWindow setPlayersWindow = new SetPlayersWindow(worldResult.getMap());

		// Number of armies for 5 players
		GameController gc = new GameController();
		int numArmies = gc.getPlayersArmies(5);

		// Setting Players
		// 5 Players with different strategies
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(setPlayer("Player1", false, numArmies, "ADD", "Human"));

		// Setting Cards
		ArrayList<Card> cards = gc.loadCards(worldResult.map.getTerritories().size());

		// Passing all the Game elements to the function
		gameController = setPlayersWindow.setStartupPhase(worldResult.map, cards, playerList);

		assertTrue(gameController.isWinner());

	}

	/**
	 * Testing isWinner() function return value
	 */
	@Test
	@DisplayName("isWinner()=>FALSE")
	void isWinnerTestFalse() {

		String mapFolder = System.getProperty("user.dir") + "/src/com/risk/tests/controller/maps/";
		String world = mapFolder + "world.map";
		MapMessage worldResult = mapToMapMessage(world);
		SetPlayersWindow setPlayersWindow = new SetPlayersWindow(worldResult.getMap());

		// Number of armies for 5 players
		GameController gc = new GameController();
		int numArmies = gc.getPlayersArmies(5);

		// Setting Players
		// 5 Players with different strategies
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(setPlayer("Player1", false, numArmies, "ADD", "Human"));
		playerList.add(setPlayer("Player2", true, numArmies, "ADD", "AGGRESSIVE"));
		playerList.add(setPlayer("Player3", true, numArmies, "ADD", "BENEVOLENT"));
		playerList.add(setPlayer("Player4", true, numArmies, "ADD", "RANDOM"));
		playerList.add(setPlayer("Player5", true, numArmies, "ADD", "CHEATER"));

		// Setting Cards
		ArrayList<Card> cards = gc.loadCards(worldResult.map.getTerritories().size());

		// Passing all the Game elements to the function
		gameController = setPlayersWindow.setStartupPhase(worldResult.map, cards, playerList);

		assertFalse(gameController.isWinner());

	}

}