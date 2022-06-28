package com.mycompany.app.serverSide.benchWithTobaccoServer;

import com.mycompany.app.common.Bench;
import com.mycompany.app.common.Ingredient;

import java.util.Arrays;

public class BenchWithTobacco extends Bench {
	
	public BenchWithTobacco() {
		this.replenishIngredients();
		this.ingredientName = this.ingredients[0].getName();
		this.id += this.ingredientName;
	}
	
	/**
	 * Replenish the amount of ingredients on the bench.
	 */
	@Override
	public synchronized void replenishIngredients() {
		Arrays.fill(ingredients, Ingredient.createTobacco());
		String output = ingredientName + "s repuestos.";
		
		if (!output.equals("nulls repuestos.")) {
			System.out.println(output);
		}
	}
}
