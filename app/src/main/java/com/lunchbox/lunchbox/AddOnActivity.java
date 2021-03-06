package com.lunchbox.lunchbox;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddOnActivity extends AppCompatActivity {

    AddOnItem addOnItem;
    List<AddOnItem> addOnItems;
    Button addOnContinueButton;
    List<Meal> meals;

    FirebaseFirestore firestore;
    AddOnRecyclerAdapter addOnRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_on);

        addOnContinueButton = findViewById(R.id.proceed_to_pay_button_addon);

        Intent intent = getIntent();
        try{
            meals = (List<Meal>) intent.getSerializableExtra("meals");
        } catch (Exception e){
            e.printStackTrace();
        }

//        addOnItem = new AddOnItem(0,"Item 0",179.9,false,0);
//        addOnItems.add(addOnItem);
//        addOnItem = new AddOnItem(1,"Item 1",169.9,false,0);
//        addOnItems.add(addOnItem);
//        addOnItem = new AddOnItem(2,"Item 2",169.9,true,0);
//        addOnItems.add(addOnItem);
//        addOnItem = new AddOnItem(3,"Item 3",179.9,true,0);
//        addOnItems.add(addOnItem);
//        addOnItem = new AddOnItem(4,"Item 4",179.9,true,0);
//        addOnItems.add(addOnItem);
//        addOnItem = new AddOnItem(5,"Item 5",179.9,true,0);
//        addOnItems.add(addOnItem);
//        addOnItem = new AddOnItem(6,"Item 6",179.9,true,0);
//        addOnItems.add(addOnItem);
//        addOnItem = new AddOnItem(7,"Item 7",179.9,false,0);
//        addOnItems.add(addOnItem);
//        addOnItem = new AddOnItem(8,"Item 8",179.9,true,0);
//        addOnItems.add(addOnItem);
//        addOnItem = new AddOnItem(9,"Item 9",179.9,true,0);
//        addOnItems.add(addOnItem);
//
//        firestore = FirebaseFirestore.getInstance();
//
//        // Get a new write batch
//        WriteBatch batch = firestore.batch();
//
//        for (AddOnItem a : addOnItems){
//            batch.set(firestore.collection("addOnItems").document(String.valueOf(a.itemId)),a);
//        }
//
//        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                // ...
//                Toast.makeText(AddOnActivity.this, "UPLOAD SUCCESS " + String.valueOf(task.isSuccessful()), Toast.LENGTH_SHORT).show();
//            }
//        });

        addOnItems = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.addOnRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        addOnRecyclerAdapter = new AddOnRecyclerAdapter(addOnItems, new AddOnAdapterListener() {
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

        firestore = FirebaseFirestore.getInstance();

        firestore.collection("addOnItems")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()){
                            addOnItems.add(snapshot.toObject(AddOnItem.class));
                            addOnRecyclerAdapter.notifyDataSetChanged();
                        }
                    }


                });

    }


    public void continueSelectingDates(View view) {
        Map<Integer,AddOnItem> addOnItemMap = new HashMap<>();
        for (AddOnItem addOnItem : addOnItems){
            addOnItemMap.put(addOnItem.itemId,addOnItem);
        }

        if (meals != null){
            Intent intent = new Intent(AddOnActivity.this,DateSelectionActivity.class);
            intent.putExtra("addOnItems", (Serializable) addOnItemMap);
            intent.putExtra("meals", (Serializable) meals);
            startActivity(intent);
        } else {
            Toast.makeText(this, "WORK IN PROGRESS", Toast.LENGTH_SHORT).show();
        }

    }
}
