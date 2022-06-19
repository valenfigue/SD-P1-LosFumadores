package ingredients;

/**
 * An ingredient for smokers to create a cigar.
 *
 * @author valen
 */
public class Tobacco extends Ingredient {
	
	protected Tobacco(String name) {
		super(name);
	}
	
	/**
	 * Creates a specific ingredient with its name.
	 *
	 * @return The ingredient.
	 */
	@Override
	public Ingredient createIngredient() {
		String name = "Tabaco";
		return new Tobacco(name);
	}
}
