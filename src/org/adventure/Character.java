package org.adventure;
import java.util.ArrayList;
import java.util.List;


public class Character {
	private int weightCapacity;
	private List<Container> containers = new ArrayList<Container>();
	private Item leftHand;
	private Item rightHand;

	
	public void addContainer(Container container) {
		// Need some logic to make sure we don't wear 5 backpacks.
	}
	
	public void removeContainer(Container container) {
		
	}
	
	public Container getContainer(String name) {
		return null;
	}

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
	
	public boolean isHolding(Item item) {
		if (getRightHand() != null && getRightHand().equals(item)) {
			return true;
		}
		else if (getLeftHand() != null && getLeftHand().equals(item)) {
			return true;
		}
		return false;
	}
	
	public void describeMe() {
		System.out.println("You are wearing..");
		if (getRightHand() != null) {
			System.out.println("You are carring " + getRightHand().getDescription());			
		}
		if (getLeftHand() != null) {
			System.out.println("You are carring " + getLeftHand().getDescription());			
		}
	}
	
}
