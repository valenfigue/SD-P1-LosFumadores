package com.mycompany.app;

import junit.framework.TestCase;

public class BenchTest extends TestCase {
	private Bench bench;
	
	public void setUp() throws Exception {
		super.setUp();
		bench = new Bench();
	}
	
	/**
	 * Tests when a smoker wants to take a paper when the bench is full of tobaccos.
	 * <p></p>
	 * A tobacco should be given and the amount of tobaccos decreased.
	 */
	public void testGiveTobaccoWhenFull() {
		Ingredient tobacco = Ingredient.createTobacco();
		int initialTotalTobaccos = bench.amountTobaccosLeft();
		
		Ingredient ingredientFromBench = bench.giveTobacco();
		int finalTotalTobaccos = bench.amountTobaccosLeft();
		
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
	public void testGiveTobaccoWhenNotFullButNotEmpty() {
		Ingredient tobacco = Ingredient.createTobacco();
		int initialTotalTobaccos = bench.amountTobaccosLeft();

		Ingredient ingredientFromBench = null;
		for (int i = 0; i < 2; i++) {
			ingredientFromBench = bench.giveTobacco();
		}
		int finalTotalTobaccos = bench.amountTobaccosLeft();
		
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
	public void testGiveTobaccoWhenEmpty() {
		Ingredient ingredientFromBench = null;
		for (int i = 0; i <= 2; i++) {
			ingredientFromBench = bench.giveTobacco();
		}
		int finalTotalTobaccos = bench.amountTobaccosLeft();
		
		assertNull(ingredientFromBench);
		assertEquals(0, finalTotalTobaccos);
	}
	
	/**
	 * Tests when a smoker wants to take a matchstick when the bench is full of matchsticks.
	 * <p></p>
	 * A matchstick should be given and the amount of matchsticks decreased.
	 */
	public void testGiveMatchstickWhenFull() {
		Ingredient matchstick = Ingredient.createMatchstick();
		int initialTotalMatchsticks = bench.amountMatchsticksLeft();
		
		Ingredient ingredientFromBench = bench.giveMatchstick();
		int finalTotalMatchsticks = bench.amountMatchsticksLeft();
		
		try {
			assertEquals(matchstick.name(), ingredientFromBench.name());
			assertEquals(initialTotalMatchsticks - 1, finalTotalMatchsticks);
		} catch (NullPointerException e) {
			fail();
		}
	}
	
	/**
	 * Tests when a smoker wants to take a matchstick and one has already been taken.
	 * <p></p>
	 * A matchstick should be given and the amount of matchsticks decreased.
	 */
	public void testGiveMatchstickWhenNotFullButNotEmpty() {
		Ingredient matchstick = Ingredient.createMatchstick();
		int initialTotalMatchsticks = bench.amountMatchsticksLeft();
		
		Ingredient ingredientFromBench = null;
		for (int i = 0; i < 2; i++) {
			ingredientFromBench = bench.giveMatchstick();
		}
		int finalTotalMatchsticks = bench.amountMatchsticksLeft();
		
		try {
			assertEquals(matchstick.name(), ingredientFromBench.name());
			assertEquals(initialTotalMatchsticks - 2, finalTotalMatchsticks);
		} catch (NullPointerException e) {
			fail();
		}
	}
	
	/**
	 * Tests when a smoker wants to take a matchstick but there are no more left on the bench.
	 * <p></p>
	 * Any matchstick should be given and the amount of matchsticks should be equal to zero.
	 */
	public void testGiveMatchstickWhenEmpty() {
		Ingredient ingredientFromBench = null;
		for (int i = 0; i <= 2; i++) {
			ingredientFromBench = bench.giveMatchstick();
		}
		int finalTotalMatchsticks = bench.amountMatchsticksLeft();
		
		assertNull(ingredientFromBench);
		assertEquals(0, finalTotalMatchsticks);
	}
	
	/**
	 * Tests when a smoker wants to take a paper when the bench is full of papers.
	 * <p></p>
	 * A paper should be given and the amount of papers decreased.
	 */
	public void testGivePaperWhenFull() {
		Ingredient paper = Ingredient.createPaper();
		int initialTotalPapers = bench.amountPapersLeft();
		
		Ingredient ingredientFromBench = bench.givePaper();
		int finalTotalPapers = bench.amountPapersLeft();
		
		try {
			assertEquals(paper.name(), ingredientFromBench.name());
			assertEquals(initialTotalPapers - 1, finalTotalPapers);
		} catch (NullPointerException e) {
			fail();
		}
	}
	
	/**
	 * Tests when a smoker wants to take a paper and one has already been taken.
	 * <p></p>
	 * A paper should be given and the amount of papers decreased.
	 */
	public void testGivePaperWhenNotFullButNotEmpty() {
		Ingredient paper = Ingredient.createPaper();
		int initialTotalPapers = bench.amountPapersLeft();

		Ingredient ingredientFromBench = null;
		for (int i = 0; i < 2; i++) {
			ingredientFromBench = bench.givePaper();
		}
		int finalTotalPapers = bench.amountPapersLeft();
		
		try {
			assertEquals(paper.name(), ingredientFromBench.name());
			assertEquals(initialTotalPapers - 2, finalTotalPapers);
		} catch (NullPointerException e) {
			fail();
		}
	}
	
	/**
	 * Tests when a smoker wants to take a paper but there are no more left on the bench.
	 * <p></p>
	 * Any paper should be given and the amount of papers should be equal to zero.
	 */
	public void testGivePaperWhenEmpty() {
		Ingredient ingredientFromBench = null;
		for (int i = 0; i <= 2; i++) {
			ingredientFromBench = bench.givePaper();
		}
		int finalTotalPapers = bench.amountPapersLeft();
		
		assertNull(ingredientFromBench);
		assertEquals(0, finalTotalPapers);
	}
	
	public void testReplenishTobaccos() {
		int totalTobaccos = bench.amountTobaccosLeft();
		for (int i = 0; i <= totalTobaccos; i++) {
			bench.giveTobacco();
		}
		int initialTotalTobaccos = bench.amountTobaccosLeft();
		
		bench.replenishTobaccos();
		int finalTotalTobaccos = bench.amountTobaccosLeft();
		
		assertTrue(initialTotalTobaccos < finalTotalTobaccos);
		assertEquals(totalTobaccos, finalTotalTobaccos);
	}
	
	public void testReplenishMatchsticks() {
		int totalMatchsticks = bench.amountMatchsticksLeft();
		for (int i = 0; i <= totalMatchsticks; i++) {
			bench.giveMatchstick();
		}
		int initialTotalMatchsticks = bench.amountMatchsticksLeft();
		
		bench.replenishMatchsticks();
		int finalTotalMatchsticks = bench.amountMatchsticksLeft();
		
		assertTrue(initialTotalMatchsticks < finalTotalMatchsticks);
		assertEquals(totalMatchsticks, finalTotalMatchsticks);
	}
	
	public void testReplenishPapers() {
		int totalPapers = bench.amountPapersLeft();
		for (int i = 0; i <= totalPapers; i++) {
			bench.givePaper();
		}
		int initialTotalPapers = bench.amountPapersLeft();
		
		bench.replenishPapers();
		int finalTotalPapers = bench.amountPapersLeft();
		
		assertTrue(initialTotalPapers < finalTotalPapers);
		assertEquals(totalPapers, finalTotalPapers);
	}
	
	public void testGetId() {
		Bench anotherBench = new Bench();
		
		assertEquals("Bench N° " + (this.bench.getNumber() + 1), anotherBench.getId());
	}
	
	public void testGetNumber() {
		Bench anotherBench = new Bench();
		
		assertEquals(this.bench.getNumber() + 1, anotherBench.getNumber());
	}
}