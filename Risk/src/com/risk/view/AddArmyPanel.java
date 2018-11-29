package com.risk.view;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.risk.model.Card;
import com.risk.model.Game;
import com.risk.model.Player;
import com.risk.model.Territory;

/**
 * Add Army JPanel
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 *
 */
public class AddArmyPanel extends JPanel implements Observer {

	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("javadoc")
	public JButton btnTurnInCards;
	@SuppressWarnings("javadoc")
	public JButton btnAddArmy;
	@SuppressWarnings("javadoc")
	public JList<String> addArmyCardslist;
	@SuppressWarnings("javadoc")
	public JList<String> addArmyTerrlist;
	@SuppressWarnings("javadoc")
	private JLabel plyrNameAddArmyLbl;
	@SuppressWarnings("javadoc")
	public JButton btnReinforceArmy;
	@SuppressWarnings("javadoc")
	public JButton btnReinforceAddArmy;

	/**
	 * add army
	 * 
	 * @param game game object
	 */
	public AddArmyPanel(Game game) {
		Player currentPlayer = game.getCurrentPlayer();

		setBounds(662, 16, 700, 302);
		setLayout(null);
		setVisible(true);

		plyrNameAddArmyLbl = new JLabel(currentPlayer.getName());
		plyrNameAddArmyLbl.setBounds(15, 16, 69, 20);
		add(plyrNameAddArmyLbl);

		JScrollPane addArmyTerrScrollPane = new JScrollPane((Component) null);
		addArmyTerrScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		addArmyTerrScrollPane.setBounds(15, 52, 240, 234);
		add(addArmyTerrScrollPane);

		addArmyTerrlist = new JList<String>();
		addArmyTerrScrollPane.setViewportView(addArmyTerrlist);

		JScrollPane addArmyCardScrollPane = new JScrollPane((Component) null);
		addArmyCardScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		addArmyCardScrollPane.setBounds(270, 52, 184, 234);
		add(addArmyCardScrollPane);

		addArmyCardslist = new JList<String>();
		addArmyCardScrollPane.setViewportView(addArmyCardslist);

		btnAddArmy = new JButton("Add");
		btnAddArmy.setBounds(508, 212, 151, 29);
		add(btnAddArmy);

		btnReinforceAddArmy = new JButton("Add");
		btnReinforceAddArmy.setBounds(508, 212, 151, 29);
		add(btnReinforceAddArmy);
		btnReinforceAddArmy.setVisible(false);

		btnTurnInCards = new JButton("Turn In Cards");
		btnTurnInCards.setBounds(508, 257, 151, 29);
		add(btnTurnInCards);

		btnReinforceArmy = new JButton("Reinforce");
		btnReinforceArmy.setBounds(508, 167, 151, 29);
		add(btnReinforceArmy);

		// Populating fields
		ArrayList<Territory> territories;
		territories = currentPlayer.getOwnedTerritories();
		String[] territoriesNames = new String[territories.size()];
		for (int i = 0; i < territories.size(); i++) {
			territoriesNames[i] = territories.get(i).getName() + "(" + territories.get(i).getNumberOfArmies() + ")";
		}
		addArmyTerrlist.setListData(territoriesNames);

		ArrayList<Card> cards = currentPlayer.getCards();
		String[] cardNames = new String[cards.size()];
		for (int i = 0; i < cards.size(); i++) {
			cardNames[i] = cards.get(i).getArmyType();
		}
		addArmyCardslist.setListData(cardNames);

	}

	/**
	 * observer design pattern
	 * 
	 * @param arg0 Observable
	 * @param arg1 Object
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		Game game = (Game) arg1;
		Player currentPlayer = game.getCurrentPlayer();
		// Populating fields
		plyrNameAddArmyLbl.setText(currentPlayer.getName());
		ArrayList<Territory> territories;
		territories = currentPlayer.getOwnedTerritories();
		String[] territoriesNames = new String[territories.size()];
		for (int i = 0; i < territories.size(); i++) {
			territoriesNames[i] = territories.get(i).getName() + "(" + territories.get(i).getNumberOfArmies() + ")";
		}
		addArmyTerrlist.setListData(territoriesNames);

		ArrayList<Card> cards = currentPlayer.getCards();
		String[] cardNames = new String[cards.size()];
		for (int i = 0; i < cards.size(); i++) {
			cardNames[i] = cards.get(i).getArmyType();
		}
		addArmyCardslist.setListData(cardNames);
	}

	/**
	 * Get the btnTurnInCards
	 * 
	 * @return the btnTurnInCards
	 */
	public JButton getBtnTurnInCards() {
		return btnTurnInCards;
	}

	/**
	 * Set the btnTurnInCards
	 * 
	 * @param btnTurnInCards the btnTurnInCards to set
	 */
	public void setBtnTurnInCards(JButton btnTurnInCards) {
		this.btnTurnInCards = btnTurnInCards;
	}

	/**
	 * Get the btnAddArmy
	 * 
	 * @return the btnAddArmy
	 */
	public JButton getBtnAddArmy() {
		return btnAddArmy;
	}

	/**
	 * Set the btnAddArmy
	 * 
	 * @param btnAddArmy the btnAddArmy to set
	 */
	public void setBtnAddArmy(JButton btnAddArmy) {
		this.btnAddArmy = btnAddArmy;
	}

	/**
	 * Get addArmyCardslist
	 * 
	 * @return the addArmyCardslist
	 */
	public JList<String> getAddArmyCardslist() {
		return addArmyCardslist;
	}

	/**
	 * Set addArmyCardslist
	 * 
	 * @param addArmyCardslist the addArmyCardslist to set
	 */
	public void setAddArmyCardslist(JList<String> addArmyCardslist) {
		this.addArmyCardslist = addArmyCardslist;
	}

	/**
	 * Get addArmyTerrlist
	 * 
	 * @return the addArmyTerrlist
	 */
	public JList<String> getAddArmyTerrlist() {
		return addArmyTerrlist;
	}

	/**
	 * Set addArmyTerrlist
	 * 
	 * @param addArmyTerrlist the addArmyTerrlist to set
	 */
	public void setAddArmyTerrlist(JList<String> addArmyTerrlist) {
		this.addArmyTerrlist = addArmyTerrlist;
	}

	/**
	 * Get btnReinforceArmy
	 * 
	 * @return the btnReinforceArmy
	 */
	public JButton getBtnReinforceArmy() {
		return btnReinforceArmy;
	}

	/**
	 * Set btnReinforceArmy
	 * 
	 * @param btnReinforceArmy the btnReinforceArmy to set
	 */
	public void setBtnReinforceArmy(JButton btnReinforceArmy) {
		this.btnReinforceArmy = btnReinforceArmy;
	}

	/**
	 * Get btnReinforceAddArmy
	 * 
	 * @return the btnReinforceAddArmy
	 */
	public JButton getBtnReinforceAddArmy() {
		return btnReinforceAddArmy;
	}

	/**
	 * Set btnReinforceAddArmy
	 * 
	 * @param btnReinforceAddArmy the btnReinforceAddArmy to set
	 */
	public void setBtnReinforceAddArmy(JButton btnReinforceAddArmy) {
		this.btnReinforceAddArmy = btnReinforceAddArmy;
	}

}
