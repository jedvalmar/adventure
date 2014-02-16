package org.adventure.random;

import java.util.Random;

public abstract class TestRnd {

	public static void main(String[] args) {
		Random rnd = new Random();
		int i =10000;
		int x = 0;
		while (i-- > 0) {
			int attack   = (int) new Skill(null, 2).getValue();
			int defense   = (int) new Skill(null, 1).getValue();
			if (attack > defense) {
				x++;
			}
		}
		System.out.println("Hit Percent " + x/100);
	}

}
