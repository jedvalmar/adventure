package org.adventure.items.armor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.adventure.BodyPart;
import org.adventure.BodyPartType;
import org.adventure.items.Item;
import org.adventure.items.weapons.DamageType;
import org.adventure.random.Dice;
import org.adventure.random.IRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Armor extends Item {
	Logger log = LoggerFactory.getLogger(Armor.class);
	Map<BodyPartType, Integer> healthMap = new HashMap<BodyPartType, Integer>();
	Map<DamageType, IRandom> protection = new HashMap<DamageType, IRandom>();
	Map<DamageType, Double> deteriorationRate = new HashMap<DamageType, Double>();
	
	public Set<BodyPartType> getBodyPartTypes() {
		return this.healthMap.keySet();
	}
	public Integer getArmorHealth(BodyPartType bodyPartType) {
		return healthMap.get(bodyPartType);
	}

	public void setArmorHealth(BodyPartType bodyPartType, Integer health) {
		this.healthMap.put(bodyPartType, health);
	}

	public Map<DamageType, IRandom> getProtection() {
		return protection;
	}

	public void addProtection(DamageType damageType, IRandom protection) {
		this.protection.put(damageType, protection);
	}

	public void addProtection(DamageType damageType, int deviation, int mean) {
		this.protection.put(damageType, new Dice(deviation, mean));
	}
	
	public Map<DamageType, Double> getDeteriorationRate() {
		return deteriorationRate;
	}

	public void setDeteriorationRate(DamageType damageType, Double deteriorationRate) {
		this.deteriorationRate.put(damageType, deteriorationRate);
	}

	public int calculateDamage(Map<DamageType, Integer> damages, BodyPart bodyPart) {
		Set<DamageType> dTypes = damages.keySet();
		int totalDamage = 0;
		for (DamageType dType : dTypes) {
			int damage = damages.get(dType);

			if (protection.containsKey(dType)) {
				BodyPartType bodyPartType = bodyPart.getBodyPartType();
				int damageReduction = protection.get(dType).getValue();
				Integer armorHealth = this.getArmorHealth(bodyPartType);
				if (damage < damageReduction) {
					damageReduction = damage;
				}
				if (damageReduction > armorHealth) {
					damageReduction = armorHealth;
				}
				int armorDamageAmount = damageArmor(dType, damageReduction);
				int reamainingArmorHealth = armorHealth - armorDamageAmount;
				setArmorHealth(bodyPartType, reamainingArmorHealth);
				log.debug(" Damage:" + dType + " : "+ damage + " Armor Absorbs: " + damageReduction + " remaining " + reamainingArmorHealth);
				totalDamage = totalDamage + damage - damageReduction;
			}
			else {
				log.debug(" Damage " + dType + ":" + damage);
				totalDamage = totalDamage + damage;
			}
			int bodyPartHealth = bodyPart.getHealth();
			if (totalDamage > bodyPartHealth) {
				log.debug("Damage exceeds body part health :" + bodyPartHealth);
				totalDamage = bodyPartHealth;
			}
			bodyPart.setHealth(bodyPartHealth - totalDamage);;
		}

		// Need to set the injury based on  the amount of health left.
		return totalDamage;
	}
	
	private int damageArmor(DamageType dType, int amount) {
		Double rate = 0d;
		if (this.deteriorationRate.containsKey(dType)) {
			rate = this.deteriorationRate.get(dType);
		}
		return (int)(amount * rate);
	}
}
