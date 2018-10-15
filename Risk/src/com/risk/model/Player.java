/**
 * 
 */
package com.risk.model;

import java.util.ArrayList;

/**
 * @author Hareesh Kavumkulath
 *
 */
public class Player {
	
	public String name;
	public int numberOfArmies;
	public ArrayList<Territory> ownedTerritories = new ArrayList<Territory>();
	public ArrayList<Continent> ownedContinents = new ArrayList<Continent>();
	public boolean isComputer;
	
	public Player(String name, boolean isComputer, int numberOfArmies) {
		this.name = name;
		this.isComputer = isComputer;
		this.numberOfArmies = numberOfArmies;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfArmies() {
		return numberOfArmies;
	}
	public void setNumberOfArmies(int numberOfArmies) {
		this.numberOfArmies = numberOfArmies;
	}
	public ArrayList<Territory> getOwnedTerritories() {
		return ownedTerritories;
	}
	public void setOwnedTerritories(ArrayList<Territory> ownedTerritories) {
		this.ownedTerritories = ownedTerritories;
	}
	public ArrayList<Continent> getOwnedContinents() {
		return ownedContinents;
	}
	public void setOwnedContinents(ArrayList<Continent> ownedContinents) {
		this.ownedContinents = ownedContinents;
	}
	public boolean isComputer() {
		return isComputer;
	}
	public void setComputer(boolean isComputer) {
		this.isComputer = isComputer;
	}	

}
