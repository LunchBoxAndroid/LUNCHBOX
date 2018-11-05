package com.lunchbox.lunchbox;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

//Function of this activity : To check if a user is signed in or not

public class FlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        // For transparent notification and navigation bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (userLoggedIn()){
                    // TODO:Change activity to main page
                    Intent intent=new Intent(FlashActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // TODO:Change activity to sign in / sign up page
                    Intent intent=new Intent(FlashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },3000);
    }

    private boolean userLoggedIn() {
        return (FirebaseAuth.getInstance().getCurrentUser() != null);
    }
}
