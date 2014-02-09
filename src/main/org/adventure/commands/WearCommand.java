package org.adventure.commands;

import org.adventure.GameState;
import org.adventure.items.IItem;

public class WearCommand extends ItemCommand {
	public WearCommand() {
		super();
		this.addCommandPattern("wear <item>");
	}
	
	@Override
	public void action(Command command) {
		super.action(command);
		IItem leftHandItem = getState().getCharacter().getLeftHand();
		IItem rightHandItem = getState().getCharacter().getRightHand();
		
		if (getItem("<item>").equals(rightHandItem)) {
			GameState.getState().getCharacter().wear(getItem("<item>"));
			getState().getCharacter().setRightHand(null);
		}
		else if (getItem("<item>").equals(leftHandItem)) {
			GameState.getState().getCharacter().wear(getItem("<item>"));
			getState().getCharacter().setLeftHand(null);
		}
	}
}
