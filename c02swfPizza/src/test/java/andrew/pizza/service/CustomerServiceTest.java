package andrew.pizza.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import andrew.pizza.domain.Customer;
import andrew.pizza.domain.CustomerNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/context_app.xml"})
public class CustomerServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);
	
	@Autowired
	CustomerService customerService;
	
	@Test
	public void lookupByPhoneNumTest(){
		try {
			Customer cus = customerService.lookupByPhoneNum("13758193654");
			logger.info(cus.toString());
		} catch (CustomerNotFoundException e) {
			logger.info(e.getMessage());
		}
		
		try {
			Customer cus = customerService.lookupByPhoneNum("155141411454");
			logger.info(cus.toString());
		} catch (CustomerNotFoundException e) {
			logger.info(e.getMessage());
		}
	}
	
	@Test
	public void registerCustomerTest(){
		Customer customer = new Customer("Andrew", "Sandun", "Hangzhou", "Zhejiang", "310027","12345678910");
		this.customerService.addCustomer(customer);
		try {
			customer = this.customerService.lookupByPhoneNum("12345678910");
			logger.info(customer.toString());
		} catch (CustomerNotFoundException e) {
			assertTrue("Not add customer.", false);
		}
	}
}
