package com.lunchbox.lunchbox;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;


import java.util.concurrent.TimeUnit;

public class VerifyNumberActivity extends AppCompatActivity {

    PinView pinView;
    TextView textView;
    String phoneNumber;
    private String mVerificationId;
    FirebaseAuth firebaseAuth;
    Button resend;
    Button validate;

    private PhoneAuthProvider.ForceResendingToken resendingToken;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            signInWithPhoneAuthCredential(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyNumberActivity.this, "OTP verification failed\n"+e.getMessage(), Toast.LENGTH_SHORT).show();
            resend.setEnabled(true);
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            textView.setText(String.format("OTP sent to +91 %s", phoneNumber));
            mVerificationId = s;
            resendingToken = forceResendingToken;
        }

        @Override
        public void onCodeAutoRetrievalTimeOut(String s) {
            super.onCodeAutoRetrievalTimeOut(s);
            resend.setEnabled(true);
            Toast.makeText(VerifyNumberActivity.this, "Auto retrieval of OTP is timed out please enter OTP manually or resend the code", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_number);

        firebaseAuth = FirebaseAuth.getInstance();

        pinView = findViewById(R.id.otp_view);
        textView = findViewById(R.id.otpSentTextView);
        resend = findViewById(R.id.resend);
        validate = findViewById(R.id.validate);

        resend.setEnabled(false);
        pinView.setEnabled(true);
        pinView.requestFocus();

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resend.setEnabled(false);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+phoneNumber,
                        60,
                        TimeUnit.SECONDS,
                        VerifyNumberActivity.this,
                        mCallbacks,
                        resendingToken
                );
            }
        });



        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("phoneNumber");
        sendOTP(phoneNumber);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = pinView.getText().toString();

                if (code.isEmpty() || code.length() < 6){
                    return;
                }

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId,code);

                //signing the user
                signInWithPhoneAuthCredential(credential);
            }
        });

//        pinView.setOtpCompletionListener(new OnOtpCompletionListener() {
//            @Override
//            public void onOtpCompleted(String s) {
//                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, s);
//
//                //signing the user
//                signInWithPhoneAuthCredential(credential);
//            }
//        });


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        pinView.setEnabled(false);

        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if (firebaseAuth.getCurrentUser() != null) {
                            Intent intent = new Intent(VerifyNumberActivity.this, SignupActivity.class);//HomeActivity.class
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
//                            FirebaseUserMetadata metadata = firebaseAuth.getCurrentUser().getMetadata();
//                            if (metadata.getCreationTimestamp() == metadata.getLastSignInTimestamp()) {
//                                //Switch to Welcome Screen of a new user
//                            } else {
//                                //Switch to Welcome back screen of an existing user
//                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Sign In","Error",e);
                        Toast.makeText(VerifyNumberActivity.this, "Sign In error\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                        pinView.setEnabled(true);
                    }
                });
    }

    private void sendOTP(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phoneNumber,        // Phone number to verify
                60,                        // Timeout duration
                TimeUnit.SECONDS,           // Unit of timeout
                this,                // Activity (for callback binding)
                mCallbacks);                // OnVerificationStateChangedCallbacks
    }

}
