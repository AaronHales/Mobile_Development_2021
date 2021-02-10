package com.example.mutlipleactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ReceiveMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);
    }

    public void onClickGoBack(View view) {
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
        Intent intent3 = new Intent(this, TestActivity.class);
        startActivity(intent3);
    }

    public void onClickGoForward(View view) {
        Intent intent3 = new Intent(this, TestActivity.class);
        startActivity(intent3);
    }
}