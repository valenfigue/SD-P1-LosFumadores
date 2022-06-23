package com.mycompany.app;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
		try {
			this.receiveCigar();
			this.takeMissingIngredients();
			this.sendCigar();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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
	
	protected abstract void takeMissingIngredients();
	
	private void askToVendorForIngredients() {
	}
	
	public int getTriesCount() {
		return triesCount;
	}
	
	public void setTriesCount(int newCount) {
		this.triesCount = newCount;
	}
	
	public void receiveCigar() throws IOException {
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		for (int i = 0; i < this.cigar.length; i++) {
			String ingredientName = dataInputStream.readUTF();
			switch (ingredientName) {
				case "Tabaco" -> cigar[i] = Ingredient.createTobacco();
				case "Fósforo" -> cigar[i] = Ingredient.createMatchstick();
				case "Papel" -> cigar[i] = Ingredient.createPaper();
				case "end" -> cigar[i] = null;
			}
		}
	}
	
	public void sendCigar() throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		for (Ingredient ingredient : this.cigar) {
			if (ingredient != null) {
				dataOutputStream.writeUTF(ingredient.name());
			} else {
				dataOutputStream.writeUTF("end");
			}
		}
	}
}
