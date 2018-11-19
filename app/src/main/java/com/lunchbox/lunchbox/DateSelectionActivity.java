package com.lunchbox.lunchbox;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DateSelectionActivity extends AppCompatActivity {

    List<Meal> meals;
    Map<Integer,AddOnItem> itemMap;
    List<Boolean> checks;
    ArrayList<Integer> items;

    RecyclerView recyclerView;
    Button button;
    List<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selection);

        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.button_select_dates_continue);

        Intent intent = getIntent();
        meals = (List<Meal>) intent.getSerializableExtra("meals");
        itemMap = (Map<Integer, AddOnItem>) intent.getSerializableExtra("addOnItems");

        items = new ArrayList<>();

        for (int itemId : itemMap.keySet()){
            int itemCount = itemMap.get(itemId).quantity;
            while(itemCount-- > 0){
                items.add(itemId);
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            meals.sort(new Comparator<Meal>() {
                @Override
                public int compare(Meal o1, Meal o2) {
                    if (o1.getDate().equals(o2.getDate())){
                        return o1.getMealType()-o2.getMealType();
                    }
                    return o1.getDate().compareTo(o2.getDate());
                }
            });
        }

        checks = new ArrayList<>();
        for (Meal ignored : meals){
            checks.add(false);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        DateSelectionAdapter adapter = new DateSelectionAdapter(meals, checks, new DateSelectionAdapterListener() {
            @Override
            public Void dateSelected(View v, int position) {
                checks.set(position,true);
                return null;
            }

            @Override
            public Void dateUnselected(View v, int position) {
                checks.set(position,false);
                return null;
            }

            @Override
            public void dateSelectionChanged(View v, int adapterPosition) {
                //Toast.makeText(DateSelectionActivity.this, String.valueOf(adapterPosition), Toast.LENGTH_SHORT).show();
                if (checks.get(adapterPosition)){
                    list = meals.get(adapterPosition).getAddOn();
                    for (int i : items){
                        list.remove(list.lastIndexOf(i));
                    }
                    meals.get(adapterPosition).setAddOn(list);
                    checks.set(adapterPosition,false);
                } else {
                    list = meals.get(adapterPosition).getAddOn();
                    list.addAll(items);
                    meals.get(adapterPosition).setAddOn(list);
                    checks.set(adapterPosition,true);
                }
            }
        });

        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DateSelectionActivity.this,OrderActivity.class);
                intent.putExtra("addOnItems", (Serializable) itemMap);
                intent.putExtra("meals", (Serializable) meals);
                startActivity(intent);
            }
        });

    }
}
