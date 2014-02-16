package org.adventure.items;

import org.adventure.IContainer;

public class WearableContainer extends Container implements IContainer, IWearable {
	private WearableType wearableType;
	
	public WearableContainer(WearableType wearableType, int volumeCapacity) {
		super();
		setVolumeCapacity(volumeCapacity);
	}

	public WearableType getWearableType() {
		return wearableType;
	}

	public void setWearableType(WearableType wearableType) {
		this.wearableType = wearableType;
	}

}
