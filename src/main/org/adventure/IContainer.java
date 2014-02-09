package org.adventure;

import org.adventure.items.IItem;

public interface IContainer {


	public abstract boolean addItem(IItem item);

	public abstract boolean removeItem(IItem item);
	
	public abstract IItem getItem(String itemName);

	public abstract boolean isContentsVisible();
	
}