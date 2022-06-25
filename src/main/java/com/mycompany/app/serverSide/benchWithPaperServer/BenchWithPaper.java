package com.mycompany.app.serverSide.benchWithPaperServer;

import com.mycompany.app.common.Bench;
import com.mycompany.app.common.Ingredient;

import java.util.Arrays;

public class BenchWithPaper extends Bench {
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
		String output = ingredientName + "es repuestos.";
		
		if (!output.equals("nulles repuestos.")) {
			System.out.println(output);
		}
	}
}
