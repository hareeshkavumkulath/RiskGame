package com.risk.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextPane;

public class InstructionsView extends JTextPane implements Observer{
	
	public InstructionsView(String instructionsMsg) {
		super();
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
