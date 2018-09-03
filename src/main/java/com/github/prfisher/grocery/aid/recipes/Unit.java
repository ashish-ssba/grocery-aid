package com.github.prfisher.grocery.aid.recipes;

import java.util.HashMap;
import java.util.Map;

public enum Unit {
    NONE(""), 
    TEASPOON("tsp", "t"), 
    TABLESPOON("tbsp", "T"), 
    FLUID_OUNCE("fl oz"), 
    CUP("cup", "c"), 
    PINT("pt", "p"), 
    QUART("qt", "q"),
    GALLON("gal"),
    MILLILITER("ml", "mL", "millilitre"),
    LITER("l", "L", "litre"),
    POUND("lb"),
    OUNCE("oz"),
    MILLIGRAM("mg", "milligramme"),
    GRAM("g", "gramme"),
    KILLIGRAM("kg", "kilogramme");

    private static class Holder {
        static Map<String, Unit> UNIT_MAP = new HashMap<>();
    }

    private String abbreviation;

    private Unit(final String... alternativeSpellings) {
        Holder.UNIT_MAP.put(this.name().toLowerCase().replace("_", " "), this);

        for (final String spelling : alternativeSpellings) {
            Holder.UNIT_MAP.put(spelling, this);
        }

        this.abbreviation = alternativeSpellings[0];
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public static Unit parse(final String untrimmedUnit) {
        final String unit = untrimmedUnit.trim().replace(".", "");

        if (Holder.UNIT_MAP.containsKey(unit)) {
            return Holder.UNIT_MAP.get(unit);
        } else if (Holder.UNIT_MAP.containsKey(unit.toLowerCase())) {
            return Holder.UNIT_MAP.get(unit.toLowerCase());
        } else {
            return Unit.NONE;
        }
    }
}