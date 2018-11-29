package com.risk.view;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;

/**
 * The InstructionsView class shows instructions for player to play this game.
 * This class sets up the user interface for instructions.
 * 
 * @author Hareesh Kavumkulath
 * @version 1.1
 */

public class InstructionsView extends JTextPane implements Observer {

	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = 7597048260648030086L;

	/**
	 * This is a constructor. It sets instructionsMsg messages in a certain format.
	 * 
	 * @param instructionsMsg This is the message information and set it into a certain user interface
	 */
	public InstructionsView(String instructionsMsg) {
		super();
		setFont(new Font("Calibri", Font.PLAIN, 18));
		setForeground(Color.RED);
		setText(instructionsMsg);
		setBounds(46, 148, 300, 500);
		setEditable(false);
		repaint();
	}

	@Override
	/**
	 * This is a function calling the update method. It shows the latest situation.
	 * 
	 * @param o This is a name "o" by using Observable class
	 * @param arg This is a name "arg" by using java Object class
	 */
	public void update(Observable o, Object arg) {
		String message = getText();
		if (arg.toString().equals("")) {
			setText(arg.toString());
		} else {
			message = message + arg.toString();
			setText(message);
		}
		repaint();
	}

}
