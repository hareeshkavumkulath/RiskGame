package com.risk.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.risk.controller.GameController;
import com.risk.model.AttackStatus;
import com.risk.model.Card;
import com.risk.model.CardsObservable;
import com.risk.model.Continent;
import com.risk.model.Game;
import com.risk.model.GameInstructions;
import com.risk.model.Map;
import com.risk.model.Player;
import com.risk.model.Territory;

/**
 * This class implements Game Window pages functionalities and design user interface.
 * 
 * @author Hareesh Kavumkulath
 * @version 1.1
 */
public class GameWindow extends JFrame{

	private Game game;
	private GameController controller;
	private GameInstructions gameInstructions;
	private Player currentPlayer;
	private InstructionsView instructionsPane;
	private AddArmyPanel addArmyPanel;

	/**
	 * Create the application.
	 * 
	 * @param map Map pass map model to GameWindow.java
	 */
	public GameWindow(Game currentGame, GameController controller) {
		this.game = currentGame;
		this.controller = controller;
		this.gameInstructions = controller.getGameInstructions(); 
		this.currentPlayer = game.getCurrentPlayer();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Risk");
		getContentPane().setForeground(Color.GREEN);
		setBounds(100, 100, 1926, 909);
		getContentPane().setLayout(null);
		
		// Map View Panel
		MapViewPanel mapViewPanel = new MapViewPanel(game);
		mapViewPanel.setBounds(15, 16, 632, 302);
		getContentPane().add(mapViewPanel);
		game.addObserver(mapViewPanel);
		
		//Display Player World Domination
		WorldDominationView worldDomViewPanel = new WorldDominationView(game);
		worldDomViewPanel.setBounds(15, 369, 618, 371);
		getContentPane().add(worldDomViewPanel);
		game.addObserver(worldDomViewPanel);
		
		// Display Instructions Pane
		instructionsPane = new InstructionsView(gameInstructions.getInstructions());
		gameInstructions.addObserver(instructionsPane);
				
		JScrollPane instructionsScrollPane = new JScrollPane(instructionsPane);
		instructionsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		instructionsScrollPane.setBounds(1390, 16, 499, 302);
		getContentPane().add(instructionsScrollPane);
				
		setVisible(true);
		
		// Add Army Panel
		addArmyPanel = new AddArmyPanel(game);
		getContentPane().add(addArmyPanel);
		game.addObserver(addArmyPanel);
	}


}
