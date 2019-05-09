package com.agritech.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.agritech.R;
import com.google.firebase.auth.FirebaseAuth;

public class RegisteredPeople extends AppCompatActivity {
    String userId;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_people);

        mAuth = FirebaseAuth.getInstance();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();



    }
}
