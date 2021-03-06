package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/*
 * Mobile App Dev
 * Chapter 26
 * Activity: Reminder Alarm
 * Aaron Hales
 * 5/4/21
 */

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
        saveButton = (Button) findViewById(R.id.saveBttn);
        timeButton = (Button) findViewById(R.id.timeBttn);
        dateButton = (Button) findViewById(R.id.dateBttn);

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
        description = (EditText) findViewById(R.id.etDesc);
        name = (EditText) findViewById(R.id.etAlarmName);

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
            AMPM = " PM";   // Add the AM/PM value
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
        int id = v.getId();
        if (id == R.id.saveBttn) {
            EditText name = (EditText)findViewById(R.id.etAlarmName);
            EditText desc = (EditText)findViewById(R.id.etDesc);

            thisAlarm.alarmName = name.getText().toString();
            thisAlarm.alarmDesc = desc.getText().toString();
            DialogFragment newFragment = new MyAlertDialog();
            newFragment.show(getFragmentManager(), "MyAlertDialog");
        }
        else if (id == R.id.dateBttn) {
            DialogFragment newFragment = new MyDatePicker();
            newFragment.show(getFragmentManager(), "MyDatePicker");
        }
        else if (id == R.id.timeBttn) {
            DialogFragment newFragment = new MyTimePicker();
            newFragment.show(getFragmentManager(), "MyTimePicker");
        }
    }

    public static class MyDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            AddAlarm activity = (AddAlarm)getActivity();
            activity.thisAlarm.alarmYear = year;
            activity.thisAlarm.alarmMonth = month;
            activity.thisAlarm.alarmDay = dayOfMonth;

            Button dateButton = (Button)activity.findViewById(R.id.dateBttn);
            String monthFormat = "";
            String dayFormat = "";
            if (month < 9) {
                monthFormat = "0" + (month + 1);
            }
            else {
                monthFormat = "" + (month + 1);
            }
            if (dayOfMonth < 10) {
                dayFormat = "0" + dayOfMonth;
            }
            else {
                dayFormat = "" + dayOfMonth;
            }
            dateButton.setText(monthFormat + "/" + dayFormat + "/" + year);

        }
    }

    public static class MyTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar now = Calendar.getInstance();
            int hour = now.get(Calendar.HOUR_OF_DAY);
            int minute = now.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute, false);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            AddAlarm activity = (AddAlarm)getActivity();
            activity.thisAlarm.alarmHour = hourOfDay;
            activity.thisAlarm.alarmMinute = minute;
            String AMPM = " AM";
            String strMinute = "";
            Button timeButton = (Button)activity.findViewById(R.id.timeBttn);

            if (hourOfDay > 12) {
                hourOfDay -= 12;
                AMPM = " PM";
            }
            else {
                AMPM = " AM";
            }
            if (minute < 10) {
                strMinute = "0" + minute;
            }
            else {
                strMinute = "" + minute;
            }
            timeButton.setText(hourOfDay + ":" + strMinute + AMPM);
        }
    }

    public static class MyAlertDialog extends DialogFragment {

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Are you sure you want to save this alarm");
            builder.setCancelable(false);

            // Create a "Yes" button in the dialog
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    AddAlarm activity = (AddAlarm)getActivity();
                    if (activity.thisAlarm.alarmName == null || activity.thisAlarm.alarmName.length() == 0) {
                        return;
                    }
                    if (activity.thisAlarm.alarmDesc == null || activity.thisAlarm.alarmDesc.length() == 0) {
                        return;
                    }
                    if (activity.thisAlarm.alarmYear == 0) {
                        return;
                    }
                    if (activity.thisAlarm.alarmHour == 0) {
                        return;
                    }
                    activity.thisAlarm.setAlarm(activity);
//                    activity.clearAlarmScreen();
                    activity.closeAlarmScreen();
                }
            });

            // Create a "No" button in the dialog
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // There is no need to do anything in this method, since the
                    // user canceled the save action
                }
            });

            return builder.create();
        }
    }

    public void clearAlarmScreen() {
        // Get a handle to the relevant controls on the screen
        description = (EditText) findViewById(R.id.etDesc);
        name = (EditText) findViewById(R.id.etAlarmName);
        timeButton = (Button) findViewById(R.id.timeBttn);
        dateButton = (Button) findViewById(R.id.dateBttn);

        // Reset all fields back to their initial state
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