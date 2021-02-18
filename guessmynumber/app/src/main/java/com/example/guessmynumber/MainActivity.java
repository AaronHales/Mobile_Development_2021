package com.example.guessmynumber;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int guess;
    int tries = 3;
    Random r = new Random();
    Integer number = r.nextInt(10) + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            number = savedInstanceState.getInt("number");
            tries = savedInstanceState.getInt("tries");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView test = (TextView)findViewById(R.id.testoutput);
        test.setText(number.toString());

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("tries", tries);
        savedInstanceState.putInt("number", number);
    }

    public void onGuess(View view) {
        tries--;
        EditText guessbox = (EditText)findViewById(R.id.number_field);
        guess = Integer.parseInt(guessbox.getText().toString());
        Intent makeGuess = new Intent(this, play.class);
        makeGuess.putExtra("guess", guess);
        makeGuess.putExtra("number", number);
        makeGuess.putExtra("tries", tries);
        startActivity(makeGuess);

    }
}