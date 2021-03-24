package com.example.whack_a_mole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;

public class Options extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        setupSpinner();
        loadSettings();
    }

    private void setupSpinner() {
        // Get a handle to the spinner view control
        Spinner sp = (Spinner) findViewById(R.id.molessp);
        // Create an array of integers to use in the array
        String[] numMoles = {"3", "4", "5", "6", "7", "8"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numMoles);
        sp.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        // if the "Play" button was clicked
        if (v.getId() != R.id.playbttn) {
            return; // play button not clicked, nothing to do!
        }
        Intent playintent = new Intent(this, Game.class);
        String name;
        int numMoles;
        int difficulty;
        int duration;

        EditText etName = (EditText) findViewById(R.id.etName);
        RadioButton easy = (RadioButton) findViewById(R.id.easyrb);
        RadioButton medium = (RadioButton) findViewById(R.id.mediumrb);
        SeekBar sb = (SeekBar) findViewById(R.id.durationsb);
        Spinner sp = (Spinner) findViewById(R.id.molessp);

        name = etName.getText().toString();
        duration = sb.getProgress();
        numMoles = sp.getSelectedItemPosition() + 3;
        if (easy.isChecked()) {
            difficulty = 3;
        }
        else if (medium.isChecked()) {
            difficulty = 2;
        }
        else {
            difficulty = 1;
        }

//        saveSettingsInIntent(difficulty, name, numMoles, duration, playintent);
        saveSettingsInPrefs(difficulty, name, numMoles, duration);

        startActivity(playintent);

    }

    private void saveSettingsInPrefs(int difficulty, String name, int numMoles, int duration) {
        // Get a reference to the shared preferences for our application
        SharedPreferences prefs = getSharedPreferences("WhackSettings", MODE_PRIVATE);
        // Get an editor object that we can use to write our option settings
        SharedPreferences.Editor editor = prefs.edit();

        // Save all options information to the Shared Preferences area
        editor.putString("name", name);
        editor.putInt("difficulty", difficulty);
        editor.putInt("numMoles", numMoles);
        editor.putInt("duration", duration);

        editor.commit();
    }

    private void loadSettings() {
        // Get a reference to the shared preferences for our application
        SharedPreferences prefs = getSharedPreferences("WhackSettings", MODE_PRIVATE);
        // Get the player name, nuumber of moles, duration, and difficulty values
        String playerName = prefs.getString("name", "Default");
        int difficultyLevel = prefs.getInt("difficulty", 1);
        int numMoles = prefs.getInt("numMoles", 8);
        int duration = prefs.getInt("duration", 20);

        EditText etName = (EditText) findViewById(R.id.etName);
        RadioButton easy = (RadioButton) findViewById(R.id.easyrb);
        RadioButton medium = (RadioButton) findViewById(R.id.mediumrb);
        RadioButton hard = (RadioButton) findViewById(R.id.hardrb);
        SeekBar sb = (SeekBar) findViewById(R.id.durationsb);
        Spinner sp = (Spinner) findViewById(R.id.molessp);

        etName.setText(playerName);
        sb.setProgress(duration);
        sp.setSelection(numMoles - 3);
        if (difficultyLevel == 3) {
            easy.setChecked(true);
        }
        else if (difficultyLevel == 2) {
            medium.setChecked(true);
        }
        else {
            hard.setChecked(true);
        }

    }

    private void saveSettingsInIntent(int difficulty, String name, int numMoles, int duration, Intent myIntent) {
        myIntent.putExtra("name", name);
        myIntent.putExtra("numMoles", numMoles);
        myIntent.putExtra("difficulty", difficulty);
        myIntent.putExtra("duration", duration);
    }
}