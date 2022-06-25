package com.mycompany.app.common;

import com.mycompany.app.common.Ingredient;
import junit.framework.TestCase;

public class IngredientTest extends TestCase {
	
	public void testCreateTobacco() {
		String expectedName = "Tabaco";
		Ingredient tobacco = Ingredient.createTobacco();
		
		assertEquals(expectedName, tobacco.name());
	}
	
	public void testCreateMatchstick() {
		String expectedName = "Fósforo";
		Ingredient matchstick = Ingredient.createMatchstick();
		
		assertEquals(expectedName, matchstick.name());
	}
	
	public void testCreatePaper() {
		String expectedName = "Papel";
		Ingredient paper = Ingredient.createPaper();
		
		assertEquals(expectedName, paper.name());
	}
}