package com.example.whack_a_mole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

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

        saveDatatoIF();
    }

    private void saveDatatoIF() {
        try {
            FileOutputStream fos = openFileOutput("HighScores.txt", MODE_APPEND);
            writeToFOS(fos);
        }
        catch (Exception e) {
            CharSequence text = "The file could not be opened\n" + e.toString();
            int dur = Toast.LENGTH_SHORT;
            Toast message = Toast.makeText(this, text, dur);
            message.show();
        }
    }

    public void writeToFOS(FileOutputStream fos) {
        try {
            // Open the file and assign it to an output stream writer
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            // Figure out which character is an endLine on the current system
            String endLine = System.getProperty("line.separator");
            // Write the players name to the title
            osw.write(playerName + endLine);
            osw.write(score + endLine);
            // Make sure all data has been sent out to the file
            osw.flush();
            // Close the file
            osw.close();
        }
        catch (Exception e) {
            CharSequence text = "Could not write to the file\n" + e.toString();
            int dur = Toast.LENGTH_SHORT;
            Toast message = Toast.makeText(this, text, dur);
            message.show();
        }
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