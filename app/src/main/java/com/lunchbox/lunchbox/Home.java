package com.lunchbox.lunchbox;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.squareup.timessquare.CalendarPickerView.SelectionMode.RANGE;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date today = new Date();
        Calendar calen=Calendar.getInstance();
        calen.add(Calendar.MONTH,1);

        calendar.init(today, calen.getTime())
                .withSelectedDate(today);
        calendar.init(today, calen.getTime())
                .inMode(RANGE);
        List<Date> selDate= calendar.getSelectedDates();

    }
}
