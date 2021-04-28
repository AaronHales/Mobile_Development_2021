package com.example.reminderapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

// This class will handle the messages sent to this application when an
// alarm goes off
public class AlarmReceiver extends BroadcastReceiver {

    // This method is called when one of our alarms go off on the user's device
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            // Get the extras from the incoming Intent
            Bundle bun = intent.getExtras();
            String name = bun.getString("alarm_name");  // Retrieves the user-defined alarm name
            String desc = bun.getString("alarm_desc");  // Retrieves the user-defined alarm description

            // Send a message to the user about the alarm
            Toast t = Toast.makeText(context, name + "\n\n" + desc, Toast.LENGTH_LONG);
            t.show();
        }
        catch (Exception e) {
            Toast t = Toast.makeText(context, "Something went wrong.\nError Code:\n" + e, Toast.LENGTH_LONG);
            t.show();
        }
    }
}