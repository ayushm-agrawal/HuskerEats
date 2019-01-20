package com.manali.huskereats;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class RoleActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private boolean subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        // get checkbox view
        final CheckBox deliverCheckBox = findViewById(R.id.deliverCheck);
        final CheckBox orderCheckBox = findViewById(R.id.orderCheck);
        final Button nextButton = findViewById(R.id.btnNext);

        // get Firebase instance
        mAuth = FirebaseAuth.getInstance();
        // Database Instance
        mDatabase = FirebaseDatabase.getInstance();
        // Database reference to users
        mRef = mDatabase.getReference().child("Users");

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user id
                String userId = mAuth.getCurrentUser().getUid();
                // Get current user database reference
                DatabaseReference currentUserDb = mRef.child(userId);

                // If a user wants to order and deliver
                if (deliverCheckBox.isChecked() && orderCheckBox.isChecked()){
                    currentUserDb.child("deliver").setValue(true);
                    currentUserDb.child("order").setValue(true);
                    currentUserDb.child("credits").setValue(0);
                    subscription = true;
                }
                // If the user wants to just deliver
                else if(deliverCheckBox.isChecked() && !orderCheckBox.isChecked()) {
                    currentUserDb.child("deliver").setValue(true);
                    currentUserDb.child("order").setValue(false);
                    currentUserDb.child("credits").setValue(0);
                    subscription = false;
                }
                // If the user just wants to order
                else if(!deliverCheckBox.isChecked() && orderCheckBox.isChecked()){
                    currentUserDb.child("deliver").setValue(false);
                    currentUserDb.child("order").setValue(true);
                    subscription = true;
                }
                // check if the user has checked order
                if (subscription){
                    Intent subscriptionIntent = new Intent(RoleActivity.this, SubscriptionActivity.class);
                    startActivity(subscriptionIntent);
                }
                //otherwise
                else{
                    subscription = true;
                    Intent homeIntent = new Intent(RoleActivity.this, HomeActivity.class);
                    homeIntent.putExtra("popup", subscription);
                    startActivity(homeIntent);
                }

            }
        });



    }

}
