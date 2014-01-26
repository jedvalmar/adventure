package org.adventure.commands;

import org.adventure.GameState;
import org.adventure.Item;

public class TakeCommand extends ItemCommand {

	
	@Override
	public String getVerb() {
		return "take";
	}

	@Override
	public void action() {
		Item leftHandItem = getState().getCharacter().getLeftHand();
		Item rightHandItem = getState().getCharacter().getRightHand();
		
		if (rightHandItem == null) {
			GameState.getState().getCharacter().setRightHand(getCurrentItem());
			getState().getCurrentRoom().removeItem(getCurrentItem());
		}
		else if (leftHandItem == null) {
			GameState.getState().getCharacter().setLeftHand(getCurrentItem());
			getState().getCurrentRoom().removeItem(getCurrentItem());
		}
		else {
			System.out.println("Your hands are full.");
		}
	}

}
