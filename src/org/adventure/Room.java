package org.adventure;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.adventure.commands.Action;
import org.adventure.items.IItem;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Room implements IContainer {
	@JsonIgnore
	private List<Action> validCommands = new ArrayList<Action>();
	private CopyOnWriteArrayList<PlayerCharacter> characters = new CopyOnWriteArrayList<PlayerCharacter>();
	String name;
	String[] description;
	List<IItem> items = new ArrayList<IItem>();
	
	
	public List<PlayerCharacter> getCharacters() {
		return characters;
	}

	public String getName() {
		return name;
	}

	public List<IItem> getItems() {
		return items;
	}

	public String[] getDescription() {
		return description;
	}

	public Room setDescription(String... description) {
		this.description = description;
		return this;
	}

//	public String getPrompt() {
//		StringBuilder sb = new StringBuilder();
//		sb.append(getDescription());
//		if (items.size() > 0) {
//			sb.append(" In the room is: ");
//			for (IItem item : items) {
//				sb.append(item.getDescription());
//			}
//			sb.append(" Others here: ");
//			for (PlayerCharacter c : this.characters) {
//				sb.append(c.getName());
//			}
//		}
//		return sb.toString();
//	}
	
	public Room addItems(IItem... itemsToAdd) {
		for (IItem item : itemsToAdd) {
			item.setContainer(this);
		}
		return this;
	}

	@Override
	public void removeItem(IItem item) {
		items.remove(item);
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
	public void addItem(IItem item) {
		this.items.add(item);			
	}

	@Override
	public boolean isContentsVisible() {
		return false;
	}

	@Override
	public boolean canAddItem(IItem item) {
		return true;
	}

	@Override
	public boolean canRemoveItem(IItem item) {
		return true;
	}

	public void addCharacter(PlayerCharacter character) {
		sendMessage(character.getName() + " entered the room.");
		this.characters.add(character);
	}
	
	public void removeCharacter(PlayerCharacter character) {
		this.characters.remove(character);
		sendMessage(character.getName()+ " left the room.");
	}
	
	public PlayerCharacter getCharacter(String name) {
		for (PlayerCharacter character : this.characters) {
			if (character.getName().toLowerCase().startsWith(name)) {
				return character;
			}
		}
		return null;
	}
	
	public void sendMessage(String message) {
		for (PlayerCharacter c : this.characters) {
			c.sendMessage(message);
		}
	}
}
