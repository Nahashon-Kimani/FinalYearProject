package com.agritech.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.agritech.R;
import com.agritech.adapter.ChatRecyclerView;
import com.agritech.model.ChatModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Forum extends AppCompatActivity {
    ImageView sendMessage;
    EditText typeMessage;
    RecyclerView chatRecyclerView;
    DatabaseReference mRef;
    ArrayList<ChatModel> chatModelArrayList = new ArrayList<>();
    FirebaseAuth mAuth;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        chatRecyclerView = findViewById(R.id.chat_recycler_view);
        sendMessage = findViewById(R.id.send_message);
        typeMessage = findViewById(R.id.type_message);

        mRef = FirebaseDatabase.getInstance().getReference("Forum");
        mAuth = FirebaseAuth.getInstance();


        //This method is used to send a new message
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatMessage();
            }
        });

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    chatModelArrayList.add(snapshot.getValue(ChatModel.class));
                }
                LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                chatRecyclerView.setLayoutManager(manager);
                chatRecyclerView.setAdapter(new ChatRecyclerView(getApplicationContext(), chatModelArrayList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Forum.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void chatMessage() {
        String text = typeMessage.getText().toString().trim();
        if (text.isEmpty()) {
            typeMessage.setError("Please enter a text");

        } else {
            mRef = FirebaseDatabase.getInstance().getReference().child("Forum");
            String currentDate = DateFormat.getDateTimeInstance().format(new Date());
            String message = typeMessage.getText().toString().trim();
            ChatModel chatModel = new ChatModel("Sender", message, currentDate);
            mRef.push().setValue(chatModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        //I need to play a sound
                        Toast.makeText(Forum.this, "Message sent Successfully", Toast.LENGTH_SHORT).show();
                        player = MediaPlayer.create(getApplicationContext(), R.raw.notification);
                        player.start();
                    } else {
                        Toast.makeText(Forum.this, "Service unavailable" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            typeMessage.setText("");

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(Forum.this, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.forum_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logOutUser();
                break;
            case R.id.user_profile:
                Toast.makeText(Forum.this, "Under development", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void logOutUser() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Forum.this, Login.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Forum.this, MainActivity.class));
    }
}
