package com.example.whack_a_mole;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

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
    int difficultyLevel = 2;    // 1 = easy, 2 = medium, 3 = hard
    int numMules = 8;           // any value between 3 and 8
    int duration = 20;          // and value up to 30 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
    }

    @Override
    public void onClick(View v) {
        // Build later
    }

    // This method is called when the game is completed
    public void gameOver() {
        // Pass for now
    }

    // This method wil choose a new button as the current mole
    // ** This method is provided complete as part of the activity starter. **
    public void setNewMole() {

    }

    // This method will retrieve all mole button IDs and place them into
    // our array of integer Button IDs.
    public void initButtons() {

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
            setNewMole();   // set a new mole on the screen
        }
        else {
            gameOver();     // If the ending time is less than the current time, the game is over
        }
    }
}