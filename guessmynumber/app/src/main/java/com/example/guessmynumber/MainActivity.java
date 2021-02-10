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
    Integer number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Random r = new Random();
        number = r.nextInt(10);
        number++;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView test = (TextView)findViewById(R.id.testoutput);
        test.setText(number.toString());

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