package com.mycompany.app.common.smokers;

import com.mycompany.app.common.Ingredient;
import com.mycompany.app.common.Bench;

import java.io.Serializable;
import java.net.Socket;

public class SmokerWithPaper extends Smoker implements Serializable {
	/**
	 * Creates a smoker that already has paper to create their cigar.
	 *
	 * <p></p>
	 * Method to be used by the client.
	 */
	public SmokerWithPaper() {
		firstIngredient(Ingredient.createPaper());
		this.socket = null;
		this.bench = null;
	}
	
	/**
	 * Creates a smoker that already has paper to create their cigar.
	 *
	 * <p></p>
	 * Method to be used by the server.
	 *
	 * @param socket Socket connection with the client.
	 * @param bench The bench where the smoker will try to take their missing ingredients from to roll their cigar and
	 *              smoke.
	 */
	public SmokerWithPaper(Socket socket, Bench bench) {
		firstIngredient(Ingredient.createPaper());
		this.socket = socket;
		this.bench = bench;
	}
}
