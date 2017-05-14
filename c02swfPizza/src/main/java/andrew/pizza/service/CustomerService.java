package andrew.pizza.service;

import andrew.pizza.domain.Customer;
import andrew.pizza.domain.CustomerNotFoundException;

public interface CustomerService {
	public Customer lookupByPhoneNum(String phoneNumber) throws CustomerNotFoundException;
	public Customer addCustomer(Customer customer);
}
