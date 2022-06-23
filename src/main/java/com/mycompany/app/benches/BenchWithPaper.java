package com.mycompany.app.benches;

import com.mycompany.app.Ingredient;

import java.util.Arrays;

public class BenchWithPaper extends Bench{
	public BenchWithPaper() {
		this.replenishIngredients();
		this.ingredientName = this.ingredients[0].name();
		this.id += this.ingredientName;
	}
	
	/**
	 * Replenish the amount of ingredients on the bench.
	 */
	@Override
	public synchronized void replenishIngredients() {
		Arrays.fill(ingredients, Ingredient.createPaper());
		System.out.println(ingredientName + "es repuestos.");
	}
}
