package com.manali.huskereats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
public class SubscriptionActivity extends AppCompatActivity {
    private Button subscribe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        subscribe = findViewById(R.id.btnSubscribe);
        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(SubscriptionActivity.this, HomeActivity.class);
                homeIntent.putExtra("subscribed", true);
                startActivity(homeIntent);
            }
        });
    }
}
