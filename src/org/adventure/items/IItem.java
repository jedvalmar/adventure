package org.adventure.items;

import org.adventure.IContainer;
import org.adventure.PlayerCharacter;
import org.adventure.commands.Action;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IItem {

	public abstract String getName();

	public abstract String getDescription();

	public abstract String getLongDescription();

	public abstract int getVolume();

	public abstract int getWeight();
	
	public abstract boolean is(String name);
	
	public boolean commandAllowed(Action command, PlayerCharacter character);
	
	@JsonIgnore
	public IContainer getContainer();
	
	public boolean setContainer(IContainer container);

}