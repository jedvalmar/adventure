package org.adventure.items.weapons;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.adventure.items.Item;
import org.adventure.random.Dice;
import org.adventure.random.IRandom;
import org.adventure.random.SkillType;

public class Weapon extends Item {
	SkillType weaponType;
	Map<DamageType, IRandom> damages = new HashMap<DamageType, IRandom>();
	int baseAttackRate = 6;
	
	public SkillType getWeaponType() {
		return weaponType;
	}
	public Weapon setWeaponType(SkillType weaponType) {
		this.weaponType = weaponType;
		return this;
	}
	public int getBaseAttackRate() {
		return baseAttackRate;
	}
	public void setBaseAttackRate(int baseAttackRate) {
		this.baseAttackRate = baseAttackRate;
	}
	public Map<DamageType, Integer> getDamages() {
		Map<DamageType, Integer> damages = new HashMap<DamageType, Integer>();
		Set<DamageType> dTypes = this.damages.keySet();
		for (DamageType key : dTypes) {
			damages.put(key, this.damages.get(key).getValue());
		}
		return damages;
	}
	
	public void addDamage(DamageType damageType, IRandom random) {
		damages.put(damageType, random);
	}
	
	public void addDamage(DamageType damageType, int deviation, int mean) {
		damages.put(damageType, new Dice(deviation, mean));
	}
}
