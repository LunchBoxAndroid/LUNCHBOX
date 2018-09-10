package com.lunchbox.lunchbox;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

//Function of this activity : To check if a user is signed in or not

public class FlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        // For transparent notification and navigation bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        if (userLoggedIn()){
            // TODO:Change activity to main page
        } else {
            // TODO:Change activity to sign in / sign up page
            Intent intent=new Intent(this,SignInActivity.class);
            startActivity(intent);
        }
    }

    private boolean userLoggedIn() {
        // TODO:create method to check if user is logged in or not and remove the timer
        CountDownTimer countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent=new Intent(FlashActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        };

        countDownTimer.start();
        return true;
    }
}
