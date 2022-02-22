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
/**
 * StoreOrderActivity for the Store Orders of the customers
 * @author Dhrukumar Patel, Oluwadamola Olugboji
 *
 */
public class StoreOrderActivity extends AppCompatActivity {
    private Button returnButton, removeOrderButton;
    private ListView storeOrderListView;
    private TextView subtotalText;
    private Spinner customerNumber;
    private ArrayAdapter<String> adapter, spinnerAdapter;

    /**
     * On Create Method that is implemented in the whole activity to open and process other activity and passing data.
     * @param savedInstanceState input bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);
        returnButton = (Button) findViewById(R.id.returnButton);
        removeOrderButton = (Button) findViewById(R.id.removeOrderButton);
        customerNumber = (Spinner) findViewById(R.id.customerNumber);
        storeOrderListView = (ListView) findViewById(R.id.storeOrderListView);
        subtotalText = (TextView) findViewById(R.id.subtotalText);

        StoreOrders storeOrders = (StoreOrders) getIntent().getSerializableExtra("storeOrders");
        ArrayList<String> customerNumberList = new ArrayList<String>();
        for (Order temp : storeOrders.getOrderList()) {
            customerNumberList.add(temp.getPhoneNumber());
        }

        spinnerAdapter = new ArrayAdapter<String>(StoreOrderActivity.this, android.R.layout.simple_list_item_1, customerNumberList);
        customerNumber.setAdapter(spinnerAdapter);
        spinnerAdapter.notifyDataSetChanged();

        customerNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * customer Number is selcted from the spinnerView to see the whole order of that customer
             * @param parent parent for SpinnerView
             * @param view SpinnerView
             * @param position position of the customer number selected from the spinnerView
             * @param id id of the customer number selected from the spinnerView
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                double total = storeOrders.getOrderList().get(position).getOrderTotal() + storeOrders.getOrderList().get(position).getOrderTax();
                subtotalText.setText("Total:" + String.valueOf(Round.round(total, 2)));
                ArrayList<Pizza> orderList = storeOrders.getOrderList().get(position).getPizzaList();
                ArrayList<String> pizzaList = new ArrayList<String>();
                for (Pizza temp : orderList) {
                    pizzaList.add(temp.toString());
                }
                adapter = new ArrayAdapter<String>(StoreOrderActivity.this, android.R.layout.simple_list_item_1, pizzaList);
                storeOrderListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                removeOrderButton.setOnClickListener(new View.OnClickListener() {
                    /**
                     * removing the order from the store orders
                     * @param v SpinnerView
                     */
                    @Override
                    public void onClick(View v) {

                        storeOrders.getOrderList().remove(position);
                        customerNumberList.remove(position);
                        pizzaList.remove(adapter.getItem(position));
                        adapter.notifyDataSetChanged();
                        spinnerAdapter.notifyDataSetChanged();
                        subtotalText.setText("Total:" + String.valueOf(Round.round(total, 2)));

                        Intent resultIntent3 = new Intent();
                        resultIntent3.putExtra("resStoreOrders1", storeOrders);
                        setResult(RESULT_FIRST_USER, resultIntent3);

                        if (customerNumberList.size() < 1) {
                            adapter.clear();
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("resStoreOrders", storeOrders);
                            setResult(RESULT_OK, resultIntent);
                            finish();
                        }
                        finish();
                        startActivity(getIntent());
                    }
                });
            }

            /**
             * when nothing is selected in customerNumber spinner view
             * @param parent spinnerView
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                subtotalText.setText("Total:" + String.valueOf(0.00));
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            /**
             * returning to the mainActivity
             * @param v view changing.
             */
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("resStoreOrders", storeOrders);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });


    }

}
