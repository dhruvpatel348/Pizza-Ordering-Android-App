package com.example.project5;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representation of a Pizza
 * Contains abstract method price which will be inherited to the subclasses
 * Contains protected variable toppings and size to determine the cost of Pizza
 * Contains methods to set/get the values of the instance variables
 * 
 * @author Dhruvkumar Patel, Oluwadamola Olugboji
 *
 */

public abstract class Pizza implements Serializable {
	 protected ArrayList<Topping> toppings = new ArrayList<Topping>();
	 protected Size size;
	 
	 /**
		 * Abstract Method
		 * @return price of Pizza
		 */
	 public abstract double price();
	 
	 /**
		 * Gets the list of toppings
		 * @return ArrayList containing toppings
		 */
	 
	 public ArrayList<Topping> getToppings()
	 {
		 return toppings;
	 }
	 /**
		 * Sets the size of the current pizza
		 * @param size from the Size class
		 */
	 public void setSize(Size size)
	 {
		 this.size = size;
	 }



}

