package andrew.pizza.service;

import andrew.pizza.domain.Order;

public interface PricingEngine {
	public double calculateOrderTotal(Order order);
}
