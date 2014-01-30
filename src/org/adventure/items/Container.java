package org.adventure.items;
import java.util.ArrayList;
import java.util.List;

import org.adventure.IContainer;


public class Container extends Item implements IContainer {
	private int volumeCapacity;
	private WearableType containerType;
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

	public Container(String name, String description, int volume, int weight, int volumeCapacity, WearableType contrainerType) {
		super(name, description, volume, weight);
	}

	
	
	@Override
	public String getDescription() {
		if (isContentsVisible()) {
			StringBuilder sb = new StringBuilder();
			sb.append(super.getDescription());
			sb.append(getContentsPrefix());
			for (IItem item : items) {
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


	public IContainer setVolumeCapacity(int volumeCapacity) {
		this.volumeCapacity = volumeCapacity;
		return this;
	}

	public WearableType getContainerType() {
		return containerType;
	}


	public IContainer setContainerType(WearableType containerType) {
		this.containerType = containerType;
		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.adventure.ICOntainer#addItem(org.adventure.Item)
	 */
	@Override
	public boolean addItem(Item item) {
		//TODO: Check make sure there is capacity for the item.
		//TODO: Need to get the current volume of the contents.
			if (item.getVolume() > this.getVolumeCapacity() - this.getVolume()) {
				System.out.println(item.getName() +" doesn't fit.");
			}
			else {
				this.items.add(item);
				return true;
			}			
		return false;
	}

	@Override
	public int getVolume() {
		int theContainersEmptyVolume=  super.getVolume();
		int volume = theContainersEmptyVolume;
		for (IItem item : this.items) {
			volume = volume + item.getVolume();
		}
		
		return volume;
	}

	@Override
	public int getWeight() {
		int theContainersweight =  super.getWeight();
		int weight = theContainersweight;
		for (IItem item : this.items) {
			weight = weight + item.getWeight();
		}
		
		return weight;
	}

	
	
	public boolean isContentsVisible() {
		return contentsVisible;
	}

	public IContainer setContentsVisible(boolean contentsVisible) {
		this.contentsVisible = contentsVisible;
		return this;
	}


	public String getContentsPrefix() {
		return contentsPrefix;
	}

	public IContainer setContentsPrefix(String contentsPrefix) {
		this.contentsPrefix = contentsPrefix;
		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.adventure.ICOntainer#getItem(java.lang.String)
	 */
	@Override
	public IItem getItem(String itemName) {
		for (IItem item : this.items) {
			if (itemName.equals(item.getName())) {
				return item;
			}
		}
		return null;
	}
	
}
