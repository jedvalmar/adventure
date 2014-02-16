package org.adventure.random;

import java.util.concurrent.ThreadLocalRandom;

public class Skill implements IRandom {
	SkillType skillType;
	int level;
	public Skill(SkillType skillType, int level) {
		this.level = level;
		this.skillType = skillType;
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
