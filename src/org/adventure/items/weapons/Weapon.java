package org.adventure.items.weapons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.adventure.items.Item;
import org.adventure.random.Dice;
import org.adventure.random.IRandom;
import org.adventure.random.SkillType;

public class Weapon extends Item {
	SkillType weaponType;
	Map<String, Map<DamageType, IRandom>> attacks = new HashMap<String, Map<DamageType, IRandom>>();
	List<String> attackTypes = new ArrayList<String>();
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
	public String getAttackType() {
		int index = 0;
		if (getAttackTypes().size() > 1) {
			index = ThreadLocalRandom.current().nextInt(getAttackTypes().size());
		}
		String attackType = getAttackTypes().get(index);
		return attackType;
	}
	
	public Map<DamageType, Integer> getDamages(String attackType) {
		Map<DamageType, Integer> damages = new HashMap<DamageType, Integer>();
		Set<DamageType> dTypes = getDamageMatrix(attackType).keySet();
		for (DamageType key : dTypes) {
			damages.put(key, getDamageMatrix(attackType).get(key).getValue());
		}
		return damages;
	}
	
	public void addDamage(String attackType, DamageType damageType, IRandom random) {
		getDamageMatrix(attackType).put(damageType, random);
	}
	
	public void addDamage(String attackType, DamageType damageType, int deviation, int mean) {
		getDamageMatrix(attackType).put(damageType, new Dice(deviation, mean));
	}
	private Map<DamageType, IRandom> getDamageMatrix(String attackType) {
		Map<DamageType, IRandom> matrix = attacks.get(attackType);
		if (matrix == null) {
			matrix = new HashMap<DamageType, IRandom>();
			attacks.put(attackType, matrix);
			attackTypes.add(attackType);
		}
		return matrix;
	}
	
	public List<String> getAttackTypes() {
		return attackTypes;
	}
}
