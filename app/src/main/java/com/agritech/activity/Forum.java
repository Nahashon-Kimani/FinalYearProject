package com.agritech.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.agritech.R;
import com.agritech.model.ChatModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class Forum extends AppCompatActivity {
    ImageView sendMessage;
    EditText typeMessage;
    RecyclerView chatRecyclerView;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        sendMessage = findViewById(R.id.send_message);
        typeMessage = findViewById(R.id.type_message);


        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatMessage();
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
            mRef.push().setValue(chatModel);
            typeMessage.setText("");
            //Toast.makeText(Forum.this, message, Toast.LENGTH_SHORT).show();

        }
    }
}
