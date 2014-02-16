package org.adventure.items.armor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.adventure.items.Item;
import org.adventure.items.weapons.DamageType;
import org.adventure.random.IRandom;

public class Armor extends Item {

	private int health;
	Map<DamageType, IRandom> protection = new HashMap<DamageType, IRandom>();
	Map<DamageType, Double> deteriorationRate = new HashMap<DamageType, Double>();
	public int calculateDamage(Map<DamageType, Integer> damages) {
		Set<DamageType> dTypes = damages.keySet();
		int totalDamage = 0;
		for (DamageType dType : dTypes) {
			int damage = damages.get(dType);
			if (protection.containsKey(dType)) {
				int damageReduction = protection.get(dType).getValue();
				if (damageReduction > health) {
					damageReduction = health;
					damageArmor(dType, damageReduction);
				}
				totalDamage = totalDamage + damage - damageReduction;
			}
			else {
				totalDamage = totalDamage + damage;
			}
		}

		// Need to set the injury based on  the amount of health left.
		return totalDamage;
	}
	
	private void damageArmor(DamageType dType, int amount) {
		Double rate = 0d;
		if (this.deteriorationRate.containsKey(dType)) {
			rate = this.deteriorationRate.get(dType);
		}
		health = health - (int)(amount * rate);
	}
}
