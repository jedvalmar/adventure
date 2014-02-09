package org.adventure;
import java.util.ArrayList;
import java.util.List;

import org.adventure.commands.Action;
import org.adventure.items.IItem;



public class Room implements IContainer {
	private List<Action> validCommands = new ArrayList<Action>();
	String description;
	List<IItem> items = new ArrayList<IItem>();
	
	public String getDescription() {
		return description;
	}

	public Room setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getPrompt() {
		StringBuilder sb = new StringBuilder();
		sb.append(getDescription());
		if (items.size() > 0) {
			sb.append(" In the room is: ");
			for (IItem item : items) {
				sb.append(item.getDescription());
			}			
		}
		return sb.toString();
	}
	
	public Room addItems(IItem... itemsToAdd) {
		for (IItem item : itemsToAdd) {
			items.add(item);			
		}
		return this;
	}

	@Override
	public boolean removeItem(IItem item) {
		return items.remove(item);
	}
	
	public IItem getItem(String itemName) {
		for (IItem item : items) {
			if (item.getName().equals(itemName)) {
				return item;
			} 
			else if (item instanceof IContainer) {
				IContainer container = (IContainer) item;
				if (container.isContentsVisible()) {
					IItem itemInContainer = container.getItem(itemName);
					if (itemInContainer != null) {
						container.removeItem(itemInContainer);
						return itemInContainer;
					}
				}
				
			}
		}
		return null;
	}
	
	public Room addCommand(Action command) {
		validCommands.add(command);
		return this;
	}

	public List<Action> getValidCommands() {
		return validCommands;
	}

	@Override
	public boolean addItem(IItem item) {
		this.items.add(item);			
		return true;
	}

	@Override
	public boolean isContentsVisible() {
		return false;
	}

	
}
