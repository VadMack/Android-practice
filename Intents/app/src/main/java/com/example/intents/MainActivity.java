package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_CODE = 1;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.hiText);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, SecondaryActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String name = data.getStringExtra("NAME");
            textView.setText("Hi, " + name + "!");
        } else {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
        }

    }


}