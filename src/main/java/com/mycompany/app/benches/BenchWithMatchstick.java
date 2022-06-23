package com.mycompany.app.benches;

import com.mycompany.app.Ingredient;

import java.util.Arrays;

public class BenchWithMatchstick extends Bench{
	public BenchWithMatchstick() {
		this.replenishIngredients();
		this.ingredientName = this.ingredients[0].name();
		this.id += this.ingredientName;
	}
	
	/**
	 * Replenish the amount of ingredients on the bench.
	 */
	@Override
	public synchronized void replenishIngredients() {
		Arrays.fill(ingredients, Ingredient.createMatchstick());
		System.out.println(ingredientName + "s repuestos.");
	}
}
