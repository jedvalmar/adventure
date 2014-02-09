package org.adventure.commands;

import org.adventure.GameState;
import org.adventure.items.IItem;

public class DropCommand extends ItemCommand {

	
	public DropCommand() {
		super();
		this.addCommandPattern("drop <item>");
	}



	@Override
	public void action(Command command) {
		super.action(command);
		// Is the item in your hand.
		getState().getCurrentRoom().addItems(getItem("<item>"));
		//Remove it from your hand.
		IItem leftItem = GameState.getState().getCharacter().getLeftHand();
		if (leftItem != null && leftItem.equals(getItem("<item>"))) {
			GameState.getState().getCharacter().setLeftHand(null);
		}
		IItem rightItem = GameState.getState().getCharacter().getRightHand();
		if (rightItem != null && rightItem.equals(getItem("<item>"))) {
			GameState.getState().getCharacter().setRightHand(null);
		}
		
	}

}
