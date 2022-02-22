package com.example.project5;

import java.io.Serializable;

/**
 * Class representation of a Pepperoni Pizza 
 * Contains methods to get the price of Pepperoni Pizza
 * Contains methods to return a textual representation of the Pizza and its toppings.
 * 
 * @author Dhruvkumar Patel, Oluwadamola Olugboji
 *
 */
public class Pepperoni extends Pizza implements Serializable {
	
	/**
	 * Constructor method
	 */
	public Pepperoni()
	{
		size = Size.valueOf("small");
		toppings.add(new Topping("Pepperoni"));
	}
	/**
	 * Calculates the Price of the Pepperoni Pizza
	 * @return price of the Pepperoni pizza
	 */
	@Override
	public double price() {
		if(toppings.size() <= 1)
		{
			if(size == Size.valueOf("small"))
				return Cons.PEPPERONI_PRICE;
			else if(size == Size.valueOf("medium"))
					return Cons.PEPPERONI_PRICE + Cons.SIZE_INCREASE;
			else 
				return Cons.PEPPERONI_PRICE + Cons.SIZE_INCREASE + Cons.SIZE_INCREASE;
		}
		else
		{
			if(size == Size.valueOf("small"))
				return Cons.PEPPERONI_PRICE + ((toppings.size() - 1)*  Cons.TOPPING_PRICE);
			else if(size == Size.valueOf("medium"))
					return Cons.PEPPERONI_PRICE + Cons.SIZE_INCREASE + ((toppings.size() - 1) *  Cons.TOPPING_PRICE);
			else 
				return Cons.PEPPERONI_PRICE + Cons.SIZE_INCREASE + Cons.SIZE_INCREASE + ((toppings.size() - 1) *  Cons.TOPPING_PRICE);
		}
		
	}
	
	/**
	 * Textual representation of the Pizza and its toppings
	 * @return String containing the pizza and its toppings
	 */
	
	@Override
	 public String toString() {
		String a = "Pepperoni pizza: ";
		String b = "";
		for(Topping temp : getToppings()) {
			b += temp.getToppingName() + ", ";
		}
		 return a + b;
	 }

}
