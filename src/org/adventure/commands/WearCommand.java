package org.adventure.commands;

import org.adventure.PlayerCharacter;
import org.adventure.items.IItem;

public class WearCommand extends ItemCommand {
	public WearCommand() {
		super();
		this.addCommandPattern("wear <item>");
	}
	
	@Override
	public void action(Command command, PlayerCharacter character) {
		super.action(command, character);
		IItem leftHandItem = character.getLeftHand();
		IItem rightHandItem = character.getRightHand();
		
		IItem item = getItem("<item>", character);
		if (item != null) {
			if (item.equals(rightHandItem)) {
				character.setRightHand(null);
			}
			else if (item.equals(leftHandItem)) {
				character.setLeftHand(null);
			}	
			if (character.wear(item)) {
				character.sendMessage(new StringBuilder("You put on the ").append(command.getItem("<item>")).toString());				
			}
			else {
				
			}
		}
	}
}
