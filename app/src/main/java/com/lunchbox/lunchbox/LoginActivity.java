package com.lunchbox.lunchbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText phoneNumberEditText;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        continueButton = findViewById(R.id.verifyButton);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneNumberEditText.getText().toString().trim();

                if ( phone.isEmpty() || phone.length() != 10 ){
                    phoneNumberEditText.setError("Enter a valid phone number");
                    phoneNumberEditText.requestFocus();
                    return;
                }

                Intent intent = new Intent(LoginActivity.this,VerifyNumberActivity.class);
                intent.putExtra("phoneNumber",phone);
                startActivity(intent);
            }
        });
    }
}
