package org.adventure;
import java.util.ArrayList;
import java.util.List;


public class Container extends Item {
	private int volumeCapacity;
	private ContainerType containerType;
	private List<Item> items = new ArrayList<Item>();
	private boolean contentsVisible = false;
	private String contentsPrefix;
	
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

	
	
	@Override
	public String getDescription() {
		if (isContentsVisible()) {
			StringBuilder sb = new StringBuilder();
			sb.append(super.getDescription());
			sb.append(getContentsPrefix());
			for (Item item : items) {
				sb.append(item.getDescription());
				sb.append(",");
			}
			return sb.toString();
		}
		return super.getDescription();
	}

	public int getVolumeCapacity() {
		return volumeCapacity;
	}

	public Container setVolumeCapacity(int volumeCapacity) {
		this.volumeCapacity = volumeCapacity;
		return this;
	}

	public ContainerType getContainerType() {
		return containerType;
	}

	public Container setContainerType(ContainerType containerType) {
		this.containerType = containerType;
		return this;
	}
	
	public Container addItem(Item item) {
		//TODO: Check make sure there is capacity for the item.
		//TODO: Need to get the current volume of the contents.
		if (item.getVolume() > this.getVolumeCapacity() - this.getVolume()) {
			System.out.println("That doesn't fit.");
		}
		else {
			this.items.add(item);
		}
		return this;
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

	
	
	public boolean isContentsVisible() {
		return contentsVisible;
	}

	public Container setContentsVisible(boolean contentsVisible) {
		this.contentsVisible = contentsVisible;
		return this;
	}

	public String getContentsPrefix() {
		return contentsPrefix;
	}

	public Container setContentsPrefix(String contentsPrefix) {
		this.contentsPrefix = contentsPrefix;
		return this;
	}
	
	
	
}
