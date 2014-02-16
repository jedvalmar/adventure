package org.adventure.commands;

import org.adventure.PlayerCharacter;
import org.adventure.items.IItem;

public abstract class ItemCommand extends Action {
	Command command;
	@Override
	public void action(Command command, PlayerCharacter character) {
		this.command = command;
	}


	public IItem getItem(String itemToken, PlayerCharacter character) {
		String itemName = this.command.getItem(itemToken);
		IItem item = character.getCurrentRoom().getItem(itemName);
		if (item == null) {
			item = character.getItem(itemName);
		}
		if (item != null && item.commandAllowed(this, character)) {
			return item;			
		}
		character.sendMessage(new StringBuilder("Could not find the ").append(itemName).toString());
		return null;
	}


	

	
}
