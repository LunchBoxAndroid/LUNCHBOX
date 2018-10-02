package com.lunchbox.lunchbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class AddOnActivity extends AppCompatActivity {

    AddOnItem addOnItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_on);

        final List<AddOnItem> addOnItems = new ArrayList<>();

        addOnItem = new AddOnItem("Chicken Butter Masala",179.9,false,0);
        addOnItems.add(addOnItem);
        addOnItem = new AddOnItem("Chicken Hydrabadi",169.9,false,0);
        addOnItems.add(addOnItem);
        addOnItem = new AddOnItem("Paneer Hydrabadi",169.9,true,0);
        addOnItems.add(addOnItem);
        addOnItem = new AddOnItem("Paneer Butter Masala",179.9,true,0);
        addOnItems.add(addOnItem);
        addOnItems.add(addOnItem);
        addOnItems.add(addOnItem);
        addOnItems.add(addOnItem);
        addOnItem = new AddOnItem("Chicken Butter Masala",179.9,false,0);
        addOnItems.add(addOnItem);
        addOnItems.add(addOnItem);
        addOnItems.add(addOnItem);


        RecyclerView recyclerView = findViewById(R.id.addOnRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        AddOnRecyclerAdapter addOnRecyclerAdapter = new AddOnRecyclerAdapter(addOnItems, new AddOnAdapterListener() {
            @Override
            public void addButtonOnClick(View v, int position) {
                addOnItems.get(position).quantity++;
                Log.d("Quantity of "+position,String.valueOf(addOnItems.get(position).quantity));
            }

            @Override
            public void addButtonSmallOnClick(View v, int position) {
                addOnItems.get(position).quantity++;
                Log.d("Quantity of "+position,String.valueOf(addOnItems.get(position).quantity));
            }

            @Override
            public void subButtonOnClick(View v, int position) {
                addOnItems.get(position).quantity--;
                Log.d("Quantity of "+position,String.valueOf(addOnItems.get(position).quantity));
            }
        });

        recyclerView.setAdapter(addOnRecyclerAdapter);

    }




}
