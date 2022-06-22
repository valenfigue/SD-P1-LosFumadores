package com.mycompany.app;

import java.net.Socket;

/**
 * They take ingredients from benches to roll their cigars and smoke. They also ask the vendor when they can't find
 * the ingredients they need.
 *
 * @author valen
 */
public abstract class Smoker extends Client {
	protected Ingredient[] cigar = new Ingredient[3];
	
	protected void fistIngredient(Ingredient ingredient) {
		this.cigar[0] = ingredient;
		this.actorName = "Fumador con " + ingredient.name();
	}
	
	@Override
	public void run() {
		System.out.println(this.getActorName() + " conectado.");
		
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
	
	/*public void lookForMissingIngredients() {
//		Scanner sn = new Scanner(System.in);
		
//		System.out.println("¿En cuál banca desea buscar los ingredientes faltantes?");
//		int benchNumber = sn.nextInt(); // TODO cómo manejar las bancas y son tres servidores al mismo tiempo.
	
	}*/
	
	protected abstract void takeMissingIngredients();
	
	private void askToVendorForIngredients() {
	}
	
	private void clearExtraCigarIngredients() {
	}
}
