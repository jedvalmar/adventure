package org.adventure.random;

import java.util.concurrent.ThreadLocalRandom;

public class Skill implements IRandom {
	SkillType weaponType;
	int level;
	public Skill(SkillType weaponType, int level) {
		this.level = level;
		this.weaponType = weaponType;
	}
	
	public boolean check(int level) {
		int testValue = getValue(level);
		return testValue <= getValue();
	}
	
	@Override
	public int getValue() {
		return getValue(this.level);
	}
	
	public int getValue(int level) {
		int result   = (int) (ThreadLocalRandom.current().nextGaussian() * 2 * level + level);
		result = Math.abs(result);
		return result;
	}
}
