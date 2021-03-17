package com.example.whack_a_mole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HighScores extends AppCompatActivity implements View.OnClickListener {
    Intent playscreen_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_scores);

        playscreen_intent = new Intent(this, Game.class);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.hsplaybttn) {
            startActivity(playscreen_intent);
            finish();
        }
        else if (v.getId() == R.id.hsquitbttn) {
            finish();
        }
    }
}