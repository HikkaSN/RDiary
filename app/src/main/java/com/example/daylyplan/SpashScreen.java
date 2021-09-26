package com.example.daylyplan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class SpashScreen extends AppCompatActivity {

    private Button in_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spash_screen_layout);

        in_btn = findViewById(R.id.in_btn);
    }
    public void nextto(View view){

            Intent intent = new Intent(this,Login.class);
            startActivity(intent);

    }
}

