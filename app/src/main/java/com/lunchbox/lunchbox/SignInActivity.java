package com.lunchbox.lunchbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void signup(View view)
    {

        Intent intent=new Intent(SignInActivity.this,HomeActivity.class);
        startActivity(intent);
    }
    public void login(View view)
    {
        Intent intent=new Intent(SignInActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}
