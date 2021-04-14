package com.example.whack_a_mole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WMTitleScreen extends AppCompatActivity implements View.OnClickListener {
    Intent play_intent;
    Intent optionscreen_intent;

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wm_title_screen);

        play_intent = new Intent(this, Game.class);
        optionscreen_intent = new Intent(this, Options.class);


        text = (TextView) findViewById(R.id.titletext);
        Shader textShadow = new LinearGradient(0, 0, 0, text.getLineHeight(),
                new int[]{Color.parseColor("#9BA600"),
                        Color.parseColor("#DEFF38"),
                        Color.parseColor("#FFFFFF")},
                null, Shader.TileMode.REPEAT);
        text.getPaint().setShader(textShadow);
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