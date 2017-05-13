package andrew.mvc.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import andrew.pizza.domain.Customer;
import andrew.pizza.domain.CustomerNotFoundException;
import andrew.pizza.domain.Order;
import andrew.pizza.service.CustomerService;
import andrew.pizza.service.PaymentService;

@Component
public class PizzaAction {
	private static final Logger logger = LoggerFactory.getLogger(PizzaAction.class);

	@Autowired
	CustomerService customerService;

	@Autowired
	PaymentService paymentService;

	public Customer lookupCustomer(String phoneNumber) throws CustomerNotFoundException {
		logger.info("lookupByPhoneNum: " + phoneNumber);
		return this.customerService.lookupByPhoneNum(phoneNumber);
	}

	public void addCustomer(Customer customer) {
		this.customerService.addCustomer(customer);
	}

	public void saveOrder(Order order) {
		logger.info("saveOrder --->  " + order.toString());
	}

	public boolean checkDeliveryArea(String zipCode) {
		logger.warn("TODO: Flesh out the checkDeliveryArea() method.");
		return "310013".equals(zipCode);
	}
}
