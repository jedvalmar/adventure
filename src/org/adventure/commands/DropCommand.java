package org.adventure.commands;

import org.adventure.GameState;
import org.adventure.Item;

public class DropCommand extends ItemCommand {

	@Override
	public String getVerb() {
		return "drop";
	}

	@Override
	public void action() {
		// Is the item in your hand.
		getState().getCurrentRoom().addItems(getCurrentItem());
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
