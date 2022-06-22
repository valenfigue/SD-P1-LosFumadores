package com.mycompany.app;

import java.net.Socket;

/**
 * They take ingredients from benches to roll their cigars and smoke. They also ask the vendor when they can't find
 * the ingredients they need.
 *
 * @author valen
 */
public class Smoker extends Client {
	private Ingredient[] cigar = new Ingredient[3];
	
	private Smoker(Ingredient ingredient, Socket socket) {
		this.cigar[0] = ingredient;
		this.name = "Fumador con " + ingredient.name();
	}
	
	/**
	 * Creates a smoker that already has tobacco to create their cigar.
	 *
	 * @return A smoker with tobacco.
	 */
	public static Smoker createSmokerWithTobacco(Socket socket) {
		Ingredient ingredient = Ingredient.createTobacco();
		
		return new Smoker(ingredient, socket);
	}
	
	/**
	 * Creates a smoker that already has matchstick to create their cigar.
	 *
	 * @return A smoker with matchstick.
	 */
	public static Smoker createSmokerWithMatchstick(Socket socket) {
		Ingredient ingredient = Ingredient.createMatchstick();
		
		return new Smoker(ingredient, socket);
	}
	
	/**
	 * Creates a smoker that already has paper to create their cigar.
	 *
	 * @return A smoker with paper.
	 */
	public static Smoker createSmokerWithPaper(Socket socket) {
		Ingredient ingredient = Ingredient.createPaper();
		
		return new Smoker(ingredient, socket);
	}
	
	@Override
	public void run() {
		this.lookForMissingIngredients();
	}
	
	/**
	 * Put on the smoker to sleep for 10 seconds to simulate that they are smoking.
	 */
	private void smoke() {
		try {
			System.out.println("El fumador que empieza con " + this.cigar[0].name() + " está fumando ahora.");
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	private void rollCigar() {
		if (this.checkCigarIngredients()) {
			this.smoke();
			this.clearExtraCigarIngredients();
		}
	}
	
	/**
	 * Checks if the smoker has all the ingredients they need to roll their cigar and smoke.
	 *
	 * @return True, if the smoker has all the ingredients; False, if they don't.
	 */
	private boolean checkCigarIngredients() {
		for (Ingredient ingredient : this.cigar) {
			if (ingredient == null) {
				return false;
			}
		}
		
		return true;
	}
	
	public void lookForMissingIngredients() {
//		Scanner sn = new Scanner(System.in);
		
//		System.out.println("¿En cuál banca desea buscar los ingredientes faltantes?");
//		int benchNumber = sn.nextInt(); // TODO cómo manejar las bancas y son tres servidores al mismo tiempo.
	
	}
	
	private void takeMissingIngredients() {
	}
	
	private void askToVendorForIngredients() {
	}
	
	private void clearExtraCigarIngredients() {
	}
}
