package org.adventure.items;

import org.adventure.IContainer;

public class WearableContainer extends Wearable implements IContainer {

	private Container container = new Container();
	
	public WearableContainer(WearableType wearableType, int volumeCapacity) {
		super(wearableType);
		container.setVolumeCapacity(volumeCapacity);
	}

	@Override
	public boolean addItem(IItem item) {
		return container.addItem(item);
	}

	@Override
	public IItem getItem(String itemName) {
		return container.getItem(itemName);
	}

	@Override
	public boolean removeItem(IItem item) {
		return container.removeItem(item);
	}

	@Override
	public String getLongDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getDescription());
		sb.append(" In the ").append(getName()).append(" there is ");
		sb.append(container.getDescription());
		return sb.toString();
	}

	@Override
	public boolean isContentsVisible() {
		return container.isContentsVisible();
	}

	public WearableContainer setContentsVisible(boolean contentsVisible) {
		 container.setContentsVisible(contentsVisible);
		 return this;
	}
}
