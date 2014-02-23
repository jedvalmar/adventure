package org.adventure.items.weapons;

public class WeaponFactory {
	public Weapon newLongSword() {
		Weapon w = new Weapon();
		w.setName("long sword");
		w.addDamage("slashes", DamageType.SLASH, 10, 5);
		w.addDamage("slashes", DamageType.BLUNT, 10, 5);
		w.addDamage("stabs", DamageType.PIERCE, 10, 8);
		w.setVolume(15);
		w.setBaseAttackRate(8);
		return w;
	}
	
	public Weapon newShortSword() {
		Weapon w = new Weapon();
		w.setName("short sword");
		w.addDamage("slashes",DamageType.SLASH, 8, 4);
		w.addDamage("slashes",DamageType.BLUNT, 8, 3);
		w.addDamage("stabs", DamageType.PIERCE, 8, 5);
		w.setVolume(10);
		w.setBaseAttackRate(6);
		return w;
		
	}
		
		public Weapon newGreatSword() {
			Weapon w = new Weapon();
			w.setName("great sword");
			w.addDamage("slashes",DamageType.SLASH, 16, 8);
			w.addDamage("slashes",DamageType.BLUNT, 16, 8);
			w.addDamage("stabs", DamageType.PIERCE, 12, 6);
			w.setVolume(20);
			w.setBaseAttackRate(10);
			return w;
			
	}
		
		public Weapon newHandAxe() {
			Weapon w = new Weapon();
			w.setName("hand axe");
			w.addDamage("slashes",DamageType.SLASH, 8, 4);
			w.addDamage("slashes",DamageType.BLUNT, 8, 3);
			w.setVolume(10);
			w.setBaseAttackRate(6);
			return w;
			
}
		
		public Weapon newWarAxe() {
			Weapon w = new Weapon();
			w.setName("war axe");
			w.addDamage("slashes",DamageType.SLASH, 16, 8);
			w.addDamage("slashes",DamageType.BLUNT, 16, 8);
			w.setVolume(20);
			w.setBaseAttackRate(11);
			return w;
	}
		
		public Weapon newMaceLight() {
			Weapon w = new Weapon();
			w.setName("light mace");
			w.addDamage("hits",DamageType.PIERCE, 8, 4);
			w.addDamage("hits",DamageType.BLUNT, 10, 5);
			w.setVolume(10);
			w.setBaseAttackRate(10);
			return w;
	}
		
		public Weapon newMaceHeavy() {
			Weapon w = new Weapon();
			w.setName("heavy mace");
			w.addDamage("hits",DamageType.PIERCE, 12, 6);
			w.addDamage("hits",DamageType.BLUNT, 14, 7);
			w.setVolume(15);
			w.setBaseAttackRate(12);
			return w;
	}
}
