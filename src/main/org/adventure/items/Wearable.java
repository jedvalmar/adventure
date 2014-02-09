package org.adventure.items;

public class Wearable extends Item implements IWearable {
	private WearableType wearableType;
	
	public Wearable(WearableType wearableType) {
		this.wearableType = wearableType;
	}

	@Override
	public WearableType getWearableType() {
		return this.wearableType;
	}


}
