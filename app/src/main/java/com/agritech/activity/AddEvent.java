package com.agritech.activity;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agritech.R;
import com.agritech.model.EventModel;
import com.agritech.model.UserDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;

public class AddEvent extends AppCompatActivity {
    EditText eTitle, eDate, eLocation, eEntryFee;
    Button submit;
    DatabaseReference mRef;
    MediaPlayer player;
    FirebaseAuth mAuth;
    String userID;
    String senderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        eTitle = findViewById(R.id.e_title);
        eDate = findViewById(R.id.e_date);
        eLocation = findViewById(R.id.e_location);
        eEntryFee = findViewById(R.id.e_entry_fee);
        submit = findViewById(R.id.submit);

        //getting the current user
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            userID = mAuth.getCurrentUser().getUid();
            FirebaseDatabase.getInstance().getReference().child("Users").child(userID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        UserDetails details = snapshot.getValue(UserDetails.class);
                        senderName = details.getName().toUpperCase();
                        Toast.makeText(AddEvent.this, senderName, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewEvent();
            }
        });

    }

    public void setNewEvent() {
        String title = eTitle.getText().toString().trim();
        String date = eDate.getText().toString().trim();
        String location = eLocation.getText().toString().trim();
        String entryFee = eEntryFee.getText().toString().trim();

        /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String currentDate = sdf.format(new Date());*/

        //Validation of input details.
        if (title.isEmpty()) {
            eTitle.setError("Event Title should not be null");
            eTitle.requestFocus();
        }
        if (date.isEmpty()) {
            eDate.setError("Date should not be null");
            eDate.requestFocus();
        }
        if (location.isEmpty()) {
            eLocation.setError("Please input event Location");
            eLocation.requestFocus();
        }
        if (entryFee.isEmpty()) {
            eEntryFee.setError("Please input Entry Fee");
            eEntryFee.requestFocus();
        }

        if (!title.isEmpty() && !date.isEmpty() && !location.isEmpty() && !entryFee.isEmpty()) {
            //Getting the input date of the event
            String currentDate = DateFormat.getDateTimeInstance().format(new Date());

            EventModel event = new EventModel(title, date, location, entryFee, "UP-Coming", currentDate, senderName);
            FirebaseDatabase.getInstance().getReference().child("Events").push().setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(AddEvent.this, "Event added Successfully", Toast.LENGTH_SHORT).show();
                        //playSound();//This method is used to play a notification onc message sent successfully
                        player = MediaPlayer.create(getApplicationContext(), R.raw.notification);
                        player.start();
                        finish();
                    } else {
                        Toast.makeText(AddEvent.this, "An Error occurred. Please try again" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
        }
    }

    //This method is used to play a notification onc message sent successfully
    public void playSound() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
            ringtone.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
