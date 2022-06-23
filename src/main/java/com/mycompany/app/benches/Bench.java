package com.mycompany.app.benches;

import com.mycompany.app.Ingredient;

/**
 * Where the ingredients needed by smokers are to create their cigars.
 * The vendor replenish the ingredients.
 *
 * @author valen
 */
public abstract class Bench {
	/**
	 * Bench identifier to the user.
	 */
	protected String id = "Banca con ";
	protected String ingredientName;
	
	protected final Ingredient[] ingredients = new Ingredient[2];
	
	/**
	 * Gives one ingredient to a smoker.
	 * Then, decrease the amount of ingredients on the bench.
	 *
	 * @return An ingredient.
	 */
	public synchronized Ingredient giveIngredient() {
		int amountOfIngredients = this.ingredients.length;
		for (int i = amountOfIngredients - 1; i >= 0; i--) {
			if (this.ingredients[i] != null) {
				Ingredient ingredient = this.ingredients[i];
				this.ingredients[i] = null;
				return ingredient;
			}
		}
		
		return null;
	}
	
	/**
	 * Replenish the amount of ingredients on the bench.
	 */
	public abstract void replenishIngredients();
	
	/**
	 * Counts how many ingredients are left in the bench.
	 *
	 * @return Amount of ingredients in the bench.
	 */
	public int countIngredientsLeft() {
		int totalIngredientsLeft = 0;
		
		for (Ingredient ingredient : this.ingredients) {
			if (ingredient != null) {
				totalIngredientsLeft += 1;
			}
		}
		
		return totalIngredientsLeft;
	}
	
	/**
	 * Gets the bench's identifier with its number.
	 *
	 * @return Bench's identifier.
	 */
	public String getId() {
		return id;
	}
	
	public String getIngredientName() {
		return this.ingredientName;
	}
}
