package org.adventure;

import org.adventure.commands.TakeCommand;

public class GameState {

	private static GameState instance = new GameState();
	
	public TakeCommand takeCommand = new TakeCommand();
	
	private Character character = new Character();
	private Room currentRoom;
	private boolean end;
	
	public static GameState getState() {
		return instance;
	}
	
	
	public void gotoRoom(Room room) {
		this.currentRoom = room;
	}


	public Character getCharacter() {
		return character;
	}


	public Room getCurrentRoom() {
		return currentRoom;
	}


	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}


	public boolean isEnd() {
		return end;
	}


	public void setEnd(boolean end) {
		this.end = end;
	}


	public void setCharacter(Character character) {
		this.character = character;
	}
	
	
	
}
