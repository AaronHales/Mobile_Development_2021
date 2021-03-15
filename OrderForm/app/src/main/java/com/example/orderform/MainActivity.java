package com.example.orderform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    String order = "";
    boolean sprinkles_checked;
    boolean cream_checked;
    boolean drizzle_checked;
    String toppings = "";
    String name = "";
    String sprinkles_select = "";
    String cream_select = "";
    String drizzle_select = "";
    String drink_type = "";
    String size = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void radioChecked(View view) {
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.drink);
        int id = radiogroup.getCheckedRadioButtonId();
        switch (id) {
            case R.id.coffee_radio:
                drink_type = "Coffee";
                break;
            case R.id.tea_radio:
                drink_type = "Tea";
                break;
            case R.id.hotchoc_radio:
                drink_type = "Hot Chocolate";
                break;
        }
    }

    public void onCheckedCb(View view) {
        CheckBox sprinkles_cb = (CheckBox) findViewById(R.id.sprinkles_cb);
        CheckBox cream_cb = (CheckBox) findViewById(R.id.cream_cb);
        CheckBox drizzle_cb = (CheckBox) findViewById(R.id.drizzles_cb);
        Spinner sprinkles_spin = (Spinner) findViewById(R.id.sprinkles_spinner);
        Spinner cream_spin = (Spinner) findViewById(R.id.cream_spinner);
        Spinner drizzle_spin = (Spinner) findViewById(R.id.drizzle_spinner);
        sprinkles_checked = sprinkles_cb.isChecked();
        cream_checked = cream_cb.isChecked();
        drizzle_checked = drizzle_cb.isChecked();
        if (sprinkles_checked) {
            sprinkles_select = String.valueOf(sprinkles_spin.getSelectedItem());
            toppings += sprinkles_select + " ";

        }
        if (cream_checked) {
            cream_select = String.valueOf(cream_spin.getSelectedItem());
            toppings += cream_select + " ";
        }
        if (drizzle_checked) {
            drizzle_select = String.valueOf(drizzle_spin.getSelectedItem());
            toppings += drizzle_select + " ";
        }
    }

    public void onSubmitClicked(View view) {
        EditText name_txt = (EditText) findViewById(R.id.personName);
        name = name_txt.getText().toString();
        Spinner spinner_size = (Spinner) findViewById(R.id.size_spinner);
        size = String.valueOf(spinner_size.getSelectedItem());

    }
}