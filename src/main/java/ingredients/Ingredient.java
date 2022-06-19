package ingredients;

/**
 * An element that a smoker will use to make a cigar.
 *
 * @author valen
 */
public abstract class Ingredient {
	protected final String name;
	
	protected Ingredient(String name) {
		this.name = name;
	}
	
	/**
	 * Creates a specific ingredient with its name.
	 *
	 * @return The ingredient.
	 */
	public abstract Ingredient createIngredient();
	
	public String getName() {
		return name;
	}
}
