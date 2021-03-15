package com.example.whack_a_mole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity implements View.OnClickListener{

    // Set up an array of integers to hold the Button IDs of the "moles"
    ArrayList<Integer> myButtonIDs = new ArrayList<>();
    // The Handler will be used to run a timer in our game
    protected Handler taskHandler = new Handler();
    // The isComplete variable will tell us when time is up!
    protected Boolean isComplete = false;
    Button currentMole;
    // Use the current time as the start time for the game
    Long startTime = System.currentTimeMillis();
    // Keep track of how many times the user has hit the mole
    int score = 0;
    // Settings
    // The following variables are used to configure the game
    // Establish default game configuration settings here!
    String playerName = "Default";
    int difficultyLevel = 2;    // 1 = hard, 2 = medium, 3 = easy
    int numMules = 8;           // any value between 3 and 8
    int duration = 20;          // and value up to 30 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        initButtons(); // Initialize all 8 buttons
        setNewMole(); // Set one mole as the current mole
        setTimer(difficultyLevel * 1000);
    }

    @Override
    public void onClick(View v) {
        if (isComplete) {
            return;
        }
        if (v == currentMole) {
            score++;
            TextView tvscore = (TextView) findViewById(R.id.score);
            tvscore.setText("Score: " + score);
            setNewMole();
        }
    }

    // This method is called when the game is completed
    public void gameOver() {
        // Set the isComplete variable to true to stop the timer
        isComplete = true;
        TextView tvscore = (TextView) findViewById(R.id.score);
        tvscore.setText("Game Over!\nScore: " + score);

        // Create a new Intent for the GameOver screen and pass the number of
        // hits for the player and the player's name
        Intent gameoverintent = new Intent(this, GameOver.class);
        gameoverintent.putExtra("score", score);
        gameoverintent.putExtra("name", playerName);
        // Start the new activity with our intent
        startActivity(gameoverintent);
        finish();
    }

    // This method wil choose a new button as the current mole
    // ** This method is provided complete as part of the activity starter. **
    public void setNewMole() {
        Random generator = new Random(); // Create a random number generator

        int randomItem = generator.nextInt(numMules);

        int newButtonId = myButtonIDs.get(randomItem);
        if (currentMole != null) {
            currentMole.setVisibility(View.INVISIBLE);
        }
        Button newMole = (Button) findViewById(newButtonId);
        newMole.setVisibility(View.VISIBLE);
        currentMole = newMole;
    }

    // This method will retrieve all mole button IDs and place them into
    // our array of integer Button IDs.
    public void initButtons() {
        ViewGroup group = (ViewGroup) findViewById(R.id.GameLayout);
        View v;
        // Now we can loop through all the controls and find just the buttons
        for (int i = 0; i < group.getChildCount(); i++) {
            v = group.getChildAt(i);
            if (v instanceof Button) {
                v.setOnClickListener(this); // Set the onClickListener for the button
                // If the game is not over
                if (!isComplete) {
                    myButtonIDs.add(v.getId()); // Add the Button ID to the array
                    v.setVisibility(View.INVISIBLE); // Set the Button to invisible
                }
            }
        }
    }

    // This method will create the timer that will allow us to switch current moles
    protected void setTimer(long time) {
        // Get the time that we want our timer to last for the input parameter
        final long elapse = time;
        // Create a new "runnable" task - this will create a timer feature
        Runnable t = new Runnable() {
            @Override
            public void run() {
                onTimerTask();  // Change the current mole on the screen
                // If the game is not complete
                if(!isComplete) {
                    // Create a new timer task to go off when the next mole should be shown
                    taskHandler.postDelayed(this, elapse);
                }
            }
        };
        // Create the new timer task to go off when the next mole should be shown
        taskHandler.postDelayed(t, elapse);
    }

    // This method will change the current mole whenever the timer goes off
    protected void onTimerTask() {
        // Calculate our ending time based on the duration setting
        long endtime = startTime + (duration * 1000);

        // If the ending time is greater than the current time, keep the game going
        if (endtime > System.currentTimeMillis()) {
            setNewMole();   // Set a new mole on the screen
        }
        else {
            gameOver();     // If the ending time is less than the current time, the game is over
        }
    }
}