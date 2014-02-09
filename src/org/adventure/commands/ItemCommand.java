package org.adventure.commands;

import org.adventure.GameState;
import org.adventure.items.Item;

public abstract class ItemCommand extends Action {
	Command command;
	@Override
	public void action(Command command) {
		this.command = command;
	}


	public Item getItem(String itemToken) {
		String itemName = this.command.getItemMap().get(itemToken);
		Item item = (Item)GameState.getState().getCurrentRoom().getItem(itemName);
		if (item == null) {
			item = (Item)GameState.getState().getCharacter().getLeftHand();
			if (item == null || item.getName().equals(itemName) == false) {
				item = (Item)GameState.getState().getCharacter().getRightHand();
			}
		}
		if (item != null && item.commandAllowed(this)) {
			return item;			
		}
		return null;
	}


	

	
}
