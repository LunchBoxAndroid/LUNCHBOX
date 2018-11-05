package com.lunchbox.lunchbox;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioGroup;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.squareup.timessquare.CalendarPickerView.SelectionMode.MULTIPLE;
import static com.squareup.timessquare.CalendarPickerView.SelectionMode.RANGE;
import static com.squareup.timessquare.CalendarPickerView.SelectionMode.SINGLE;

public class Home extends AppCompatActivity {

    RadioGroup radioGroupMealType;
    Button dailyOrderContinueButton;
    CalendarPickerView calendar;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        firebaseAuth = FirebaseAuth.getInstance();

        calendar = findViewById(R.id.calendar_view);
        radioGroupMealType = findViewById(R.id.radioButtonMealType);
        dailyOrderContinueButton = findViewById(R.id.button6);

        dailyOrderContinueButton.setEnabled(false);

        radioGroupMealType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                dailyOrderContinueButton.setEnabled(true);
            }
        });

        Calendar upperLimit=Calendar.getInstance();
        Calendar lowerLimit=Calendar.getInstance();
        lowerLimit.add(Calendar.DATE,1);
        upperLimit.add(Calendar.DATE,28);

        calendar.init(lowerLimit.getTime(), upperLimit.getTime())
                .inMode(MULTIPLE);

        final long[] count={0};
//        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
//
//            @Override
//            public void onDateSelected(Date date) {
////                if (count[0] < 6)
////                    count[0]++;
////                else{
////                    calendar.selectDate(date);
////                }
//            }
//
//            @Override
//            public void onDateUnselected(Date date) {
////                count[0]--;
//            }
//
//
//        });
        dailyOrderContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Boolean> orderType = new HashMap<>();
                orderType.put("DailyOrder",true);
                orderType.put("BulkOrder",false);
                orderType.put("AddOnOrder",false);
                List<Date> selDate= calendar.getSelectedDates();
                Order order = new Order(UUID.randomUUID().toString(),firebaseAuth.getUid(), Timestamp.now(),selDate,orderType,selDate.size()*100);
                Log.i("OrderId",order.getOrderId());
                Log.i("UserId",order.getUid());
                Log.i("TimeStamp",order.getTimestamp().toString());
                Log.i("OrderType",order.getOrderType().toString());
                Log.i("Selected Dates",order.getDates().toString());
                Log.i("Price",String.valueOf(order.getPrice()));
            }
        });


    }
}
