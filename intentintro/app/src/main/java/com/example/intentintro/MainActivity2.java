package com.example.intentintro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age", 0);
        age += 10;
        TextView agelbl = (TextView)findViewById(R.id.agelblpg2);
        agelbl.setText(name + " are " + age + " years old");
        TextView lbl = (TextView)findViewById(R.id.welcomelblpg2);
        lbl.setText("Welcome " + name);
    }

    public void onBttn2Click(View view) {
        Intent goToPage3 = new Intent(this, MainActivity3.class);
        startActivity(goToPage3);
    }
}