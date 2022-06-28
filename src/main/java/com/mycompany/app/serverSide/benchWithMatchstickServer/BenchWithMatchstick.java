package com.mycompany.app.serverSide.benchWithMatchstickServer;

import com.mycompany.app.common.Bench;
import com.mycompany.app.common.Ingredient;

import java.util.Arrays;

/**
 * The bench that has matchsticks.
 *
 * @author valen
 */
public class BenchWithMatchstick extends Bench {
	public BenchWithMatchstick() {
		this.replenishIngredients();
		this.ingredientName = this.ingredients[0].getName();
		this.id += this.ingredientName;
	}
	
	/**
	 * Replenish the bench's ingredients.
	 */
	@Override
	public synchronized void replenishIngredients() {
		Arrays.fill(ingredients, Ingredient.createMatchstick());
		String output = ingredientName + "s repuestos.";
		
		if (!output.equals("nulls repuestos.")) { // To avoid this message when the bench is created.
			System.out.println(output);
		}
	}
}
