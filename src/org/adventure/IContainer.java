package org.adventure;

import org.adventure.items.IItem;
import org.adventure.items.Item;

public interface IContainer {


	public abstract boolean addItem(Item item);

	public abstract IItem getItem(String itemName);

}