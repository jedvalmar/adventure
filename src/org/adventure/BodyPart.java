package org.adventure;


public class BodyPart {

	private BodyPartType bodyPartType;
	private String name;
	private int health = 100;
	//Some way to store injuries.
	private Injury injury;
	
	public BodyPart(BodyPartType bodyPartType, String name, int health) {
		super();
		this.bodyPartType = bodyPartType;
		this.name = name;
		this.health = health;
	}
	public BodyPartType getBodyPartType() {
		return bodyPartType;
	}
	public void setBodyPartType(BodyPartType bodyPartType) {
		this.bodyPartType = bodyPartType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public Injury getInjury() {
		return injury;
	}
	public void setInjury(Injury injury) {
		this.injury = injury;
	}
}
