package com.mycompany.app.serverSide.benchWithMatchstickServer;

import com.mycompany.app.common.Bench;
import com.mycompany.app.common.Ingredient;

import java.util.Arrays;

public class BenchWithMatchstick extends Bench {
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
		String output = ingredientName + "s repuestos.";
		
		if (!output.equals("nulls repuestos.")) {
			System.out.println(output);
		}
	}
}
