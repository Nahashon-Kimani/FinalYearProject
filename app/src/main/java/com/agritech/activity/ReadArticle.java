package com.agritech.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.agritech.R;

public class ReadArticle extends AppCompatActivity {
    TextView readTitle, readDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_article);

        readTitle = findViewById(R.id.read_title);
        readDesc = findViewById(R.id.read_desc);

        Intent intent = new Intent();
        String desc = intent.getStringExtra("desc");
        String title = intent.getStringExtra("title");
        Toast.makeText(ReadArticle.this, desc + "\n" + title, Toast.LENGTH_SHORT).show();

        readDesc.setText(intent.getStringExtra("desc"));
        readDesc.setText(intent.getStringExtra("title"));

    }
}
