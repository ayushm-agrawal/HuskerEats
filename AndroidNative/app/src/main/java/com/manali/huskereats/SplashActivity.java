package com.manali.huskereats;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashActivity extends AppCompatActivity {
    //variables
    private RelativeLayout rlayout;
    private ImageView img;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //set values to variables
        img = findViewById(R.id.splashLogo);
        rlayout = findViewById(R.id.rlay);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {

                //If user is already logged in then send them to MainActivty
                if(firebaseUser != null){
                    Intent mainIntent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(mainIntent);
                    finish();
                    //Otherwise go to Welcome Activity
                }else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }, secondsDelayed * 2250);
    }
}
