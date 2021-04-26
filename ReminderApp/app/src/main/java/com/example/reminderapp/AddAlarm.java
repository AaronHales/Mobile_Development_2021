package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class AddAlarm extends AppCompatActivity implements View.OnClickListener{

    // Create a global instance of AlarmKeeper. This will allow is to access this variable
    // anywhere in our class.
    AlarmKeeper thisAlarm;

    // Used to track the selected alarm from a list
    int index = -1;

    // Get handles to all of our buttons and edit text
    Button timeButton;
    Button saveButton;
    Button dateButton;
    EditText description;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);

        // Get handles to all of our buttons
        saveButton = (Button) findViewById(R.id.alarmSaveBttn);
        timeButton = (Button) findViewById(R.id.alarmTimeBttn);
        dateButton = (Button) findViewById(R.id.alarmDateBttn);

        // Set the button's onClickListeners
        saveButton.setOnClickListener(this);
        timeButton.setOnClickListener(this);
        dateButton.setOnClickListener(this);

        // Initialize the AlarmKeeper object
        thisAlarm = new AlarmKeeper();

        // Get information from the incoming Intent
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            // Retrieve the alarm information in string format
            String alarmString = extras.getString("alarm");

            // Get the index value of the alarm
            index = extras.getInt("index");

            // If we have a valid alarm
            if ((index >= 0) && (alarmString != null)) {
                // Build an AlarmKeeper object form the alarmString
                thisAlarm.fromString(alarmString);
                displayAlarm(); // Show the alarm information!
            }

        }
    }

    private void displayAlarm() {
        description = (EditText) findViewById(R.id.editAlarmDes);
        name = (EditText) findViewById(R.id.editAlarmName);

        // Set the alarm description and name
        description.setText(thisAlarm.alarmDesc);
        name.setText(thisAlarm.alarmName);

        // Set the alarm date as the text for the alarmDateBttn
        dateButton.setText((thisAlarm.alarmMonth + 1) + "/" + thisAlarm.alarmDay + "/" + thisAlarm.alarmYear);

        // Create a string to use for the AM/PM value in the time
        String AMPM = " AM";
        // Create a string value to format the minute field in the time
        String strMinute;
        // Adjust hour to 12-hour time
        int hour = thisAlarm.alarmHour;
        if (hour > 12) {
            hour -= 12;
            AMPM = " PM";
        }

        // Format the minute value to be two-digit value
        if (thisAlarm.alarmMinute < 10) {
            strMinute = "0" + thisAlarm.alarmMinute;
        }
        else {
            strMinute = ""+thisAlarm.alarmMinute;
        }

        // Place the time value into the alarmTimeBttn
        timeButton.setText(hour + ":" + strMinute + AMPM);
    }

    @Override
    public void onClick(View v) {

    }

    public static class MyDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        public Dialog onCreateDialog(Bundle savedInstanceState) {

            return null;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }
    }

    public static class MyTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        public Dialog onCreateDialog(Bundle savedInstanceState) {

            return null;
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        }
    }

    public static class MyAlertDialog extends DialogFragment {

        public Dialog onCreateDialog(Bundle savedInstanceState) {

            return null;
        }
    }

    public void clearAlarmScreen() {
        description = (EditText) findViewById(R.id.editAlarmDes);
        name = (EditText) findViewById(R.id.editAlarmName);
        timeButton = (Button) findViewById(R.id.alarmTimeBttn);
        dateButton = (Button) findViewById(R.id.alarmDateBttn);

        description.setText("");
        name.setText("");
        timeButton.setText("Set Alarm Time");
        dateButton.setText("Set Alarm Date");

        // Call the clear() method on the AlarmKeeper object to clear out all data
        thisAlarm.clear();
    }

    public void closeAlarmScreen() {
        // Build a string from all the alarm information
        String alarmString = thisAlarm.buildString();

        // Pass the alarm string in a new Intent to the Alarm List class
        Intent listItent = new Intent(AddAlarm.this, AlarmList.class);
        listItent.putExtra("alarm", alarmString);
        listItent.putExtra("index", index);

        // Sent the resulting intent back to the AlarmList screen
        setResult(RESULT_OK, listItent);

        // Close this screen
        finish();
    }
}