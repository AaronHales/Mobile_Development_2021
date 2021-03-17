package com.example.whack_a_mole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOver extends AppCompatActivity implements View.OnClickListener {
    String playerName;
    int score;
    Intent gamescreen_intent;
    Intent hsscreen_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        TextView message = (TextView) findViewById(R.id.tvmessage);
        TextView scoresmes = (TextView) findViewById(R.id.tvGameOver);
        score = getIntent().getExtras().getInt("score");
        playerName = getIntent().getExtras().getString("name");
        message.setText("You hit " + score + " times!");
        scoresmes.setText("Game Over, " + playerName);

        gamescreen_intent = new Intent(this, Game.class);
        hsscreen_intent = new Intent(this, HighScores.class);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.buttonPlay) {
            startActivity(gamescreen_intent);
            finish();
        }
        else if (v.getId() == R.id.buttonScores) {
            startActivity(hsscreen_intent);
            finish();
        }

    }
}