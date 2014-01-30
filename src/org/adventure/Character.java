package org.adventure;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.adventure.items.IItem;
import org.adventure.items.IWearable;
import org.adventure.items.Item;
import org.adventure.items.WearableType;


public class Character implements IContainer {

	private List<IContainer> containers = new ArrayList<IContainer>();
	private Item leftHand;
	private Item rightHand;
	private Map<WearableType, List<IWearable>> clothing = new HashMap<WearableType, List<IWearable>>();
	
	public Item getLeftHand() {
		return leftHand;
	}

	public void setLeftHand(Item leftHand) {
		this.leftHand = leftHand;
	}

	public Item getRightHand() {
		return rightHand;
	}

	public void setRightHand(Item rightHand) {
		this.rightHand = rightHand;
	}
	
	public boolean isHolding(IItem item) {
		if (getRightHand() != null && getRightHand().equals(item)) {
			return true;
		}
		else if (getLeftHand() != null && getLeftHand().equals(item)) {
			return true;
		}
		return false;
	}
	
	public int getFreeHands() {
		int cnt = 2;
		if (getRightHand() != null) cnt--;
		if (getLeftHand() != null) cnt--;
		return cnt;
	}
	
	public void describeMe() {
		System.out.print("You are wearing: ");
		Collection<List<IWearable>> clothes = this.clothing.values();
		for (List<IWearable> list : clothes) {
			for (IWearable iWearable : list) {
				System.out.print(iWearable.getDescription());
			}
		}
		System.out.println();
		if (getRightHand() != null) {
			System.out.println("You are carring " + getRightHand().getDescription() + " in your right hand.");			
		}
		if (getLeftHand() != null) {
			System.out.println("You are carring " + getLeftHand().getDescription() + " in your left hand.");			
		}
	}

	public boolean removeItem(Item item) {
		boolean removed = false;
			if (item instanceof IWearable) {
				IWearable wearable = (IWearable) item;
				List<IWearable> warnClothingOfType = this.clothing.get(wearable.getWearableType());
				if (warnClothingOfType.contains(wearable)) {
					warnClothingOfType.remove(warnClothingOfType.indexOf(wearable));
					removed = true;
				}
			}
			else {
				System.out.println("Huh, what do you want to take off?");
			}

		return removed;
	}
	
	/**
	 * Adding an item to a character is the same thing as pick up an item.. It will go into the characters hands if they have 
	 * a free hand.  
	 */
	@Override
	public boolean addItem(Item item) {
		if (getFreeHands() > 0) {
			if (getRightHand() == null) {
				setRightHand(item);
			}
			else {
				setLeftHand(item);
			}
			return true;
		}
		return false;
	}

	public boolean wear(Item item) {
		boolean added = false;
		if (item instanceof IWearable) {
			IWearable wearable = (IWearable) item;
			WearableType wearableType = wearable.getWearableType();
			
			List<IWearable> clothingType = this.clothing.get(wearableType);
			if (clothingType == null) {
				clothingType = new ArrayList<IWearable>();
				this.clothing.put(wearableType, clothingType);
			}
			if (clothingType.size() < determineAllowableNumberOfClothingType(wearableType)) {
				addWearableContainer(item);
				clothingType.add(wearable);
				added = true;
			}
		}
		if (added == false) {
			System.out.println("Unable to wear that.");
		}
		return added;
	}

	private int determineAllowableNumberOfClothingType(WearableType wearableType) {
		if (WearableType.SHOULDER.equals(wearableType)) {
			return 2;
		}
		return 1;
	}
	
	private void addWearableContainer(Item item) {
		if (item instanceof IContainer) {
			IContainer container = (IContainer) item;
			this.containers.add(container);
		}
	}


	@Override
	public IItem getItem(String itemName) {
		if (getLeftHand() != null && getLeftHand().is(itemName)) {
			return getLeftHand();
		}
		else if (getRightHand() != null && getRightHand().is(itemName)) {
			return getRightHand();
		}
		for (List<IWearable> wearables : this.clothing.values()) {
			for (IWearable iWearable : wearables) {
				if (iWearable.is(itemName)) {
					return iWearable;
				}
			}
		}
		return null;
	}
	
}
