package com.example.intentintro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onBttnClick(View view) {
        Intent goToPage2 = new Intent(this, MainActivity2.class);
        EditText name = (EditText) findViewById(R.id.nametxtpg1);
        String value = name.getText().toString();
        goToPage2.putExtra("name", value);
        goToPage2.putExtra("age", 5);
        startActivity(goToPage2);
    }

}