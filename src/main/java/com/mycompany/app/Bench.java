package com.mycompany.app;

import java.util.Arrays;

/**
 * Where the ingredients needed by smokers are to create their cigars.
 * The vendor replenish the ingredients.
 *
 * @author valen
 */
public class Bench {
	/**
	 * The number of benches created.
	 */
	private static int benchNumber = 0;
	/**
	 * Bench identifier to the user.
	 */
	private String id = "Bench N° ";
	private final int number; // TODO eliminar, ya que no es necesario.
	private final Ingredient[] tobaccos = new Ingredient[2];
	private final Ingredient[] matchsticks = new Ingredient[2];
	private final Ingredient[] papers = new Ingredient[2];
	
	/**
	 * Creates a bench instance with a couple of tobaccos, a couple of matchsticks and a couple of papers.
	 */
	public Bench() {
		benchNumber += 1;
		this.id += benchNumber;
		number = benchNumber;
		
		this.replenishTobaccos();
		this.replenishMatchsticks();
		this.replenishPapers();
	}
	
	/**
	 * Checks the amount of tobaccos left and, if there are any, it gives a tobacco to a smoker.
	 * Then, decrease the amount of tobaccos on the bench.
	 *
	 * @return A tobacco.
	 */
	public synchronized Ingredient giveTobacco() {
		int amountOfTobaccos = this.tobaccos.length;
		for (int i = amountOfTobaccos - 1; i >= 0; i--) {
			if (this.tobaccos[i] != null) {
				Ingredient tobacco = this.tobaccos[i];
				this.tobaccos[i] = null;
				return tobacco;
			}
		}
		
		return null;
	}
	
	/**
	 * Checks the amount of matchsticks left and, if there are any, it gives a matchstick to a smoker.
	 * Then, decrease the amount of matchsticks on the bench.
	 *
	 * @return A matchstick.
	 */
	public synchronized Ingredient giveMatchstick() {
		int amountOfMatchsticks = this.matchsticks.length;
		for (int i = amountOfMatchsticks - 1; i >= 0; i--) {
			if (this.matchsticks[i] != null) {
				Ingredient tobacco = this.matchsticks[i];
				this.matchsticks[i] = null;
				return tobacco;
			}
		}
		
		return null;
	}
	
	/**
	 * Checks the amount of papers left and, if there are any, it gives a paper to a smoker.
	 * Then, decrease the amount of papers on the bench.
	 *
	 * @return A paper.
	 */
	public synchronized Ingredient givePaper() {
		int amountOfPapers = this.papers.length;
		for (int i = amountOfPapers - 1; i >= 0; i--) {
			if (this.papers[i] != null) {
				Ingredient paper = this.papers[i];
				this.papers[i] = null;
				return paper;
			}
		}
		
		return null;
	}
	
	/**
	 * Replenish the amount of tobaccos on the bench.
	 */
	public void replenishTobaccos() {
		Arrays.fill(tobaccos, Ingredient.createTobacco());
	}
	
	/**
	 * Replenish the amount of matchsticks on the bench.
	 */
	public void replenishMatchsticks() {
		Arrays.fill(matchsticks, Ingredient.createMatchstick());
	}
	
	/**
	 * Replenish the amount of papers on the bench.
	 */
	public void replenishPapers() {
		Arrays.fill(papers, Ingredient.createPaper());
	}
	
	public synchronized int amountTobaccosLeft() {
		int totalTobaccosLeft = 0;
		
		for (Ingredient tobacco : this.tobaccos) {
			if (tobacco != null) {
				totalTobaccosLeft += 1;
			}
		}
		
		return totalTobaccosLeft;
	}
	
	public synchronized int amountMatchsticksLeft() {
		int totalMatchsticksLeft = 0;
		
		for (Ingredient matchstick : this.matchsticks) {
			if (matchstick != null) {
				totalMatchsticksLeft += 1;
			}
		}
		
		return totalMatchsticksLeft;
	}
	
	public synchronized int amountPapersLeft() {
		int totalPapersLeft = 0;
		
		for (Ingredient paper : this.papers) {
			if (paper != null) {
				totalPapersLeft += 1;
			}
		}
		
		return totalPapersLeft;
	}
	
	/**
	 * Gets the bench's identifier with its number.
	 *
	 * @return Bench's identifier.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Gets the bench's number.
	 *
	 * @return Bench's number.
	 */
	public int getNumber() {
		return number;
	}
}
