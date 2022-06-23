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
public class Smoker extends Client {
	protected Ingredient[] cigar = new Ingredient[3];
	protected int triesCount = 1;
	
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
		this.clearExtraCigarIngredients();
		this.restartTriesCount();
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
	
	private void clearExtraCigarIngredients() {
		for (int i = 1; i < this.cigar.length; i++) {
			this.cigar[i] = null;
		}
	}
	
	protected void takeMissingIngredients() {
		if (bench.countIngredientsLeft() > 0) {
			if (this.cigar[0].name().equals(bench.getIngredientName())){
				System.out.println(this.actorName + " ya tiene este ingrediente.");
			} else {
				for (int i = 1; i < this.cigar.length; i++) {
					if (this.cigar[i] != null) {
						if (this.cigar[i].name().equals(bench.getIngredientName())) {
							System.out.println(this.actorName + " ya tiene este ingrediente.");
						}
					} else {
						this.cigar[i] = bench.giveIngredient();
						System.out.println(this.actorName + " acaba de tomar " + this.cigar[1].name());
						break;
					}
				}
			}
			
		} else {
			System.out.println("No hay ingredients en esta banca.");
		}
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
