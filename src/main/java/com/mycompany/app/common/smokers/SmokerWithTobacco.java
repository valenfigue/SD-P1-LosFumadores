package com.mycompany.app.common.smokers;

import com.mycompany.app.common.Bench;
import com.mycompany.app.common.Ingredient;

import java.io.Serializable;
import java.net.Socket;

public class SmokerWithTobacco extends Smoker implements Serializable { // EVERY SMOKER MUST IMPLEMENTS 'Serializable'.
	/**
	 * Creates a smoker that already has tobacco to create their cigar.
	 *
	 * <p></p>
	 * Method to be used by the client.
	 */
	public SmokerWithTobacco() {
		firstIngredient(Ingredient.createTobacco());
		this.socket = null;
		this.bench = null;
	}
	
	/**
	 * Creates a smoker that already has tobacco to create their cigar.
	 *
	 * <p></p>
	 * Method to be used by the server.
	 *
	 * @param socket Socket connection with the client.
	 * @param bench The bench where the smoker will try to take their missing ingredients from to roll their cigar and
	 *              smoke.
	 */
	public SmokerWithTobacco(Socket socket, Bench bench) {
		firstIngredient(Ingredient.createTobacco());
		this.socket = socket;
		this.bench = bench;
	}
}
