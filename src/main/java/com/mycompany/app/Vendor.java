package com.mycompany.app;

import com.mycompany.app.benches.Bench;

import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Replenish the ingredients for cigars of the benches,
 * two at the same time, randomly, at the request of a smoker.
 *
 * @author valen
 */
public class Vendor extends Client implements Serializable {
	
	public Vendor() {}
	
	public Vendor(Socket socket, Bench bench) {
		this.socket = socket;
		this.bench = bench;
	}
	
	@Override
	public void run() { // TODO poner saltos de línea para diferenciar cada acción.
		System.out.println("El vendedor repondrá los ingredientes de la " + bench.getId().toLowerCase());
		bench.replenishIngredients();
	}
	
	
	
	public void replenishIngredientsOnBenches() {
	
	}
	
	/**
	 * Gets a bench number between 1 and 3.
	 *
	 * @return Bench number.
	 */
	public int getBenchNumberRandomly(int oldBenchNumber) {
		int minNumberBenches = 1;
		int maxNumberBenches = 3;
		int benchNumber;
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		
		do {
			benchNumber = tlr.nextInt(minNumberBenches, maxNumberBenches + 1);
		} while (benchNumber != oldBenchNumber);
		
		return 1;
	}
}
