package com.lunchbox.lunchbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BulkOrderActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button proceedToPay;
    Button addOn;
    TextView count;
    Button buttonAdd, buttonSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_order);

        radioGroup = findViewById(R.id.radioButtonMealType2);
        proceedToPay = findViewById(R.id.proceed_to_pay_button_bulk);
        addOn = findViewById(R.id.add_on_button_bulk);
        buttonAdd = findViewById(R.id.chap_add);
        buttonSub = findViewById(R.id.chap_sub);
        count = findViewById(R.id.chap_count);

        proceedToPay.setEnabled(false);
        addOn.setEnabled(false);
        buttonAdd.setEnabled(false);
        buttonSub.setEnabled(false);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                buttonAdd.setEnabled(true);
                buttonSub.setEnabled(true);
                if (!count.getText().toString().trim().equals("0")){
                    proceedToPay.setEnabled(true);
                    proceedToPay.setEnabled(true);
                }
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count.setText(String.valueOf(1 + Integer.parseInt(count.getText().toString().trim())));
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = Integer.parseInt(count.getText().toString().trim());

                if (c > 0) {
                    count.setText(String.valueOf(c-1));
                }
            }
        });

        addOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
