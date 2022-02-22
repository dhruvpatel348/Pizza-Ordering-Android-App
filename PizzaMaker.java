package com.example.project5;

import java.io.Serializable;

/**
 * Class representation of a Pizza Maker
 * Create an instance of subclasses based on the chosen flavor
 * Contains methods to return Pizza Flavor instance 
 * 
 * @author Dhruvkumar Patel, Oluwadamola Olugboji
 *
 */

public class PizzaMaker implements Serializable {
	/**
	 * Method that Create an instance of subclasses based on the chosen flavor
	 * @return Pizza flavor instance
	 * @param flavor pizza type
	 */
	public static Pizza createPizza(String flavor) {
		Pizza pizza = null;

        if (flavor.equals("Deluxe")) {
            pizza = new Deluxe();
        } else if (flavor.equals("Hawaiian")) {
            pizza = new Hawaiian();
        } else if (flavor.equals("Pepperoni")) {
            pizza = new Pepperoni();

        }
        return pizza;
}
}
