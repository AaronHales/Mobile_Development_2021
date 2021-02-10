package com.example.guessmynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class play extends AppCompatActivity {
    int tries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        int guess = getIntent().getIntExtra("guess", 1);
        int number = getIntent().getIntExtra("number", 1);
        tries = getIntent().getIntExtra("tries", 0);
        TextView guesstext = (TextView)findViewById(R.id.player_guess);
        guesstext.setText("You guessed: " + guess);

        TextView triesleft = (TextView)findViewById(R.id.tries_left);
        triesleft.setText("You have " + tries + " remaining");

        TextView highorlowtxt = (TextView)findViewById(R.id.high_or_low);
        if (guess < number) {
            highorlowtxt.setText("Guess higher");
        }
        else if (guess > number) {
            highorlowtxt.setText("Guess lower");
        }
        else {
            if (tries > 0) {
                if (guess == number) {
                    highorlowtxt.setText("You guess correctly, great job!");
                }
                else {
                    highorlowtxt.setText("You guessed wrong, try again and maybe think harder");
                }
            }
            else {
                highorlowtxt.setText("You did not guess correctly and ran out of tries");
            }
        }
    }

    public void buttonClick (View view) {
        if (tries > 0) {
            Intent backToGuess = new Intent(this, MainActivity.class);
            startActivity(backToGuess);
        }
    }
}