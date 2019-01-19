package com.manali.huskereats;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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



    }

}
