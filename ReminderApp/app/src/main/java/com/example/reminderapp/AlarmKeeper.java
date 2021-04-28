package com.example.reminderapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Random;
import java.util.StringTokenizer;

public class AlarmKeeper {

    public String alarmName;    // The user-defined name for the alarm
    public String alarmDesc;    // The user-defined description of alarm
    public int alarmYear;       // The Integer 4-digit year
    public int alarmMonth;      // The zero-based Integer month value
    public int alarmDay;        // The Integer day value
    public int alarmHour;      // The Integer hour value (24-hour value)
    public int alarmMinute;    // The Integer minute value
    private int alarmReqCode;  // The unique ID for the alarm

    public AlarmKeeper() {
        // Get a random value to use as the Alarm ID (or Request code)
        Random r = new Random(System.currentTimeMillis());
        alarmReqCode= (1 + r.nextInt(2)) * 10000 + r.nextInt(10000);
    }

    // This method will allow us to build an alarm object
    // from the string that was created with the buildString() method.
    // This is used to "unpack" the information that was passed from one
    // screen to another.
    public void fromString(String alarmString) {
        // Use a String Tokenizer, which will split the string into
        // smaller sections, using the comma to determine where a field
        // starts and ends.
        StringTokenizer st = new StringTokenizer(alarmString, ",");

        alarmName = st.nextToken();
        alarmDay = Integer.parseInt(st.nextToken());
        alarmMonth = Integer.parseInt(st.nextToken());
        alarmYear = Integer.parseInt(st.nextToken());
        alarmHour = Integer.parseInt(st.nextToken());
        alarmMinute = Integer.parseInt(st.nextToken());
        alarmDesc = st.nextToken();
        alarmReqCode = Integer.parseInt(st.nextToken());
    }

    // This method will set a new Alarm ID (or Request Code)
    public void setReqCode(int reqCode) {
        alarmReqCode= reqCode;
    }

    // This method will return the current Alarm ID (or Request Code)
    public int getReqCode() {
        return alarmReqCode;
    }

    // This method will set our alarm in the Android OS
    public void setAlarm(Context context) {
        // Get a Calendar object with current time
        Calendar cal = Calendar.getInstance();

        // Set our Calendar object to the alarm time
        cal.set(alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinute);

        // Create a new Intent that points to the Alarm Receiver class
        // Also add the alarm name and description to this Intent
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("alarm_name", alarmName);
        intent.putExtra("alarm_desc", alarmDesc);

        // Create a Pending Intent for our alarm
        PendingIntent sender = PendingIntent.getBroadcast(context, alarmReqCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get the AlarmManager service
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Set the alarm!
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
    }

    // This method will allow us to cancel an alarm that has already been set in the system
    public void cancelAlarm(Context context) {
        // Create a new Intent that points to the Alarm Receiver class
        Intent intent = new Intent(context, AlarmKeeper.class);
        intent.putExtra("alarm_message", alarmDesc);

        // Create a Pending Intent for our alarm
        PendingIntent sender = PendingIntent.getBroadcast(context, alarmReqCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get the AlarmManager service
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Cancel the alarm!
        alarmManager.cancel(sender);

    }

    public String buildString() {
        // Build a string that has all of our alarm fields listed,
        // with each field separated by a comma

        String strReturn = "";

        strReturn += alarmName + ",";
        strReturn += alarmDay + ",";
        strReturn += alarmMonth + ",";
        strReturn += alarmYear + ",";
        strReturn += alarmHour + ",";
        strReturn += alarmMinute + ",";
        strReturn += alarmDesc + ",";
        strReturn += alarmReqCode;

        return strReturn;
    }

    // This method will clear all of the information from the current AlarmKeeper object
    public void clear() {
        alarmName = "";
        alarmDesc = "";
        alarmDay = 0;
        alarmMonth = 0;
        alarmYear = 0;
        alarmHour = 0;
        alarmMinute = 0;

        // Get a new Alarm ID (or Request Code)
        Random r = new Random( System.currentTimeMillis() );
        alarmReqCode =  (1 + r.nextInt(2)) * 10000 + r.nextInt(10000);
    }
}