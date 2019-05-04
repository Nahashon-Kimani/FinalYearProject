package com.agritech.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.agritech.R;
import com.agritech.model.UserDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class Register extends AppCompatActivity {
    EditText emailEdt, fullNameEdt, locationEdt, passwordEdt;
    Button registerBtn;
    FirebaseAuth mAuth;
    ProgressBar registerProgress;
    TextView registerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEdt = findViewById(R.id.email_address);
        fullNameEdt = findViewById(R.id.user_full_name);
        locationEdt = findViewById(R.id.user_location);
        passwordEdt = findViewById(R.id.password);
        registerBtn = findViewById(R.id.register_btn);
        registerProgress = findViewById(R.id.regiter_progress);
        registerLogin = findViewById(R.id.login_register);

        registerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    public void registerUser() {
        registerProgress.setVisibility(View.VISIBLE);
        final String email = emailEdt.getText().toString().trim();
        final String fullName = fullNameEdt.getText().toString().trim();
        final String location = locationEdt.getText().toString().trim();
        String password = passwordEdt.getText().toString().trim();


        if (email.isEmpty()) {
            emailEdt.setError("Email is required");
            emailEdt.requestFocus();
        } else if (fullName.isEmpty()) {
            fullNameEdt.setError("Email is required");
            fullNameEdt.requestFocus();
        } else if (location.isEmpty()) {
            locationEdt.setError("Email is required");
            locationEdt.requestFocus();
        } else if (password.isEmpty()) {
            passwordEdt.setError("Email is required");
            passwordEdt.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEdt.setError("Invalid Email Address");
            emailEdt.requestFocus();
        } else if (password.length() <= 4) {
            passwordEdt.setError("Password too short");
            passwordEdt.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        registerProgress.setVisibility(View.GONE);
                        //Getting the current registration time of user
                        String uId = mAuth.getUid();
                        Toast.makeText(Register.this, uId, Toast.LENGTH_SHORT).show();
                        String currentDate = DateFormat.getDateTimeInstance().format(new Date());
                        UserDetails details = new UserDetails(fullName, location, email, currentDate);
                        FirebaseDatabase.getInstance().getReference("Users").child(uId).push().setValue(details).
                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Register.this, "Successfully registered. Please login", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(Register.this, Login.class));
                                        } else {
                                            Toast.makeText(Register.this, "An error occurred" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            });
        }


    }
}
