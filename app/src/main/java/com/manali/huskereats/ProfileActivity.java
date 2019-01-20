package com.manali.huskereats;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private TextView mName;
    private TextView mPhoneNumber;
    private TextView mRole;
    private TextView mCredits;
    private DatabaseReference ref;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private FirebaseUser user;
    private String userKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // find views
        mName = findViewById(R.id.userName);
        mPhoneNumber = findViewById(R.id.userPhoneNumber);
        mRole = findViewById(R.id.roleText);
        mCredits = findViewById(R.id.creditText);
        mAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        ref = mDatabase.getReference();
        user = mAuth.getCurrentUser();
        assert user != null;
        userKey = user.getUid();

        //go through user to populate the profile
        ref.child("Users").child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //get name
               mName.setText(dataSnapshot.child("name").getValue(String.class));
               //get phone number
               mPhoneNumber.setText(dataSnapshot.child("phoneNumber").getValue(String.class));
               //depending on role get more profile information
               if (dataSnapshot.child("deliver").getValue(boolean.class) && dataSnapshot.child("order").getValue(boolean.class)){
                   mRole.setText("Both");
                   mCredits.setText((dataSnapshot.child("credits").getValue(long.class)).toString());
                   mCredits.setVisibility(View.VISIBLE);
               }else if (dataSnapshot.child("deliver").getValue(boolean.class) && !dataSnapshot.child("order").getValue(boolean.class)){
                   mRole.setText("Deliver");
                   mCredits.setText((dataSnapshot.child("credits").getValue(long.class)).toString());
                   mCredits.setVisibility(View.VISIBLE);
               }else if (!dataSnapshot.child("deliver").getValue(boolean.class) && dataSnapshot.child("order").getValue(boolean.class)) {
                   mRole.setText("Order");
               }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });


        }

    }


