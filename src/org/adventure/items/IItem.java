package org.adventure.items;

public interface IItem {

	public abstract String getName();

	public abstract String getDescription();

	public abstract String getLongDescription();

	public abstract int getVolume();

	public abstract int getWeight();
	
	public abstract boolean is(String name);

}