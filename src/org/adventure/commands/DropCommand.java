package org.adventure.commands;

import org.adventure.GameState;
import org.adventure.Item;
import org.adventure.Room;

public class DropCommand extends ItemCommand {

	@Override
	public String getVerb() {
		return "drop";
	}

	@Override
	public void action(Room room) {
		// Is the item in your hand.
		room.addItem(getCurrentItem());
		//Remove it from your hand.
		Item leftItem = GameState.getState().getCharacter().getLeftHand();
		if (leftItem != null && leftItem.equals(getCurrentItem())) {
			GameState.getState().getCharacter().setLeftHand(null);
		}
		Item rightItem = GameState.getState().getCharacter().getRightHand();
		if (rightItem != null && rightItem.equals(getCurrentItem())) {
			GameState.getState().getCharacter().setRightHand(null);
		}
		
	}

}
