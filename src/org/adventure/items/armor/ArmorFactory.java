package org.adventure.items.armor;

import org.adventure.BodyPartType;
import org.adventure.items.weapons.DamageType;

public class ArmorFactory {

	public Armor getLeather() {
		Armor armor = new Armor();
		armor.setName("Leather Armor");
		armor.setArmorHealth(BodyPartType.ARM, 30);
		armor.setArmorHealth(BodyPartType.HEAD, 30);
		armor.setArmorHealth(BodyPartType.NECK, 30);
		armor.setArmorHealth(BodyPartType.LEG, 30);
		armor.setArmorHealth(BodyPartType.HAND, 30);
		armor.setArmorHealth(BodyPartType.FOOT, 30);
		armor.setArmorHealth(BodyPartType.TORSO, 30);
		armor.setArmorHealth(BodyPartType.BACK, 30);
		armor.addProtection(DamageType.SLASH, 2,8);
		armor.addProtection(DamageType.BLUNT, 1,5);
		armor.addProtection(DamageType.FIRE, 2,5);
		armor.addProtection(DamageType.COLD, 2,5);
		armor.setDeteriorationRate(DamageType.SLASH, 0.25d);
		armor.setDeteriorationRate(DamageType.FIRE, 0.25d);
		armor.setDeteriorationRate(DamageType.COLD, 0.25d);
		return armor;
	}
	
	public Armor getIron() {
		Armor armor = new Armor();
		armor.setName("Iron Armor");
		armor.setArmorHealth(BodyPartType.ARM, 40);
		armor.setArmorHealth(BodyPartType.HEAD, 40);
		armor.setArmorHealth(BodyPartType.NECK, 40);
		armor.setArmorHealth(BodyPartType.LEG, 40);
		armor.setArmorHealth(BodyPartType.HAND, 40);
		armor.setArmorHealth(BodyPartType.FOOT, 40);
		armor.setArmorHealth(BodyPartType.TORSO, 40);
		armor.setArmorHealth(BodyPartType.BACK, 40);
		armor.addProtection(DamageType.SLASH, 4,10);
		armor.addProtection(DamageType.BLUNT, 2,8);
		armor.addProtection(DamageType.PIERCE, 2,10);
		armor.addProtection(DamageType.FIRE, 2,5);
		armor.addProtection(DamageType.COLD, 2,5);
		armor.setDeteriorationRate(DamageType.SLASH, 0.10d);
		armor.setDeteriorationRate(DamageType.BLUNT, 0.30d);
		armor.setDeteriorationRate(DamageType.FIRE, 0.25d);
		armor.setDeteriorationRate(DamageType.COLD, 0.25d);
		return armor;
	}
	
	public Armor getPaddedChain() {
		Armor armor = new Armor();
		armor.setName("Padded Chainmail");
		armor.setArmorHealth(BodyPartType.ARM, 35);
		armor.setArmorHealth(BodyPartType.HEAD, 35);
		armor.setArmorHealth(BodyPartType.NECK, 35);
		armor.setArmorHealth(BodyPartType.LEG, 35);
		armor.setArmorHealth(BodyPartType.HAND, 35);
		armor.setArmorHealth(BodyPartType.FOOT, 35);
		armor.setArmorHealth(BodyPartType.TORSO, 35);
		armor.setArmorHealth(BodyPartType.BACK, 35);
		armor.addProtection(DamageType.SLASH, 2,8);
		armor.addProtection(DamageType.BLUNT, 2,10);
		armor.addProtection(DamageType.PIERCE, 1,5);
		armor.addProtection(DamageType.FIRE, 2,5);
		armor.addProtection(DamageType.COLD, 2,5);
		armor.setDeteriorationRate(DamageType.SLASH, 0.20d);
		armor.setDeteriorationRate(DamageType.BLUNT, 0.10d);
		armor.setDeteriorationRate(DamageType.FIRE, 0.25d);
		armor.setDeteriorationRate(DamageType.COLD, 0.25d);
		return armor;
		
	}
}
