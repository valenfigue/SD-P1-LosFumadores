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
	protected final Ingredient[] cigar = new Ingredient[3];
	protected int triesCount = 1;
	
	protected void fistIngredient(Ingredient ingredient) {
		this.cigar[0] = ingredient;
		this.actorName = "Fumador con " + ingredient.name();
	}
	
	@Override
	public void run() {
		System.out.println(this.actorName + " conectado.");
		try {
			this.receiveCigar(); // The smoker receive the ingredients that already has, from the server.
			this.sendBenchId();
//			this.wait(6 * 1000);
			this.takeMissingIngredients(); // Tries to take a missing ingredient.
			this.sendCigar(); // The smoker send their updated ingredients list.
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		} /*catch (InterruptedException e) {
			System.out.println("El " + this.actorName + " ha sido interrumpido.");
		}*/
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
	
	public void sendBenchId() throws IOException, InterruptedException {
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		dataOutputStream.writeUTF(bench.getId());
		
		System.out.println("\n" + this.actorName + " va a intentar tomar un ingrediente de esta banca.");
		sleep(6 * 1000);
	}
	
	public void receiveBenchId() throws IOException, InterruptedException {
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		benchId = dataInputStream.readUTF();
		
		System.out.println("\n" + this.actorName + " va a intentar tomar un ingrediente de la " + benchId);
		sleep(6 * 1000);
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
			System.out.println("A " + this.actorName + " le falta(n) " + missingIngredientsCount + " ingrediente(s).");
		}
		
		return allIngredients;
	}
	
	public int countCigarIngredients() {
		int ingredientsQuantity = 0;
		for (int i = 1; i < this.cigar.length; i++) {
			if (this.cigar[i] != null) {
				ingredientsQuantity += 1;
			}
		}
		
		return ingredientsQuantity;
	}
	
	
	public void rollCigar() {
		this.smoke();
		this.clearExtraCigarIngredients();
	}
	
	/**
	 * Put on the smoker to sleep for 10 seconds to simulate that they are smoking.
	 */
	private void smoke() {
		try {
			System.out.println("\nEl fumador que empieza con " + this.cigar[0].name() + " está fumando ahora.\n");
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	private void clearExtraCigarIngredients() {
		for (int i = 1; i < this.cigar.length; i++) {
			this.cigar[i] = null;
		}
	}
	
	protected void takeMissingIngredients() {
		if (bench.countIngredientsLeft() > 0) {
			for (int i = 0; i < this.cigar.length; i++) {
				if (this.cigar[i] != null) {
					if (this.cigar[i].name().equals(bench.getIngredientName())){
						System.out.println(this.actorName + " ya tiene este ingrediente.");
						break;
					}
				} else {
					this.cigar[i] = bench.giveIngredient();
					System.out.println(this.actorName + " acaba de tomar " + this.cigar[1].name());
					break;
				}
			}
			
		} else {
			System.out.println("No hay ingredients en esta banca.");
		}
	}
	
	public int getTriesCount() {
		return triesCount;
	}
	
	public void increaseTriesCount() {
		this.triesCount += 1;
	}
	
	public void restartTriesCount() {
		this.triesCount = 1;
	}
}
