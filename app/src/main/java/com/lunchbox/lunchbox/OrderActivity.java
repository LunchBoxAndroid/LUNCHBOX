package com.lunchbox.lunchbox;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {

    FloatingActionButton addOnButton;
    TextView tv;
    Order order;
    List<Meal> meals;
    Map<Integer,AddOnItem> addOnItemMap;
    private int mealPrice = 100;
    Button button;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        addOnButton = findViewById(R.id.add_on_button);
        tv = findViewById(R.id.textView4);
        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);

        Intent intent = getIntent();
        meals = (List<Meal>) intent.getSerializableExtra("meals");
        addOnItemMap = (Map<Integer, AddOnItem>) intent.getSerializableExtra("addOnItems");

        StringBuilder s = new StringBuilder();

        for (Meal meal : meals){
            s.append(meal.toString()).append("\n");
        }

        tv.setText(s.toString());

        addOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(OrderActivity.this,AddOnActivity.class);
                intent1.putExtra("meals", (Serializable) meals);
                startActivity(intent1);
            }
        });

        calculateTotalMoney();

    }

    private void calculateTotalMoney() {
        double total = 0;
        if (addOnItemMap != null){
            for (Meal meal : meals){
                total += meal.getMealCount() * mealPrice;
                for (int itemId : meal.getAddOn()){
                    total += addOnItemMap.get(itemId).itemPrice;
                }
            }
        } else {
            for (Meal meal : meals){
                total += meal.getMealCount() * mealPrice;
            }
        }
        button.setText(String.format("Proceed to pay %.2f", total));
        progressBar.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);

    }
}
