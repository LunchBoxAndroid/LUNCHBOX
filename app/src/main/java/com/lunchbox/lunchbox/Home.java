package com.lunchbox.lunchbox;

import android.content.Intent;
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

import java.io.Serializable;
import java.util.ArrayList;
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

        dailyOrderContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Date> selDate= calendar.getSelectedDates();

                int mealType;
                if (radioGroupMealType.getCheckedRadioButtonId() == R.id.radioDailyLunch)
                    mealType = Meal.LUNCH;
                else if (radioGroupMealType.getCheckedRadioButtonId() == R.id.radioDailyDinner)
                    mealType = Meal.DINNER;
                else
                    return;

                List<Meal> meals = new ArrayList<>();

                for (Date date : selDate){
                    meals.add(new Meal(date,mealType,new ArrayList<Integer>(),1));
                }

                Intent intent = new Intent(Home.this,OrderActivity.class);
                intent.putExtra("meals", (Serializable) meals);
                startActivity(intent);
            }
        });


    }
}
