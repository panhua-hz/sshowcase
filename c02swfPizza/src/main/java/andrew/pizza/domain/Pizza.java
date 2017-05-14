package andrew.pizza.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Pizza implements Serializable{
	private static final long serialVersionUID = 5815956177286076721L;
	
	private PizzaSize pizzaSize;
	private List<PizzaTopping> pizzaToppings;

	public Pizza() {
		pizzaToppings = new ArrayList<PizzaTopping>();
		pizzaSize = PizzaSize.LARGE;
	}

	public PizzaSize getPizzaSize() {
		return pizzaSize;
	}

	public void setPizzaSize(PizzaSize pizzaSize) {
		this.pizzaSize = pizzaSize;
	}

	public void setPizzaSize(String sizeString) {
		this.pizzaSize = PizzaSize.valueOf(sizeString);
	}

	public List<PizzaTopping> getPizzaToppings() {
		return pizzaToppings;
	}

	public void setPizzaToppings(List<PizzaTopping> pizzaToppings) {
		this.pizzaToppings = pizzaToppings;
	}

	public void setPizzaToppings(String[] toppingStrings) {
		for (int i = 0; i < toppingStrings.length; i++) {
			pizzaToppings.add(PizzaTopping.valueOf(toppingStrings[i]));
		}
	}
	
    @Override
    public String toString() {
    	StringBuffer sb = new StringBuffer(pizzaSize.toString());
    	sb.append(": ");
    	for (PizzaTopping top : this.pizzaToppings){
    		sb.append(",").append(top);
    	}
    	return sb.toString();
    }

}
