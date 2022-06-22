package com.mycompany.app.smokers;

import com.mycompany.app.Bench;
import com.mycompany.app.Ingredient;
import com.mycompany.app.Smoker;

import java.net.Socket;

public class SmokerWithTobacco extends Smoker {
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
	
	}
}
