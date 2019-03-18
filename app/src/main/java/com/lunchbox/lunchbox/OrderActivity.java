package com.lunchbox.lunchbox;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;

    FloatingActionButton addOnButton;
    TextView tv;
    Order order;
    List<Meal> meals;
    Map<Integer,AddOnItem> addOnItemMap;
    private long mealPrice = 100;
    Button button;
    ProgressBar progressBar;

    String orderId;
    String UID;

    double total = 0;

    private DatabaseReference databaseReference;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        addOnButton = findViewById(R.id.add_on_button);
        //tv = findViewById(R.id.tv);
        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);

        UID = FirebaseAuth.getInstance().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        firestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        meals = (List<Meal>) intent.getSerializableExtra("meals");
        addOnItemMap = (Map<Integer, AddOnItem>) intent.getSerializableExtra("addOnItems");

        StringBuilder s = new StringBuilder();

        for (Meal meal : meals){
            s.append(meal.toString()).append("\n");
        }

        //tv.setText(s.toString());
        recyclerView=findViewById(R.id.recyclerView);
        layoutManager= new LinearLayoutManager(this);
        recyclerAdapter= new OrderRecyclerAdapter(meals);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setHasFixedSize(true);

        addOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(OrderActivity.this,AddOnActivity.class);
                intent1.putExtra("meals", (Serializable) meals);
                startActivity(intent1);
            }
        });

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("mealPrice")){
                    mealPrice = (long) dataSnapshot.child("mealPrice").getValue();
                    calculateTotalMoney();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void calculateTotalMoney() {
        if (addOnItemMap != null){
            for (Meal meal : meals){
                total += meal.getMealCount() * mealPrice;
                for (int itemId : meal.getAddOn()){
                    total += addOnItemMap.get(itemId).itemPrice;
                }
            }
        } else {
            for (Meal meal : meals){
                total += meal.getMealCount() * mealPrice;
            }
        }
        button.setText(String.format(Locale.US,"Proceed to pay %.2f", total));
        progressBar.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);

    }

    public void proceedToPay(View ignore){
        databaseReference.child("orderCount").runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                Log.i("Debug",mutableData.toString());
                Object o = mutableData.getValue();
                if ( o == null){
                    return Transaction.success(mutableData);
                }
                long orderCount = (long) o;

                // Set value and report transaction success
                mutableData.setValue(orderCount + 1);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                // Transaction completed
                Log.d("DATABASE", "postTransaction:onComplete:" + databaseError);
                orderId = "OID" + dataSnapshot.getValue().toString();

                populateClasses();
            }
        });
    }

    private void populateClasses() {
        for (Meal meal : meals ){
            meal.setOrderId(orderId);
            meal.setUid(UID);
        }

        order = new Order(orderId,UID,Timestamp.now(),total,null,meals);

        Log.d("Order",order.toString());

//        firestore.collection("Users")
//                .document(UID)
//                .collection("Orders")
//                .document(orderId)
//                .set(order)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            Toast.makeText(OrderActivity.this, "Order has been taken...!!", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            Toast.makeText(OrderActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });

        WriteBatch batch = firestore.batch();

        batch.set(firestore.collection("Users").document(UID).collection("Orders").document(orderId),order);

        int i = 1;

        for (Meal meal : meals){
            batch.set(firestore.collection("Users").document(UID).collection("Meals").document(orderId + "MID" + String.valueOf(i)),meal);
            batch.set(firestore.collection("Meals").document(orderId + "MID" + String.valueOf(i)),meal);
            i++;
        }

        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(OrderActivity.this, "Order has been taken...!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(OrderActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
