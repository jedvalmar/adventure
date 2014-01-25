package org.adventure.commands;

import org.adventure.GameState;
import org.adventure.Item;
import org.adventure.Room;

public abstract class ItemCommand extends Command{

	private Item currentItem = null;
	
	public abstract String getVerb();

	
	public Item getCurrentItem() {
		return currentItem;
	}


	public void setCurrentItem(Item currentItem) {
		this.currentItem = currentItem;
	}


	@Override
	public boolean contains(String value) {
		// is the first word of value = 'examine' or 'look' 
		// get the name of the object...
		// Is the object in sight (in the room or in hands)
		// Print the long description of the object.
		
		currentItem = null;
		if (value.toLowerCase().startsWith(getVerb().toLowerCase())) {
			String itemName = value.substring(getVerb().length() + 1);
			currentItem = getItem(itemName);
			return (currentItem != null);
		}
		
		return false;
	}

	
	private Item getItem(String itemName) {
		Item item = GameState.getState().getCurrentRoom().getItem(itemName);
		if (item == null) {
			item = GameState.getState().getCharacter().getLeftHand();
			if (item == null || item.getName().equals(itemName) == false) {
				item = GameState.getState().getCharacter().getRightHand();
			}
		}
		if (item.commandAllowed(this)) {
			return item;			
	}
		return null;
	}


	@Override
	public void action(Room room) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currentItem == null) ? 0 : currentItem.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCommand other = (ItemCommand) obj;
		if (getVerb() == null) {
			if (other.getVerb() != null)
				return false;
		} else if (!getVerb().equals(other.getVerb()))
			return false;
		return true;
	}



	
	
}
