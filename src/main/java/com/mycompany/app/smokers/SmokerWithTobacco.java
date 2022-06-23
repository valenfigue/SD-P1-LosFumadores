package com.mycompany.app.smokers;

import com.mycompany.app.Bench;
import com.mycompany.app.Ingredient;
import com.mycompany.app.Smoker;

import java.io.Serializable;
import java.net.Socket;

public class SmokerWithTobacco extends Smoker implements Serializable { // EVERY SMOKER MUST IMPLEMENTS 'Serializable'.
	/**
	 * Creates a smoker that already has tobacco to create their cigar.
	 */
	public SmokerWithTobacco() {
		this.fistIngredient(Ingredient.createTobacco());
		this.socket = null;
		this.bench = null;
	}
	
	/**
	 * Creates a smoker that already has tobacco to create their cigar.
	 *
	 * @param socket Socket connection with the client.
	 * @param bench The bench where the smoker will try to take their missing ingredients from to roll their cigar and
	 *              smoke.
	 */
	public SmokerWithTobacco(Socket socket, Bench bench) {
		this.fistIngredient(Ingredient.createTobacco());
		this.socket = socket;
		this.bench = bench;
	}
	
	/**
	 *
	 */
	@Override
	protected void takeMissingIngredients() {
		if (bench.amountMatchsticksLeft() > 0) {
			this.cigar[1] = bench.giveMatchstick();
			System.out.println(this.actorName + " acaba de tomar " + this.cigar[1].name());
		} else {
			System.out.println("No hay cerillos en esta banca.");
		}
		
		if (bench.amountPapersLeft() > 0) {
			this.cigar[2] = bench.givePaper();
			System.out.println(this.actorName + " acaba de tomar " + this.cigar[2].name());
		} else {
			System.out.println("No hay papel en esta banca.");
		}
		
		this.triesCount += 1;
		this.checkCigarIngredients();
	}
}
