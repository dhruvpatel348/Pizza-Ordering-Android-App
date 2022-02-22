package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * PizzaOrderActivity class for the add the pizza to the order
 * @author Dhrukumar Patel, Oluwadamola Olugboji
 *
 */
public class PizzaOrderActivity extends AppCompatActivity {

    private Button addTopping, removeTopping, addToOrder;
    private TextView pizzaCost;
    private ListView unselectedToppingsListView, selectedToppingsListView;
    private Spinner pizzaSize;
    private String[] selected;
    private String[] additional;
    private String[] sizeArray;

    private final ArrayList<String> topSelectList = new ArrayList<String>();
    private final ArrayList<String> topAddList = new ArrayList<String>();
    private final ArrayList<String> spinnerList = new ArrayList<String>();

    private ArrayAdapter<String> adapter, adapter1, adapterSpin;
    private ArrayList<Topping> top;

    /**
     * On Create Method that is implemented in the whole activity to open and process other activity and passing data.
     * @param savedInstanceState input bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_order);
        pizzaCost = findViewById(R.id.pizzaCost);
        addTopping = findViewById(R.id.addToppingButton);
        removeTopping = findViewById(R.id.removeToppingButton);
        addToOrder = findViewById(R.id.addToOrderButton);
        unselectedToppingsListView = findViewById(R.id.unselectedToppingsListView);
        selectedToppingsListView = findViewById(R.id.selectedToppingsListView);
        pizzaSize = findViewById(R.id.pizzaSize);

        sizeArray = new String[]{"small", "medium", "large"};
        spinnerList.addAll(Arrays.asList(sizeArray));
        adapterSpin = new ArrayAdapter<String>(PizzaOrderActivity.this,
                android.R.layout.simple_list_item_1, spinnerList);

        pizzaSize.setAdapter(adapterSpin);
        Intent intent = getIntent();
        Pizza pizza = (Pizza) intent.getSerializableExtra("pizza");

        pizzaSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Selcting the size of pizza from the spinner view
             * @param parent parent as adapterView of spinner View
             * @param view spinner View input
             * @param position position of the item(size) which we select
             * @param id id of the size which we select
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = pizzaSize.getItemAtPosition(position).toString();
                if (selectedFromList == "small") {
                    //pizzaCost.setText("2.00");
                    pizza.setSize(Size.valueOf("small"));
                    pizzaCost.setText("Subtotal:" + String.valueOf(Round.round(pizza.price(), 2)));
                }
                if (selectedFromList == "medium") {
                    //pizzaCost.setText("2.00");
                    pizza.setSize(Size.valueOf("medium"));
                    pizzaCost.setText("Subtotal:" + String.valueOf(Round.round(pizza.price(), 2)));
                }
                if (selectedFromList == "large") {
                    //pizzaCost.setText("2.00");
                    pizza.setSize(Size.valueOf("large"));
                    pizzaCost.setText("Subtotal:" + String.valueOf(Round.round(pizza.price(), 2)));
                }

            }

            /**
             * when nothing is selected in spinner view then this method is implemented
             * @param parent adapterView as the parent of the spinnerView
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        selected = intent.getStringArrayExtra("selected");
        additional = intent.getStringArrayExtra("additional");
        topSelectList.addAll(Arrays.asList(selected));
        adapter = new ArrayAdapter<String>(PizzaOrderActivity.this,
                android.R.layout.simple_list_item_1, topSelectList);

        selectedToppingsListView.setAdapter(adapter);


        topAddList.addAll(Arrays.asList(additional));
        adapter1 = new ArrayAdapter<String>(PizzaOrderActivity.this,
                android.R.layout.simple_list_item_1, topAddList);

        unselectedToppingsListView.setAdapter(adapter1);
        unselectedToppingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * this method implements when unselected toppings listView(additional toppings) are selected
             * @param parent parent of the listView
             * @param view listView
             * @param position postion of the item that is selected
             * @param id id of the item that is selected
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = unselectedToppingsListView.getItemAtPosition(position).toString();
                final boolean[] bool1 = {false};

                addTopping.setOnClickListener(new View.OnClickListener() {
                    /**
                     * when add topping button is selected it adds the topping to the current pizza
                     * @param v listView is utilised
                     */
                    @Override
                    public void onClick(View v) {
                        if (!bool1[0]) {
                            if (adapter.getCount() == 7) {
                                Toast.makeText(PizzaOrderActivity.this, "Cannot add more than 7 toppings",
                                        Toast.LENGTH_LONG).show();
                                return;
                            } else {
                                topSelectList.add(selectedFromList);
                                adapter.notifyDataSetChanged();
                                topAddList.remove(selectedFromList);
                                adapter1.notifyDataSetChanged();

                                pizza.getToppings().add(new Topping(selectedFromList));
                                pizzaCost.setText("Subtotal:" + String.valueOf(Round.round(pizza.price(), 2)));
                                bool1[0] = true;
                            }
                        }

                    }
                });
            }
        });

        addToOrder.setOnClickListener(new View.OnClickListener() {
            /**
             * when the addToOrder button is pressed the pizza is added to the current Order
             * Here we pass back the pizza to the MainActivity and there we implement addToOrder
             * @param v listView
             */
            @Override
            public void onClick(View v) {


                Intent resultIntent = new Intent();
                resultIntent.putExtra("resPizza", pizza);
                setResult(RESULT_OK, resultIntent);
                finish();

            }
        });

        selectedToppingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * this method implements when selected toppings listView(selected toppings)
             * @param parent parent of the ListView
             * @param view listView
             * @param position position of the selected item
             * @param id id of the selected item
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = selectedToppingsListView.getItemAtPosition(position).toString();
                final boolean[] bool = {false};
                //selectedToppingsListView.setSelected(true);
                removeTopping.setOnClickListener(new View.OnClickListener() {
                    final List valid = Arrays.asList(selected);

                    /**
                     * when remove button is pressed the topping that is selcted is removed from curren pizza
                     * @param v listView
                     */
                    @Override
                    public void onClick(View v) {
                        if (valid.contains(selectedFromList)) {
                            Toast.makeText(PizzaOrderActivity.this, "Cannot remove essential topping ",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (!bool[0]) {
                            topSelectList.remove(selectedFromList);
                            adapter.notifyDataSetChanged();
                            topAddList.add(selectedFromList);
                            adapter1.notifyDataSetChanged();


                            for (int i = 0; i < pizza.toppings.size(); i++) {
                                if (pizza.getToppings().get(i).getToppingName() == selectedFromList) {
                                    pizza.getToppings().remove(i);
                                }
                            }
                            pizzaCost.setText("Subtotal:" + String.valueOf(Round.round(pizza.price(), 2)));
                            bool[0] = true;
                        }

                    }

                });

            }


        });


    }

}
