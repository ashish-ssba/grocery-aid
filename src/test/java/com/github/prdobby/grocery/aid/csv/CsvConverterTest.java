package com.github.prdobby.grocery.aid.csv;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.github.prdobby.grocery.aid.models.GroceryList;

public class CsvConverterTest {

    private static final String OMELETTE = "Omelette, 3 egg, 10oz diced onion, 1tbsp butter, 5g cheddar";
    private static final String BURRITO = "Burrito, 1 tortilla, 5.75g cheddar, 5 oz beef, 1 fl oz salsa";

    private CsvConverter converter;

    @Before
    public void setup() throws Exception {
        this.converter = new CsvConverter();
    }

    @Test
    public void testCsvConverterCanBeInstantiated() throws Exception {
        assertThat(converter).isNotNull();
    }

    @Test
    public void parsesIngredient_NoUnit() throws Exception {
        final String input = "Fried Egg, 1 egg";

        final GroceryList result = converter.apply(input);

        assertThat(result.getRecipes()).containsOnly("Fried Egg");
        assertThat(ingredientsAsStrings(result)).containsOnly("1 egg");
    }

    @Test
    public void parsesIngredient_NoSpaceBetweenAmountAndUnit() throws Exception {
        final String input = "Milk, 1pt milk";

        final GroceryList result = converter.apply(input);

        assertThat(result.getRecipes()).containsOnly("Milk");
        assertThat(ingredientsAsStrings(result)).containsOnly("1 pt milk");
    }

    @Test
    public void parsesIngredient_SpaceBetweenAmountAndUnit() throws Exception {
        final String input = "Milk, 1 pt milk";

        final GroceryList result = converter.apply(input);

        assertThat(result.getRecipes()).containsOnly("Milk");
        assertThat(ingredientsAsStrings(result)).containsOnly("1 pt milk");
    }

    @Test
    public void parsesIngredient_SpaceInUnit_NoSpaceBetweenAmountAndUnit() throws Exception {
        final String input = "Milk, 1fl oz milk";

        final GroceryList result = converter.apply(input);

        assertThat(result.getRecipes()).containsOnly("Milk");
        assertThat(ingredientsAsStrings(result)).containsOnly("1 fl oz milk");
    }

    @Test
    public void parsesIngredient_SpaceInUnit_SpaceBetweenAmountAndUnit() throws Exception {
        final String input = "Milk, 1 fl oz milk";

        final GroceryList result = converter.apply(input);

        assertThat(result.getRecipes()).containsOnly("Milk");
        assertThat(ingredientsAsStrings(result)).containsOnly("1 fl oz milk");
    }

    @Test
    public void parsesIngredient_NonStandardUnitName() throws Exception {
        final String input = "Milk, 1 litre milk";

        final GroceryList result = converter.apply(input);

        assertThat(result.getRecipes()).containsOnly("Milk");
        assertThat(ingredientsAsStrings(result)).containsOnly("1 l milk");
    }

    @Test
    public void parsesIngredient_DecimalInIngredient() throws Exception {
        final String input = "Milk, 1.5 pt milk";

        final GroceryList result = converter.apply(input);

        assertThat(result.getRecipes()).containsOnly("Milk");
        assertThat(ingredientsAsStrings(result)).containsOnly("1.5 pt milk");
    }

    @Test
    public void parsesIngredient_DecimalStartsIngredient() throws Exception {
        final String input = "Milk, .5 pt milk";

        final GroceryList result = converter.apply(input);

        assertThat(result.getRecipes()).containsOnly("Milk");
        assertThat(ingredientsAsStrings(result)).containsOnly("0.5 pt milk");
    }

    @Test
    public void parsesMultipleLines() throws Exception {
        final String input = String.join("\n", OMELETTE, BURRITO);

        final GroceryList result = converter.apply(input);

        assertThat(result.getRecipes()).containsOnly("Omelette", "Burrito");
        assertThat(ingredientsAsStrings(result)).containsOnly("3 egg", "10 oz diced onion", "1 tbsp butter", "5 g cheddar",
            "1 tortilla", "5.75 g cheddar", "5 oz beef", "1 fl oz salsa");
    }

    private List<String> ingredientsAsStrings(final GroceryList groceryList) {
        return groceryList.getIngredients().stream()
            .map(Object::toString)
            .collect(Collectors.toList());
    }
}
