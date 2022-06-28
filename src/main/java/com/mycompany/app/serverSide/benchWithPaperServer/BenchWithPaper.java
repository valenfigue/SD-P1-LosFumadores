package com.mycompany.app.serverSide.benchWithPaperServer;

import com.mycompany.app.common.Bench;
import com.mycompany.app.common.Ingredient;

import java.util.Arrays;

/**
 * The bench that has papers.
 *
 * @author valen
 */
public class BenchWithPaper extends Bench {
	public BenchWithPaper() {
		this.replenishIngredients();
		this.ingredientName = this.ingredients[0].getName();
		this.id += this.ingredientName;
	}
	
	/**
	 * Replenish the bench's ingredients.
	 */
	@Override
	public synchronized void replenishIngredients() {
		Arrays.fill(ingredients, Ingredient.createPaper());
		String output = ingredientName + "es repuestos.";
		
		if (!output.equals("nulles repuestos.")) { // To avoid this message when the bench is created.
			System.out.println(output);
		}
	}
}
