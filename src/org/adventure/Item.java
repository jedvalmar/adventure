package org.adventure;

import java.util.HashMap;
import java.util.Map;

import org.adventure.commands.Command;
import org.adventure.commands.CommandCondition;

public class Item {
	private String name;
	private String description;
	private String longDescription;
	private int volume;
	private int weight;
	private Map<Command, CommandCondition> commandConditionMap = new HashMap<Command, CommandCondition>();
	
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

	public String getName() {
		return name;
	}

	public Item setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Item setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public Item setLongDescription(String longDescription) {
		this.longDescription = longDescription;
		return this;
	}

	public int getVolume() {
		return volume;
	}

	public Item setVolume(int volume) {
		this.volume = volume;
		return this;
	}

	public int getWeight() {
		return weight;
	}

	public Item setWeight(int weight) {
		this.weight = weight;
		return this;
	}
	
	public boolean commandAllowed(Command command) {
		CommandCondition commandCondition = this.commandConditionMap.get(command);
		if (commandCondition != null) {
			return commandCondition.conditional(command.getState());			
		}
		return true;
	}

	public Item addCommandCondition(Command command, CommandCondition commandCondition) {
		this.commandConditionMap.put(command, commandCondition);
		return this;
	}
}
