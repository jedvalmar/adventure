package org.adventure;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.adventure.commands.combat.AttackCommand;
import org.adventure.items.IItem;
import org.adventure.items.IWearable;
import org.adventure.items.WearableType;
import org.adventure.items.armor.Armor;
import org.adventure.items.weapons.DamageType;
import org.adventure.items.weapons.Weapon;
import org.adventure.random.Skill;
import org.adventure.random.SkillType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PlayerCharacter implements IContainer {
	Logger log = LoggerFactory.getLogger(AttackCommand.class);
	
	private UUID id = UUID.randomUUID();
	@JsonIgnore
	private WebSocketSession session;
	private final AtomicInteger busyFor = new AtomicInteger();
	private List<BodyPart> bodyParts = new ArrayList<BodyPart>();
	private Map<BodyPartType, Armor> armorMap = new HashMap<BodyPartType, Armor>();
	private List<IContainer> containers = new ArrayList<IContainer>();
	private IItem leftHand;
	private IItem rightHand;
	private Map<WearableType, List<IWearable>> clothing = new HashMap<WearableType, List<IWearable>>();
	@JsonIgnore
	private Room currentRoom;
	
	private String name;
	private int health = 100;
	private int nourishment;
	private int strength;
	private int agility;
	private int speed;
	private int wisdom;
	private int inteligence;
	private int charisma;
	
	private Map<SkillType, Skill> skills = new HashMap<SkillType, Skill>();
	//Need some sort of skills.  Should have to train any any type of weapon in the game to improve.
	private Weapon defaultWeapon = new Weapon().setWeaponType(SkillType.HAND_TO_HAND);
	
	public PlayerCharacter() {
		super();
		defaultWeapon.setName("a fist");
		defaultWeapon.addDamage("punch",DamageType.BLUNT, 1, 3);
		bodyParts.add(new BodyPart(BodyPartType.ARM,"Left Arm",100));
		bodyParts.add(new BodyPart(BodyPartType.ARM,"Right Arm",100));
		bodyParts.add(new BodyPart(BodyPartType.NECK,"Neck",100));
		bodyParts.add(new BodyPart(BodyPartType.HEAD,"Head",100));
		bodyParts.add(new BodyPart(BodyPartType.LEG,"Left Leg",100));
		bodyParts.add(new BodyPart(BodyPartType.LEG,"Right Leg",100));
		bodyParts.add(new BodyPart(BodyPartType.BACK,"Back",200));
		bodyParts.add(new BodyPart(BodyPartType.TORSO,"Torso",200));
		bodyParts.add(new BodyPart(BodyPartType.HAND,"Left Hand",50));
		bodyParts.add(new BodyPart(BodyPartType.HAND,"Right Hand",50));
		bodyParts.add(new BodyPart(BodyPartType.FOOT,"Left Foot",50));
		bodyParts.add(new BodyPart(BodyPartType.FOOT,"Right Foot",50));
	}

	public Weapon getWeapon() {
		IItem item = getRightHand();
		if (item instanceof Weapon) {
			Weapon weapon = (Weapon) item;
			return weapon;
		}
		return defaultWeapon;
	}
	
	public List<BodyPart> getBodyParts() {
		return bodyParts;
	}

	public void setBodyParts(List<BodyPart> bodyParts) {
		this.bodyParts = bodyParts;
	}

	public int calculateDamage(Map<DamageType, Integer> damages, BodyPart bodyPart) {
		int damage = this.armorMap.get(bodyPart.getBodyPartType()).calculateDamage(damages, bodyPart);
		removeHealth(damage);
		return damage;
	}
	
	public boolean skillCheck(SkillType type, int level) {
		return getSkill(type).check(level);
	}
	
	public Skill getSkill(SkillType skillType) {
		Skill skill = skills.get(skillType);
		if (skill == null) {
			skill = new Skill(skillType, 1);
			skills.put(skillType, skill);
		}
		return skill;
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IItem getLeftHand() {
		return leftHand;
	}

	public void setLeftHand(IItem leftHand) {
		this.leftHand = leftHand;
	}

	public IItem getRightHand() {
		return rightHand;
	}

	public void setRightHand(IItem rightHand) {
		this.rightHand = rightHand;
	}
	
	public void gotoRoom(Room room) {
		if (room.equals(this.currentRoom) == false){
			sendRoom();
			if (currentRoom != null) {
				this.currentRoom.removeCharacter(this);				
			}
			this.currentRoom = room;
			this.currentRoom.addCharacter(this);
		}
	}
	
	public void broadcastMessage(String message) {
		this.currentRoom.sendMessage(message);
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
	
	public String describeMe() {
		StringBuilder result = new StringBuilder();
		result.append("You are wearing: ");
		System.out.print("You are wearing: ");
		Collection<List<IWearable>> clothes = this.clothing.values();
		for (List<IWearable> list : clothes) {
			for (IWearable iWearable : list) {
				System.out.print(iWearable.getDescription());
				result.append(iWearable.getDescription());
			}
		}
		System.out.println();
		if (getRightHand() != null) {
			System.out.println("You are carring " + getRightHand().getDescription() + " in your right hand.");	
			result.append("You are carring " + getRightHand().getDescription() + " in your right hand.");
		}
		if (getLeftHand() != null) {
			System.out.println("You are carring " + getLeftHand().getDescription() + " in your left hand.");
			result.append("You are carring " + getLeftHand().getDescription() + " in your left hand.");
		}
		return result.toString();
	}

	public void unWear(IWearable wearable) {
		if (getFreeHands() > 0) {
			List<IWearable> warnClothingOfType = this.clothing.get(wearable.getWearableType());
			if (warnClothingOfType != null && warnClothingOfType.contains(wearable)) {
				warnClothingOfType.remove(warnClothingOfType.indexOf(wearable));
			}
			addItem(wearable);
		}
		else {
			sendMessage("Your hands are full.");
		}
	}
	
	public void unWear(Armor armor) {
		if (getFreeHands() > 0) {
			if (armorMap.containsValue(armor)) {
				for (BodyPartType bodyPartType : armorMap.keySet()) {
					if (armor.equals(armorMap.get(bodyPartType))) {
						armorMap.put(bodyPartType, null);
					}
				}
			}
			addItem(armor);
		}
		else {
			sendMessage("Your hands are full.");
		}
	}
	
	public void removeItem(IItem item) {
		if (isHolding(item)) {
			if (getRightHand() != null && getRightHand().equals(item)) {
				setRightHand(null);
			}
			else if (getLeftHand() != null && getLeftHand().equals(item)) {
				setLeftHand(null);
			}
		}
	}
	
	/**
	 * Adding an item to a character is the same thing as pick up an item.. It will go into the characters hands if they have 
	 * a free hand.  
	 */
	@Override
	public void addItem(IItem item) {
		if (getRightHand() == null) {
			setRightHand(item);
		}
		else {
			setLeftHand(item);
		}
	}

	
	
	@Override
	public boolean canAddItem(IItem item) {
		boolean result = false;
		if (item != null) {
			if (item.getContainer() == null) {
				result = getFreeHands() > 0;
			}
			else if (item.getContainer().equals(this) == false) {
				result = getFreeHands() > 0;
			}
		}
		return result;
	}

	@Override
	public boolean canRemoveItem(IItem item) {
		return this.equals(item.getContainer());
	}

	public boolean wear(IItem item) {
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
			else {
				sendMessage(new StringBuilder("How about removing one you already have on?").toString());
			}
		}
		else if (item instanceof Armor) {
			Armor armor = (Armor) item;
			for (BodyPartType bodyPartType : armor.getBodyPartTypes()) {
				if (armorMap.containsKey(bodyPartType)) {
					sendMessage(new StringBuilder("How about removing one you already have on?").toString());
					return false;
				}
			}
			for (BodyPartType bodyPartType : armor.getBodyPartTypes()) {
				armorMap.put(bodyPartType, armor);
				added = true;
			}
		}
		return added;
	}

	public IWearable getWorn(WearableType type) {
		return getWorn(type, 0);
	}
	
	public IWearable getWorn(WearableType type, int index) {
		List<IWearable> warnClothingOfType = this.clothing.get(type);
		if (warnClothingOfType.size() > index) {
			return warnClothingOfType.get(index);			
		}
		return null;
	}
	
	public boolean isWearing(IItem item) {
		if (item instanceof IWearable) {
			IWearable wearable = (IWearable) item;
			List<IWearable> warnClothingOfType = this.clothing.get(wearable.getWearableType());
			if (warnClothingOfType != null && warnClothingOfType.contains(wearable)) {
				return true;
			}
		}
		else if (item instanceof Armor) {
			for (Armor armor : this.armorMap.values()) {
				if (armor.equals(item)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private int determineAllowableNumberOfClothingType(WearableType wearableType) {
		if (WearableType.SHOULDER.equals(wearableType)) {
			return 2;
		}
		return 1;
	}
	
	private void addWearableContainer(IItem item) {
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
		for (BodyPartType partType : this.armorMap.keySet()) {
			Armor armor = this.armorMap.get(partType);
			if (armor.is(itemName)) {
				return armor;
			}
		}
		return null;
	}

	@Override
	public boolean isContentsVisible() {
		return false;
	}
	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	public void setSession(WebSocketSession session) {
		this.session = session;
	}
	
	public WebSocketSession getSession() {
		return session;
	}

	public void sendMessage(String message) {
    	try {
    		StringBuilder sb = new StringBuilder();
    		sb.append("{\"messages\":[\"");
    		sb.append(message);
    		sb.append("\"]}");
    		if (this.session != null && this.session.isOpen()) {
    			this.session.sendMessage(new TextMessage(sb.toString()));    			
    		}
    		else {
    			log.debug(sb.toString());
    		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void sendRoom() {
    	try {
    		JsonFactory jsonFactory = new JsonFactory();
    		Writer writter = new StringWriter();
    		jsonFactory.createGenerator(writter).setCodec(new ObjectMapper()).writeObject(getCurrentRoom());
    		StringBuilder sb = new StringBuilder();
    		sb.append("{\"room\":");
    		sb.append(writter.toString());
    		sb.append("}");
    		if (this.session != null && this.session.isOpen()) {
    			this.session.sendMessage(new TextMessage(sb.toString()));    			
    		}
    		else {
    			log.debug(sb.toString());
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void addHealth(int amount) {
		this.health = this.health + amount;
	}
	
	public void removeHealth(int amount) {
		this.health = this.health - amount;
		if (this.health <= 0) {
			log.debug("Death");
		}
	}
	public int getHealth() {
		return this.health;
	}
	public int getBusyFor() {
		return busyFor.get();
	}

	public int decrementBusyFor() {
		if (this.busyFor.get() > 0) {
			int result =  this.busyFor.decrementAndGet();
			if (result == 0) {
				getTimerTask().cancel();
				timer.purge();
				timerTask = null;
			}
		}
		
		return 0;
	}
	
	private static Timer timer = new Timer();
	private TimerTask timerTask;
	
	private TimerTask getTimerTask() {
		if (timerTask == null) {
			timerTask = new TimerTask() {
				@Override
				public void run() {
					PlayerCharacter.this.busyFor.decrementAndGet();
				}
			};
		}
		return timerTask;
	}
	
	public void setBusyFor(int busyFor) {
		int initalValue = this.busyFor.getAndAdd(busyFor);
		if (initalValue <= 0 && timerTask == null) {
			timer.scheduleAtFixedRate(getTimerTask(), 0, 1000);
		}
	}
	
	public boolean isBusy() {
		return this.busyFor.get() > 0;
	}
	
}
