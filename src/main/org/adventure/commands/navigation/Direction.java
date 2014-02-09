package org.adventure.commands.navigation;

public enum Direction {
	NORTH("N", "North", "Go"), 
	SOUTH("S","South","Go"), 
	EAST, 
	WEST,
	DOOR("Go");
	
	private String[] validValues;
	private Direction(String... validValues) {
		this.validValues = validValues;
	}
	public String[] getValidValues() {
		return validValues;
	}
	
	
}
