/**
 * 
 */
package com.risk.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import com.risk.model.Card;
import com.risk.model.CardsObservable;
import com.risk.model.Player;

/**
 * @author Hareesh Kavumkulath
 *
 */
public class CardsJList extends JPanel implements Observer{
	
	/**
	 * create a random serialVersionUID
	 */
	private static final long serialVersionUID = -3174204953083961860L;;
	private JList<String> list = new JList<String>();
	JButton btnNewButton = new JButton("Turn In");

	public CardsJList() {
		super();
		setBounds(1051, 106, 324, 205);
		setLayout(null);
		
		setVisible(false);
		
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		JScrollPane scrollPane_4 = new JScrollPane(list);
		scrollPane_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_4.setBounds(15, 16, 156, 173);
		add(scrollPane_4);
		
		btnNewButton.setBounds(186, 160, 115, 29);
		add(btnNewButton);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		CardsObservable obj = (CardsObservable)arg1;
		Player player = obj.getPlayer();
		ArrayList<Card> cards = player.getCards();
		String[] cardTypes = new String[cards.size()];
		for(int i=0;i<cards.size();i++) {
			cardTypes[i] = cards.get(i).getArmyType();
		}
		list.setListData(cardTypes);
	}
	/**
	 * Get button
	 * 
	 * @return JButton btnNewButton
	 */
	public JButton getBtnNewButton() {
		return btnNewButton;
	}
	
	/**
	 * Get card list
	 * 
	 * @return JList card list
	 */
	public JList<String> getList() {
		return list;
	}

}
