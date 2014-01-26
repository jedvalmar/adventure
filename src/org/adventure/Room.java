package org.adventure;
import java.util.ArrayList;
import java.util.List;

import org.adventure.commands.Command;



public class Room {
	private List<Command> validCommands = new ArrayList<Command>();
	String description;
	List<Item> items = new ArrayList<Item>();
	
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
			for (Item item : items) {
				sb.append(item.getDescription());
			}			
		}
		return sb.toString();
	}
	
	public Room addItems(Item... itemsToAdd) {
		for (Item item : itemsToAdd) {
			items.add(item);			
		}
		return this;
	}

	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public Item getItem(String itemName) {
		for (Item item : items) {
			if (item.getName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}
	
	public Room addCommand(Command command) {
		validCommands.add(command);
		return this;
	}

	public List<Command> getValidCommands() {
		return validCommands;
	}
	
	
}
