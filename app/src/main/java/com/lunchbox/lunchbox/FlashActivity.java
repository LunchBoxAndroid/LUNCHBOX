package com.lunchbox.lunchbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//Function of this activity : To check if a user is signed in or not

public class FlashActivity extends AppCompatActivity {

    public void signup(View view)
    {

        Intent intent=new Intent(FlashActivity.this,HomeActivity.class);
        startActivity(intent);
    }
    public void login(View view)
    {
        Intent intent=new Intent(FlashActivity.this,HomeActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_flash);
        if (userLoggedIn()){
            // TODO:Change activity to main page
        } else {
            // TODO:Change activity to sign in / sign up page
        }
    }

    private boolean userLoggedIn() {
        // TODO:create method to check if user is logged in or not
        return false;
    }
}
