package com.example.whack_a_mole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class HighScores extends AppCompatActivity implements View.OnClickListener {
    Intent playscreen_intent;
    Intent optionscreen_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_scores);

        playscreen_intent = new Intent(this, Game.class);
        optionscreen_intent = new Intent(this, Options.class);

        boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

        // Load in all high scores
        if (isSDPresent) {
            loadHsSD();
        }
        else {
            loadHsIF();
        }


    }

    // Checks to see if a SD card is mounted and does not check for free space
    public static boolean hasRealRemovableSdCard(Context context) {
        return ContextCompat.getExternalFilesDirs(context, null).length >= 2;
    }

    // Checks to see if SD is mounted and has free space
    public static boolean externalMemoryAvailable(Activity context) {
        File[] storages = ContextCompat.getExternalFilesDirs(context, null);
        if (storages.length > 1 && storages[0] != null && storages[1] != null)
            return true;
        else
            return false;

    }

    private void loadHsSD() {
        try {
            // Open the SD Card Directory on the device
            File privateLocation = getExternalFilesDir(null);
            // Create or Open the HighScores.txt file form the SD card
            File myfile = new File(privateLocation, "HighScores.txt");
            // Create an InputStream that will allow you to read from the file
            FileInputStream fis = openFileInput("HighScores.txt");
            // Read scores from the FileInputStream
            readScoresFIS(fis);
        }
        catch (Exception e) {
            CharSequence text = "The file could not be opened from SD card\n" + e.toString();
            int dur = Toast.LENGTH_LONG;
            Toast message = Toast.makeText(this, text, dur);
            message.show();
        }
    }

    private void loadHsIF() {
        try {
            FileInputStream fis = openFileInput("HighScores.txt");
            readScoresFIS(fis);
        }
        catch (Exception e) {
            CharSequence text = "The file could not be opened\n" + e.toString();
            int dur = Toast.LENGTH_LONG;
            Toast message = Toast.makeText(this, text, dur);
            message.show();
        }
    }

    private void readScoresFIS(FileInputStream fis) {
        // create InputSreamReader
        InputStreamReader isr = new InputStreamReader(fis);

        // Get a handle to the two text fields on the screen
        TextView tvname = (TextView) findViewById(R.id.tvPlayerName);
        TextView tvscore = (TextView) findViewById(R.id.tvScore);

        // Figure out which character is an endline on the current device
        String endLine = System.getProperty("line.separator");

        LinkedList<String> playerNames = new LinkedList<>();
        LinkedList<Integer> playerScores = new LinkedList<>();

        try {
            // Use a BufferedReader to allow for easy reading of th file data
            BufferedReader buffreader = new BufferedReader(isr);
            // Read in the data, line-by-line
            String name = buffreader.readLine();

            // While we still have data
            while (name != null) {
                // Read in the next line (whih will contain the player's score)
                String strScore = buffreader.readLine();
                int score = Integer.parseInt(strScore); // convert to int
                // Place the name and score into the linked list in sorted order!
                ListIterator<Integer> scoreIter = playerScores.listIterator();
                ListIterator<String> playerIter = playerNames.listIterator();

                while (scoreIter.hasNext()) {
                    // get the next integer and also iterate to the next name
                    Integer thisScore = scoreIter.next();
                    playerIter.next();

                    // if new score is larger than this one
                    if (score >= thisScore) {
                        break;  // stop looking we know where to insert!
                    }
                }

                // if there are any scores at all, wee need to rewind both iterators by on to insert in the correct spot
                if (playerScores.size() > 0) {

                    scoreIter.previous();
                    playerIter.previous();
                }

                // add this score and name into the linked lists in sorted order
                scoreIter.add(new Integer(score));
                playerIter.add(name);

                // Read in the next line in the file
                name = buffreader.readLine();
            }
            buffreader.close();

        }
        catch (Exception e) {
            CharSequence text = "There was an issue with reading the file\n" + e.toString();
            int dur = Toast.LENGTH_LONG;
            Toast message = Toast.makeText(this, text, dur);
            message.show();
        }

        // Now, iterate again over the sorted list
        ListIterator<Integer> scoreIter = playerScores.listIterator();
        ListIterator<String> playerIter = playerNames.listIterator();

        // These strings will contain the sorted scores and corresponding names
        String sortedNames = "";
        String sortedScores= "";

        int numPreset = 0;  // Count how many scores we're adding
        while (scoreIter.hasNext()) {
            // Get the score and corresponding name
            Integer score = scoreIter.next();
            String name = playerIter.next();

            sortedScores += score.toString() + endLine;
            sortedNames += name + endLine;

            numPreset++;
            if (numPreset >= 10) {
                break;
            }
        }
        // Put the sorted names in the name TextView
        tvname.setText(sortedNames);

        // Put the sorted scores in the score TextView
        tvscore.setText(sortedScores);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.hsplaybttn) {
            startActivity(playscreen_intent);
            finish();
        }
        else if (v.getId() == R.id.hsquitbttn) {
            startActivity(optionscreen_intent);
            finish();
        }
    }
}