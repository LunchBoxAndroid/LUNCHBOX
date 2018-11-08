package com.lunchbox.lunchbox;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.squareup.timessquare.CalendarPickerView.SelectionMode.RANGE;


public class rescheduleFragment extends Fragment {
    View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_reschedule, container, false);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        CalendarPickerView calendar = (CalendarPickerView)view.findViewById(R.id.calendar_view);
        Date today = new Date();
        Calendar calen=Calendar.getInstance();
        calen.add(Calendar.MONTH,1);

        calendar.init(today, calen.getTime())
                .withSelectedDate(today);
        calendar.init(today, calen.getTime())
                .inMode(RANGE);
        List<Date> selDate= calendar.getSelectedDates();
        return view;
    }



}
