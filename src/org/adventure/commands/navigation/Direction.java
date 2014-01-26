package org.adventure.commands.navigation;

public enum Direction {
	NORTH("N", "North", "Go North"), 
	SOUTH("S","South","Go South"), 
	EAST, 
	WEST,
	DOOR("Go Door");
	
	private String[] validValues;
	private Direction(String... validValues) {
		this.validValues = validValues;
	}
	public String[] getValidValues() {
		return validValues;
	}
	
	
}
