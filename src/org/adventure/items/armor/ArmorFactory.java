package org.adventure.items.armor;

import org.adventure.BodyPartType;
import org.adventure.items.weapons.DamageType;

public class ArmorFactory {

	public Armor getLeather() {
		Armor armor = new Armor();
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
}
