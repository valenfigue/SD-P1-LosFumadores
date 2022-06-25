package com.mycompany.app.benchWithTobaccoServer;

import com.mycompany.app.common.Bench;
import com.mycompany.app.common.Ingredient;
import com.mycompany.app.serverSide.benchWithTobaccoServer.BenchWithTobacco;
import junit.framework.TestCase;

public class BenchWithTobaccoTest extends TestCase {
	private Bench bench;
	
	public void setUp() throws Exception {
		super.setUp();
		bench = new BenchWithTobacco();
	}
	
	/**
	 * Tests when a smoker wants to take a paper when the bench is full of tobaccos.
	 * <p></p>
	 * A tobacco should be given and the amount of tobaccos decreased.
	 */
	public void testGiveIngredientWhenFull() {
		Ingredient tobacco = Ingredient.createTobacco();
		int initialTotalTobaccos = bench.countIngredientsLeft();
		
		Ingredient ingredientFromBench = bench.giveIngredient();
		int finalTotalTobaccos = bench.countIngredientsLeft();
		
		try {
			assertEquals(tobacco.name(), ingredientFromBench.name());
			assertEquals(initialTotalTobaccos - 1, finalTotalTobaccos);
		} catch (NullPointerException e) {
			fail();
		}
	}
	
	/**
	 * Tests when a smoker wants to take a tobacco and one has already been taken.
	 * <p></p>
	 * A tobacco should be given and the amount of tobaccos decreased.
	 */
	public void testGiveIngredientWhenNotFullButNotEmpty() {
		Ingredient tobacco = Ingredient.createTobacco();
		int initialTotalTobaccos = bench.countIngredientsLeft();
		
		Ingredient ingredientFromBench = null;
		for (int i = 0; i < 2; i++) {
			ingredientFromBench = bench.giveIngredient();
		}
		int finalTotalTobaccos = bench.countIngredientsLeft();
		
		try {
			assertEquals(tobacco.name(), ingredientFromBench.name());
			assertEquals(initialTotalTobaccos - 2, finalTotalTobaccos);
		} catch (NullPointerException e) {
			fail();
		}
	}
	
	/**
	 * Tests when a smoker wants to take a tobacco but there are no more left on the bench.
	 * <p></p>
	 * Any tobacco should be given and the amount of tobaccos should be equal to zero.
	 */
	public void testGiveIngredientWhenEmpty() {
		Ingredient ingredientFromBench = null;
		for (int i = 0; i <= 2; i++) {
			ingredientFromBench = bench.giveIngredient();
		}
		int finalTotalTobaccos = bench.countIngredientsLeft();
		
		assertNull(ingredientFromBench);
		assertEquals(0, finalTotalTobaccos);
	}
	
	/**
	 * Tests if the bench's identifier is given.
	 * <p></p>
	 * A title "Banca con ", followed by the bench's ingredient, should be returned.
	 */
	public void testGetId() {
		String id = "Banca con Tabaco";
		assertEquals(id, bench.getId());
	}
	
	/**
	 * Tests if the ingredients that the bench has is given.
	 * <p></p>
	 * The bench's ingredient's name should be returned.
	 */
	public void testGetIngredientName() {
		String ingredientName = "Tabaco";
		assertEquals(ingredientName, bench.getIngredientName());
	}
	
	/**
	 * Tests if the bench's ingredient is replenished after being left empty.
	 * <p></p>
	 * Being left with no ingredients, and the bench being replenished right after, it should
	 * have the same amount of ingredients as when it was created.
	 */
	public void testReplenishIngredients() {
		int totalTobaccos = bench.countIngredientsLeft();
		for (int i = 0; i <= totalTobaccos; i++) {
			bench.giveIngredient();
		}
		int initialTotalTobaccos = bench.countIngredientsLeft();
		
		bench.replenishIngredients();
		int finalTotalTobaccos = bench.countIngredientsLeft();
		
		assertTrue(initialTotalTobaccos < finalTotalTobaccos);
		assertEquals(totalTobaccos, finalTotalTobaccos);
	}
}