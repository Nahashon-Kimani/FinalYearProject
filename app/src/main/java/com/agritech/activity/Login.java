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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText userEmail, userPassword;
    ProgressBar loginProgress;
    Button loginBtn;
    FirebaseAuth mAuth;
    TextView registerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_pass);
        loginBtn = findViewById(R.id.login_btn);
        loginProgress = findViewById(R.id.login_progress);
        registerLogin = findViewById(R.id.register_login);

        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();//This method allow users to login
            }
        });
        registerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!(mAuth.getCurrentUser() == null)) {
            startActivity(new Intent(Login.this, Forum.class));
        }
    }

    public void loginUser() {
        //credential validation

        loginProgress.setVisibility(View.VISIBLE);
        String email = userEmail.getText().toString().trim();
        String password = userPassword.getText().toString().trim();

        if (email.isEmpty()) {
            userEmail.setError("Email is required");
            //loginProgress.setVisibility(View.GONE);
            userEmail.requestFocus();
        } else if (password.isEmpty()) {
            userPassword.setError("Password is required");
            //loginProgress.setVisibility(View.GONE);
            userPassword.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userEmail.setError("Invalid Email Address");
            //loginProgress.setVisibility(View.GONE);
            userEmail.requestFocus();
        } else {
            Toast.makeText(Login.this, email + password, Toast.LENGTH_SHORT).show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    loginProgress.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        startActivity(new Intent(Login.this, Forum.class));
                    } else {
                        loginProgress.setVisibility(View.GONE);
                        Toast.makeText(Login.this, "Wrong Credentials" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
