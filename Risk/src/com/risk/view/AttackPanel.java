package com.risk.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.risk.model.Game;
import com.risk.model.Player;
import com.risk.model.Territory;

import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JButton;

/**
 * Attack Panel to show the attack phase territory
 * 
 * @author Jingya Pan
 */
public class AttackPanel extends JPanel implements Observer {

	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("javadoc")
	public JList<String> attackingTerr;
	@SuppressWarnings("javadoc")
	public JList<String> attackedTerr;
	@SuppressWarnings("javadoc")
	public JButton btnAttack;
	@SuppressWarnings("javadoc")
	public JButton btnEndAttack;
	@SuppressWarnings("javadoc")
	public Game game;
	@SuppressWarnings("javadoc")
	private JLabel lblPlayer;
	@SuppressWarnings("javadoc")
	public JCheckBox chckbxAllOutMode;
	@SuppressWarnings("javadoc")
	private Player currentPlayer;

	/**
	 * Create the panel.
	 * 
	 * @param newGame Game current game
	 */
	public AttackPanel(Game newGame) {
		game = newGame;
		setBounds(648, 369, 726, 468);
		setLayout(null);

		JLabel labelName = new JLabel("Attack View");
		labelName.setBounds(15, 15, 138, 20);
		add(labelName);

		lblPlayer = new JLabel("");
		lblPlayer.setBounds(15, 50, 151, 20);
		add(lblPlayer);

		JLabel lblAttackedCountry = new JLabel("Attacking Territory");
		lblAttackedCountry.setBounds(181, 50, 206, 20);
		add(lblAttackedCountry);

		JLabel lblOpponentTerritory = new JLabel("Opponent Territory");
		lblOpponentTerritory.setBounds(448, 50, 197, 20);
		add(lblOpponentTerritory);

		JScrollPane attackingTerrPane = new JScrollPane((Component) null);
		attackingTerrPane.setBounds(179, 85, 247, 271);
		attackingTerrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(attackingTerrPane);

		attackingTerr = new JList<String>();
		attackingTerrPane.setViewportView(attackingTerr);

		JScrollPane attackedTerrPane = new JScrollPane((Component) null);
		attackedTerrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		attackedTerrPane.setBounds(448, 85, 247, 271);
		add(attackedTerrPane);

		attackedTerr = new JList<String>();
		attackedTerrPane.setViewportView(attackedTerr);

		chckbxAllOutMode = new JCheckBox("All Out Mode");
		chckbxAllOutMode.setBounds(259, 390, 167, 29);
		add(chckbxAllOutMode);

		btnAttack = new JButton("Attack");
		btnAttack.setBounds(442, 390, 115, 29);
		add(btnAttack);

		btnEndAttack = new JButton("End Attack");
		btnEndAttack.setBounds(572, 390, 123, 29);
		add(btnEndAttack);
		setVisible(false);

		currentPlayer = this.game.getCurrentPlayer();
		lblPlayer.setText(currentPlayer.getName());

		ArrayList<Territory> attackingTerritories;

		lblPlayer.setText(currentPlayer.getName());

		attackingTerritories = currentPlayer.getOwnedTerritories();
		String[] attackingTerrNames = new String[attackingTerritories.size()];
		for (int i = 0; i < attackingTerritories.size(); i++) {
			attackingTerrNames[i] = attackingTerritories.get(i).getName() + "-"
					+ attackingTerritories.get(i).getContinent() + "(" + attackingTerritories.get(i).getNumberOfArmies()
					+ ")";
		}
		attackingTerr.setListData(attackingTerrNames);

		attackingTerr.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {

					currentPlayer = game.getCurrentPlayer();

					int selectedIndex = attackingTerr.getSelectedIndex();
					ArrayList<Territory> attackedTerritories = currentPlayer.getOwnedTerritories().get(selectedIndex)
							.getAdjacentTerritories();
					String[] attackedTerrNames = new String[attackedTerritories.size()];
					for (int i = 0; i < attackedTerrNames.length; i++) {
						Territory tempTerritory = attackedTerritories.get(i);
						attackedTerrNames[i] = tempTerritory.getName() + "-" + tempTerritory.getContinent() + "("
								+ tempTerritory.getRuler().getName() + "-" + tempTerritory.getNumberOfArmies() + ")";
					}
					attackedTerr.setListData(attackedTerrNames);
				} catch (Exception ex) {
					// logger.log(Level.INFO, "Exception in attacking territory JList" +
					// ex.toString());
				}
			}
		});

	}

	/**
	 * Observer design pattern
	 *
	 * @param obs Observable
	 * @param obj Object
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable obs, Object obj) {
		this.game = (Game) obj;
		Player currentPlayer = this.game.getCurrentPlayer();
		lblPlayer.setText(currentPlayer.getName());

		int attackingTerrSelectedIndex = attackingTerr.getSelectedIndex();
		int adjTerrSelectedIndex = attackedTerr.getSelectedIndex();
		attackingTerr.setSelectedIndex(attackingTerrSelectedIndex);
		if (attackingTerrSelectedIndex < 0) {
			attackingTerrSelectedIndex = 0;
		}
		if (adjTerrSelectedIndex < 0) {
			adjTerrSelectedIndex = 0;
		}
		ArrayList<Territory> attackingTerritories = currentPlayer.getOwnedTerritories();
		String[] territoryNames = new String[attackingTerritories.size()];
		for (int i = 0; i < attackingTerritories.size(); i++) {
			territoryNames[i] = attackingTerritories.get(i).getName();
			if (attackingTerritories.get(i).getRuler() != null) {
				territoryNames[i] = attackingTerritories.get(i).getName() + "-"
						+ attackingTerritories.get(i).getContinent() + "("
						+ attackingTerritories.get(i).getNumberOfArmies() + ")";
			}
		}
		attackingTerr.setListData(territoryNames);
		attackingTerr.setSelectedIndex(attackingTerrSelectedIndex);

		try {
			int selectedIndex = attackingTerr.getSelectedIndex();
			ArrayList<Territory> attackedTerritories = currentPlayer.getOwnedTerritories().get(selectedIndex)
					.getAdjacentTerritories();
			String[] attackedTerrNames = new String[attackedTerritories.size()];
			for (int i = 0; i < attackedTerrNames.length; i++) {
				Territory tempTerritory = attackedTerritories.get(i);
				attackedTerrNames[i] = tempTerritory.getName() + "-" + tempTerritory.getContinent() + "("
						+ tempTerritory.getRuler().getName() + "-" + tempTerritory.getNumberOfArmies() + ")";
			}
			attackedTerr.setListData(attackedTerrNames);
			attackedTerr.setSelectedIndex(adjTerrSelectedIndex);
		} catch (Exception ex) {
			// logger.log(Level.INFO, "Exception in attacking territory JList" +
			// ex.toString());
		}

		lblPlayer.setText(currentPlayer.getName());

	}

	/**
	 * Get the attacking Territory
	 * 
	 * @return JList attackingTerr
	 */
	public JList<String> getAttackingTerr() {
		return attackingTerr;
	}

	/**
	 * Set the attacking Territory
	 * 
	 * @param attackingTerr list of attacking territory
	 */
	public void setAttackingTerr(JList<String> attackingTerr) {
		this.attackingTerr = attackingTerr;
	}

	/**
	 * Get the attacked Territory
	 * 
	 * @return JList attackedTerr
	 */
	public JList<String> getAttackedTerr() {
		return attackedTerr;
	}

	/**
	 * Set the attacked Territory
	 * 
	 * @param attackedTerr list of attacked territory
	 */
	public void setAttackedTerr(JList<String> attackedTerr) {
		this.attackedTerr = attackedTerr;
	}

	/**
	 * Get the btnAttack
	 * 
	 * @return JButton btnAttack
	 */
	public JButton getBtnAttack() {
		return btnAttack;
	}

	/**
	 * Set the btnAttack
	 * 
	 * @param btnAttack button
	 */
	public void setBtnAttack(JButton btnAttack) {
		this.btnAttack = btnAttack;
	}

	/**
	 * Get the btnEndAttack
	 * 
	 * @return btnEndAttack button
	 */
	public JButton getBtnEndAttack() {
		return btnEndAttack;
	}

	/**
	 * Set the btnEndAttack
	 * 
	 * @param btnEndAttack button
	 */
	public void setBtnEndAttack(JButton btnEndAttack) {
		this.btnEndAttack = btnEndAttack;
	}

	/**
	 * Get the chckbxAllOutMode
	 * 
	 * @return chckbxAllOutMode
	 */
	public JCheckBox getChckbxAllOutMode() {
		return chckbxAllOutMode;
	}

	/**
	 * Set the chckbxAllOutMode
	 * 
	 * @param chckbxAllOutMode checkbox
	 */
	public void setChckbxAllOutMode(JCheckBox chckbxAllOutMode) {
		this.chckbxAllOutMode = chckbxAllOutMode;
	}

}
