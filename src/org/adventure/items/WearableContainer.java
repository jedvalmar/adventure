package org.adventure.items;

import org.adventure.IContainer;

public class WearableContainer extends Wearable implements IContainer {

	private Container container = new Container();
	
	public WearableContainer(WearableType wearableType, int volumeCapacity) {
		super(wearableType);
		container.setVolumeCapacity(volumeCapacity);
	}

	@Override
	public boolean addItem(Item item) {
		return container.addItem(item);
	}

	@Override
	public IItem getItem(String itemName) {
		return container.getItem(itemName);
	}

}
