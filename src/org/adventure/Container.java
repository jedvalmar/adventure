package org.adventure;
import java.util.ArrayList;
import java.util.List;


public class Container extends Item {
	private int volumeCapacity;
	private ContainerType containerType;
	private List<Item> items = new ArrayList<Item>();
	
	public Container() {
		super();
	}

	public Container(String name, String description, int volume, int weight,
			String longDescription) {
		super(name, description, volume, weight, longDescription);
	}

	public Container(String name, String description, int volume, int weight, int volumeCapacity, ContainerType contrainerType) {
		super(name, description, volume, weight);
	}

	public int getVolumeCapacity() {
		return volumeCapacity;
	}

	public void setVolumeCapacity(int volumeCapacity) {
		this.volumeCapacity = volumeCapacity;
	}

	public ContainerType getContainerType() {
		return containerType;
	}

	public void setContainerType(ContainerType containerType) {
		this.containerType = containerType;
	}
	
	public void addItem(Item item) {
		//TODO: Check make sure there is capacity for the item.
		//TODO: Need to get the current volume of the contents.
		if (item.getVolume() > this.getVolumeCapacity() - this.getVolume()) {
			System.out.println("That doesn't fit.");
		}
		else {
			this.items.add(item);
		}
	}

	@Override
	public int getVolume() {
		int theContainersEmptyVolume=  super.getVolume();
		int volume = theContainersEmptyVolume;
		for (Item item : this.items) {
			volume = volume + item.getVolume();
		}
		
		return volume;
	}

	@Override
	public int getWeight() {
		int theContainersweight =  super.getWeight();
		int weight = theContainersweight;
		for (Item item : this.items) {
			weight = weight + item.getWeight();
		}
		
		return weight;
	}
	
	
	
}
