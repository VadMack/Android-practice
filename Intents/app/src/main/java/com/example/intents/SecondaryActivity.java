package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondaryActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        editText = findViewById(R.id.editTextTextPersonName);
    }

    public void onClick(View view) {
        String name = editText.getText().toString();
        Intent result = new Intent(this, MainActivity.class);
        result.putExtra("NAME", name);
        setResult(RESULT_OK, result);
        finish();
    }
}