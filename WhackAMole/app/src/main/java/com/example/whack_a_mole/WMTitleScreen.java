package com.example.whack_a_mole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WMTitleScreen extends AppCompatActivity implements View.OnClickListener {
    Intent play_intent;
    Intent optionscreen_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wm_title_screen);
        play_intent = new Intent(this, Game.class);
        optionscreen_intent = new Intent(this, Options.class);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start_gamebttn) {
            startActivity(play_intent);
            finish();
        }
        else if (v.getId() == R.id.start_optionsbttn) {
            startActivity(optionscreen_intent);
            finish();
        }
    }
}