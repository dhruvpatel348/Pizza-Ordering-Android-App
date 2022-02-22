package com.example.project5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

/**
 * MainActivity class for the Home View (Main Menu)
 *
 * @author Dhrukumar Patel, Oluwadamola Olugboji
 */
public class MainActivity extends AppCompatActivity {

    private Button currentOrderButton, storeOrderButton, pepperoniButton, hawaiianButton, deluxeButton;
    private TextInputLayout customerNumberText;
    private Pizza pizza;
    private Pizza pizza1;
    //public static Order currentOrder;
    private Order currentOrder = new Order();
    private StoreOrders storeOrders = new StoreOrders();
    //private String[] pList;
    private ArrayList<String> pList = new ArrayList<String>();

    /**
     * On Create Method that is implemented in the whole activity to open and process other activity and passing data.
     *
     * @param savedInstanceState input bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentOrderButton = (Button) findViewById(R.id.currentOrderButton);
        storeOrderButton = (Button) findViewById(R.id.storeOrderButton);
        pepperoniButton = (Button) findViewById(R.id.pepperoniButton);
        deluxeButton = (Button) findViewById(R.id.deluxeButton);
        hawaiianButton = (Button) findViewById(R.id.hawaiianButton);
        customerNumberText = (TextInputLayout) findViewById(R.id.customerNumberText);
        currentOrderButton.setOnClickListener(new View.OnClickListener() {
            /**
             * when currentOrder button is pressed to open current order activity
             * @param v view for current order activity.
             */
            @Override
            public void onClick(View v) {
                if (checkNumber() == false) {
                    Toast.makeText(MainActivity.this, "Enter a valid 10 digit number",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);

                pList.clear();
                for (Pizza temp : currentOrder.getPizzaList()) {
                    // ArrayList<Pizza> temp = MainActivity.currentOrder.getPizzaList();
                    pList.add(temp.toString());
                }
                currentOrder.setPhoneNumber(customerNumberText.getEditText().getText().toString());
                intent.putStringArrayListExtra("pList", pList);
                intent.putExtra("currentOrder", currentOrder);
                intent.putExtra("customerNumber", customerNumberText.getEditText().getText().toString());
                startActivityForResult(intent, 2);
                //openCurrentOrder();

            }
        });
        storeOrderButton.setOnClickListener(new View.OnClickListener() {
            /**
             * when storeOrder button is pressed to open store order activity
             * @param v view for store order activity.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StoreOrderActivity.class);
                intent.putExtra("storeOrders", storeOrders);
                startActivityForResult(intent, 3);
            }
        });

        pepperoniButton.setOnClickListener(new View.OnClickListener() {
            /**
             * when pepperoniPizza button is pressed to open pizzaOrder activity
             * @param v view for pizza order activity with pepperoni pizza inputs.
             */
            @Override
            public void onClick(View v) {
                if (checkNumber() == false) {
                    Toast.makeText(MainActivity.this, "Enter a valid 10 digit number",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, PizzaOrderActivity.class);
                String[] selected = {"Pepperoni"};
                String[] additional = {"Ham", "Chicken", "Sausage", "Mozarella", "Pineaple", "Mushrooms", "Green pepper", "Onion", "Olives"};
                intent.putExtra("selected", selected);
                intent.putExtra("additional", additional);
                pizza = PizzaMaker.createPizza("Pepperoni");
                intent.putExtra("pizza", pizza);
                startActivityForResult(intent, 1);
            }
        });
        /**
         * when HawaiianPizza button is pressed to open pizzaOrder activity
         * @param v view for pizza order activity with hawaiian pizza inputs.
         */
        hawaiianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkNumber() == false) {
                    Toast.makeText(MainActivity.this, "Enter a valid 10 digit number",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, PizzaOrderActivity.class);
                String[] selected = {"Pineapple", "Ham"};
                String[] additional = {"Chicken", "Sausage", "Mozarella", "Pepperoni", "Mushrooms", "Green pepper", "Onion", "Olives"};
                intent.putExtra("selected", selected);
                intent.putExtra("additional", additional);
                pizza = PizzaMaker.createPizza("Hawaiian");
                intent.putExtra("pizza", pizza);
                startActivityForResult(intent, 1);
            }
        });
        deluxeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * when deluxePizza button is pressed to open pizzaOrder activity
             * @param v view for pizza order activity with deluxe pizza inputs.
             */
            @Override
            public void onClick(View v) {
                if (checkNumber() == false) {
                    Toast.makeText(MainActivity.this, "Enter a valid 10 digit number",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, PizzaOrderActivity.class);

                String[] selected = {"Pepperoni", "Mushrooms", "Green Pepper", "Onion", "Olives"};
                String[] additional = {"Ham", "Pineapple", "Chicken", "Sausage", "Mozarella"};
                intent.putExtra("selected", selected);
                intent.putExtra("additional", additional);
                pizza = PizzaMaker.createPizza("Deluxe");
                intent.putExtra("pizza", pizza);
                startActivityForResult(intent, 1);
            }
        });


    }

    /**
     * Changing the result after coming back to Main Activity from other activity
     *
     * @param requestCode the code to identify the input matched to start a activity
     * @param resultCode  the code to identify if the activity open by pressing a button or pressing back button from another activity
     * @param data data that passes from the other activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                pizza1 = (Pizza) data.getSerializableExtra("resPizza");
                currentOrder.addPizza(pizza1);
                //Order currentOrder = new Order();

            }

        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                currentOrder = (Order) data.getSerializableExtra("resOrder");
                storeOrders.placeOrder(currentOrder);
                currentOrder = new Order("", new ArrayList<Pizza>());
            }
            else if(resultCode == RESULT_FIRST_USER){
                currentOrder = (Order) data.getSerializableExtra("currentOrder1");
            }

        } else if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                storeOrders = (StoreOrders) data.getSerializableExtra("resStoreOrders");

            }
            else if(resultCode == RESULT_FIRST_USER){
                storeOrders = (StoreOrders) data.getSerializableExtra("resStoreOrders1");
            }
        }
    }

    /**
     * checking the phonenumber as it required only 10 number digits
     * @return boolean true or false.
     */
    public Boolean checkNumber() {
        String customerNum = customerNumberText.getEditText().getText().toString();
        if (customerNum.length() != 10 || customerNum.matches("[a-zA-Z]+")) {
            return false;
        }
        return true;
    }


}