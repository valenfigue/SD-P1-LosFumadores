package com.mycompany.app;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Replenish the ingredients for cigars of the benches,
 * two at the same time, randomly, at the request of a smoker.
 *
 * @author valen
 */
public class Vendor extends Client {
	
	@Override
	public void run() {
	
	}
	
	public void replenishIngredientsOnBenches() {
	
	}
	
	/**
	 * Gets a bench number between 1 and 3.
	 *
	 * @return Bench number.
	 */
	private int getBenchNumberRandomly() {
		int minNumberBenches = 1;
		int maxNumberBenches = 3;
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		
		return tlr.nextInt(minNumberBenches, maxNumberBenches + 1);
	}
}
