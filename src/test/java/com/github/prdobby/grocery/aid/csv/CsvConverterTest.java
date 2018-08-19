package com.github.prdobby.grocery.aid.csv;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.github.prdobby.grocery.aid.models.GroceryList;

public class CsvConverterTest {

    private static final String OMELETTE = "Omelette, 3 egg, 10oz diced onion, 1tbsp butter, 5g cheddar";
    private static final String TACO = "Taco, 1 tortilla, 4oz beef, 1tsp cilantro";
    private static final String BURRITO = "Burrito, 1 tortilla, 10g cheddar, 5oz beef, 1tbsp salsa";

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
    public void parsesALineCorrectly() throws Exception {
        final String input = OMELETTE;

        final GroceryList result = converter.apply(input);

        assertThat(result.getRecipes()).containsOnly("Omelette");
        assertThat(ingredientsAsStrings(result)).containsOnly("3.0 egg", "10.0 oz diced onion", "1.0 tbsp butter", "5.0 g cheddar");
    }

    @Test
    public void parsesLinesWithDifferentIngredients() throws Exception {
        final String input = String.join("\n", OMELETTE, TACO);

        final GroceryList result = converter.apply(input);
        assertThat(result.getRecipes()).containsOnly("Omelette", "Taco");
        assertThat(ingredientsAsStrings(result)).containsOnly("3.0 egg", "10.0 oz diced onion", "1.0 tbsp butter", 
                "5.0 g cheddar", "1.0 tortilla", "4.0 oz beef", "1.0 tsp cilantro");
    }

    @Test
    public void parsesLinesWithCombiningIngredients() throws Exception {
        final String input = String.join("\n", OMELETTE, TACO, BURRITO);

        final GroceryList result = converter.apply(input);
        assertThat(result.getRecipes()).containsOnly("Omelette", "Taco", "Burrito");
        assertThat(ingredientsAsStrings(result)).containsOnly("3.0 egg", "10.0 oz diced onion", "1.0 tbsp butter", 
                "15.0 g cheddar", "2.0 tortilla", "9.0 oz beef", "1.0 tsp cilantro", "1.0 tbsp salsa");
    }

    @Test
    public void parseLineUnregularDecimals() throws Exception {
        final String input = "Omelette, 0.5oz onion, .25tbsp butter, 1 egg";

        final GroceryList result = converter.apply(input);
        assertThat(result.getRecipes()).containsOnly("Omelette");
        assertThat(ingredientsAsStrings(result)).containsOnly("1.0 egg", "0.25 tbsp butter", "0.5 oz onion");
    }

    private List<String> ingredientsAsStrings(final GroceryList groceryList) {
        return groceryList.getIngredients().stream()
            .map(Object::toString)
            .collect(Collectors.toList());
    }
}
