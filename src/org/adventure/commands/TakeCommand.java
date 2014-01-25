package org.adventure.commands;

import org.adventure.GameState;
import org.adventure.Item;
import org.adventure.Room;

public class TakeCommand extends ItemCommand {

	
	@Override
	public String getVerb() {
		return "take";
	}

	@Override
	public void action(Room room) {
		Item leftHandItem = GameState.getState().getCharacter().getLeftHand();
		Item rightHandItem = GameState.getState().getCharacter().getRightHand();
		
		if (rightHandItem == null) {
			GameState.getState().getCharacter().setRightHand(getCurrentItem());
			room.removeItem(getCurrentItem());
		}
		else if (leftHandItem == null) {
			GameState.getState().getCharacter().setLeftHand(getCurrentItem());
			room.removeItem(getCurrentItem());
		}
		else {
			System.out.println("Your hands are full.");
		}
	}

}
