package com.lunchbox.lunchbox;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText phoneNumberEditText;
    TextInputLayout textInputLayout;
    Button verifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        verifyButton = findViewById(R.id.verifyButton);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        textInputLayout = findViewById(R.id.textInputLayout);

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneNumberEditText.getText().toString().trim();

                if ( phone.isEmpty() || phone.length() != 10 ){
                    textInputLayout.setError("Enter a 10 digit phone number");
                    textInputLayout.requestFocus();
//                    phoneNumberEditText.setError("Enter a 10 digit phone number");
//                    phoneNumberEditText.requestFocus();
                    return;
                }

                Intent intent = new Intent(LoginActivity.this,VerifyNumberActivity.class);
                intent.putExtra("phoneNumber",phone);
                startActivity(intent);
            }
        });
    }
}
