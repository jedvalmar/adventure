package org.adventure.items;

import java.util.HashMap;
import java.util.Map;

import org.adventure.IContainer;
import org.adventure.PlayerCharacter;
import org.adventure.commands.Action;
import org.adventure.commands.CommandCondition;

public class Item implements IItem {
	private String name;
	private String description;
	private String longDescription;
	private int volume;
	private int weight;
	private IContainer container;
	private Map<Action, CommandCondition> commandConditionMap = new HashMap<Action, CommandCondition>();
	
	public Item() {
		super();
	}

	public Item(String name, String description,
			int volume, int weight, String longDescription) {
		super();
		this.name = name;
		this.description = description;
		this.longDescription = longDescription;
		this.volume = volume;
		this.weight = weight;
	}

	public Item(String name, String description, int volume, int weight) {
		super();
		this.name = name;
		this.description = description;
		this.volume = volume;
		this.weight = weight;
	}

	/* (non-Javadoc)
	 * @see org.adventure.IItem#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	public Item setName(String name) {
		this.name = name;
		return this;
	}

	/* (non-Javadoc)
	 * @see org.adventure.IItem#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	public Item setDescription(String description) {
		this.description = description;
		return this;
	}

	/* (non-Javadoc)
	 * @see org.adventure.IItem#getLongDescription()
	 */
	@Override
	public String getLongDescription() {
		return longDescription;
	}

	public Item setLongDescription(String longDescription) {
		this.longDescription = longDescription;
		return this;
	}

	/* (non-Javadoc)
	 * @see org.adventure.IItem#getVolume()
	 */
	@Override
	public int getVolume() {
		return volume;
	}

	public Item setVolume(int volume) {
		this.volume = volume;
		return this;
	}

	/* (non-Javadoc)
	 * @see org.adventure.IItem#getWeight()
	 */
	@Override
	public int getWeight() {
		return weight;
	}

	public Item setWeight(int weight) {
		this.weight = weight;
		return this;
	}
	
	public boolean commandAllowed(Action command, PlayerCharacter character) {
		CommandCondition commandCondition = this.commandConditionMap.get(command);
		if (commandCondition != null) {
			return commandCondition.conditional(character);			
		}
		return true;
	}

	public IItem addCommandCondition(Action command, CommandCondition commandCondition) {
		this.commandConditionMap.put(command, commandCondition);
		return this;
	}

	@Override
	public boolean is(String name) {
		return this.getName().toLowerCase().equals(name);
	}

	public IContainer getContainer() {
		return container;
	}

	public boolean setContainer(IContainer container) {
		if (container.canAddItem(this)) {
			if (this.container == null || this.container.canRemoveItem(this)) {
				if (this.container != null) {
					this.container.removeItem(this);					
				}
				this.container = container;	
				this.container.addItem(this);
				return true;				
			}
		}
		return false;
	}
	
	
}
