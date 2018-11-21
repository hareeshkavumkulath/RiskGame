package com.risk.model;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.risk.controller.GameController;
import com.risk.view.StartWindow;

public class TournamentGame {
	
	/**
	 * Logger object setup for the log file
	 */
	static Logger logger = Logger.getLogger(StartWindow.class.getName());
	
	private Game game;
	private GameController controller;
	private GameInstructions gameInstructions;
	private Player currentPlayer;
	private int turns;
	private int count;
	private String winner;

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
				currentPlayer.reinforce(gameInstructions, controller);
				onGame();
			}else if(currentPlayer.getPhase().equals("ATTACK")) {
				currentPlayer.attack(gameInstructions, controller, game);
				if(!controller.isWinner()) {
					onGame();
				}else {
					this.winner = currentPlayer.getName();
					logger.log(Level.INFO, this.winner + " has conquered the Map.");
				}
			}else if(currentPlayer.getPhase().equals("FORTIFY")) {
				currentPlayer.setFortificationStatus(false);
				currentPlayer.fortify(gameInstructions, controller);
				nextPlayer();
				onGame();
			}
		}
	}

	/**
	 * @return the winner
	 */
	public String getWinner() {
		return winner;
	}

	/**
	 * @param winner the winner to set
	 */
	public void setWinner(String winner) {
		this.winner = winner;
	}

}
