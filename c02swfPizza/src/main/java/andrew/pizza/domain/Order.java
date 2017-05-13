package andrew.pizza.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import andrew.pizza.service.PricingEngine;
import andrew.pizza.service.PricingEngineImpl;

@Configurable("order")
public class Order implements Serializable {
	private static final long serialVersionUID = -6607144621569612468L;

	private Customer customer;
	private List<Pizza> pizzas;
	private double totalAmount;
	private Payment payment;
	
	public Order(){
		this.customer = new Customer();
		this.pizzas = new ArrayList<Pizza>();
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	public void addPizza(Pizza pizza){
		this.pizzas.add(pizza);
	}
	
	public double getTotalAmount() {
		this.pricingEngine = new PricingEngineImpl();
		totalAmount = this.pricingEngine.calculateOrderTotal(this);
		return totalAmount;
	}

	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	private transient PricingEngine pricingEngine;
	@Autowired
	public void setPricingEngine(PricingEngine pricingEngine) {
		this.pricingEngine = pricingEngine;
	}

    @Override
    public String toString() {
    	StringBuffer sb = new StringBuffer();
        if (this.customer != null){
        	sb.append(this.customer.toString()).append("\n");
        }
        if (pizzas != null && pizzas.size()>0){
        	for (Pizza pz : pizzas){
        		sb.append(pz.toString()).append("\n");
        	}
        }
        sb.append("TotalAmount: ").append(this.totalAmount).append("\n");
        if (payment != null){
        	sb.append(payment.toString());
        }
        return sb.toString();
    }

}
