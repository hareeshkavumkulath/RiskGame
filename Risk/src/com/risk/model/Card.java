/**
 * 
 */
package com.risk.model;

/**
 * Model class for Cards
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 *
 */
public class Card {

	public String armyType;
	public int numArmies;
	public Territory territory;
	
	public String getArmyType() {
		return armyType;
	}
	public void setArmyType(String armyType) {
		this.armyType = armyType;
	}
	public int getNumArmies() {
		return numArmies;
	}
	public void setNumArmies(int numArmies) {
		this.numArmies = numArmies;
	}
	public Territory getTerritory() {
		return territory;
	}
	public void setTerritory(Territory territory) {
		this.territory = territory;
	}
	
	public void addArmy(String armyType) {
		if(armyType.equalsIgnoreCase("Infantry")) {
			this.numArmies = 1;
		}else if(armyType.equalsIgnoreCase("Cavalry")) {
			this.numArmies = 5;
		}else {
			this.numArmies = 10;
		}
	}
	
}
