package com.example.orderform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

/*
 * Mobile App Dev
 * Chapter 5 Exercise
 * Order Form
 * Aaron Hales
 * 3/16/21
 */

public class MainActivity extends AppCompatActivity {
    String order = "";

    boolean sprinkles_checked;
    boolean cream_checked;
    boolean drizzle_checked;
    boolean ice_checked;
    boolean child_temp_checked;
    boolean reg_temp_checked;
    boolean store_pickup;

    String toppings = "";
    String name = "";
    String sprinkles_select = "";
    String cream_select = "";
    String drizzle_select = "";
    String drink_type = "";
    String size = "";
    String temp = "";
    String pickup = "";

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

    public void onSubmitClicked(View view) {
        order = "";
        temp = "";
        name = "";
        size = "";
        drink_type = "";
        toppings = "";
        pickup = "";
        EditText name_txt = (EditText) findViewById(R.id.personName);
        name = name_txt.getText().toString();

        Spinner spinner_size = (Spinner) findViewById(R.id.size_spinner);
        size = String.valueOf(spinner_size.getSelectedItem());

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
            toppings += sprinkles_select + ", ";

        }
        if (cream_checked) {
            cream_select = String.valueOf(cream_spin.getSelectedItem());
            toppings += cream_select + ", ";
        }
        if (drizzle_checked) {
            drizzle_select = String.valueOf(drizzle_spin.getSelectedItem());
            toppings += drizzle_select;
        }
        if (!sprinkles_checked && !cream_checked && !drizzle_checked) {
            toppings += "None";
        }

        Switch ice_switch = (Switch) findViewById(R.id.iced_switch);
        Switch child_temp_switch = (Switch) findViewById(R.id.child_switch);
        Switch regulart_switch = (Switch) findViewById(R.id.regular_switch);
        ice_checked = ice_switch.isChecked();
        child_temp_checked = child_temp_switch.isChecked();
        reg_temp_checked = regulart_switch.isChecked();
        if (ice_checked) {
            temp += "Iced, ";
        }
        if (child_temp_checked) {
            temp += "Child Temperature, ";
        }
        if (reg_temp_checked) {
            temp += "Regular Temperature";
        }

        ToggleButton pickup_bttn = (ToggleButton) findViewById(R.id.pickup_toggle);
        store_pickup = pickup_bttn.isChecked();
        if (store_pickup) {
            pickup = "To stay";
        }
        else if (!store_pickup) {
            pickup = "To go";
        }
        order = "Order for " + name + "\n" + size + " " + drink_type + "\nToppings: " + toppings + "\nTemperature: " + temp + "\nOrder: " + pickup;
        CharSequence order_toast = order;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, order_toast, duration);
        toast.show();

    }
}