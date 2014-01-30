package org.adventure.commands;

import org.adventure.GameState;
import org.adventure.items.IItem;

public class DropCommand extends ItemCommand {

	
	public DropCommand() {
		super();
		this.addVerb("drop");
	}



	@Override
	public void action(Command command) {
		super.action(command);
		// Is the item in your hand.
		getState().getCurrentRoom().addItems(getCurrentItem());
		//Remove it from your hand.
		IItem leftItem = GameState.getState().getCharacter().getLeftHand();
		if (leftItem != null && leftItem.equals(getCurrentItem())) {
			GameState.getState().getCharacter().setLeftHand(null);
		}
		IItem rightItem = GameState.getState().getCharacter().getRightHand();
		if (rightItem != null && rightItem.equals(getCurrentItem())) {
			GameState.getState().getCharacter().setRightHand(null);
		}
		
	}

}
