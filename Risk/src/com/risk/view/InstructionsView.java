package com.risk.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextPane;
import java.awt.Color;

public class InstructionsView extends JTextPane implements Observer{
	
	public InstructionsView(String instructionsMsg) {
		super();
		setForeground(Color.RED);
		setText(instructionsMsg);
		setBounds(46, 148, 300, 500);
		setEditable(false);
	}

	@Override
	public void update(Observable o, Object arg) {
		String message = getText();
		message = message + "\r\n" + arg.toString();
		setText(message);
	}

}
