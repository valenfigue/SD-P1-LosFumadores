package com.mycompany.app;

import java.io.Serializable;

/**
 * They take ingredients from benches to roll their cigars and smoke. They also ask the vendor when they can't find
 * the ingredients they need.
 *
 * @author valen
 */
public abstract class Smoker extends Client implements Serializable {
	protected Ingredient[] cigar = new Ingredient[3];
	protected int triesCount = 1;
	
	protected void fistIngredient(Ingredient ingredient) {
		this.cigar[0] = ingredient;
		this.actorName = "Fumador con " + ingredient.name();
	}
	
	@Override
	public void run() {
		
		if (this.checkCigarIngredients()) { // When the smoker has all the ingredients.
			this.rollCigar();
		} else { // When the smoker doesn't have all the ingredients.
			if (this.triesCount < 3) { // When the smoker hasn't tried more than two times to take their missing ingredients.
				System.out.println(this.getActorName() + " conectado.");
				this.takeMissingIngredients();
				this.triesCount += 1;
			}
		}
	}
	
	/**
	 * Checks if the smoker has all the ingredients they need to roll their cigar and smoke.
	 *
	 * @return True, if the smoker has all the ingredients; False, if they don't.
	 */
	public boolean checkCigarIngredients() {
		boolean allIngredients = true;
		
		for (Ingredient ingredient : this.cigar) {
			if (ingredient != null) {
				System.out.println("Este fumador tiene " + ingredient.name());
			} else {
				allIngredients = false;
			}
		}
		
		return allIngredients;
	}
	
	private void rollCigar() {
		this.smoke();
		this.clearExtraCigarIngredientsAndTriesCount();
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
	
	private void clearExtraCigarIngredientsAndTriesCount() {
		for (int i = 1; i < this.cigar.length; i++) {
			this.cigar[i] = null;
		}
		
		this.triesCount = 1;
	}
	
	/*public void lookForMissingIngredients() {
//		Scanner sn = new Scanner(System.in);

//		System.out.println("¿En cuál banca desea buscar los ingredientes faltantes?");
//		int benchNumber = sn.nextInt(); // TODO cómo manejar las bancas y son tres servidores al mismo tiempo.
	
	}*/
	
	protected abstract void takeMissingIngredients();
	
	private void askToVendorForIngredients() {
	}
	
	public int getTriesCount() {
		return triesCount;
	}
	
	public void setTriesCount(int newCount) {
		this.triesCount = newCount;
	}
}
