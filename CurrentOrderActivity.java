package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * CurrentOrder Activity class for the Current order of the Customer
 * @author Dhrukumar Patel, Oluwadamola Olugboji
 *
 */
public class CurrentOrderActivity extends AppCompatActivity {

    private Button removePizzaButton, placeOrderButton;
    private TextView customerNumberText, subtotalText, orderTotalText, taxText;
    private ListView currentOrderListView;
    private ArrayList<String> pList;
    private ArrayAdapter<String> adapter;
    private Order currentOrder = new Order();
    private String customerNumber;

    /**
     *  On Create Method that is implemented in the whole activity to open and process other activity and passing data.
     * @param savedInstanceState input bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        removePizzaButton = (Button) findViewById(R.id.removePizzaButton);
        placeOrderButton = (Button) findViewById(R.id.placeOrderButton);
        subtotalText = (TextView) findViewById(R.id.subtotalText);
        taxText = (TextView) findViewById(R.id.taxText);
        orderTotalText = (TextView) findViewById(R.id.orderTotalText);
        customerNumberText = (TextView) findViewById(R.id.customerNumberText);

        currentOrderListView = (ListView) findViewById(R.id.currentOrderListView);
        Intent intent = getIntent();

        currentOrder = (Order) intent.getSerializableExtra("currentOrder");
        pList = intent.getStringArrayListExtra("pList");
        customerNumber = (String) intent.getSerializableExtra("customerNumber");
        customerNumberText.setText("Phone Number:" + customerNumber);
        adapter = new ArrayAdapter<String>(CurrentOrderActivity.this,
                android.R.layout.simple_list_item_1, pList);

        currentOrderListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        handleCurrentOrderTotal();
        removePizzaButton.setOnClickListener(new View.OnClickListener() {
            /**
             * when no pizza is selcted and remove pizza button is pressed to display toast message
             * @param v listview
             */
            @Override
            public void onClick(View v) {
                //Object selectedObj = list1.getSelectedItem();

                if (currentOrderListView.getSelectedItem() == null) {
                    Toast.makeText(CurrentOrderActivity.this, "No pizza selected", Toast.LENGTH_LONG).show();
                    return;
                }
                if (pList.size() < 1)
                    finish();
            }
        });
        currentOrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * when a pizza is selcted from listview
             * @param parent parent of ListView
             * @param view listView
             * @param position position of the selected Pizza item
             * @param id id of the selected pizza item
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedFromList = currentOrderListView.getItemAtPosition(position).toString();
                //int select = (int) currentOrderListView.getItemAtPosition(position);
                double total = currentOrder.getPizzaList().get(position).price();
                subtotalText.setText("Subtotal:" + String.valueOf(Round.round(total, 2)));
                taxText.setText("Tax:" + String.valueOf(Round.round(total * Cons.TAX, 2)));

                removePizzaButton.setOnClickListener(new View.OnClickListener() {
                    /**
                     * pizza is removed from the currentOrder of the customer
                     * @param v listView
                     */
                    @Override
                    public void onClick(View v) {

                        currentOrder.getPizzaList().remove(position);
                        pList.remove(selectedFromList);
                        adapter.notifyDataSetChanged();
                        handleCurrentOrderTotal();
                        subtotalText.setText("Subtotal:" + String.valueOf(0.00));
                        taxText.setText("Tax:" + String.valueOf(0.0));
                        Intent resultIntent3 = new Intent();
                        resultIntent3.putExtra("currentOrder1", currentOrder);
                        setResult(RESULT_FIRST_USER, resultIntent3);
                        finish();
                        startActivity(getIntent());

                    }

                });
            }


        });

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            /**
             * placing the whole order of the customer and send it to the store order data
             * @param v listView
             */
            @Override
            public void onClick(View v) {
                if (adapter.isEmpty()) {
                    Toast.makeText(CurrentOrderActivity.this, "No pizza to place Order", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("resOrder", currentOrder);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

    }

    /**
     * Method that handles the total textView that shows the total including the tax in the textView
     */
    void handleCurrentOrderTotal() {
        double total = 0.0;
        for (Pizza temp : currentOrder.getPizzaList()) {
            total += temp.price() + (temp.price() * Cons.TAX);
        }
        total = Round.round(total, 2);
        orderTotalText.setText("total:" + String.valueOf(total));
    }
}