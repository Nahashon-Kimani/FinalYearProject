package com.agritech.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agritech.R;
import com.agritech.model.EventModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddEvent extends AppCompatActivity {
EditText eTitle,eDate, eLocation, eEntryFee;
Button submit;
DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        eTitle = findViewById(R.id.e_title);
        eDate = findViewById(R.id.e_date);
        eLocation = findViewById(R.id.e_location);
        eEntryFee = findViewById(R.id.e_entry_fee);
        submit = findViewById(R.id.submit);



        mRef = FirebaseDatabase.getInstance().getReference().child("Events");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewEvent();
            }
        });

    }
    public void setNewEvent(){
        String title = eTitle.getText().toString().trim();
        String date = eDate.getText().toString().trim();
        String location =  eLocation.getText().toString().trim();
        String entryFee = eEntryFee.getText().toString().trim();

        /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String currentDate = sdf.format(new Date());*/

        String currentDate = DateFormat.getDateTimeInstance().format(new Date());

        EventModel event = new EventModel(title, date, location, entryFee, "UP-Coming", currentDate);
        mRef.push().setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(AddEvent.this, "Event added Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(AddEvent.this, "An Error occurred. Please try again"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
