package com.example.viewsexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    boolean cb1bool;
    boolean cb2bool;
    boolean cb3bool;
    String bread = "";
    TextView text1;
    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;
    String Jelly = "";
    String text1_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1 = (TextView) findViewById(R.id.text1);
        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);
        Integer x = text1.getHeight();
        text1.setText(x.toString());

        EditText name = (EditText) findViewById(R.id.editText);
        String name_entry = name.getText().toString();
        //text1.setText(name_entry);

        Button bttn1 = (Button) findViewById(R.id.button1);
        bttn1.setHeight(100);
        //bttn1.setOnClickListener(this.onButtonClicked(bttn1));

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleBttn);


    }

    public void onCheckedCb(View view) {
//        cb1bool = cb1.isChecked();
//        cb2bool = cb2.isChecked();
//        cb3bool = cb3.isChecked();
        bread = "";
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.checkBox1:
                if (checked) {
                    bread += cb1.getText() + " ";
                }
                break;
            case R.id.checkBox2:
                if (checked) {
                    bread += cb2.getText() + " ";
                }
                break;
            case R.id.checkBox3:
                if (checked) {
                    bread += cb3.getText() + " ";
                }
                break;
        }
//        if (cb1bool) {
//            bread += cb1.getText() + " ";
//        }
//        if (cb2bool) {
//            bread += cb2.getText() + " ";
//        }
//        if (cb3bool) {
//            bread += cb3.getText() + " ";
//        }
    }

    public void radioChecked(View view) {
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.jelly);
        int id = radiogroup.getCheckedRadioButtonId();
        switch (id) {
            case R.id.radioButton1:
                Jelly = "Strawberry";
                break;
            case R.id.radioButton2:
                Jelly = "Grape";
                break;
            case R.id.radioButton3:
                Jelly = "Mixed Berry";
                break;
        }
//        text1.setText(Jelly);
    }

    public void onToggleSwitch(View view) {
        boolean ons = ((Switch) view).isChecked();

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        if(ons) {
            layout.setBackgroundColor(Color.GREEN);
        }
        else {
            layout.setBackgroundColor(Color.RED);
        }
    }

    public void onToggleBttn(View view) {
        boolean ont = ((ToggleButton) view).isChecked();

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        if(ont) {
            layout.setBackgroundColor(Color.CYAN);
        }
        else {
            layout.setBackgroundColor(Color.BLUE);
        }
    }

    public void onButtonClicked(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String toasted = String.valueOf(spinner.getSelectedItem());
//        text1.setText(String.valueOf(spinner.getSelectedItem()));
        text1_text = bread + " " + Jelly + " " + toasted;
        text1.setText(text1_text);

        ImageView pic = (ImageView) findViewById(R.id.pic);
        if (pic.getResources().equals(R.drawable.madeline)) {
            int image = R.drawable.androidlogo;
            String description = "Android Logo";
            pic.setImageResource(image);
            pic.setContentDescription(description);
        }
        CharSequence text = "This is a toast message";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();

    }
}