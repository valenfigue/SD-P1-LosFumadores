package com.mycompany.app.common;

import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Replenish the ingredients for cigars of two randomly selected benches, for a smoker's request.
 *
 * @author valen
 */
public class Vendor extends Client implements Serializable { // Interface needed to send its instance through sockets.
	
	/**
	 * Creates a vendor.
	 * <p></p>
	 * Method to be used in the server side.
	 *
	 * @param socket Socket connection between the server and the vendor (client).
	 * @param bench The bench where the vendor is.
	 */
	public Vendor(Socket socket, Bench bench) {
		this.actorName = "Vendedor";
		this.socket = socket;
		this.bench = bench;
	}
	
	/**
	 * Creates a vendor.
	 * <p></p>
	 * Method to be used in the client side.
	 */
	public Vendor() {
		this.actorName = "Vendedor";
	}
	
	/**
	 * Gets, randomly, a bench number between 1 and 3.
	 *
	 * @param oldBenchNumber The number of the bench where the vendor has already gone.
	 * @return The number of the bench where the vendor will go.
	 */
	public synchronized int getBenchNumberRandomly(int oldBenchNumber) {
		int minNumberBenches = 1;
		int maxNumberBenches = 3;
		int benchNumber;
		
		// Choosing randomly between 1 and 3.
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		do {
			benchNumber = tlr.nextInt(minNumberBenches, maxNumberBenches + 1);
		} while (benchNumber == oldBenchNumber);
		
		return benchNumber;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("\nVendedor conectado.");
			sleep(sleepingTime);
			this.bench.replenishIngredients();
		} catch (InterruptedException e) {
			System.out.println("El vendedor ha sido interrumpido.");
		}
	}
	
	/**
	 * Sets the bench's ID depending on the bench number chosen before; and shows it to the user.
	 *
	 * @param benchNumber The number of the bench the vendor will go.
	 */
	public void setBenchId(int benchNumber) {
		String benchId;
		switch (benchNumber) {
			case 1:
				benchId = "banca con tabaco.";
				break;
			case 2:
				benchId = "banca con fósforos.";
				break;
			case 3:
				benchId = "banca con papel.";
				break;
			default:
				benchId = "";
				break;
		}
		
		this.benchId = benchId;
		
		System.out.println("Se repondrán los ingredientes de la " + benchId);
	}
}
