package andrew.pizza.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import andrew.pizza.domain.Order;

public class OrderServiceImpl implements OrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Override
	public void saveOrder(Order order) {
		LOGGER.debug("SAVING ORDER:  " );
	    LOGGER.debug("   Customer:  " + order.getCustomer().getName());
	    LOGGER.debug("   # of Pizzas:  " + order.getPizzas().size());
	    LOGGER.debug("   Payment:  " + order.getPayment());
	}

}
