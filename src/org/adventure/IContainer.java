package org.adventure;

import org.adventure.items.IItem;

public interface IContainer {


	public abstract void addItem(IItem item);
	
	public abstract boolean canAddItem(IItem item);

	public abstract void removeItem(IItem item);
	
	public abstract boolean canRemoveItem(IItem item);
	
	public abstract IItem getItem(String itemName);

	public abstract boolean isContentsVisible();
	
}