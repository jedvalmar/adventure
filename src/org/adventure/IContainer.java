package org.adventure;

import org.adventure.items.IItem;

public interface IContainer {


	public abstract boolean addItem(IItem item);

	public abstract IItem getItem(String itemName);

}