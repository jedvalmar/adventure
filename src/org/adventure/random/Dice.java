package org.adventure.random;

import java.util.concurrent.ThreadLocalRandom;

public class Dice implements IRandom {
	int deviation;
	int mean;


	public Dice(int deviation, int mean) {
		super();
		this.deviation = deviation;
		this.mean = mean;
	}


	@Override
	public int getValue() {
		int result   = (int) (ThreadLocalRandom.current().nextGaussian() * deviation + mean);
		result = Math.abs(result);
		return result;
	}

}
