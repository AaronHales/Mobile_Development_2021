package com.example.whack_a_mole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OptionScreen extends AppCompatActivity implements View.OnClickListener {
    Intent options_title_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_screen);
        options_title_intent = new Intent(this, WMTitleScreen.class);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit_optionsbttn) {
            startActivity(options_title_intent);
            finish();
        }
    }
}