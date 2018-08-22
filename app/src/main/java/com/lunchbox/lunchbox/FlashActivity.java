package com.lunchbox.lunchbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//Function of this activity : To check if a user is signed in or not

public class FlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        if (userLoggedIn()){
            //Change activity to main page
        } else {
            //Change activity to sign in / sign up page
        }
    }

    private boolean userLoggedIn() {
        //create method to check if user is logged in or not
        return false;
    }
}
