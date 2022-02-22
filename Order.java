package com.example.project5;
/**
* Class representation of the current order of the customer
* Contains methods that add pizza, remove pizza and return the instance variables and Pizza information
* @author Dhruvkumar Patel, Oluwadamola Olugboji
*/
import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable{
	private String phoneNumber;
	private ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
	/**
	 * Constructor
	 * @param phoneNumber of the customer
	 * @param pizzaList of the customer
	 */
	public Order(String phoneNumber, ArrayList<Pizza> pizzaList)
	{
		this.phoneNumber = phoneNumber;
		this.pizzaList = pizzaList;
	}

	public Order() {

	}

	/**
 * Method to add pizza to the Pizzalist
 * @param pizza the pizza that get to be added
 */
	public void addPizza(Pizza pizza)
	{
		pizzaList.add(pizza);
		//return true;
	}
	/**
	 * Method to remove pizza from the pizalist
	 * @param pizza  pizza that customer want to remove 
	 */
	public void removePizza(Pizza pizza)
	{
		pizzaList.remove(pizza);
	}
	/**
	 * Method to get the whole list of the pizza that the customer added
	 * @return pizzaList that customer want to order
	 */
	public ArrayList<Pizza> getPizzaList(){
		return pizzaList;
	}
	/**
	 * Method to set the phoneNumber
	 * @param phoneNumber as a input to set
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
		
	}
	/**
	 * Method to get Phone Number of Current Order
	 * @return phoneNumber that wanted to be accessed
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	/**
	 * Method to get Order Total of the customer
	 * @return total of the customer
	 */
	public double getOrderTotal() {
		double total = 0.0;
		for(Pizza temp: pizzaList) {
			total += temp.price();
		}
		return total;
	}
	/**
	 * Method to get the tax of the order of the customer
	 * @return tax total
	 */
	public double getOrderTax() {
		double total = 0.0;
		for(Pizza temp: pizzaList) {
			total += (temp.price() * 0.06625);
		}
		return total;
	}
	/**
	 * Method to get the textual representation of the PizzaList
	 * @return string of all the pizza that the customer ordered.
	 */
	@Override
	public String toString() {
		String outputString = "";
		for(int i = 0; i<pizzaList.size(); i++) {
			outputString += "Pizza " + String.valueOf(i) + ": "+ pizzaList.get(i).toString();
		}
		return outputString;
	}
	

}
