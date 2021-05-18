package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class Snooze extends AppCompatActivity implements View.OnClickListener {

    String alarmName;
    String alarmDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snooze);

        Button b = (Button)findViewById(R.id.btnSnooze);
        b.setOnClickListener(this);

        Intent myIntent = getIntent();
        Bundle bun = myIntent.getExtras();
        alarmName = bun.getString("name");
        alarmDesc = bun.getString("desc");
    }

    @Override
    public void onClick(View v) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(c.getTimeInMillis() + 600000);

        AlarmKeeper snoozeAlarm = new AlarmKeeper();
        snoozeAlarm.alarmDesc = alarmDesc;
        snoozeAlarm.alarmName = alarmName;
        snoozeAlarm.alarmDay = c.get(Calendar.DAY_OF_MONTH);
        snoozeAlarm.alarmMonth = c.get(Calendar.MONTH);
        snoozeAlarm.alarmYear = c.get(Calendar.YEAR);
        snoozeAlarm.alarmHour = c.get(Calendar.HOUR_OF_DAY);
        snoozeAlarm.alarmMinute = c.get(Calendar.MINUTE);

        snoozeAlarm.setAlarm(Snooze.this);
        finish();
    }
}