package org.adventure.items.weapons;

public class WeaponFactory {
	public Weapon newLongSword() {
		Weapon w = new Weapon();
		w.setName("long sword");
		w.addDamage("slash", DamageType.SLASH, 10, 5);
		w.addDamage("slash", DamageType.BLUNT, 10, 5);
		w.addDamage("stab", DamageType.PIERCE, 10, 8);
		w.setVolume(15);
		w.setBaseAttackRate(8);
		return w;
	}
	
	public Weapon newShortSword() {
		Weapon w = new Weapon();
		w.setName("short sword");
		w.addDamage("slash",DamageType.SLASH, 8, 4);
		w.addDamage("slash",DamageType.BLUNT, 8, 3);
		w.addDamage("stab", DamageType.PIERCE, 8, 5);
		w.setVolume(10);
		w.setBaseAttackRate(6);
		return w;
	}
}
