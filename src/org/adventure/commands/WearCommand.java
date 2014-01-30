package org.adventure.commands;

import org.adventure.GameState;
import org.adventure.items.IItem;

public class WearCommand extends ItemCommand {
	public WearCommand() {
		super();
		this.addVerb("wear");
	}
	
	@Override
	public void action(Command command) {
		super.action(command);
		IItem leftHandItem = getState().getCharacter().getLeftHand();
		IItem rightHandItem = getState().getCharacter().getRightHand();
		
		if (getCurrentItem().equals(rightHandItem)) {
			GameState.getState().getCharacter().wear(getCurrentItem());
			getState().getCharacter().setRightHand(null);
		}
		else if (getCurrentItem().equals(leftHandItem)) {
			GameState.getState().getCharacter().wear(getCurrentItem());
			getState().getCharacter().setLeftHand(null);
		}
	}
}
