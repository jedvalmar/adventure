package org.adventure.commands;

import org.adventure.GameState;
import org.adventure.items.Item;

public abstract class ItemCommand extends Action {

	private Item subject = null;
	
	public Item getCurrentItem() {
		return subject;
	}


	public void setCurrentItem(Item currentItem) {
		this.subject = currentItem;
	}


	@Override
	public void action(Command command) {
		this.subject = getItem(command.getSubject());
	}


	private Item getItem(String itemName) {
		Item item = GameState.getState().getCurrentRoom().getItem(itemName);
		if (item == null) {
			item = GameState.getState().getCharacter().getLeftHand();
			if (item == null || item.getName().equals(itemName) == false) {
				item = GameState.getState().getCharacter().getRightHand();
			}
		}
		if (item != null && item.commandAllowed(this)) {
			return item;			
		}
		return null;
	}


	

	
}
