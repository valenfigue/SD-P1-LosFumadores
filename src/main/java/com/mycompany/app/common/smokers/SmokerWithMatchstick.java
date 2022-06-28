package com.mycompany.app.common.smokers;

import com.mycompany.app.common.Ingredient;
import com.mycompany.app.common.Bench;

import java.io.Serializable;
import java.net.Socket;

public class SmokerWithMatchstick extends Smoker implements Serializable {
	/**
	 * Creates a smoker that already has matchstick to create their cigar.
	 *
	 * <p></p>
	 * Method to be used by the client.
	 */
	public SmokerWithMatchstick() {
		firstIngredient(Ingredient.createMatchstick());
		this.socket = null;
		this.bench = null;
	}
	
	/**
	 * Creates a smoker that already has matchstick to create their cigar.
	 *
	 * <p></p>
	 * Method to be used by the server.
	 *
	 * @param socket Socket connection with the client.
	 * @param bench The bench where the smoker will try to take their missing ingredients from to roll their cigar and
	 *              smoke.
	 */
	public SmokerWithMatchstick(Socket socket, Bench bench) {
		firstIngredient(Ingredient.createMatchstick());
		this.socket = socket;
		this.bench = bench;
	}
}
