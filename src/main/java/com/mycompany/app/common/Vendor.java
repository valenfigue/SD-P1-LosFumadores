package com.mycompany.app.common;

import java.io.DataOutputStream;
import java.io.IOException;
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
	
	public Vendor(Socket socket, Bench bench) {
		this.actorName = "Vendedor";
		this.socket = socket;
		this.bench = bench;
	}
	
	public Vendor() {
		this.actorName = "Vendedor";
	}
	
	/**
	 * Gets a bench number between 1 and 3.
	 *
	 * @return Bench number.
	 */
	public synchronized int getBenchNumberRandomly(int oldBenchNumber) {
		int minNumberBenches = 1;
		int maxNumberBenches = 3;
		int benchNumber;
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
			/*int oldIngredientsQuantity = this.bench.countIngredientsLeft();
			System.out.println("Total de ingredientes en la banca: " + oldIngredientsQuantity);*/
			
//			this.sendBenchId("El vendedor repondrá los ingredientes de la " + bench.getId());
			sleep(sleepingTime);
			this.bench.replenishIngredients();
			
			/*int newIngredientsQuantity = this.bench.countIngredientsLeft();
			System.out.println("Total de ingredientes en la banca: " + newIngredientsQuantity);
			int totalIngredientsReplenished = newIngredientsQuantity - oldIngredientsQuantity;
			System.out.println("Total de ingredientes repuestos (int): " + totalIngredientsReplenished);
			
			String stringTotal = String.valueOf(totalIngredientsReplenished);
			System.out.println("Total de ingredientes repuestos (String): " + stringTotal);
			DataOutputStream dataOutputStream = new DataOutputStream(this.socket.getOutputStream());*/
//			dataOutputStream.writeUTF(stringTotal);
		} catch (InterruptedException e) {
			System.out.println("El vendedor ha sido interrumpido.");
		} /*catch (IOException e) {
			throw new RuntimeException(e);
		}*/
	}
	
	public void setBenchId(int benchNumber) {
		String benchId;
		switch (benchNumber) {
			case 1 -> benchId = "banca con tabaco.";
			case 2 -> benchId = "banca con fósforos.";
			case 3 -> benchId = "banca con papel.";
			default -> benchId = "";
		}
		
		this.benchId = benchId;
		
		System.out.println("Se repondrán los ingredientes de la " + benchId);
	}
}
