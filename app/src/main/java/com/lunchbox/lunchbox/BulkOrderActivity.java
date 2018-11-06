package com.lunchbox.lunchbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.squareup.timessquare.CalendarPickerView.SelectionMode.MULTIPLE;
import static com.squareup.timessquare.CalendarPickerView.SelectionMode.SINGLE;

public class BulkOrderActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button proceedToPay;
    TextView count;
    Button buttonAdd, buttonSub;

    FirebaseAuth firebaseAuth;
    CalendarPickerView calender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_order);

        firebaseAuth = FirebaseAuth.getInstance();

        radioGroup = findViewById(R.id.radioButtonMealType2);
        proceedToPay = findViewById(R.id.proceed_to_pay_button_bulk);
        //addOn = findViewById(R.id.add_on_button_bulk);
        buttonAdd = findViewById(R.id.chap_add);
        buttonSub = findViewById(R.id.chap_sub);
        count = findViewById(R.id.chap_count);
        calender = findViewById(R.id.calendar_view_bulk);

        proceedToPay.setEnabled(false);
        buttonAdd.setEnabled(false);
        buttonSub.setEnabled(false);

        Calendar upperLimit=Calendar.getInstance();
        Calendar lowerLimit=Calendar.getInstance();
        lowerLimit.add(Calendar.DATE,1);
        upperLimit.add(Calendar.DATE,28);

        calender.init(lowerLimit.getTime(), upperLimit.getTime())
                .inMode(SINGLE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                buttonAdd.setEnabled(true);
                buttonSub.setEnabled(true);
                if (!count.getText().toString().trim().equals("0")){
                    proceedToPay.setEnabled(true);
                }
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count.setText(String.valueOf(1 + Integer.parseInt(count.getText().toString().trim())));
                proceedToPay.setEnabled(true);
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int c = Integer.parseInt(count.getText().toString().trim());

                if (c > 0) {
                    count.setText(String.valueOf(c-1));
                }

                if (c == 0){
                    proceedToPay.setEnabled(false);
                }
            }
        });

        proceedToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Date> dates = calender.getSelectedDates();

                if (dates.size() == 0) {
                    Toast.makeText(BulkOrderActivity.this, "We need a date to deliver your order\nPlease select a date", Toast.LENGTH_LONG).show();
                    return;
                }

                int c = Integer.parseInt(count.getText().toString().trim());
                int mealType;
                if (radioGroup.getCheckedRadioButtonId() == R.id.radioBulkLunch)
                    mealType = Order.LUNCH;
                else if (radioGroup.getCheckedRadioButtonId() == R.id.radioBulkDinner)
                    mealType = Order.DINNER;
                else
                    return;
                Order order = new Order(firebaseAuth.getUid(),dates,Order.ORDER_BULK,c*100,c,mealType);
                Log.i("Order",order.toString());
                Intent intent = new Intent(BulkOrderActivity.this,OrderActivity.class);
                intent.putExtra("Redirect",Order.ORDER_BULK);
                intent.putExtra("OrderObject",order);
                startActivity(intent);
            }
        });
    }
}
