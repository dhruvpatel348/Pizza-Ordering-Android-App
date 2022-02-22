package com.example.project5;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representation of a Deluxe Pizza 
 * Contains methods to get the price of Deluxe Pizza
 * Contains methods to return a textual representation of the Pizza and its toppings.
 * 
 * @author Dhruvkumar Patel, Oluwadamola Olugboji
 *
 */
public class Deluxe extends Pizza implements Serializable {
	
	/**
	 * Constructor method
	 */
	public Deluxe()
	{
		size = Size.valueOf("small");
		toppings.add(new Topping("Pepperoni"));
		toppings.add(new Topping("Mushrooms"));
		toppings.add(new Topping("Green Pepper"));
		toppings.add(new Topping("Onion"));
		toppings.add(new Topping("Olives"));	
		
	}
	
	/**
	 * Constructor method
	 * @param size size of Pizza and its toppings
	 * @param toppings toppings arrayList
	 */
	public Deluxe(Size size, ArrayList<Topping> toppings)
	{
		this.size = size;
		this.toppings = toppings;
	}

	/**
	 * Calculates the Price of the Deluxe Pizza
	 * @return price of the deluxe pizza
	 */
	@Override
	public double price() {
		if(toppings.size() <= Cons.FIVE_TOPPINGS)
		{
			if(size == Size.valueOf("small"))
				return Cons.DELUXE_PRICE;
			else if(size == Size.valueOf("medium"))
					return Cons.DELUXE_PRICE + Cons.SIZE_INCREASE;
			else
				return Cons.DELUXE_PRICE + Cons.SIZE_INCREASE + Cons.SIZE_INCREASE;
		}
		else
		{
			if(size == Size.valueOf("small"))
				return Cons.DELUXE_PRICE + ((toppings.size() - Cons.FIVE_TOPPINGS) * Cons.TOPPING_PRICE);
			else if(size == Size.valueOf("medium"))
					return Cons.DELUXE_PRICE + Cons.SIZE_INCREASE + ((toppings.size() - Cons.FIVE_TOPPINGS) *  Cons.TOPPING_PRICE);
			else 
				return Cons.DELUXE_PRICE + Cons.SIZE_INCREASE + Cons.SIZE_INCREASE + ((toppings.size() - Cons.FIVE_TOPPINGS) *  Cons.TOPPING_PRICE);
		}
	}
	
	/**
	 * Textual representation of the Pizza and its toppings
	 * @return String containing the pizza and its toppings
	 */
	@Override
	 public String toString() {
		String a = "Deluxe pizza: ";
		String b = "";
		for(Topping temp : getToppings()) {
			b += temp.getToppingName() + ", ";
		}
		 return a + b;
	 }

}
