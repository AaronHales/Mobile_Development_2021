package com.example.whack_a_mole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
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

        // Simpler way, but not the best for stability
        boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

        if (externalMemoryAvailable(this)) {
            saveDatatoSD();
        }
        else{
            saveDatatoIF();
        }


    }

    // Checks to see if a SD card is mounted and does not check for free space
    // A little more complex than the boolean isSDPresent but does not check for free space
    public static boolean hasRealRemovableSdCard(Context context) {
        return ContextCompat.getExternalFilesDirs(context, null).length >= 2;
    }

    // Checks to see if SD is mounted and has free space
    // Complex because it checks for if the storage is connected, longer than 2, and directories are not null
    public static boolean externalMemoryAvailable(Activity context) {
        File[] storages = ContextCompat.getExternalFilesDirs(context, null);
        if (storages.length > 1 && storages[0] != null && storages[1] != null)
            return true;
        else
            return false;

    }

    private void saveDatatoSD() {
        try {
            // Open the SD Card Directory on the device
            File privateLocation = getExternalFilesDir(null);
            // Create or Open the HighScores.txt file form the SD card
            File myfile = new File(privateLocation, "HighScores.txt");

            // Build a FileOutputStream and write some text
            // Note: we use the "true" flag to tell the compiler to append our data to the existing file
            FileOutputStream fos = new FileOutputStream(myfile, true);
            writeToFOS(fos);

        }
        catch (Exception e) {
            CharSequence text = "The file could not be opened\n" + e.toString();
            int dur = Toast.LENGTH_LONG;
            Toast message = Toast.makeText(this, text, dur);
            message.show();
        }
    }

    private void saveDatatoIF() {
        try {
            FileOutputStream fos = openFileOutput("HighScores.txt", MODE_APPEND);
            writeToFOS(fos);
        }
        catch (Exception e) {
            CharSequence text = "The file could not be opened\n" + e.toString();
            int dur = Toast.LENGTH_LONG;
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
            int dur = Toast.LENGTH_LONG;
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