package org.adventure;
import java.util.ArrayList;
import java.util.List;

import org.adventure.commands.Command;
import org.adventure.commands.CommandHandler;



public class Room extends CommandHandler {
	String description;
	List<Item> items = new ArrayList<Item>();
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void processAction(Command command) {
		command.action(this);
	}

	@Override
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
	
	public void addItem(Item... itemsToAdd) {
		for (Item item : itemsToAdd) {
			items.add(item);			
		}
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
}
