package com.risk.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.risk.model.Game;
import com.risk.model.GameInstructions;
import com.risk.model.Player;
import com.risk.view.StartWindow;

/**
 * Class for Tournament Game mode
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 *
 */
public class TournamentGame {
	
	/**
	 * Logger object setup for the log file
	 */
	static Logger logger = Logger.getLogger(StartWindow.class.getName());
	
	@SuppressWarnings("javadoc")
	private Game game;
	@SuppressWarnings("javadoc")
	private GameController controller;
	@SuppressWarnings("javadoc")
	private GameInstructions gameInstructions;
	@SuppressWarnings("javadoc")
	private Player currentPlayer;
	@SuppressWarnings("javadoc")
	private int turns;
	@SuppressWarnings("javadoc")
	private int count;
	@SuppressWarnings("javadoc")
	private String winner;

	/**
	 * Constructor sets game parameters
	 * 
	 * @param currentGame Game
	 * @param controller GameController
	 * @param numTurns Integer number of turns
	 */
	public TournamentGame(Game currentGame, GameController controller, int numTurns) {
		logger.log(Level.INFO, "Inside tournament home");
		this.game = currentGame;
		this.controller = controller;
		this.gameInstructions = controller.getGameInstructions(); 
		this.currentPlayer = currentGame.getCurrentPlayer();
		this.turns = numTurns;
		count = 0;
		this.winner = null;
	}
	
	/**
	 * Sets the current player to the next player
	 */
	protected void nextPlayer() {
		int indexOfCurrentPlayer = game.getPlayers().indexOf(currentPlayer);
		if(indexOfCurrentPlayer + 1 >= game.getPlayers().size()) {
			currentPlayer = game.getPlayers().get(0);
		}else {
			currentPlayer = game.getPlayers().get(indexOfCurrentPlayer + 1);
			if(currentPlayer.getPhase().equals("REINFORCEMENT")) {
				count++;
			}
		}
		game.setCurrentPlayer(currentPlayer);
	}
	
	/**
	 * On Game function
	 */
	public void onGame() {
		logger.log(Level.INFO, "");
		if(count == turns) {
			this.winner = "Draw";
			logger.log(Level.INFO, "The game is a draw.");
		}else {
			if(currentPlayer.getPhase().equals("ADD")) {
				controller.addArmyRandom(currentPlayer);
				nextPlayer();
				onGame();
			}else if(currentPlayer.getPhase().equals("REINFORCEMENT")) {
				currentPlayer.reinforce(null, gameInstructions, controller);
				onGame();
			}else if(currentPlayer.getPhase().equals("ATTACK")) {
				currentPlayer.attack(null, null, false, gameInstructions, controller);
				if(!controller.isWinner()) {
					onGame();
				}else {
					this.winner = currentPlayer.getName();
					logger.log(Level.INFO, this.winner + " has conquered the Map.");
				}
			}else if(currentPlayer.getPhase().equals("FORTIFY")) {
				currentPlayer.setFortificationStatus(false);
				currentPlayer.fortify(gameInstructions, controller, null, null, 0);
				nextPlayer();
				onGame();
			}
		}
	}

	/**
	 * Returns the winner of the game
	 * 
	 * @return the winner
	 */
	public String getWinner() {
		return winner;
	}

	/**
	 * Sets the winner
	 * 
	 * @param winner the winner to set
	 */
	public void setWinner(String winner) {
		this.winner = winner;
	}

}
