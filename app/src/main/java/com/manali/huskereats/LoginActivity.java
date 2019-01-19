package com.manali.huskereats;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //declare and initialize variables
        final TextView goToSignUp = findViewById(R.id.gotoSignUp);
        final TextInputLayout email = findViewById(R.id.emailLogin);
        final TextInputLayout password = findViewById(R.id.passwordLogin);
        final Button login = findViewById(R.id.btnLogin);

        //login user with email and password
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get email and password from input
                final String emailText = Objects.requireNonNull(email.getEditText()).getText().toString().trim();
                final String passwordText = Objects.requireNonNull(Objects.requireNonNull(password.getEditText()).getText().toString().trim());

                //check for validity
                if (emailText.isEmpty()) {
                    email.getEditText().setError("Email is required");
                    email.getEditText().requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                    email.getEditText().setError("Please enter a valid email");
                    email.getEditText().requestFocus();
                    return;
                }

                if (passwordText.isEmpty()) {
                    password.getEditText().setError("Password is required");
                    password.getEditText().requestFocus();
                    return;
                }

                //Authenticate Email and Password
                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    //If successful go to main activity
                                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(mainIntent);
                                    finish();
                                }
                                else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("ActivityWelcome", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go to register to sign up!
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}
