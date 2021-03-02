package com.example.viewsexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text1 = (TextView) findViewById(R.id.text1);
        Integer x = text1.getHeight();
        text1.setText(x.toString());

        EditText name = (EditText) findViewById(R.id.editText);
        String name_entry = name.getText().toString();
        //text1.setText(name_entry);

        Button bttn1 = (Button) findViewById(R.id.button1);
        bttn1.setHeight(100);
        //bttn1.setOnClickListener(this.onButtonClicked(bttn1));

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggle_bttn);


    }

    public void onToggle(View view) {
        boolean on = ((Switch) view).isChecked();

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        if(on) {
            layout.setBackgroundColor(Color.GREEN);
        }
        else {
            layout.setBackgroundColor(Color.RED);
        }
    }

    public void onButtonClicked(View view) {

    }
}