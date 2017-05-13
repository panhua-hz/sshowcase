package andrew.pizza.domain;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

public enum PizzaTopping {
	PEPPERONI, SAUSAGE, HAMBURGER, MUSHROOM, CANADIAN_BACON, PINEAPPLE, GREEN_PEPPER, JALAPENO, TOMATO, ONION, EXTRA_CHEESE;
	public static List<PizzaTopping> asList() {
		PizzaTopping[] all = PizzaTopping.values();
		return Arrays.asList(all);
	}

	@Override
	public String toString() {
		return WordUtils.capitalizeFully(name().replace('_', ' '));
	}

}
