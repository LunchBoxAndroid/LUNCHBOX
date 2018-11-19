package com.lunchbox.lunchbox;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupActivity extends AppCompatActivity {

    Button addressButton;
    TextInputEditText fullName,
                      email,
                      phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullName=findViewById(R.id.fullName);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);

        findViewById(R.id.addressButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code for saving personal details
                startActivity(new Intent(SignupActivity.this,AddressMapActivity.class));
            }
        });
    }
}
