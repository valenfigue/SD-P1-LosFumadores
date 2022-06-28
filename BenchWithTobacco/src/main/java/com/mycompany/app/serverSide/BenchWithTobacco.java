package com.mycompany.app.serverSide;

import com.mycompany.app.common.Bench;
import com.mycompany.app.common.Ingredient;

import java.util.Arrays;

/**
 * The bench that has tobacco.
 *
 * @author valen
 */
public class BenchWithTobacco extends Bench {
	
	public BenchWithTobacco() {
		this.replenishIngredients();
		this.ingredientName = this.ingredients[0].getName();
		this.id += this.ingredientName;
	}
	
	/**
	 * Replenish the bench's ingredients.
	 */
	@Override
	public synchronized void replenishIngredients() {
		Arrays.fill(ingredients, Ingredient.createTobacco());
		String output = ingredientName + "s repuestos.";
		
		if (!output.equals("nulls repuestos.")) { // To avoid this message when the bench is created.
			System.out.println(output);
		}
	}
}
