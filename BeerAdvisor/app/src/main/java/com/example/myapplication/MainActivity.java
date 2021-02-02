package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {
    private DrinkExpert expert = new DrinkExpert();
    private Random r = new Random();

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Methods must be public
    public void onClick_findDrink(View view) {
        // Getting ref to the text view so it can have text changed
        TextView brands = (TextView) findViewById(R.id.brands);
        // Setting text on the brands text view
        brands.setText("you clicked the button");
        // Getting ref to the spinner object to get selected item
        Spinner color = (Spinner) findViewById(R.id.color);
        // Getting selected item from spinner and converting it to string
        String selected = color.getSelectedItem().toString();
        // setting text on the brands text view to selected item
        brands.setText("try this drink " + selected);

        /*
        // Whole list
        // creates a new list to store returned data from DrinkExpert
        List<String> drinks = expert.getBrands(selected);
        // sets text of brands to drinks and cast it to a string
        brands.setText("Try one of these " + selected + ": " + drinks.toString());
         */

        /*
        // Random option
        // creates a new list to store returned data from DrinkExpert
        List<String> choice_list = expert.getBrands(selected);
        // sets text of brands to drinks and cast it to a string
        brands.setText(choice_list.toString());
        brands.setText("Try this drink " + choice_list.get(r.nextInt(choice_list.size())));
         */

        //getting the drink list
        List<String> choice_list = expert.getBrands(selected);
        StringBuilder drink_string = new StringBuilder();
        for (String choice : choice_list) {
            drink_string.append(choice + "\n");
        }
        brands.setText("Try one of these " + selected + ":\n" + drink_string);

    }



}
