package com.mycompany.app;


/**
 * They take ingredients from benches to roll their cigars and smoke. They also ask the vendor when they can't find
 * the ingredients they need.
 *
 * @author valen
 */
public abstract class Smoker extends Client {
	protected Ingredient[] cigar = new Ingredient[3];
	protected int triesCount = 0;
	
	protected void fistIngredient(Ingredient ingredient) {
		this.cigar[0] = ingredient;
		this.actorName = "Fumador con " + ingredient.name();
	}
	
	@Override
	public void run() {
		System.out.println(this.getActorName() + " conectado.");
		this.takeMissingIngredients();
	}
	
	/**
	 * Checks if the smoker has all the ingredients they need to roll their cigar and smoke.
	 *
	 * @return True, if the smoker has all the ingredients; False, if they don't.
	 */
	public boolean checkCigarIngredients() {
		boolean allIngredients = true;
		int missingIngredientsCount = 0;
		
		for (Ingredient ingredient : this.cigar) {
			if (ingredient != null) {
				System.out.println(this.actorName + " tiene " + ingredient.name());
			} else {
				missingIngredientsCount += 1;
				allIngredients = false;
			}
		}
		
		if (missingIngredientsCount != 0) {
			System.out.println("A " + this.actorName + " le falta(n) " + missingIngredientsCount + " ingredientes.");
		}
		
		return allIngredients;
	}
	
	public void rollCigar() {
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
