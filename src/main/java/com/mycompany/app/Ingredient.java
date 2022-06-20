package com.mycompany.app;

/**
 * An element that a smoker will use to make a cigar.
 *
 * @author valen
 */
public record Ingredient(String name) {
	
	
	/**
	 * Creates the ingredient tobacco.
	 *
	 * @return A tobacco.
	 */
	public static Ingredient createTobacco() {
		String name = "Tabaco";
		return new Ingredient(name);
	}
	
	/**
	 * Creates the ingredient matchstick.
	 *
	 * @return A matchstick.
	 */
	public static Ingredient createMatchstick() {
		String name = "Fósforo";
		return new Ingredient(name);
	}
	
	/**
	 * Creates the ingredient paper.
	 *
	 * @return A paper.
	 */
	public static Ingredient createPaper() {
		String name = "Papel";
		return new Ingredient(name);
	}
}
