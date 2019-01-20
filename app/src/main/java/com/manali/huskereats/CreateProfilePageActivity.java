package com.manali.huskereats;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProfilePageActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private ImageView userAvatar;
    private EditText userName;
    private EditText userPhoneNumber;
    private Button nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        //declare and initialize variables
        userAvatar = findViewById(R.id.userAvatar);
        userName = findViewById(R.id.userName);
        userPhoneNumber = findViewById(R.id.userPhoneNumber);
        nextButton = findViewById(R.id.btnNext);

        nextButton.setOnClickListener(this);

        //get Firebase instance
        mAuth = FirebaseAuth.getInstance();

        //check if user exists
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    //save the user information
    private void saveUserInformation() {
        String name = userName.getText().toString().trim();
        String phoneNumber = userPhoneNumber.getText().toString().trim();
        String emailAddress = getIntent().getExtras().getString("email");

        UserInformation userInformation = new UserInformation(name, phoneNumber, emailAddress);

        FirebaseUser user = mAuth.getCurrentUser();

        mDatabaseReference.child("Users").child(user.getUid()).setValue(userInformation);
    }

    @Override
    public void onClick(View view) {
        if (view == nextButton) {
            saveUserInformation();
            finish();
            startActivity(new Intent(this, RoleActivity.class));
        }
    }
}
