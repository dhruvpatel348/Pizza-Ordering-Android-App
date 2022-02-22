package com.example.project5;

import java.io.Serializable;

/**
 * Class representation of a Hawaiian Pizza 
 * Contains methods to get the price of Hawaiian Pizza
 * Contains methods to return a textual representation of the Pizza and its toppings.
 * 
 * @author Dhruvkumar Patel, Oluwadamola Olugboji
 *
 */
public class Hawaiian extends Pizza implements Serializable {
	/**
	 * Constructor method
	 */
	public Hawaiian()
	{
		size = Size.valueOf("small");
		toppings.add(new Topping("Pineaple"));
		toppings.add(new Topping("Ham"));
	}
	/**
	 * Calculates the Price of the Hawaiian Pizza
	 * @return price of the Hawaiian pizza
	 */
	@Override
	public double price() {
		if(toppings.size() <= Cons.TWO_TOPPINGS)
		{
			if(size == Size.valueOf("small"))
				return Cons.HAWAIIAN_PRICE;
			else if(size == Size.valueOf("medium"))
					return Cons.HAWAIIAN_PRICE + Cons.SIZE_INCREASE;
			else 
				return Cons.HAWAIIAN_PRICE + Cons.SIZE_INCREASE + Cons.SIZE_INCREASE;
		}
		else
		{
			if(size == Size.valueOf("small"))
				return Cons.HAWAIIAN_PRICE + ((toppings.size() - Cons.TWO_TOPPINGS) *  Cons.TOPPING_PRICE);
			else if(size == Size.valueOf("medium"))
					return Cons.HAWAIIAN_PRICE + Cons.SIZE_INCREASE + ((toppings.size() - Cons.TWO_TOPPINGS) *  Cons.TOPPING_PRICE);
			else 
				return Cons.HAWAIIAN_PRICE + Cons.SIZE_INCREASE + Cons.SIZE_INCREASE + ((toppings.size() - Cons.TWO_TOPPINGS) *  Cons.TOPPING_PRICE);
		}
		
	}
	
	/**
	 * Textual representation of the Pizza and its toppings
	 * @return String containing the pizza and its toppings
	 */
	
	@Override
	 public String toString() {
		String a = "Hawaiian pizza: ";
		String b = "";
		for(Topping temp : getToppings()) {
			b += temp.getToppingName() + ", ";
		}
		 return a + b;
	 }

}
