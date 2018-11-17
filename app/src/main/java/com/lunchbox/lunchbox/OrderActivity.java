package com.lunchbox.lunchbox;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    FloatingActionButton addOnButton;
    TextView tv;
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        addOnButton = findViewById(R.id.add_on_button);
        tv = findViewById(R.id.textView4);

        Intent intent = getIntent();
        int redirect = intent.getIntExtra("Redirect",-1);
        order  = (Order) intent.getSerializableExtra("OrderObject");

        tv.setText(order.toString());

        addOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(OrderActivity.this,AddOnActivity.class);
                intent1.putExtra("OrderObject",order);
            }
        });

    }
}
