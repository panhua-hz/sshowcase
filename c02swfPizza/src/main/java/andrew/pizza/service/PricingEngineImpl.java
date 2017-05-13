package andrew.pizza.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import andrew.pizza.domain.Order;
import andrew.pizza.domain.Pizza;
import andrew.pizza.domain.PizzaSize;

@Repository
public class PricingEngineImpl implements PricingEngine {
	private static final Logger logger = LoggerFactory.getLogger(PricingEngineImpl.class);

	private static Map<PizzaSize, Float> SIZE_PRICES;
	static {
		SIZE_PRICES = new HashMap<PizzaSize, Float>();
		SIZE_PRICES.put(PizzaSize.SMALL, 6.99f);
		SIZE_PRICES.put(PizzaSize.MEDIUM, 7.99f);
		SIZE_PRICES.put(PizzaSize.LARGE, 8.99f);
		SIZE_PRICES.put(PizzaSize.GINORMOUS, 9.99f);
	}
	private static float PRICE_PER_TOPPING = 0.20f;

	@Override
	public double calculateOrderTotal(Order order) {
		logger.debug("Calculating order total");
		float total = 0.0f;
		for (Pizza pizza : order.getPizzas()) {
			float pizzaPrice = SIZE_PRICES.get(pizza.getPizzaSize());
			if (pizza.getPizzaToppings().size() > 2) {
				pizzaPrice += (pizza.getPizzaToppings().size() * PRICE_PER_TOPPING);
			}
			total += pizzaPrice;
		}
		return total;
	}

}
