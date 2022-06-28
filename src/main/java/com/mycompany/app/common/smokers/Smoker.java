package com.mycompany.app.common.smokers;


import com.mycompany.app.common.Client;
import com.mycompany.app.common.Ingredient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * They take ingredients from benches to roll their cigars and smoke. They also ask the vendor when they can't find
 * the ingredients they need.
 *
 * @author valen
 */
public class Smoker extends Client {
	
	/**
	 * Ingredients container.
	 */
	protected final Ingredient[] cigar = new Ingredient[3];
	/**
	 * Save how many times the smoker has tried to take ingredients from the benches and smoke without success.
	 * When it comes to 2, the smoker calls the vendor.
	 */
	protected int triesCount = 1;
	
	public Smoker(Ingredient ingredient) {
		this.cigar[0] = ingredient;
		this.actorName = "Fumador con " + ingredient.getName();
	}
	
	@Override
	public void run() {
		System.out.println("\n" + this.actorName + " conectado.");
		try {
			this.receiveCigar(); // The smoker receive the ingredients that already has, from the server.
			this.sendBenchId(this.actorName + " va a intentar tomar un ingrediente de esta banca.");
			sleep(this.sleepingTime);
			this.takeMissingIngredients(); // Tries to take a missing ingredient.
			this.sendCigar(); // The smoker send their updated ingredients list.
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Receive the name of every ingredient the smoker has, from the server or the client, to creates a new cigar.
	 *
	 * @throws IOException If there are problems sending and receiving messages between the server and the client.
	 */
	public void receiveCigar() throws IOException {
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		for (int i = 0; i < this.cigar.length; i++) {
			String ingredientName = dataInputStream.readUTF();
			switch (ingredientName) {
				case "Tabaco":
					cigar[i] = Ingredient.createTobacco();
					break;
				case "Fósforo":
					cigar[i] = Ingredient.createMatchstick();
					break;
				case "Papel":
					cigar[i] = Ingredient.createPaper();
					break;
				case "end":
					cigar[i] = null;
					break;
			}
		}
	}
	
	/**
	 * Sends the name of every ingredient the smoker has.
	 *
	 * @throws IOException If there are problems sending and receiving messages between the server and the client.
	 */
	public void sendCigar() throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		for (Ingredient ingredient : this.cigar) {
			if (ingredient != null) {
				dataOutputStream.writeUTF(ingredient.getName());
			} else {
				dataOutputStream.writeUTF("end");
			}
		}
	}
	
	/**
	 * Checks if the smoker has all the ingredients they need to roll their cigar and smoke. Shows which ingredients
	 * the smoker has and how many are left for them to smoke.
	 *
	 * @return True, if the smoker has all the ingredients; False, if they don't.
	 */
	public boolean checkCigarIngredients() {
		boolean allIngredients = true;
		int missingIngredientsCount = 0;
		System.out.println(this.actorName + " tiene:");
		for (Ingredient ingredient : this.cigar) { // List all the ingredients the smoker has.
			if (ingredient != null) {
				System.out.println("    - " + ingredient.getName());
			} else { // Counts how many ingredients the smoker doesn't have.
				missingIngredientsCount += 1;
				allIngredients = false;
			}
		}
		
		if (missingIngredientsCount != 0) {
			System.out.println("\n  Le falta(n) " + missingIngredientsCount + " ingrediente(s).");
		}
		
		return allIngredients;
	}
	
	/**
	 * Counts how many ingredients the smoker has without showing it to the user.
	 *
	 * @return How many ingredients the smoker has.
	 */
	public int countCigarIngredients() {
		int ingredientsQuantity = 0;
		for (int i = 1; i < this.cigar.length; i++) {
			if (this.cigar[i] != null) {
				ingredientsQuantity += 1;
			}
		}
		
		return ingredientsQuantity;
	}
	
	/**
	 * Makes the smoker to smoke. Later, restart the extra ingredients to null and the tries count.
	 */
	public void rollCigar() {
		this.smoke();
		this.clearExtraCigarIngredients();
		this.restartTriesCount();
	}
	
	/**
	 * Put down the smoker to sleep for 10 seconds to simulate that they are smoking.
	 */
	private void smoke() {
		try {
			System.out.println("\n" + this.actorName + " está fumando ahora.\n");
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			System.out.println("El fumador ha sido interrumpido mientras fumaba.\n");
		}
	}
	
	/**
	 * Assign null to the extra ingredients.
	 */
	private void clearExtraCigarIngredients() {
		for (int i = 1; i < this.cigar.length; i++) {
			this.cigar[i] = null;
		}
	}
	
	/**
	 * Checks if the bench has any ingredient left. If it does, assign to bench's ingredient the smoker's cigar's
	 * next empty space and notifies to the user. But, if the smoker already has that ingredient, doesn't make any
	 * assignment and also notifies to the user.
	 */
	protected void takeMissingIngredients() {
		if (bench.countIngredientsLeft() > 0) {
			for (int i = 0; i < this.cigar.length; i++) {
				if (this.cigar[i] != null) {
					if (this.cigar[i].getName().equals(bench.getIngredientName())){
						System.out.println(this.actorName + " ya tiene este ingrediente.");
						break;
					}
				} else {
					this.cigar[i] = bench.giveIngredient();
					System.out.println(this.actorName + " acaba de tomar " + this.cigar[1].getName() + ".");
					break;
				}
			}
			
		} else {
			System.out.println("No hay ingredients en esta banca.");
		}
		System.out.println();
	}
	
	public int getTriesCount() {
		return triesCount;
	}
	
	/**
	 * Adds 1 to the tries count.
	 */
	public void increaseTriesCount() {
		this.triesCount += 1;
	}
	
	/**
	 * Restarts the tries count to 1.
	 */
	public void restartTriesCount() {
		this.triesCount = 1;
	}
}
